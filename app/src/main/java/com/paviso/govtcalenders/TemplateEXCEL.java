package com.paviso.govtcalenders;

import android.os.Environment;
import android.util.Log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TemplateEXCEL {

    public void createCellRow(Sheet sheet, Cell cell, Row rowHeader2, int cellno, CellStyle Style, String value) {
        cell = rowHeader2.createCell(cellno);
        for (int i = 0; i <= 7; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue(value);
        }
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        /*cell = rowHeader2.createCell(cellno);
        cell.setCellValue(value);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
        cell.setCellStyle(Style);*/
    }

    public void createCellRow1(Sheet sheet, Cell cell, Row rowHeader2, int cellno, CellStyle Style, String value) {
        cell = rowHeader2.createCell(cellno);
        for (int i = 0; i <= 7; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue(value);
        }
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,7));

        /*cell = rowHeader2.createCell(cellno);
        cell.setCellValue(value);
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,7));
        cell.setCellStyle(Style);*/
    }

    public void createTable(Sheet sheet, Workbook wb, CellStyle Style, String[] header, ArrayList<String[]> clients, String filename) {
        try {
            int indexC = 0;
            Cell cell = null;
            Row rowHeader = sheet.createRow(2);

            while (indexC < header.length) {
                cell = rowHeader.createCell(indexC);
                cell.setCellValue(header[indexC]);
                cell.setCellStyle(Style);
                sheet.setColumnWidth(indexC, (15 * 350));
                indexC++;
            }
            int r= 3;
            //Row rowHeader1 = sheet.createRow(4);
            for (int indexR = 0; indexR < clients.size(); indexR++) {
                String[] row = clients.get(indexR);
                Row rowHeader1 = sheet.createRow(r++);
                for (indexC = 0; indexC < header.length; indexC++) {
                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {
                        cell = rowHeader1.createCell(indexC);
                        cell.setCellValue(row[indexC]);
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 350));
                    } else {
                        cell = rowHeader1.createCell(indexC);
                        cell.setCellValue("");
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 350));
                    }
                }
            }

            Row rowFooter = sheet.createRow(r);
            cell = rowFooter.createCell(7);
            cell.setCellValue("App Designed by XDTPL");
            cell.setCellStyle(Style);
            sheet.setColumnWidth(indexC, (15 * 400));

        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createsheet(Sheet sheet, Workbook wb, CellStyle Style, String[] header, ArrayList<String[]> clients, String filename) {
                    // Create a path where we will place our List of objects on external storage
        try{
            File file = createFile(filename);
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(file);
                wb.write(os);
                Log.w("FileUtils", "Writing file" + file);
            } catch (IOException e) {
                Log.w("FileUtils", "Error writing " + file, e);
            } catch (Exception e) {
                Log.w("FileUtils", "Failed to save file", e);
            } finally {
                try {
                    if (null != os)
                        os.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }
    private File createFile(String filename) {
        File excelFile = null;
        File folder = new File(Environment.getExternalStorageDirectory() + "/Download/Calendar/");
        if (!folder.exists())
            //File folder = new File(Environment.getExternalStorageDirectory() + "/Download/PDF/");
            folder.mkdir();
        try {
            File file = new File(Environment.getRootDirectory().toString() + File.separator + filename + ".xls");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelFile = new File(folder, filename + ".xls");
    }
}
