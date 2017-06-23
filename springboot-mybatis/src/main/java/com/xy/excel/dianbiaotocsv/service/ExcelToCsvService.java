package com.xy.excel.dianbiaotocsv.service;

import com.xy.util.CSVUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiayun on 2017/6/1.
 */
@Service
public class ExcelToCsvService {

    @Value("${excel.db.path}")
    String dbPath;

    public void excelToCsv() throws Exception {
        File file=new File(dbPath);
        XSSFWorkbook workbook = new XSSFWorkbook(new BufferedInputStream(
                new FileInputStream(file)));
        XSSFSheet sheet;
        Map<String,List<String>> signalMap=new HashMap<>(); //key为信号channel
        for (int i = 0; i < workbook.getNumberOfSheets(); i++)
        {
            sheet = workbook.getSheetAt(i);
            String sheetName=sheet.getSheetName();
            if("点表".equals(sheetName)||"智能鼓风".equals(sheetName)||"智能启停".equals(sheetName)){
                XSSFRow row;
                for(int i1 =1 ;i1<sheet.getLastRowNum();i1++){
                    row=sheet.getRow(i1);
                    if(row==null){
                        continue;
                    }
                    XSSFCell channelCell=row.getCell(4); //所属plc
                    XSSFCell descriptionCell=row.getCell(3); //信号名称
                    XSSFCell labelCell=row.getCell(6); //label
                    XSSFCell plcCell=row.getCell(7); //plc内部标签
                    XSSFCell typeCell=row.getCell(8); //数据类型
                    String channel=channelCell.getStringCellValue();
                    if(channel.trim().equals("")||channel.trim().length()<=0){
                        channel="";
                    }
                    String description=descriptionCell.getStringCellValue();
                    if(description.trim().equals("")||description.trim().length()<=0){
                        description="";
                    }
                    String label=labelCell.getStringCellValue();
                    if(label.trim().equals("")||label.trim().length()<=0){
                        label="";
                    }
                    String plcLabel=plcCell.getStringCellValue();
                    if(plcLabel.trim().equals("")||plcLabel.trim().length()<=0){
                        plcLabel="";
                    }
                    String type=typeCell.getStringCellValue();
                    if(type.trim().equals("")||type.trim().length()<=0){
                        type="";
                    }
                    if(!"".equals(channel)) {
                        if (!signalMap.containsKey(channel)) {
                            String columnName="Tag Name,Address,Data Type,Respect Data Type,Client Access,Scan Rate,Description";
                            ArrayList<String> records=new ArrayList<>();
                            records.add(columnName);
                            signalMap.put(channel, records);
                        }
                        String dbCsv=label+","+plcLabel+","+type+",1,R/W,100,"+description;
                        List<String> signalList = signalMap.get(channel);
                        signalList.add(dbCsv);
                    }
                }
            }
        }

        String date=new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        for (Map.Entry<String,List<String>> entry:signalMap.entrySet()) {
            String channel=entry.getKey();
            CSVUtils.exportCsv(new File("D:\\csv\\"+date+"\\"+channel+".csv"),entry.getValue());
        }


    }

}
