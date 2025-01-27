package common;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class GatherData {
    public static final MediaType JSON=MediaType.get("application/json");
    OkHttpClient client=new OkHttpClient();

    String sendRequestForPostApi(String api,String requestjson){
        RequestBody body=RequestBody.create(requestjson,JSON);
        Request request=new Request.Builder()
                .url(api)
                .post(body)
                .build();
        try{
            Response response=client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        }catch (IOException e){
            return null;
        }
    }

    String sendRequestForGetApi(String api){
        Request request=new Request.Builder()
                .url(api)
                .build();
        try{
            Response response=client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        }catch (IOException e){
            return null;
        }
    }
}
