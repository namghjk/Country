package com.namghjk.country;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import Model.CountryModel;

public class Detail_Country extends AppCompatActivity {

    TextView tv_CountryName,tv_Population,tv_AreaInSqKM;
    ImageView img_Flag,img_Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        addControlls();
        addEvents();

        Data();
    }

    private void addEvents() {
    }

    private void addControlls() {
        tv_CountryName = findViewById(R.id.txt_countryName);
        tv_Population = findViewById(R.id.txt_population);
        tv_AreaInSqKM = findViewById(R.id.txt_areaInSqKM);
        img_Flag = findViewById(R.id.imgFlag);
        img_Map = findViewById(R.id.img_Map);
    }

    private void Data() {
        CountryModel country = (CountryModel) getIntent().getParcelableExtra("country");

       tv_CountryName.setText(country.getCountryName());
       tv_Population.setText(country.getPopulation());
       tv_AreaInSqKM.setText(country.getAreaInSqKm());





    }
}