package com.xy.excel.dianbiaotocsv.service;


import com.xy.util.BigExcelReader;
import com.xy.util.CSVUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiayun on 2017/6/2.
 */

@Service
public class BigExcelToCsvService {

    @Value("${excel.db.path}")
    String dbPath;

    public void DBToCsv() throws Exception{
        Map<String,List<String>> signalMap=new HashMap<>(); //key为信号channel
        BigExcelReader reader = new BigExcelReader() {
            public void getRows(int sheetIndex, int curRow, List<String> rowList) {

                if(rowList.size()<26){
                    return;
                }

                String channel=rowList.get(4);
                String description=rowList.get(3);
                String label=rowList.get(6);
                String plcLabel=rowList.get(7);
                String type=rowList.get(8);
                if(!("".equals(channel)||"所属PLC".equals(channel))) {
                    if (!signalMap.containsKey(channel)) {
                        //String columnName="Tag Name,Address,Data Type,Respect Data Type,Client Access,Scan Rate,Description";
                        String columnName="Tag Name,Address,Data Type,Respect Data Type,Client Access,Scan Rate,Scaling,Raw Low,Raw High,Scaled Low,Scaled High,Scaled Data Type,Clamp Low,Clamp High,Eng Units,Description,Negate Value";
                        ArrayList<String> records=new ArrayList<>();
                        records.add(columnName);
                        signalMap.put(channel, records);
                    }
                    String dbCsv="\""+label+"\",\""+plcLabel+"\","+type+",1,R/W,100,,,,,,,,,,\""+description+"\",";
                    List<String> signalList = signalMap.get(channel);
                    signalList.add(dbCsv);
                }


            }
        };

        reader.process(dbPath,2);
        reader.process(dbPath,6);
        reader.process(dbPath,7);
        String date=new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        for (Map.Entry<String,List<String>> entry:signalMap.entrySet()) {
            String channel=entry.getKey();
            File file=new File("D:\\csv\\"+date);
            file.mkdirs();
            CSVUtils.exportCsv(new File("D:\\csv\\"+date+"\\"+channel+".csv"),entry.getValue());
        }


    }
}

