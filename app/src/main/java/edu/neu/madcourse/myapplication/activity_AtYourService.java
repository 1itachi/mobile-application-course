package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class activity_AtYourService extends AppCompatActivity {

    private TextView connectionProgressOrCity;
    private TextView description;
    private TextView feelsLike;
    private TextView maxTemperature;
    private TextView minTemperature;
    private TextView temperature;
    private String UrlString = "https://api.openweathermap.org/data/2.5/weather?q=Boston&appid=a239b3c66789a888d3fd72cf4e7cc6ab&units=imperial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);
        connectionProgressOrCity = findViewById(R.id.textView_conn_progress_or_city);
        description = findViewById(R.id.textView_description);
        feelsLike = findViewById(R.id.textView_feels_like);
        maxTemperature = findViewById(R.id.textView_max_temp);
        minTemperature = findViewById(R.id.textView_min_temp);
        temperature = findViewById(R.id.textView_temperature);
    }

    public void getWeatherInformationCallback(View view) {
        GetWeatherInfoAsyncTask task = new GetWeatherInfoAsyncTask();
        task.execute();
    }

    private class GetWeatherInfoAsyncTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            connectionProgressOrCity.setText(R.string.connection_in_progress);
            feelsLike.setText("");
            maxTemperature.setText("");
            minTemperature.setText("");
            temperature.setText("");
        }

        @Override
        protected String[] doInBackground(Void... voids) {
            String[] results = new String[1];
            URL url;
            try {
                url = new URL(UrlString);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setRequestMethod("GET");
                httpConn.setDoInput(true);
                httpConn.setConnectTimeout(5000);
                httpConn.connect();

                results = new String[6];
                InputStream inputStream = httpConn.getInputStream();
                String response = convertStreamToString(inputStream);
                inputStream.close();
                httpConn.disconnect();

                JSONObject responseJSON = new JSONObject(response);
                results[0] = responseJSON.getString("name");
                JSONArray weather = responseJSON.getJSONArray("weather");
                results[1] = weather.getJSONObject(0).getString("description");
                JSONObject main = responseJSON.getJSONObject("main");
                results[2] = main.getString("temp");
                results[3] = main.getString("feels_like");
                results[4] = main.getString("temp_max");
                results[5] = main.getString("temp_min");
                return results;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            results[0] = "Something went wrong. Please try again later. ";
            return results;
        }

        @Override
        protected void onPostExecute(String[] results) {
            super.onPostExecute(results);
            connectionProgressOrCity.setText(results[0]);
            if (results.length == 1) {
                return;
            }
            description.setText(results[1]);
            temperature.setText(getString(R.string.temperature, results[2]));
            feelsLike.setText(getString(R.string.feels_like, results[3]));
            maxTemperature.setText(getString(R.string.max_temp, results[4]));
            minTemperature.setText(getString(R.string.min_temp, results[5]));
        }
    }


    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }


}