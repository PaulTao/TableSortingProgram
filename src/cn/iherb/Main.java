package cn.iherb;

import cn.iherb.bean.ProvinceCityDistrict;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private static String sheetOrigin = "C:\\Users\\Paul\\Documents\\Tencent Files\\1105832354\\FileRecv\\Chinese Province City District.XLS";
    private static String sheetOthers = "C:\\Users\\Paul\\Documents\\Tencent Files\\1105832354\\FileRecv\\ChineseProvinceCityDistrictWithEnglishNames.XLS";
    private static String sheetOriginNew = "C:\\Users\\Paul\\Documents\\Tencent Files\\1105832354\\FileRecv\\Chinese Province City District New.XLS";
    private static List<ProvinceCityDistrict> listA;    //第一张表格
    private static List<ProvinceCityDistrict> listB;
    private List<ProvinceCityDistrict> listOrigin = new ArrayList<ProvinceCityDistrict>();
    private List<ProvinceCityDistrict> listOthers = new ArrayList<ProvinceCityDistrict>();
    private Map<String, String> mapOrigin = new HashMap<String, String>();
    private Map<String, String> mapOthers = new HashMap<String, String>();

    public static void main(String[] args) {
        Main main = new Main();
        listA = main.getProvinceCityDistrictListOrigin(sheetOrigin);
        listB = main.getProvinceCityDistrictListOthers(sheetOthers);

        //merge the sheetB datas to the sheetA
        main.mergeTwoTables();

        //export a new sheetA
        main.setProvinceCityDistrictListOrigin();
    }

    /*把A表格(Origin)转换成List数据，方便使用java接口*/
    public List<ProvinceCityDistrict> getProvinceCityDistrictListOrigin(String fileLocation) {
        try {
            File f = new File(fileLocation);
            Workbook book = Workbook.getWorkbook(f);
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int columns = sheet.getColumns();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Cell cell;
                    switch (j) {
                        case 0:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("areaCode", cell.getContents());
                            break;
                        case 1:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("provinceCN", cell.getContents());
                            break;
                        case 2:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("provinceEN", cell.getContents());
                            break;
                        case 3:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("cityCN", cell.getContents());
                            break;
                        case 4:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("cityEN", cell.getContents());
                            break;
                        case 5:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("zipStart", cell.getContents());
                            break;
                        case 6:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("zipEnd", cell.getContents());
                            break;
                        case 7:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("districtCN", cell.getContents());
                            break;
                        case 8:
                            cell = sheet.getCell(j, i);
                            mapOrigin.put("districtEN", cell.getContents());
                            break;
                        default:
                            break;
                    }
                   /* optimizing like this
                   if (j == 0) {
                        cell = sheet.getCell(j, i);
                        map.put("areaCode", cell.getContents());
                    }
                    if (j == 1) {
                        cell = sheet.getCell(j, i);
                        map.put("provinceCN", cell.getContents());
                    }
                    if (j == 2) {
                        cell = sheet.getCell(j, i);
                        map.put("provinceEN", cell.getContents());
                    }
                    if (j == )*/
                }
                listOrigin.add(new ProvinceCityDistrict(mapOrigin));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return listOrigin;
    }

    /*把B表格(Others)转换成List数据，方便使用java接口*/
    public List<ProvinceCityDistrict> getProvinceCityDistrictListOthers(String fileLocation) {
        try {
            File f = new File(fileLocation);
            Workbook book = Workbook.getWorkbook(f);
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int columns = sheet.getColumns();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 7; j++) {
                    Cell cell;
                    if (j == 0) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("cityCN", cell.getContents());
                    }
                    if (j == 1) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("cityEN", cell.getContents());
                    }
                    if (j == 2) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("districtCN", cell.getContents());
                    }
                    if (j == 3) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("districtEN", cell.getContents());
                    }
                    if (j == 4) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("zipStart", cell.getContents());
                    }
                    if (j == 5) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("provinceCN", cell.getContents());
                    }
                    if (j == 6) {
                        cell = sheet.getCell(j, i);
                        mapOthers.put("provinceEN", cell.getContents());
                    }
                }
                listOthers.add(new ProvinceCityDistrict(mapOthers));
            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
        } catch (
                BiffException e)

        {
            e.printStackTrace();
        }
        return listOthers;
    }

    private void mergeTwoTables() {
        ProvinceCityDistrict provinceCityDistrictOthers;
        ProvinceCityDistrict provinceCityDistrictOrigin;
        int shouldInsertLocation = 0;

        for (int i = 0; i < listB.size(); i++) {
            Boolean has = false;//数据表A中是否有数据表B中的数据
            for (int j = 0; j < listA.size(); j++) {
                provinceCityDistrictOthers = listB.get(i);
                provinceCityDistrictOrigin = listA.get(j);
                if (provinceCityDistrictOrigin.getProvinceCN().equals(provinceCityDistrictOthers.getProvinceCN())) {
                    if (provinceCityDistrictOrigin.getCityCN().equals(provinceCityDistrictOthers.getCityCN())) {
                        if (provinceCityDistrictOrigin.getDistrictCN().equals(provinceCityDistrictOthers.getDistrictCN())) {
                            has = true;
                            shouldInsertLocation = j;
                            break;
                        }
                    }
                }
            }
            //拿着sheetB中数据遍历sheetA，如果没有则添加此条记录到sheetA
            if (!has) {
                listB.get(i).getDistrictCN();
                listA.add(shouldInsertLocation, listB.get(i));
                System.out.println(listA);
            }
        }
    }

    private void setProvinceCityDistrictListOrigin() {
        WritableWorkbook writeBook = null;
        WritableSheet firstSheet = null;
        ProvinceCityDistrict list = null;
        try {
            writeBook = Workbook.createWorkbook(new File(sheetOriginNew));
            firstSheet = writeBook.createSheet("第一个工作簿", 1);
            /*Label label = new Label(0, 0, "ddddddd");
            firstSheet.addCell(label);
            writeBook.write();*/
            for (int i = 0; i < listA.size(); i++) {
                list = listA.get(i);
                Label label = new Label(0, i, list.getAreaCode());
                firstSheet.addCell(label);
                Label label1 = new Label(1, i, list.getProvinceCN());
                firstSheet.addCell(label1);
                Label label2 = new Label(2, i, list.getProvinceEN());
                firstSheet.addCell(label2);
                Label label3 = new Label(3, i, list.getCityCN());
                firstSheet.addCell(label3);
                Label label4 = new Label(4, i, list.getCityEN());
                firstSheet.addCell(label4);
                Label label5 = new Label(5, i, list.getZipStart());
                firstSheet.addCell(label5);
                Label label6 = new Label(6, i, list.getZipEnd());
                firstSheet.addCell(label6);
                Label label7 = new Label(7, i, list.getDistrictCN());
                firstSheet.addCell(label7);
                Label label8 = new Label(8, i, list.getDistrictEN());
                firstSheet.addCell(label8);
                writeBook.write();
            }
            writeBook.close();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


