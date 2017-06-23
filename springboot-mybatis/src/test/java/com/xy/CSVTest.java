package com.xy;

import com.xy.excel.dianbiaotocsv.service.BigExcelToCsvService;
import com.xy.excel.dianbiaotocsv.service.ExcelToCsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by xiayun on 2017/6/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVTest {
    @Autowired
    ExcelToCsvService excelToCsvService;
    @Autowired
    BigExcelToCsvService bigExcelToCsvService;

    /**
     * 点表自动生成导入kepserver的csv文件
     * @throws Exception
     */
    @Test
    public void produceDBCSV() throws Exception{
        bigExcelToCsvService.DBToCsv();
    }

    @Test
    public void convertColor() throws Exception{
        BigInteger color=new BigInteger("FFFFFFFF",16);
        System.out.println("白："+color.intValue());
        BigInteger color1=new BigInteger("FFf4cece",16);
        System.out.println("红："+color1.intValue());
        BigInteger color2=new BigInteger("FFccf0bf",16);
        System.out.println("绿："+color2.intValue());
        BigInteger color3=new BigInteger("FFf4e5d6",16);
        System.out.println("黄："+color3.intValue());
        BigInteger color4=new BigInteger("FFe0e0e0",16);
        System.out.println("灰："+color4.intValue());
    }

    @Test
    public void doubleFormat(){
        DecimalFormat decimalFormat=new DecimalFormat("#####0.0");
        String result=decimalFormat.format(0.0);
        System.out.println(result);
    }


}
