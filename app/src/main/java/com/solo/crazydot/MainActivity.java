package com.solo.crazydot;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.solo.crazydot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    ActivityMainBinding binding;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (!InitSensor())
            Toast.makeText(this, "We Do not have Accelerometer", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private boolean InitSensor() {
        boolean retVal = false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
            retVal = true;
        }
        return retVal;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        binding.textViewX.setText(String.valueOf(event.values[0]));
        binding.textViewY.setText(String.valueOf(event.values[1]));
        binding.textViewZ.setText(String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
