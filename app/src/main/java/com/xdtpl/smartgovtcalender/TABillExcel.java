package com.xdtpl.smartgovtcalender;

import android.content.Context;
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

public class TABillExcel {
    private Context context;
    private File excelFile1;
    java.util.List<Integer> fields = new ArrayList<>();
    // java.util.List<Double> Rate = new ArrayList<>();
    Double Rate;

    public void createCellRow(Sheet sheet, Cell cell, Row rowHeader2, int cellno, CellStyle Style,CellStyle Style1, String value) {
        rowHeader2 = sheet.createRow(2);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue("शा.नि.वित्त विभाग क्र.टीआरडब्ल्यु-2481/प्रक्र-719/कोष 4 दि. 27 मे 1987");
        }
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 5));

        rowHeader2 = sheet.createRow(3);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue("म. को. नि. नमुना १८");
        }
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 5));

        rowHeader2 = sheet.createRow(4);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue("(नियम 249 आणि 273)");
        }
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 5));

        rowHeader2 = sheet.createRow(5);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue("प्रवास भत्ता देयक");
        }
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 5));

        rowHeader2 = sheet.createRow(7);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style1);
            if (i == 0) {
                cell.setCellValue("कोषागारासाठी");
            }
            sheet.setColumnWidth(cellno, (15 * 400));
        }
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 5));
    }

    public void createCellRow1(Sheet sheet, Cell cell, Row rowHeader1, int cellno, CellStyle Style, String[] value, String name, String desc, String month) {
        int indexC = 0;
        cell = rowHeader1.createCell(0);

        rowHeader1 = sheet.createRow(0);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader1.createCell(i);
            cell.setCellStyle(Style);
            cell.setCellValue(value[0]);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
    }

    public void createCellRow2(Sheet sheet, Cell cell, Row rowHeader2, int cellno, CellStyle Style, String value) {
        rowHeader2 = sheet.createRow(1);
        int firstrow = 1, lastrow = 1, firstcol = 0, lastcol = 2;
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("मकामुना(एच-४२)-११-२०१२-५,००,०००-पीए३");
                sheet.addMergedRegion(new CellRangeAddress(firstrow, lastrow, firstcol, lastcol));
                firstcol = firstcol + 3;
                lastcol = lastcol + 3;
                cellno = cellno + 3;
            }
            if (i == 3) {
                cell.setCellValue("सर्वसा.24-म.बाहय(सुधारीत)");
                sheet.addMergedRegion(new CellRangeAddress(firstrow, lastrow, firstcol, lastcol));
            }
        }
    }

    public void createCellRow6(Sheet sheet, Cell cell, Row rowHeader6, int cellno, CellStyle Style1, String value) {

        rowHeader6 = sheet.createRow(12);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            cell.setCellStyle(Style1);
            if (i == 1) {
                cell.setCellValue("Sub Major Head                 :");
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 5));

        rowHeader6 = sheet.createRow(13);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            cell.setCellStyle(Style1);
            if (i == 1) {
                cell.setCellValue("Sub Minor Head                 :");
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 1, 5));
    }

    public void createCellRow7(Sheet sheet, Cell cell, Row rowHeader6, int cellno, CellStyle Style, CellStyle Style1, String value) {
        rowHeader6 = sheet.createRow(14);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            if (i == 0) {
                cell.setCellValue("कोषागार प्रमाणक क्रमांक  दिनांक : ");
                rowHeader6.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
                sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 0));
            }
            if (i == 1) {
                cell.setCellValue("Detailed  Head                    :");
                sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 5));
            }
            Style.setWrapText(true);
            cell.setCellStyle(Style1);
        }
    }

    public void createCellRow8(Sheet sheet, Cell cell, Row rowHeader6, int cellno, CellStyle Style1, String value) {
        rowHeader6 = sheet.createRow(15);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            cell.setCellStyle(Style1);
            if (i == 1) {
                cell.setCellValue("Detailed  Head                    : 11 / Domestic Travel Expeneces");
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 5));

        rowHeader6 = sheet.createRow(16);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            cell.setCellStyle(Style1);
            if (i == 1) {
                cell.setCellValue("(Objection of Expenditure)");
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 5));
    }

    public void createCellRow9(Sheet sheet, Cell cell, Row rowHeader9, int cellno, CellStyle Style, String value) {
        rowHeader9 = sheet.createRow(17);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader9.createCell(i);
            Style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("विमान/रेल्वे/आगबोट/बस \n भाडे");
            }
            if (i == 1) {
                cell.setCellValue("10");
            }
            rowHeader9.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader = sheet.createRow(18);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("किलोमीटर सडक भत्ता");
            }
            if (i == 1) {
                cell.setCellValue("10");
            }
            rowHeader.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader1 = sheet.createRow(19);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader1.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("सर्वसाधारण दराने मागणी केलेल्या दैनिक भत्त्याची रक्कम");
            }
            if (i == 1) {
                cell.setCellValue("15");
            }
            rowHeader1.setHeightInPoints((3 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader2 = sheet.createRow(20);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader2.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("मागणी केलेल्या विशेष आणि सर्वसाधारण दर यातील फरकाची रक्कम ");
            }
            if (i == 1) {
                cell.setCellValue("18");
            }
            rowHeader2.setHeightInPoints((3 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader3 = sheet.createRow(21);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader3.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("एकूण रक्कम रुपये ");
            }
            rowHeader3.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader4 = sheet.createRow(22);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader4.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("वजाती");
            }
            rowHeader4.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader5 = sheet.createRow(23);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader5.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("1.प्रवास भत्ता अग्रीम ");
            }
            rowHeader5.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader6 = sheet.createRow(24);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader6.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("2.स्थायी प्रवास भत्ता अग्रीम");
            }
            rowHeader6.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader7 = sheet.createRow(25);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader7.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("3.वाहन भत्ता ");
            }
            rowHeader7.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader8 = sheet.createRow(26);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader8.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("एकूण वजाती रुपये ");
            }
            rowHeader8.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        Row rowHeader10 = sheet.createRow(27);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader10.createCell(i);
            cell.setCellStyle(Style);
            if (i == 0) {
                cell.setCellValue("शेवटच्या पानावर घेतलेली निव्वळ देय रक्कम ");
            }
            rowHeader10.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }
    }

    public void createCellRows1(Sheet sheet, Cell cell, Row rowHeader3, int cellno, CellStyle Style, String[] value) {
        //rowHeader3 = sheet.createRow(6);
        for (int i = 3; i <= 5; ++i) {
            cell = rowHeader3.createCell(i);
            if (i == 3) {
                cell.setCellValue(value[0]);
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 3, 5));
            }
            cell.setCellStyle(Style);
        }
    }//Done - Mahe : month 2020 in right side

    public void createCellRows2(Sheet sheet, Cell cell, Row rowHeader5, int cellno, CellStyle Style, String[] value) {
        rowHeader5 = sheet.createRow(6);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader5.createCell(i);
            if (i == 0) {
                cell.setCellValue(value[0]);
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 2));
            }
            cell.setCellStyle(Style);
        }
    }//Done - Karylayche nav : .... left  side

    public void createCellRows3(Sheet sheet, Cell cell, Row rowHeader3, int cellno, CellStyle Style, String[] value) {

    }//Done - indentity num

    public void createCellRows4(Sheet sheet, Cell cell, Row rowHeader5, int cellno, CellStyle Style, String[] value) {
        Row rowHeaderr = sheet.createRow(8);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeaderr.createCell(i);
            if (i == 0) {
                cell.setCellValue("ओळखचिन्ह क्रमांक दिनांक :  ");
                Style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
                sheet.addMergedRegion(new CellRangeAddress(8, 11, 0, 0));
            }
            if (i == 1) {
                cell.setCellValue("HEAD OF ACCOUNT");
                sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 5));
            }
            //Style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cell.setCellStyle(Style);
        }
    }//Done - head of dept

    public void createCellRows5(Sheet sheet, Cell cell, Row rowHeader9, int cellno, CellStyle Style, String[] value) {
        rowHeader9 = sheet.createRow(9);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader9.createCell(i);
            if (i == 1) {
                cell.setCellValue(value[0]);
                sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 5));
            }
            cell.setCellStyle(Style);
        }
    }//Done - admin dept

    public void createCellRows6(Sheet sheet, Cell cell, Row rowHeader10, int cellno, CellStyle Style, String[] value) {
        rowHeader10 = sheet.createRow(10);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader10.createCell(i);
            if (i == 1) {
                cell.setCellValue(value[0]);
                sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 5));
            }
            cell.setCellStyle(Style);
        }
    }//Done - Demand no

    public void createCellRows7(Sheet sheet, Cell cell, Row rowHeader11, int cellno, CellStyle Style, String[] value) {
        rowHeader11 = sheet.createRow(11);
        for (int i = 0; i <= 5; ++i) {
            cell = rowHeader11.createCell(i);
            if (i == 1) {
                cell.setCellValue(value[0]);
                sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 5));
            }
            cell.setCellStyle(Style);
        }
    }//Done - Major Head

    public void createTable(Sheet sheet, Workbook wb, CellStyle Style, CellStyle Style1, String[] header, ArrayList<String[]> clients,
                            String filename, String nameofPerson,
                            String desc, ArrayList<String[]> clients2, String[] header2, String[] TBheader,
                            String mainsalary, String mainoffice, String month, String[] TAheader) {

        try {

            Cell cell = null;
            int indexC = 0;
            cell = null;
            Row heading1 = sheet.createRow(27);
            for (int i = 0; i <= 20; ++i) {
                cell = heading1.createCell(i);
                //Style.setAlignment(CellStyle.ALIGN_CENTER);
                cell.setCellStyle(Style);
                cell.setCellValue("नाव :" + nameofPerson + " , " + desc + " , " + "यांचे माहे : " + month + "  चे प्रवास भत्ता देयक ");
            }
            sheet.addMergedRegion(new CellRangeAddress(27, 27, 0, 20));

            Row heading = sheet.createRow(28);
            for (int i = 0; i <= 20; ++i) {
                cell = heading.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("प्रवास भत्ता देयक ");
            }
            sheet.addMergedRegion(new CellRangeAddress(28, 28, 0, 20));

            Row name = sheet.createRow(29);
            for (int i = 0; i <= 20; ++i) {
                cell = name.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("राजप्रत्रित अधिकाऱ्याचे नाव : " + nameofPerson + "                पदनाम : " + desc);
            }
            sheet.addMergedRegion(new CellRangeAddress(29, 29, 0, 20));

            Row sal = sheet.createRow(30);
            for (int i = 0; i <= 20; ++i) {
                cell = sal.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue(" मूळ वेतन : " + mainsalary + "   मुख्यालय : " + mainoffice);
            }
            sheet.addMergedRegion(new CellRangeAddress(30, 30, 0, 20));

            Row rowHeader = sheet.createRow(34);
            while (indexC < header.length) {
                cell = rowHeader.createCell(indexC);
                cell.setCellValue(header[indexC]);
                cell.setCellStyle(Style);
                sheet.setColumnWidth(indexC, (15 * 400));
                indexC++;
            }

            int indexC1 = 0;
            int cellcnt = 13;
            Cell cell1 = null;
            while (indexC1 < header2.length) {
                cell1 = rowHeader.createCell(cellcnt++);
                cell1.setCellValue(header2[indexC1]);
                cell1.setCellStyle(Style);
                sheet.setColumnWidth(indexC1, (15 * 400));
                indexC1++;
            }
            //prawas bhatta
            int ind = 0;
            Cell ce = null;

            Row rowHeader31 = sheet.createRow(31);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader31.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("प्रवासाचा आणि मुख्यालयाचा तपशील");
            }
            sheet.addMergedRegion(new CellRangeAddress(31, 31, 0, 5));
            //vahan bhade
            int ind2 = 0;
            Cell ce2 = null;
            int cnt1 = 8;
            for (int i = 8; i <= 9; ++i) {
                cell = rowHeader31.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("वाहन भाडे");
            }
            sheet.addMergedRegion(new CellRangeAddress(31, 31, 8, 9));
            //apsent
            int ind3 = 0;
            Cell ce3 = null;
            int cnt3 = 10;
            for (int i = 10; i <= 11; ++i) {
                cell = rowHeader31.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("मुख्य.अनुपस्थिती");
            }
            sheet.addMergedRegion(new CellRangeAddress(31, 31, 10, 11));
            //Gaman

            Row rowHeader32 = sheet.createRow(32);
            for (int i = 0; i <= 2; ++i) {
                cell = rowHeader32.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("गमन");
            }
            sheet.addMergedRegion(new CellRangeAddress(32, 32, 0, 2));

            for (int i = 3; i <= 5; ++i) {
                cell = rowHeader32.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("आगमन");
            }
            sheet.addMergedRegion(new CellRangeAddress(32, 32, 3, 5));

            Row rowHeader33 = sheet.createRow(33);
            int indexC3 = 0;
            int cellcnt3 = 0;
            Cell cell3 = null;
            while (indexC3 < TAheader.length) {
                cell3 = rowHeader33.createCell(cellcnt3++);
                cell3.setCellValue(TAheader[indexC3]);
                Style1.setWrapText(true);
                cell3.setCellStyle(Style);
                sheet.setColumnWidth(indexC3, (15 * 400));
                indexC3++;
            }

            int indexC2 = 0;
            int cellcnt2 = 13;
            Cell cell2 = null;
            while (indexC2 < TBheader.length) {
                cell2 = rowHeader33.createCell(cellcnt2++);
                cell2.setCellValue(TBheader[indexC2]);
                Style1.setWrapText(true);
                cell2.setCellStyle(Style);
                sheet.setColumnWidth(indexC2, (15 * 400));
                indexC2++;
            }

            ArrayList<String> arrayList = new ArrayList<>();
            Row rowElement = sheet.createRow(100);
            int r = 35;
            for (int indexR = 0; indexR < clients.size(); indexR++) {
                String[] row = clients.get(indexR);
                String[] row1 = clients2.get(indexR);
                int colcnt1 = 13;
                int colcnt = 0;
                rowElement = sheet.createRow(r++);
                for (indexC = 0; indexC < header.length; indexC++) {
                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {
                        cell = rowElement.createCell(colcnt++);
                        cell.setCellValue(row[indexC]);
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 400));
                       /* if(row[indexC] == row[9]){
                            arrayList.add(row[9]);
                        }*/

                    } else {
                        cell = rowElement.createCell(colcnt++);
                        cell.setCellValue("");
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 400));
                    }
                }

            }
            ArrayList<String> arrayList2 = new ArrayList<>();
            int r1 = 35;
            double totalAmt = 0; //totval.TotalAmt;
            double add = 0;
            int j = 0 ;
            rowElement = sheet.getRow(r1);
            for (int indexR = 0; indexR < clients2.size(); indexR++) {
                String[] row = clients2.get(indexR);
                int colcnt = 13;
                j++;                //rowElement = sheet.getRow(r1);
                for (indexC = 0; indexC < TBheader.length; indexC++) {
                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {
                        cell = rowElement.createCell(colcnt++);
                        cell.setCellValue(row[indexC]);//row[indexC]);
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 400));
                        if (row[indexC] == row[0]) {
                            int res, val5;
                            res = Integer.parseInt(row[0]) / 2;
                            val5 = res + Integer.parseInt(row[5]);
                            String res1 = Integer.toString(res);
                            row[1] = res1;
                            row[5] = Integer.toString(val5);
                            arrayList.add(Integer.toString(val5));
                        }

                    } else {
                        cell = rowElement.createCell(colcnt++);
                        cell.setCellValue("");
                        cell.setCellStyle(Style);
                        sheet.setColumnWidth(indexC, (15 * 400));
                    }

                }
                rowElement = sheet.getRow(r1 + j);
            }
            for (int i = 0; i < arrayList.size(); i++) {
                int val = Integer.parseInt(arrayList.get(i));

                totalAmt = Double.valueOf(val) + totalAmt;
            }

            int rr = r;
            Row totamtdisplay = sheet.createRow(r);
            for (int i = 16; i <= 17; ++i) {
                cell = totamtdisplay.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("एकूण रक्कम :");
            }
            sheet.addMergedRegion(new CellRangeAddress(rr, rr, 16, 17));

            cell = totamtdisplay.createCell(18);
            cell.setCellValue("" + totalAmt);
            cell.setCellStyle(Style);
            sheet.setColumnWidth(indexC, (15 * 400));

            Row totamtdisplay2 = sheet.createRow(r + 1);
            for (int i = 15; i <= 17; ++i) {
                cell = totamtdisplay2.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("निव्वळ देय रक्कम रुपये : ");
            }
            sheet.addMergedRegion(new CellRangeAddress(rr + 1, rr + 1, 15, 17));

            cell = totamtdisplay2.createCell(18);
            cell.setCellValue("" + totalAmt);
            cell.setCellStyle(Style);
            sheet.setColumnWidth(indexC, (15 * 400));


///////////////////////////////////////////////////////////////////////////////////////////////////
            //Add Note- "प्रमाणपत्र :"
            Row mainNote = sheet.createRow(r + 2);
            for (int i = 0; i <= 20; ++i) {
                cell = mainNote.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("प्रमाणपत्र :                                 ");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 2, r + 2, 0, 20));

            Row Note = sheet.createRow(r + 3);
            for (int i = 0; i <= 20; ++i) {
                cell = Note.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("1. प्रमाणित करण्यात येते की , सदर देयक मी यापुर्वी सादर केलेले नाही .");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 3, r + 3, 0, 20));

            Row Note1 = sheet.createRow(r + 4);
            for (int i = 0; i <= 20; ++i) {
                cell = Note1.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("2. प्रमाणित करण्यात येते की , सदर प्रवासाकरीता मी आगाऊ अग्रीम घेतलेले नाही .");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 4, r + 4, 0, 20));

            Row Note2 = sheet.createRow(r + 5);
            for (int i = 0; i <= 20; ++i) {
                cell = Note2.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("3. प्रमाणित करण्यात येते की , सदर प्रवास हा फक्त शासकीय कामा करीत केलेला आहे .");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 5, r + 5, 0, 20));

            Row Note3 = sheet.createRow(r + 6);
            for (int i = 0; i <= 20; ++i) {
                cell = Note3.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("4. प्रमाणित करण्यात येते की , सदर प्रवास भत्ता देयकात दर्शविण्यात आलेले अंतर , वेळ व प्रवास भाडे माझ्या माहिती प्रमाणे बरोबर आहे .");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 6, r + 6, 0, 20));

            Row nameOfPersonrow = sheet.createRow(r + 7);
            for (int i = 16; i <= 20; ++i) {
                cell = nameOfPersonrow.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("" + nameofPerson);
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 7, r + 7, 16, 20));

            Row descrow = sheet.createRow(r + 8);
            for (int i = 16; i <= 20; ++i) {
                cell = descrow.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("" + desc);
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 8, r + 8, 16, 20));

            Row subfooter = sheet.createRow(r + 9);
            for (int i = 0; i <= 20; ++i) {
                cell = subfooter.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("राजपत्रित अधिकाऱ्याची / आहरण व सवितरण");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 9, r + 9, 0, 20));

            Row subfooter2 = sheet.createRow(r + 10);
            for (int i = 0; i <= 20; ++i) {
                cell = subfooter2.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("अधिकाऱ्याची स्वाक्षरी व पदनाम");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 10, r + 10, 0, 20));

            /*Row subfooter3 = sheet.createRow(r + 11);
            for (int i = 18; i <= 20; ++i) {
                cell = subfooter3.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("App Designed by XDTPL");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 11, r + 11, 18, 20))*/;

            Row rowHeader9 = sheet.createRow(r + 12);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader9.createCell(i);
                cell.setCellStyle(Style);
                rowHeader9.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 12, r + 12, 0, 5));

            rowHeader9 = sheet.createRow(r + 13);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader9.createCell(i);
                cell.setCellStyle(Style1);
                if (i == 0) {
                    cell.setCellValue("मागणी केलेली निव्वळ रक्कम");
                }
                rowHeader9.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 13, r + 13, 0, 5));

            rowHeader9 = sheet.createRow(r + 14);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader9.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("रोखीने / धनादेशाने रु.");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 14, r + 14, 0, 5));

            rowHeader9 = sheet.createRow(r + 15);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader9.createCell(i);
                cell.setCellStyle(Style1);
                cell.setCellValue("रु.");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 15, r + 15, 0, 5));

            rowHeader9 = sheet.createRow(r + 16);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader9.createCell(i);
                Style.setWrapText(true);
                cell.setCellStyle(Style1);
                cell.setCellValue("प्रमाणित करण्यात येते की,(1)प्रवास भत्ता देकात नमूद केलेली माहिती खरी आहे." +
                        "(2)देयकात मागणी केलेली प्रवास भत्ता रक्कम शासन निर्णय,वित्त विभाग, क्रमांक टीआरडब्ल्यु - 2775/451/एडीएम-9 दिनांक.29/11/1975" +
                        "आणि क्रमांक टीआरए-1077/156-सी एसईआर-5 दि.11/8/1977 या नुसार व त्या नंतर वेळोवेळी अद्यायावत केलेल्या शासन निर्णयानुसार आहे");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 16, r + 16, 0, 5));
            rowHeader9.setHeightInPoints((3 * sheet.getDefaultRowHeightInPoints()));

            Row rowHeader13 = sheet.createRow(r + 17);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader13.createCell(i);
                Style.setWrapText(true);
                cell.setCellStyle(Style1);
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 17, r + 17, 0, 5));

            Row rowHeader14 = sheet.createRow(r + 18);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader14.createCell(i);
                if (i == 0) {
                    cell.setCellValue("ठिकाण:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 18, r + 18, 0, 2));
                }
                if (i == 3) {
                    cell.setCellValue("आहरण व सवितरण अधिकारी यांची स्वाक्षर");
                    sheet.addMergedRegion(new CellRangeAddress(r + 18, r + 19, 3, 5));
                }
                cell.setCellStyle(Style1);
            }
            Row rowHeader15 = sheet.createRow(r + 19);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader15.createCell(i);
                if (i == 0) {
                    cell.setCellValue("दिनांक:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 19, r + 19, 0, 2));
                }
                cell.setCellStyle(Style1);
            }

            Row rowHeader16 = sheet.createRow(r + 20);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader16.createCell(i);
                if (i == 0) {
                    cell.setCellValue("                                                                          रुपये");
                    sheet.addMergedRegion(new CellRangeAddress(r + 20, r + 20, 0, 5));
                }
                cell.setCellStyle(Style1);
            }

            Row rowHeader17 = sheet.createRow(r + 21);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader17.createCell(i);
                if (i == 0) {
                    cell.setCellValue("वर्ष 2018-19 करीता अर्थसंकल्पीय तरतुद रुपये");
                    sheet.addMergedRegion(new CellRangeAddress(r + 21, r + 21, 0, 2));
                }
                cell.setCellStyle(Style1);
                sheet.addMergedRegion(new CellRangeAddress(r + 21, r + 21, 3, 5));
            }

            Row rowHeader18 = sheet.createRow(r + 22);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader18.createCell(i);
                if (i == 0) {
                    cell.setCellValue("हे देयक धरुन झालेला खर्च");
                    sheet.addMergedRegion(new CellRangeAddress(r + 22, r + 22, 0, 2));
                }
                cell.setCellStyle(Style1);
                sheet.addMergedRegion(new CellRangeAddress(r + 22, r + 22, 3, 5));
            }

            Row rowHeader19 = sheet.createRow(r + 23);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader19.createCell(i);
                if (i == 0) {
                    cell.setCellValue("शिल्लक तरतुद");
                    sheet.addMergedRegion(new CellRangeAddress(r + 23, r + 23, 0, 2));
                }
                cell.setCellStyle(Style1);
                sheet.addMergedRegion(new CellRangeAddress(r + 23, r + 23, 3, 5));
            }

            Row rowHeader20 = sheet.createRow(r + 24);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader20.createCell(i);
                cell.setCellValue("रुपये                                    /-    रुपये");
                sheet.addMergedRegion(new CellRangeAddress(r + 24, r + 24, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader21 = sheet.createRow(r + 25);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader21.createCell(i);
                cell.setCellValue("प्रमाणित करण्यात येते की , मी सर्व प्रकारची विहित तपासणी केली आहे.");
                sheet.addMergedRegion(new CellRangeAddress(r + 25, r + 25, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader22 = sheet.createRow(r + 26);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader22.createCell(i);
                if (i == 0) {
                    cell.setCellValue("ठिकाण:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 26, r + 26, 0, 2));
                }
                cell.setCellStyle(Style1);
                //sheet.addMergedRegion(new CellRangeAddress(r + 26, r + 26, 3, 5));
            }

            Row rowHeader23 = sheet.createRow(r + 27);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader23.createCell(i);
                if (i == 0) {
                    cell.setCellValue("दिनांक:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 27, r + 27, 0, 2));
                    cell.setCellStyle(Style1);
                }
                if (i == 3) {
                    cell.setCellValue("नियंत्रण अधिकाऱ्याची स्वाक्षरी आणि पदनाम :");
                    sheet.addMergedRegion(new CellRangeAddress(r + 27, r + 28, 3, 5));
                    cell.setCellStyle(Style);
                }
                //cell.setCellStyle(Style);
            }

            Row rowHeader24 = sheet.createRow(r + 28);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader24.createCell(i);
                cell.setCellValue("कोषागाराकरिता ");
                sheet.addMergedRegion(new CellRangeAddress(r + 28, r + 28, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader25 = sheet.createRow(r + 29);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader25.createCell(i);
                cell.setCellValue("Pay Rs...................................... In Words Rs............................................................................");
                sheet.addMergedRegion(new CellRangeAddress(r + 29, r + 29, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader26 = sheet.createRow(r + 30);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader26.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Accountant:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 30, r + 30, 0, 2));
                }
                if (i == 3) {
                    cell.setCellValue("Treasury Officer/Assiostant pa and Accounts officer");
                    sheet.addMergedRegion(new CellRangeAddress(r + 30, r + 30, 3, 5));
                }
                cell.setCellStyle(Style1);
            }

            Row rowHeader27 = sheet.createRow(r + 31);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader27.createCell(i);
                if (i == 0) {
                    sheet.addMergedRegion(new CellRangeAddress(r + 31, r + 31, 0, 2));
                }
                if (i == 3) {
                    cell.setCellValue("Date:");
                    sheet.addMergedRegion(new CellRangeAddress(r + 32, r + 32, 3, 5));
                }
                cell.setCellStyle(Style1);
            }

            Row rowHeader28 = sheet.createRow(r + 33);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader28.createCell(i);
                cell.setCellValue("FOR ACCOUNTANT GENERALS OFFICE");
                sheet.addMergedRegion(new CellRangeAddress(r + 33, r + 33, 0, 5));
                cell.setCellStyle(Style);
            }

            Row rowHeader29 = sheet.createRow(r + 34);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader29.createCell(i);
                cell.setCellValue("Admitted for rs.                                                         Objected to Rs.");
                sheet.addMergedRegion(new CellRangeAddress(r + 34, r + 34, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader30 = sheet.createRow(r + 35);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader30.createCell(i);
                cell.setCellValue("Reasons for objection");
                sheet.addMergedRegion(new CellRangeAddress(r + 35, r + 35, 0, 5));
                cell.setCellStyle(Style1);
            }

            Row rowHeader51 = sheet.createRow(r + 36);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader51.createCell(i);
                sheet.addMergedRegion(new CellRangeAddress(r + 36, r + 36, 0, 5));
                cell.setCellStyle(Style);
            }

            Row rowHeader52 = sheet.createRow(r + 37);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader52.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Auditor");
                }
                if (i == 1) {
                    cell.setCellValue("Section Officer:");
                }
                if (i == 2) {
                    cell.setCellValue("Reviewing Officer");
                    sheet.addMergedRegion(new CellRangeAddress(r + 37, r + 37, 2, 5));
                }
                cell.setCellStyle(Style);
            }

            Row rowHeader53 = sheet.createRow(r + 38);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader53.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Retrenchment slip No.");
                }
                cell.setCellStyle(Style1);
            }

            Row rowHeader54 = sheet.createRow(r + 39);
            for (int i = 0; i <= 5; ++i) {
                cell = rowHeader54.createCell(i);
                cell.setCellStyle(Style);
            }

            Row subfooter3 = sheet.createRow(r + 40);
            for (int i = 3; i <= 5; ++i) {
                cell = subfooter3.createCell(i);
                cell.setCellStyle(Style);
                cell.setCellValue("App Designed by XDTPL");
            }
            sheet.addMergedRegion(new CellRangeAddress(r + 40, r + 40, 3, 5));

            // Create a path where we will place our List of objects on external storage
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


    public void addMetaData(Sheet sheet, Cell cell, String title, String subject, String author) {
        /*sheet.addTitle(title);
        sheet.addSubject(subject);
        sheet.addAuthor(author);*/
    }

}
