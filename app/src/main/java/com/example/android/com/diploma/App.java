package com.example.android.com.diploma;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Keystore keystore = new FileKeystore(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                if (activity instanceof KeystoreHolder) {
                    ((KeystoreHolder) activity).setKeystore(keystore);
                }
            }
            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                // noop
            }
            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                // noop
            }
            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                // noop
            }
            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                // noop
            }
            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                // noop
            }
            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                // noop
            }
        });
    }
}
