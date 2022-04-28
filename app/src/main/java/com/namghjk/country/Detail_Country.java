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
        String countryName = getIntent().getStringExtra("countryname");
        String population = getIntent().getStringExtra("population");
        String areainsqkm = getIntent().getStringExtra("areainsqkm");
        String flag = getIntent().getStringExtra("flag");
        String map = getIntent().getStringExtra("map");

        Toast.makeText(Detail_Country.this,map,Toast.LENGTH_SHORT);

       tv_CountryName.setText(countryName);
       tv_Population.setText(population);
       tv_AreaInSqKM.setText(areainsqkm);


        try {
            URL url = new URL(flag);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            img_Flag.setImageBitmap(bitmap);

            URL url1 = new URL(map);
            HttpsURLConnection connection1 = (HttpsURLConnection) url1.openConnection();
            Bitmap bitmap1 = BitmapFactory.decodeStream(connection1.getInputStream());
            img_Map.setImageBitmap(bitmap1);
        } catch (Exception e){
            Log.e("LOI" ,e.toString());
        }



    }
}