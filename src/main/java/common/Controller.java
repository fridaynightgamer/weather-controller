package common;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class Controller {
    private static final String OPENWEATHERAPIKEY = "41efa5ceee69820b0972736be39c7516";
    public static void main(String[] args){
        GatherData gatherer=new GatherData();
        String weatherData=gatherer.sendRequestForGetApi("https://api.openweathermap.org/data/2.5/weather?lat=37.7576793&lon=-122.5076401&units=metric&appid="+OPENWEATHERAPIKEY);
        JSONObject weatherDataJson=new JSONObject(weatherData);
        System.out.println(weatherDataJson.getJSONObject("main").get("temp").toString());
    }
}
