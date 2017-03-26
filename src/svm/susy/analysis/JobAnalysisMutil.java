package svm.susy.analysis;

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
public class JobAnalysisMutil {
    public static void main(String[] args) {
        // 1、构造excel文件输入流对象
//        String sFilePath = "/home/cz/Hadoop2/spark-all/resultAna/data/kmeans/hengxiang/app-20170314051352-0032--32-16core/job.xls";
        String sFilePath = "/home/cz/Hadoop2/spark-all/resultAna/data/susy/hengxiang/app-20170228230244-0674-16-2-CORE/job.xls";
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
        int i = 1;
//        System.out.println(rows);
        while (i < rows ) {
            if(i < 11) {
//                    System.out.println(Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim()));
                    forcast += Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim());
                    i += 3;
//                    System.out.println("************************");
            } else if(i < 212){
                    System.out.println(Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim()));
                    training += Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim());
                    if(i == 211)  i++;
                    else i +=2;

            } else {
//                    System.out.println(i);
//                    System.out.println(Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim()));
                    preProcess += Double.valueOf(oFirstSheet.getCell(5,i).getContents().trim());
                    i ++;
            }
        }
        System.out.println("数据预测时间:" + forcast);
        System.out.println("模型训练时间是："+training);
        System.out.println("数据预处理时间是："+ preProcess);
    }
}
