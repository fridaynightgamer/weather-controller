package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class DataHelper {
    private final String host="localhost";
    private final String port="3306";
    private final String database="weathercontroller";
    private final String user="root";
    private final String password="Qwerty12345----";
    private String uuid;

    void insertData(HashMap<String,String> data){
        uuid=data.get("uuid");
        insertDataIntoTimestamp(data.get("timestamp"));
        insertDataIntoCoordinates(data.get("latitude"),data.get("longitude"));
        insertDataIntoSundata(data.get("sunrise"),data.get("sunset"));
        insertDataIntoWinddata(data.get("speed"),data.get("pressure"),data.get("humidity"));
        insertDataIntoTempdata(data.get("actualtemp"),data.get("maxtemp"),data.get("mintemp"),data.get("feelslike"));
        insertDataIntoApis(data.get("api"));
    }

    private void insertDataIntoTimestamp(String timestamp){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into time_stamp (identifier,time_stamp) values (?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,timestamp);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at time stamp insertion");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    private void insertDataIntoCoordinates(String lattitude, String longitude){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into coordinates (identifier,lattitude,longitude) values (?,?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,lattitude);
            stmt.setString(3,longitude);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at coordinates insertion");
            System.exit(-2);
        }
    }
    private void insertDataIntoTempdata(String actualtemp, String maxtemp, String mintemp, String feelslike){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into tempdata (identifier,actualtemp,maxtemp,mintemp,feelslike) values (?,?,?,?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,actualtemp);
            stmt.setString(3,maxtemp);
            stmt.setString(4,mintemp);
            stmt.setString(5,feelslike);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at tempdata insertion");
            e.printStackTrace();
            System.exit(-3);
        }
    }
    private void insertDataIntoSundata(String sunrise, String sunset){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into sundata (identifier,sunrise,sunset) values (?,?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,sunrise);
            stmt.setString(3,sunset);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at sundata insertion");
            System.exit(-4);
        }
    }
    private void insertDataIntoWinddata(String speed, String pressure, String humidity){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into winddata (identifier,speed,pressure,humidity) values (?,?,?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,speed);
            stmt.setString(3,pressure);
            stmt.setString(4,humidity);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at sundata insertion");
            System.exit(-5);
        }
    }

    private void insertDataIntoApis(String api){
        try{
            Connection dbconnect= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            //set timestamp
            String dbinsert="insert into apis (identifier,api) values (?,?)";
            PreparedStatement stmt=dbconnect.prepareStatement(dbinsert);
            stmt.setString(1,uuid);
            stmt.setString(2,api);
            stmt.execute();
            dbconnect.close();
        }catch(Exception e){
            System.out.println("Exception occurred at api insertion");
            System.exit(-6);
        }
    }
}
