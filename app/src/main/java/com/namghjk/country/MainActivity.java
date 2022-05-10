package com.namghjk.country;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Interpolator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

    public static final int ITEM_COUNT_LAZY = 5;
    LinearLayout loadingview;
    ListView lv_Country;
    ArrayList<CountryModel> countryModelArrayList,countryModelArrayListLazy;
    CountryAdapter countryAdapter;
    ImageView loadingImage;
    private  int maxCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControll();
        addEvents();
        
    }

    private void addEvents() {

        lv_Country.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+ visibleItemCount == totalItemCount && totalItemCount!=0){
                    LazyLoad();
                }

            }
        });

        lv_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OnItemClick(i);
            }
        });


    }

    private void OnItemClick(int i){
        CountryModel country = countryModelArrayList.get(i);
        Intent intent = new Intent(MainActivity.this,Detail_Country.class);

        intent.putExtra("country", country);

        startActivity(intent);
    }

    private void LazyLoad(){
        if(countryModelArrayList.size()==0){
            return;
        }
        int temp = 0;
        for(int i = 0; i < countryModelArrayList.size(); i++){
            if(temp == ITEM_COUNT_LAZY){
                return;
            }
            CountryModel countrySelected = countryModelArrayList.get(i);
            countryModelArrayListLazy.add(countrySelected);
            countryModelArrayList.remove(i);
            temp++;
        }
        countryAdapter.notifyDataSetChanged();
    }

    private void addControll() {
        countryModelArrayList = new ArrayList<>();
        countryModelArrayListLazy = new  ArrayList<>();
        lv_Country = findViewById(R.id.LvCountry);
        countryAdapter = new CountryAdapter(MainActivity.this,R.layout.country_item,countryModelArrayList);
        lv_Country.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
        loadingview = findViewById(R.id.loading);
        loadingImage = findViewById(R.id.loadingImg);
        CountrytTask countrytTask = new CountrytTask();
        countrytTask.execute();

        lv_Country.setVisibility(View.GONE);
        loadingview.setVisibility(View.VISIBLE);
    }

    class CountrytTask extends AsyncTask<Void,Void,ArrayList<CountryModel>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            countryAdapter.clear();

            RotateAnimation rotateAnimation = new RotateAnimation(
                    0,
                    180,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
            );
            rotateAnimation.setDuration(3000);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            loadingImage.startAnimation(rotateAnimation);
        }

        @Override
        protected void onPostExecute(ArrayList<CountryModel> countryModels) {
            super.onPostExecute(countryModels);
            countryAdapter.clear();
            countryAdapter.addAll(countryModels);

            maxCount =  countryModelArrayList.size();

            LazyLoad();

            lv_Country.setVisibility(View.VISIBLE);
            loadingview.setVisibility(View.GONE);


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<CountryModel> doInBackground(Void... voids) {
            ArrayList<CountryModel> ds = new ArrayList<>();
            try {
                URL url = new URL("http://api.geonames.org/countryInfoJSON?username=btandroid2");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }

                JSONObject jsonObject = new JSONObject(builder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("geonames");


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
                        String LinkImage = "http://img.geonames.org/flags/x/"+ countryCode +".gif";
                        String MapImage = "http://img.geonames.org/img/country/250/"+ record.getString("countryCode") +".png";
                        countryModel.setFlagImage(LinkImage);
                        countryModel.setMapImage(MapImage);
                    }
                    Log.e("Country: ",countryModel.toString());
                    ds.add(countryModel);
                }

            }catch (Exception ex){
                Log.e("LOI" ,ex.toString());
            }
            return ds;
        }
    }
}