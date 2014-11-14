package cs4720.virginia.cs.edu.rpims4;

/**
 * Created by nitipaudyal on 11/8/14.
 */
public class Light {
    private String lightColor;
    private String JSON;

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
    }

    public String getJSON() {
        return JSON;
    }

    public String getLightColor() {
        return lightColor;
    }

    public Light(String value, String color){
        JSON = value;
        lightColor = color;
    }
}



