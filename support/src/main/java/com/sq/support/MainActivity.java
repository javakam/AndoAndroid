package com.sq.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.sq.support.ipc.TCPServerService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sq.support.R.layout.activity_main);
        //开启 Socket IPC 服务端
        startService(new Intent(this, TCPServerService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.sq.support.R.menu.main, menu);
        return true;
    }

}
