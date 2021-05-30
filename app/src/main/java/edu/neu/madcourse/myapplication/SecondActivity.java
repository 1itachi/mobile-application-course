package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        display = findViewById(R.id.textViewPressed);
        display.setText("");
    }

    public void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("Pressed: ");
        sb.append(((Button) view).getText().toString());
        display.setText(sb.toString());
    }
}