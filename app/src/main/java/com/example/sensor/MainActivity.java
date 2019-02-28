package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumi;

    TextView xValue;
    TextView yValue;
    TextView zValue;
    TextView xGyroValue;
    TextView yGyroValue;
    TextView zGyroValue;
    TextView xMagnoValue;
    TextView yMagnoValue;
    TextView zMagnoValue;
    TextView light;
    TextView pressure;
    TextView temp;
    TextView humi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView) findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView) findViewById(R.id.zMagnoValue);

        light = (TextView) findViewById(R.id.light);
        pressure = (TextView) findViewById(R.id.pressure);
        temp = (TextView) findViewById(R.id.temp);
        humi = (TextView) findViewById(R.id.humi);


        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer Listener");
        }else {
            xValue.setText("Accelerometer Not Supported");
            yValue.setText("Accelerometer Not Supported");
            zValue.setText("Accelerometer Not Supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyrometer Listener");
        }else {
            xGyroValue.setText("Gyrometer Not Supported");
            yGyroValue.setText("Gyrometer Not Supported");
            zGyroValue.setText("Gyrometer Not Supported");
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magnometer Listener");
        }else {
            xMagnoValue.setText("Magnometer Not Supported");
            yMagnoValue.setText("Magnometer Not Supported");
            zMagnoValue.setText("Magnometer Not Supported");

        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Light Meter Listener");
        }else {
            light.setText("Light meter Not Supported");
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Pressure Meter Listener");
        }else {
            pressure.setText("Pressure meter: Not Supported");
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mTemp != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Temperature Listener");
        }else {
            temp.setText("Temperature meter: Not Supported");
        }

        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (mHumi != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, mHumi, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Humidity meter Listener");
        }else {
            humi.setText("Humidity meter: Not Supported");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);

            xValue.setText("xValue: " + sensorEvent.values[0]);
            yValue.setText("yValue: " + sensorEvent.values[1]);
            zValue.setText("zValue: " + sensorEvent.values[2]);

        }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE){

            xGyroValue.setText("xGyroValue: " + sensorEvent.values[0]);
            yGyroValue.setText("yGyroValue: " + sensorEvent.values[1]);
            zGyroValue.setText("zGyroValue: " + sensorEvent.values[2]);
        }else if ( sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            xMagnoValue.setText("xMagnoValue: " + sensorEvent.values[0]);
            yMagnoValue.setText("yMagnoValue: " + sensorEvent.values[1]);
            zMagnoValue.setText("zMagnoValue: " + sensorEvent.values[2]);
        } else if ( sensor.getType() == Sensor.TYPE_LIGHT) {

            light.setText("Light: " + sensorEvent.values[0]);
        } else if ( sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText("Temp: " + sensorEvent.values[0]);
        } else if ( sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humi.setText("Humidity: " + sensorEvent.values[0]);
        } else if ( sensor.getType() == Sensor.TYPE_PRESSURE) {

            pressure.setText("Pressure: " + sensorEvent.values[0]);
        }


    }


}


