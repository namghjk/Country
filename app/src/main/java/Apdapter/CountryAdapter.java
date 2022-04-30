package Apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.namghjk.country.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.CountryModel;

public class CountryAdapter extends ArrayAdapter<CountryModel> {
    @NonNull Context context;
    int resource;
    @NonNull List<CountryModel> objects;

    public CountryAdapter(@NonNull Context context, int resource, @NonNull List<CountryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        }


        CountryModel countryModel = this.objects.get(position);

        ImageView Flag = convertView.findViewById(R.id.img_Flag);
        TextView CountryName = convertView.findViewById(R.id.txt_CountryName);
        TextView Population = convertView.findViewById(R.id.txt_Population);
        TextView AreaInSqKM = convertView.findViewById(R.id.txt_AreaInSqKM);





        CountryName.setText(countryModel.getCountryName());
        Population.setText(countryModel.getPopulation());
        AreaInSqKM.setText(countryModel.getAreaInSqKm());
        Picasso.with(context).load(countryModel.getFlagImage()).into(Flag);

        return convertView;
    }
}
