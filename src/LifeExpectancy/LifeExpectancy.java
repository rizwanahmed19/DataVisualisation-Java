package LifeExpectancy;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rizwan Ahmed on 4/28/2016.
 */
public class LifeExpectancy extends PApplet {

    private UnfoldingMap map; // To display the map
    private HashMap<String, Float> lifeExpData; // Hashmap to hold country ID and values
    private List<Feature> countries;
    private List<Marker> countriesMarkers;

    public void setup(){
        size(700, 500);

        // Creates a map on this PApplet of width 700 and height 500
        map = new UnfoldingMap(this, 50, 50, 600, 400, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map); // make the map interactive
        map.setTweening(true); // make zooming in and out smooth
        map.zoomToLevel(1);
        lifeExpData = loadLifeExpData("G:\\IdeaProjects\\CourseraJava\\data\\LifeExpectancyWorldBank.csv");

        // Read data from the JSON file
        countries = GeoJSONReader.loadData(this, "G:\\IdeaProjects\\CourseraJava\\data\\countries.geo.json");

        countriesMarkers = MapUtils.createSimpleMarkers(countries);

        map.addMarkers(countriesMarkers); // Add markers to the map
        shadeCountries(); // Add color to the markers
    }

    /*
    * This helper method will shade/color the countries based on the life expectancy values
    * RED color indicates low life expectancy values
    * Blue color indicates high life expectancy values
    */
    private void shadeCountries() {
        for(Marker marker : countriesMarkers){
            String countryID = marker.getId();

            if(lifeExpData.containsKey(countryID)){
                float value = lifeExpData.get(countryID);

                int colorLevel = (int)map(value, 40, 90, 10, 255);
                marker.setColor(color(255 - colorLevel, 100, colorLevel));
            }
            else{
                marker.setColor(color(150, 150, 150));
            }
        }
    }

    /*
    *  This helper method takes a .csv file name and extracts country id and
    *  average life expectancy value of each country and returns a HashMap that contains those values.
    */
    private HashMap<String, Float> loadLifeExpData(String fileName) {
        HashMap<String, Float> lifeExpData = new HashMap<>();
        String rows[] = loadStrings(fileName); // Splits the file into rows

        for(String row : rows){
            String[] columns = row.split(","); // divide each row into columns wherever a coma occurs
            if (columns.length == 6 && !columns[5].equals("..")){
                lifeExpData.put(columns[4], Float.parseFloat(columns[5]));
            }
        }
        return lifeExpData;
    }

    public void draw(){
        map.draw();
    }
}
