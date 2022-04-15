package com.example.lightsensorpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager smr;
    TextView tv;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= (TextView)findViewById(R.id.tv2);
        smr= (SensorManager)getSystemService(Service.SENSOR_SERVICE);
        sensor=smr.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    @Override
    public  void onResume(){
        super.onResume();
        smr.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    public void onPause(){
        super.onPause();
        smr.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            String str= Float.toString(event.values[0]);
            tv.setText(str);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
