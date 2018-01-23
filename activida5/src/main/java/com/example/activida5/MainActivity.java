package com.example.activida5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.asynctasks.HttpJsonAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        httpJsonAsyncTask.execute("http://10.0.2.2/Javi/GOT/leePersonajes.php");
    }
}
