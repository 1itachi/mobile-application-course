package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method will be called upon the button click
    public void onClick(View view) {

        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        if(name.getText() == ""){
            name.setText(R.string.convert_name);
            email.setText(R.string.convert_email);
        }else{
            name.setText("");
            email.setText("");
        }

    }



}