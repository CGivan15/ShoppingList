package com.example.shoppinglist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.countItems);
        Integer totalListCount = MainActivity.listCount;
        textView.setText(totalListCount.toString());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Second Activity");
    }
}
