package Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CountryModel implements Serializable {
    private String Image;
    private String CountryName;
    private String Population;
    private String AreaInSqKm;
    private Bitmap Flag;

    public CountryModel(String image, String countryName, String population, String areaInSqKm) {
        Image = image;
        CountryName = countryName;
        Population = population;
        AreaInSqKm = areaInSqKm;
    }

    public CountryModel() {
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public Bitmap getFlag() {
        return Flag;
    }

    public void setFlag(Bitmap flag) {
        Flag = flag;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "CountryName='" + CountryName + '\'' +
                ", Image='" + Image + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                '}';
    }
}
