package com.improve.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.improve.support.ipc.socket.TCPServerService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.improve.support.R.layout.activity_main);
        //开启 Socket IPC 服务端
        startService(new Intent(this, TCPServerService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.improve.support.R.menu.main, menu);
        return true;
    }

}
