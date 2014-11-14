package cs4720.virginia.cs.edu.rpims4;

import java.util.ArrayList;


/**
 * Created by nitipaudyal on 11/8/14.
 */


public class LightColors {
    public static String green = "{\"lights\": [{\"lightId\": 1, \"red\":0,\"green\":255,\"blue\":0, \"intensity\": 0.7},{\"lightId\": 5, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String blue = "{\"lights\": [{\"lightId\": 5, \"red\":0,\"green\":0,\"blue\":255, \"intensity\": 0.7},{\"lightId\": 9, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String red = "{\"lights\": [{\"lightId\": 9, \"red\":255,\"green\":0,\"blue\":0, \"intensity\": 0.7},{\"lightId\": 13, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String orange = "{\"lights\": [{\"lightId\": 13, \"red\":255,\"green\":128,\"blue\":0, \"intensity\": 0.7},{\"lightId\": 17, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String yellow = "{\"lights\": [{\"lightId\": 17, \"red\":255,\"green\":255,\"blue\":0, \"intensity\": 0.7},{\"lightId\": 21, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String purple = "{\"lights\": [{\"lightId\": 21, \"red\":102,\"green\":0,\"blue\":102, \"intensity\": 0.7},{\"lightId\": 25, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String pink = "{\"lights\": [{\"lightId\": 25, \"red\":255,\"green\":0,\"blue\":127, \"intensity\": 0.7},{\"lightId\": 29, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String white = "{\"lights\": [{\"lightId\": 29, \"red\":255,\"green\":255,\"blue\":255, \"intensity\": 0.7},{\"lightId\": 32, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
    public static String black = "{\"lights\": [{\"lightId\": 1, \"red\":0,\"green\":0,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";

    public static ArrayList<Light> makeMyLightArray(){
        ArrayList<Light> myLights = new ArrayList<Light>();
        myLights.add(new Light(green, "green"));
        myLights.add(new Light(blue, "blue"));
        myLights.add(new Light(red, "red"));
        myLights.add(new Light(orange, "orange"));
        myLights.add(new Light(yellow, "yellow"));
        myLights.add(new Light(purple, "purple"));
        myLights.add(new Light(pink, "pink"));
        myLights.add(new Light(white, "white"));





        return myLights;
    }




    //myLights.add(new Light(green, "green"));
    //myLights.

    //ArrayList<String> userClicks = new ArrayList<String>();

}
