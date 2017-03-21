package com.cz.SVM.susy.analysis;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cz on 17-3-20.
 */
public class JobAnalysis {
    public static void main(String[] args) {
        // 1、构造excel文件输入流对象
        String sFilePath = "/home/cz/Hadoop2/spark-all/resultAna/data/kmeans/hengxiang/app-20170314193129-0055-16-4core/job.xls";
        InputStream is = null;
        try {
            is = new FileInputStream(sFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 2、声明工作簿对象
        Workbook rwb = null;
        try {
            rwb = Workbook.getWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 3、获得工作簿的个数,对应于一个excel中的工作表个数
        rwb.getNumberOfSheets();

        Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
//        System.out.println("工作表名称：" + oFirstSheet.getName());
        int rows = oFirstSheet.getRows();//获取工作表中的总行数
        int columns = oFirstSheet.getColumns();//获取工作表中的总列数

        double training = 0.0;
        double preProcess = 0.0;
        double forcast = 0.0;

        //他的结果是
        String[] fro = oFirstSheet.getCell(2,1).getContents().split(" ");
        if(fro[fro.length-1].trim().equals("min")){
            forcast += Double.valueOf(oFirstSheet.getCell(2,1).getContents().split(" ")[0].trim());
        } else if (fro[fro.length-1].trim().equals("s")){
            forcast += Double.valueOf(oFirstSheet.getCell(2,1).getContents().split(" ")[0].trim()) / 60.0;
        }else if(fro[fro.length-1].trim().equals("ms")){
            forcast += Double.valueOf(oFirstSheet.getCell(2,1).getContents().split(" ")[0].trim()) / 60000.0;
        }
//        System.out.println(forcast);

        for (int i = 1; i < rows ; i++) {
            String[] array = oFirstSheet.getCell(2,i).getContents().split(" ");
            if(i < 33) {
                if(array[array.length-1].trim().equals("min")){
                    training += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim());
                } else if (array[array.length-1].trim().equals("s")){
                    training += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim()) / 60.0;
                }else if(array[array.length-1].trim().equals("ms")){
                    training += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim()) / 60000.0;
                }
            } else {
                if(array[array.length-1].trim().equals("min")){
                    preProcess += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim());
                } else if (array[array.length-1].trim().equals("s")){
                    preProcess += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim()) / 60.0;
                }else if(array[array.length-1].trim().equals("ms")){
                    preProcess += Double.valueOf(oFirstSheet.getCell(2,i).getContents().split(" ")[0].trim()) / 60000.0;
                }

            }
        }
        System.out.println("数据预测时间:" + forcast);
        System.out.println("模型训练时间是："+training);
        System.out.println("数据预处理时间是："+ preProcess);
    }
}
