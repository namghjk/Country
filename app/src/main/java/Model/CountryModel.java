package Model;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;

public class CountryModel implements Serializable {
    private String FlagImage;
    private String MapImage;
    private String CountryName;
    private String Population;
    private String AreaInSqKm;


    public CountryModel() {
    }

    public CountryModel(String flagImage, String mapImage, String countryName, String population, String areaInSqKm) {
        FlagImage = flagImage;
        MapImage = mapImage;
        CountryName = countryName;
        Population = population;
        AreaInSqKm = areaInSqKm;
    }

    public String getFlagImage() {
        return FlagImage;
    }

    public void setFlagImage(String flagImage) {
        FlagImage = flagImage;
    }

    public String getMapImage() {
        return MapImage;
    }

    public void setMapImage(String mapImage) {
        MapImage = mapImage;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "FlagImage='" + FlagImage + '\'' +
                ", MapImage='" + MapImage + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                '}';
    }
}
