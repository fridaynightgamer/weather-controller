package common;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ControllerTest {
    private static final String OPENWEATHERAPIKEY = "41efa5ceee69820b0972736be39c7516";
    Controller mainController;

    @BeforeClass
    public void setUpEnv(){
        mainController=new Controller();
    }
    @Test
    public void captureData(){
        String weatherData=mainController.sendRequestForGetApi("https://api.openweathermap.org/data/2.5/weather?lat=37.7576793&lon=-122.5076401&units=metric&appid="+OPENWEATHERAPIKEY);
        JSONObject weatherDataJson=new JSONObject(weatherData);
        System.out.println(weatherDataJson.getJSONObject("main").get("temp").toString());
    }
}
