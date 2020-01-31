package apis;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class ReadApis {
    private ArrayList<String> allApis=new ArrayList<>();
    private void readAllApis(){
        try {
            FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\apis.xlsx"));
            XSSFSheet apisSheet=new XSSFWorkbook(file).getSheetAt(0);
            Iterator<Row> rowIterator=apisSheet.iterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row row=rowIterator.next();
                allApis.add(row.getCell(0).getStringCellValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HashMap<String,String> prepareAllApis(){
        readAllApis();
        HashMap<String,String> preparedPayloadData=new HashMap<>();
        for (String allApi : allApis) {
            try {
                FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\latlong.xlsx"));
                XSSFSheet latlongSheet = new XSSFWorkbook(file).getSheetAt(0);
                int counter=0;
                for (Row cells : latlongSheet) {
                    if(counter==0){
                        counter++;
                        continue;
                    }
                    if (cells.getCell(0) != null && cells.getCell(1) != null) {
                        String concatLat = allApi.replace("<latitude>", Double.toString(cells.getCell(0).getNumericCellValue()));
                        String finalApiEndpoint = concatLat.replace("<longitude>", Double.toString(cells.getCell(1).getNumericCellValue()));
                        preparedPayloadData.put(UUID.randomUUID().toString(), finalApiEndpoint);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return preparedPayloadData;
    }
}
