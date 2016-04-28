package LifeExpectancy;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.List;

/**
 * Created by Rizwan Ahmed on 4/28/2016.
 */
public class LifeExpectancy extends PApplet {

    private UnfoldingMap map; // To display the map

    public void setup(){
        size(800, 600);

        // Creates a map on this PApplet of width 700 and height 500
        map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map); // make the map interactive
        map.setTweening(true); // make zooming in and out smooth

    }

    public void draw(){
        map.draw();
    }
}
