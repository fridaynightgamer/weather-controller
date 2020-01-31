package common;

import apis.ReadApis;
import org.json.JSONObject;

import java.util.*;

public class Controller {
    public static void main(String[] args) throws InterruptedException {
        ReadApis readApis=new ReadApis();
        HashMap<String,String> payload=readApis.prepareAllApis();
        Set<String> payloadKeys=payload.keySet();
        for(String key:payloadKeys){
            apiCall(key,payload.get(key));
            System.out.println("Successfully completed a cycle.. Continuing to the next cycle.. Waiting for 10 seconds for API timeout..");
            Thread.sleep(10000);
        }
    }

    private static void apiCall(String uuid,String api){
        GatherData gatherer=new GatherData();
        String weatherData=gatherer.sendRequestForGetApi(api);
        JSONObject weatherDataJson=new JSONObject(weatherData);
        System.out.println(weatherDataJson.getJSONObject("main").get("temp").toString());
        executeData(uuid,weatherDataJson,api);
    }
    private static void executeData(String uuid,JSONObject jsondata,String api){
        DataHelper dataActions=new DataHelper();
        HashMap<String,String> data= new HashMap<>();
        data.put("uuid", uuid);
        data.put("timestamp",Long.toString(new Date().getTime()));
        data.put("latitude",jsondata.getJSONObject("coord").get("lat").toString());
        data.put("longitude",jsondata.getJSONObject("coord").get("lon").toString());
        data.put("sunrise",jsondata.getJSONObject("sys").get("sunrise").toString());
        data.put("sunset",jsondata.getJSONObject("sys").get("sunset").toString());
        data.put("speed",jsondata.getJSONObject("wind").get("speed").toString());
        data.put("pressure",jsondata.getJSONObject("main").get("pressure").toString());
        data.put("humidity",jsondata.getJSONObject("main").get("humidity").toString());
        data.put("actualtemp",jsondata.getJSONObject("main").get("temp").toString());
        data.put("maxtemp",jsondata.getJSONObject("main").get("temp_max").toString());
        data.put("mintemp",jsondata.getJSONObject("main").get("temp_min").toString());
        data.put("feelslike",jsondata.getJSONObject("main").get("feels_like").toString());
        data.put("api",api);
        dataActions.insertData(data);
    }
}
