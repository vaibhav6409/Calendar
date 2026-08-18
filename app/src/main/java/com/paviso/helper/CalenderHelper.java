package com.paviso.helper;

import com.paviso.Service.CDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderHelper {

    public static final int AllMonth = 15;

    public String GetMonthName(int month) {

        String monthName = "";
        switch (month) {
            case 0:
                monthName = "जानेवारी";
                break;

            case 1:
                monthName = "फेब्रुवारी";
                break;

            case 2:
                monthName = "मार्च";
                break;

            case 3:
                monthName = "एप्रिल";
                break;

            case 4:
                monthName = "मे";
                break;

            case 5:
                monthName = "जून";
                break;

            case 6:
                monthName = "जुलै";
                break;

            case 7:
                monthName = "ऑगस्ट";
                break;

            case 8:
                monthName = "सप्टेंबर";
                break;

            case 9:
                monthName = "ऑक्टोबर";
                break;

            case 10:
                monthName = "नोव्हेंबर";
                break;

            case 11:
                monthName = "डिसेंबर";
                break;
        }
        return monthName;
    }

    public List<CDate> GetHolidayList(int month) {

        List<CDate> events = new ArrayList<>();
        //Jan
        if (month == 0 || month == AllMonth) {
            CDate cd_0_26 = new CDate();
            Calendar cl_0_26 = Calendar.getInstance();
            cl_0_26.set(2026, 0, 26);
            cd_0_26.HoildayDate = cl_0_26;
            cd_0_26.HolidayText = "26 प्रजासत्ताक दिन";
            cd_0_26.HolidayText1 = " प्रजासत्ताक दिन ";
            events.add(cd_0_26);
        }
        //Feb
        if (month == 1 || month == AllMonth) {

            CDate cd_1_15 = new CDate();
            Calendar cl_1_15 = Calendar.getInstance();
            cl_1_15.set(2026, 1, 15);
            cd_1_15.HoildayDate = cl_1_15;
            cd_1_15.HolidayText = "15 महाशिवरात्री";
            cd_1_15.HolidayText1 = " महाशिवरात्री ";
            events.add(cd_1_15);

            CDate cd_1_19 = new CDate();
            Calendar cl_1_19 = Calendar.getInstance();
            cl_1_19.set(2026, 1, 19);
            cd_1_19.HoildayDate = cl_1_19;
            cd_1_19.HolidayText = "19 छत्रपती शिवाजी महाराज जयंती";
            cd_1_19.HolidayText1 = " छत्रपती शिवाजी महाराज जयंती ";
            events.add(cd_1_19);
        }
        //March
        if (month == 2 || month == AllMonth) {
            CDate cd_2_03 = new CDate();
            Calendar cl_2_03 = Calendar.getInstance();
            cl_2_03.set(2026, 2, 3);
            cd_2_03.HoildayDate = cl_2_03;
            cd_2_03.HolidayText = "03 होळी";
            cd_2_03.HolidayText1 = " होळी (दुसरा दिवस) ";
            events.add(cd_2_03);

            CDate cd_2_19 = new CDate();
            Calendar cl_2_19 = Calendar.getInstance();
            cl_2_19.set(2026, 2, 19);
            cd_2_19.HoildayDate = cl_2_19;
            cd_2_19.HolidayText = "19 गुढीपाडवा";
            cd_2_19.HolidayText1 = " गुढीपाडवा ";
            events.add(cd_2_19);

            CDate cd_2_21 = new CDate();
            Calendar cl_2_21 = Calendar.getInstance();
            cl_2_21.set(2026, 2, 21);
            cd_2_21.HoildayDate = cl_2_21;
            cd_2_21.HolidayText = "21 रमज़ान ईद";
            cd_2_21.HolidayText1 = " रमज़ान ईद ";
            events.add(cd_2_21);

            CDate cd_2_26 = new CDate();
            Calendar cl_2_26 = Calendar.getInstance();
            cl_2_26.set(2026, 2, 26);
            cd_2_26.HoildayDate = cl_2_26;
            cd_2_26.HolidayText = "26 रामनवमी";
            cd_2_26.HolidayText1 = " रामनवमी ";
            events.add(cd_2_26);

            CDate cd_2_31 = new CDate();
            Calendar cl_2_31 = Calendar.getInstance();
            cl_2_31.set(2026, 2, 31);
            cd_2_31.HoildayDate = cl_2_31;
            cd_2_31.HolidayText = "31 महावीर जयंती";
            cd_2_31.HolidayText1 = " महावीर जयंती ";
            events.add(cd_2_31);
        }
        //April
        if (month == 3 || month == AllMonth) {

            CDate cd_3_03 = new CDate();
            Calendar cl_3_03 = Calendar.getInstance();
            cl_3_03.set(2026, 3, 3);
            cd_3_03.HoildayDate = cl_3_03;
            cd_3_03.HolidayText = "03 गुड फ्रायडे";
            cd_3_03.HolidayText1 = " गुड फ्रायडे ";
            events.add(cd_3_03);

            CDate cd_3_14 = new CDate();
            Calendar cl_3_14 = Calendar.getInstance();
            cl_3_14.set(2026, 3, 14);
            cd_3_14.HoildayDate = cl_3_14;
            cd_3_14.HolidayText = "14 डॉ.बाबासाहेब आंबेडकर जयंती";
            cd_3_14.HolidayText1 = " डॉ.बाबासाहेब आंबेडकर जयंती ";
            events.add(cd_3_14);
        }
        // May
        if (month == 4 || month == AllMonth) {
            CDate cd_4_1 = new CDate();
            Calendar cl_4_1 = Calendar.getInstance();
            cl_4_1.set(2026, 4, 1);
            cd_4_1.HoildayDate = cl_4_1;
            cd_4_1.HolidayText = "01 महाराष्ट्र दिन";
            cd_4_1.HolidayText1 = " महाराष्ट्र दिन ";
            events.add(cd_4_1);

            CDate cd_4_01 = new CDate();
            Calendar cl_4_01 = Calendar.getInstance();
            cl_4_01.set(2026, 4, 1);
            cd_4_01.HoildayDate = cl_4_01;
            cd_4_01.HolidayText = "01 बुद्ध पौर्णिमा";
            cd_4_01.HolidayText1 = " बुद्ध पौर्णिमा ";
            events.add(cd_4_01);

            CDate cd_4_28 = new CDate();
            Calendar cl_4_28 = Calendar.getInstance();
            cl_4_28.set(2026, 4, 28);
            cd_4_28.HoildayDate = cl_4_28;
            cd_4_28.HolidayText = "28 बकरी ईद (ईद-उल-झुआ)";
            cd_4_28.HolidayText1 = " बकरी ईद (ईद-उल-झुआ) ";
            events.add(cd_4_28);
        }
        //June
        if (month == 5 || month == AllMonth) {
            CDate cd_5_26 = new CDate();
            Calendar cl_5_26 = Calendar.getInstance();
            cl_5_26.set(2026, 5, 26);
            cd_5_26.HoildayDate = cl_5_26;
            cd_5_26.HolidayText = "26 मोहरम";
            cd_5_26.HolidayText1 = " मोहरम ";
            events.add(cd_5_26);
        }
        //July
        if (month == 6 || month == AllMonth) {

        }
        //Aug
        if (month == 7 || month == AllMonth) {
            CDate cd_7_15 = new CDate();
            Calendar cl_7_15 = Calendar.getInstance();
            cl_7_15.set(2026, 7, 15);
            cd_7_15.HoildayDate = cl_7_15;
            cd_7_15.HolidayText = "15 स्वातंत्र्य दिवस";
            cd_7_15.HolidayText1 = " स्वातंत्र्य दिवस";
            events.add(cd_7_15);

            CDate cd_7_16 = new CDate();
            Calendar cl_7_16 = Calendar.getInstance();
            cl_7_16.set(2026, 7, 15);
            cd_7_16.HoildayDate = cl_7_16;
            cd_7_16.HolidayText = "15 पारशी नववर्ष दिन (शहेनशाही)";
            cd_7_16.HolidayText1 = " पारशी नववर्ष दिन (शहेनशाही)";
            events.add(cd_7_16);

            CDate cd_7_26 = new CDate();
            Calendar cl_7_26 = Calendar.getInstance();
            cl_7_26.set(2026, 7, 26);
            cd_7_26.HoildayDate = cl_7_26;
            cd_7_26.HolidayText = "26 ईद-ए-मिलाद";
            cd_7_26.HolidayText1 = " ईद-ए-मिलाद ";
            events.add(cd_7_26);
        }
        //Sept
        if (month == 8 || month == AllMonth) {
            CDate cd_8_14 = new CDate();
            Calendar cl_8_14 = Calendar.getInstance();
            cl_8_14.set(2026, 8, 14);
            cd_8_14.HoildayDate = cl_8_14;
            cd_8_14.HolidayText = "14 गणेशचतुर्थी";
            cd_8_14.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd_8_14);
        }
        //Oct
        if (month == 9 || month == AllMonth) {
            CDate cd_9_2 = new CDate();
            Calendar cl_9_2 = Calendar.getInstance();
            cl_9_2.set(2026, 9, 2);
            cd_9_2.HoildayDate = cl_9_2;
            cd_9_2.HolidayText = "02 महात्मा गांधी जयंती";
            cd_9_2.HolidayText1 = " महात्मा गांधी जयंती ";
            events.add(cd_9_2);

            CDate cd_9_20 = new CDate();
            Calendar cl_9_20 = Calendar.getInstance();
            cl_9_20.set(2026, 9, 20);
            cd_9_20.HoildayDate = cl_9_20;
            cd_9_20.HolidayText = "20 दसरा";
            cd_9_20.HolidayText1 = " दसरा ";
            events.add(cd_9_20);
        }
        //Nov
        if (month == 10 || month == AllMonth) {
            CDate cd_10_08 = new CDate();
            Calendar cl_10_08 = Calendar.getInstance();
            cl_10_08.set(2026, 10, 8);
            cd_10_08.HoildayDate = cl_10_08;
            cd_10_08.HolidayText = "8 लक्ष्मीपूजन ";
            cd_10_08.HolidayText1 = "  लक्ष्मीपूजन ";
            events.add(cd_10_08);

            CDate cd_10_10 = new CDate();
            Calendar cl_10_10 = Calendar.getInstance();
            cl_10_10.set(2026, 10, 10);
            cd_10_10.HoildayDate = cl_10_10;
            cd_10_10.HolidayText = "10 बलिप्रतिपदा";
            cd_10_10.HolidayText1 = " बलिप्रतिपदा ";
            events.add(cd_10_10);

            CDate cd_10_24 = new CDate();
            Calendar cl_10_24 = Calendar.getInstance();
            cl_10_24.set(2026, 10, 24);
            cd_10_24.HoildayDate = cl_10_24;
            cd_10_24.HolidayText = "24 गुरुनानक जयंती";
            cd_10_24.HolidayText1 = " गुरुनानक जयंती ";
            events.add(cd_10_24);
        }
        //Dec
        if (month == 11 || month == AllMonth) {
            CDate cd_11_25 = new CDate();
            Calendar cl_11_25 = Calendar.getInstance();
            cl_11_25.set(2026, 11, 25);
            cd_11_25.HoildayDate = cl_11_25;
            cd_11_25.HolidayText = "25 ख्रिसमस";
            cd_11_25.HolidayText1 = "ख्रिसमस";
            events.add(cd_11_25);
        }
        return events;
    }
}