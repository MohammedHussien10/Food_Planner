package com.example.foodplanner.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.core.Observable;

public class CheckNetworking {

    public static Observable<Boolean> getNetworkStatus(Context context) {
        WeakReference<Context> weakContext = new WeakReference<>(context);

        return Observable.create(emitter -> {
            Context ctx = weakContext.get();
            if (ctx == null) {
                emitter.onNext(false);
                emitter.onComplete();
                return;
            }

            ConnectivityManager connectivityManager =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager == null) {
                emitter.onNext(false);
                emitter.onComplete();
                return;
            }

            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    emitter.onNext(true);
                }

                @Override
                public void onLost(Network network) {
                    emitter.onNext(false);
                }
            };

            NetworkRequest networkRequest = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build();

            connectivityManager.registerNetworkCallback(networkRequest, networkCallback);

            // إرسال الحالة الأولية فورًا عند الاشتراك
            boolean initialState = isNetworkAvailable(ctx);
            emitter.onNext(initialState);

            // تنظيف الاشتراك عند عدم الحاجة إليه
            emitter.setCancellable(() -> connectivityManager.unregisterNetworkCallback(networkCallback));
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) return false;

        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
    }
}
