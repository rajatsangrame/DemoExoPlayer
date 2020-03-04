package com.example.rajat.demo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Arrays;

/**
 * Created by Rajat Sangrame on 3/3/20.
 * http://github.com/rajatsangrame
 */
public class SphericalPlayerView extends PlayerView implements SensorEventListener {

    private SensorManager sensorManager;
    private static final String TAG = "SphericalPlayerView";

    public SphericalPlayerView(Context context) {
        super(context);
    }

    public SphericalPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SphericalPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void performTouch(float x, float y, int motionEventAction) {

        // Obtain MotionEvent object
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;

        // List of meta states found here:
        // developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                motionEventAction,
                x,
                y,
                metaState
        );

        // Dispatch touch event to view
        dispatchTouchEvent(motionEvent);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Log.i(TAG, "onSensorChanged: " + event.accuracy);

        if (event.accuracy > SensorManager.SENSOR_STATUS_UNRELIABLE) {

            Log.i(TAG, "onSensorChanged: " + Arrays.toString(event.values));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public void registerListener() {

        if (sensorManager == null) {
            Log.e(TAG, "registerListener: null");
            return;
        }
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);

        Log.i(TAG, "registerListener: ");

    }

    public void unRegisterListener() {
        if (sensorManager == null) {
            Log.e(TAG, "unRegisterListener: null");
            return;
        }
        sensorManager.unregisterListener(this);

        Log.i(TAG, "unRegisterListener: ");

    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility == VISIBLE) {
            registerListener();
            return;
        }
        unRegisterListener();
    }

}
