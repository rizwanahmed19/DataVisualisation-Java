package LifeExpectancy;

import de.fhpotsdam.unfolding.UnfoldingMap;
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
    private HashMap<String, Float> lifeExpData;

    public void setup(){
        size(700, 500);

        // Creates a map on this PApplet of width 700 and height 500
        map = new UnfoldingMap(this, 50, 50, 600, 400, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map); // make the map interactive
        map.setTweening(true); // make zooming in and out smooth
        map.zoomToLevel(1);
        lifeExpData = loadLifeExpData("G:\\IdeaProjects\\CourseraJava\\data\\LifeExpectancyWorldBank.csv");

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
