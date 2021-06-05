package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        switch (view.getId()){
            case R.id.about:
                displayNameEmail();
                break;

            case R.id.second:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;

            case R.id.collector:
                Intent intent1 = new Intent(this, LinkCollector.class);
                startActivity(intent1);
                break;

        }

    }

    private void displayNameEmail(){
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