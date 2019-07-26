package com.example.mylibrary;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TestModal extends ReactContextBaseJavaModule implements LifecycleEventListener {
    protected static final String TAG = TestModal.class.getSimpleName();
    private static final  String TestEventName = "TestEventName";
    private Timer timer;
    public TestModal(final ReactApplicationContext reactContext) {
        super(reactContext);

        //添加监听
        reactContext.addLifecycleEventListener(this);

        //开启定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //发送事件
                WritableMap params = Arguments.createMap();
                params.putString("name", "Jack");
                reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit(TestEventName, params);

            }
        };
        timer = new Timer();
        timer.schedule(task, 1000, 1000);
    }
    @Override
    public String getName() {
        return "TestModal";
    }

    @ReactMethod
    public void testPrint(String name, ReadableMap info) {
        Log.i(TAG, name);
        Log.i(TAG, info.toString());
    }

    @ReactMethod
    public void getNativeClass(Callback callback) {
        callback.invoke("TestModal");
    }

    @ReactMethod
    public void testPromises(Boolean isResolve, Promise promise) {
        if(isResolve) {
            promise.resolve(isResolve.toString());
        }
        else {
            promise.reject(isResolve.toString());
        }
    }

    @Override
    public  Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("BGModuleName", "TestModal");
        constants.put(TestEventName, TestEventName);
        return constants;
    }

    @Override
    public void onHostResume() {
        Log.i(TAG, "onHostResume");
    }

    @Override
    public void onHostPause() {
        Log.i(TAG, "onHostPause");
        timer.cancel();
    }

    @Override
    public void onHostDestroy() {
        Log.i(TAG, "onHostDestroy");
        timer.cancel();
    }

}