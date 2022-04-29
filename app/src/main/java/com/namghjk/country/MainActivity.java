package com.namghjk.country;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import Apdapter.CountryAdapter;
import Model.CountryModel;

public class MainActivity extends AppCompatActivity {
    
    ListView lv_Country;
    ArrayList<CountryModel> countryModelArrayList;
    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControll();
        addEvents();
        
    }

    private void addEvents() {

        lv_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CountryModel country = countryModelArrayList.get(i);
                Intent intent = new Intent(MainActivity.this,Detail_Country.class);

                intent.putExtra("country",(Serializable) country);

                startActivity(intent);
            }
        });
    }

    private void addControll() {
        countryModelArrayList = new ArrayList<>();
        lv_Country = findViewById(R.id.LvCountry);
        countryAdapter = new CountryAdapter(MainActivity.this,R.layout.country_item,countryModelArrayList);
        lv_Country.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
        CountrytTask countrytTask = new CountrytTask();
        countrytTask.execute();
    }

    class CountrytTask extends AsyncTask<Void,Void,ArrayList<CountryModel>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            countryAdapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<CountryModel> countryModels) {
            super.onPostExecute(countryModels);
            countryAdapter.clear();
            countryAdapter.addAll(countryModels);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<CountryModel> doInBackground(Void... voids) {
            ArrayList<CountryModel> ds = new ArrayList<>();
            try {
                URL url = new URL("https://www.geonames.org/countryInfoJSON?username=ngiao789");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!=null){
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONObject jsonObject = new JSONObject(builder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("geonames");

                Log.e("123", String.valueOf(jsonArray.length()));

                for (int i = 0; i < jsonArray.length(); i++){

                    JSONObject record = jsonArray.getJSONObject(i);
                    CountryModel countryModel = new CountryModel();

                    if(record.has("countryName")){
                        countryModel.setCountryName(record.getString("countryName"));
                    }
                    if(record.has("population")){
                        countryModel.setPopulation(record.getString("population"));
                    }
                    if(record.has("areaInSqKm")){
                        countryModel.setAreaInSqKm(record.getString("areaInSqKm"));
                    }
                    if(record.has("countryCode")){
                        String countryCode =  record.getString("countryCode").toLowerCase();
                        String LinkImage = "https://img.geonames.org/flags/x/"+ countryCode +".gif";
                        String MapImage = "https://img.geonames.org/img/country/250/"+ record.getString("countryCode") +".png";
                        countryModel.setImage(LinkImage);
                        countryModel.setMapImage(MapImage);
                        url = new URL(LinkImage);
                        connection = (HttpsURLConnection) url.openConnection();
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        countryModel.setFlag(bitmap);

                        url = new URL(MapImage);
                        connection = (HttpsURLConnection) url.openConnection();
                        Bitmap bitmap1 = BitmapFactory.decodeStream(connection.getInputStream());
                        countryModel.setMap(bitmap1);
                    }
                    Log.e("LOI: ",countryModel.toString());
                    ds.add(countryModel);
                }

            }catch (Exception ex){
                Log.e("LOI" ,ex.toString());
            }
            return ds;
        }
    }
}