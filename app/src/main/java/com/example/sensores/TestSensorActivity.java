package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class TestSensorActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tValor;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sensor);

        int position = getIntent().getIntExtra("position", 0);
        //Busca o sensor pela posição
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(sensor.TYPE_ALL);
        sensor = sensorList.get(position);
        tValor = (TextView)findViewById(R.id.txtValor);
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
        //Registrar sensor ativo
    }

    public void onSensorChanged(SensorEvent sensorEvent){
        StringBuffer sb = new StringBuffer();
        for (int i=0; i < sensorEvent.values.length; i++){
            sb.append(i).append(": ").append(sensorEvent.values[i]).append("\n");
        }
        tValor.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
