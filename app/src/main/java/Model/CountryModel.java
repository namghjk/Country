package Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CountryModel implements Serializable {
    private String Image;
    private String MapImage;
    private String CountryName;
    private String Population;
    private String AreaInSqKm;
    private Bitmap Flag;
    private Bitmap Map;


    public CountryModel() {
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "Image='" + Image + '\'' +
                ", MapImage='" + MapImage + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                ", Flag=" + Flag +
                ", Map=" + Map +
                '}';
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public Bitmap getFlag() {
        return Flag;
    }

    public void setFlag(Bitmap flag) {
        Flag = flag;
    }

    public Bitmap getMap() {
        return Map;
    }

    public void setMap(Bitmap map) {
        Map = map;
    }

    public CountryModel(String image, String mapImage, String countryName, String population, String areaInSqKm, Bitmap flag, Bitmap map) {
        Image = image;
        MapImage = mapImage;
        CountryName = countryName;
        Population = population;
        AreaInSqKm = areaInSqKm;
        Flag = flag;
        Map = map;
    }
}
