package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //Pegam a lista de sensores
        List<String> nomes = new ArrayList<String>();
        for (Sensor s : sensorList){
            nomes.add(s.getName() + " - " + s.getVendor() + " - " +
                    s.getType()); //Parametros que iremos listar
        }
        ListView listView = (ListView)findViewById(R.id.lstSensores);
        listView.setOnClickListener((View.OnClickListener) this);
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String>adaptator = new ArrayAdapter<String>(this, layout, nomes);
        listView.setAdapter(adaptator);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Sensor sensor = sensorList.get(i);
        String msg = sensor.getName() + " - " + sensor.getVendor();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TestSensorActivity.class);
        intent.putExtra("position", i);
        startActivity(intent);
    }
}
