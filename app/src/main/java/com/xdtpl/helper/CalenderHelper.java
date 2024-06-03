package com.xdtpl.helper;

import com.xdtpl.Service.CDate;

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
            cl_0_26.set(2024, 0, 26);
            cd_0_26.HoildayDate = cl_0_26;
            cd_0_26.HolidayText = "\n\n\n26 प्रजासत्ताक दिन";
            cd_0_26.HolidayText1 = " प्रजासत्ताक दिन ";
            events.add(cd_0_26);
        }
        //Feb
        if (month == 1 || month == AllMonth) {
            CDate cd_1_19 = new CDate();
            Calendar cl_1_19 = Calendar.getInstance();
            cl_1_19.set(2024, 1, 19);
            cd_1_19.HoildayDate = cl_1_19;
            cd_1_19.HolidayText = "\n\n\n19 छत्रपती शिवाजी महाराज जयंती";
            cd_1_19.HolidayText1 = " छत्रपती शिवाजी महाराज जयंती ";
            events.add(cd_1_19);
        }
        //March
        if (month == 2 || month == AllMonth) {
            CDate cd_2_8 = new CDate();
            Calendar cl_2_8 = Calendar.getInstance();
            cl_2_8.set(2024, 2, 8);
            cd_2_8.HoildayDate = cl_2_8;
            cd_2_8.HolidayText = "\n\n08 महाशिवरात्री";
            cd_2_8.HolidayText1 = " महाशिवरात्री ";
            events.add(cd_2_8);

            CDate cd_2_25 = new CDate();
            Calendar cl_2_25 = Calendar.getInstance();
            cl_2_25.set(2024, 2, 25);
            cd_2_25.HoildayDate = cl_2_25;
            cd_2_25.HolidayText = "\n\n25 होळी";
            cd_2_25.HolidayText1 = " होळी (दुसरा दिवस) ";
            events.add(cd_2_25);

            CDate cd_2_29 = new CDate();
            Calendar cl_2_29 = Calendar.getInstance();
            cl_2_29.set(2024, 2, 29);
            cd_2_29.HoildayDate = cl_2_29;
            cd_2_29.HolidayText = "\n\n29 गुड फ्रायडे";
            cd_2_29.HolidayText1 = " गुड फ्रायडे ";
            events.add(cd_2_29);

        }
        //April
        if (month == 3 || month == AllMonth) {

            CDate cd_3_9 = new CDate();
            Calendar cl_3_9 = Calendar.getInstance();
            cl_3_9.set(2024, 3, 9);
            cd_3_9.HoildayDate = cl_3_9;
            cd_3_9.HolidayText = "\n\n09 गुढीपाडवा";
            cd_3_9.HolidayText1 = " गुढीपाडवा ";
            events.add(cd_3_9);

            CDate cd_3_11 = new CDate();
            Calendar cl_3_11 = Calendar.getInstance();
            cl_3_11.set(2024, 3, 11);
            cd_3_11.HoildayDate = cl_3_11;
            cd_3_11.HolidayText = "\n\n11 रमज़ान ईद";
            cd_3_11.HolidayText1 = " रमज़ान ईद ";
            events.add(cd_3_11);

            CDate cd_3_14 = new CDate();
            Calendar cl_3_14 = Calendar.getInstance();
            cl_3_14.set(2024, 3, 14);
            cd_3_14.HoildayDate = cl_3_14;
            cd_3_14.HolidayText = "\n\n14 डॉ.बाबासाहेब आंबेडकर जयंती";
            cd_3_14.HolidayText1 = " डॉ.बाबासाहेब आंबेडकर जयंती ";
            events.add(cd_3_14);

            CDate cd_3_17 = new CDate();
            Calendar cl_3_17 = Calendar.getInstance();
            cl_3_17.set(2024, 3, 17);
            cd_3_17.HoildayDate = cl_3_17;
            cd_3_17.HolidayText = "\n\n17 रामनवमी";
            cd_3_17.HolidayText1 = " रामनवमी ";
            events.add(cd_3_17);

            CDate cd_3_21 = new CDate();
            Calendar cl_3_21 = Calendar.getInstance();
            cl_3_21.set(2024, 3, 21);
            cd_3_21.HoildayDate = cl_3_21;
            cd_3_21.HolidayText = "\n\n21 महावीर जयंती";
            cd_3_21.HolidayText1 = " महावीर जयंती ";
            events.add(cd_3_21);
        }
        // May
        if (month == 4 || month == AllMonth) {
            CDate cd_4_1 = new CDate();
            Calendar cl_4_1 = Calendar.getInstance();
            cl_4_1.set(2024, 4, 1);
            cd_4_1.HoildayDate = cl_4_1;
            cd_4_1.HolidayText = "\n\n\n01 महाराष्ट्र दिन";
            cd_4_1.HolidayText1 = " महाराष्ट्र दिन ";
            events.add(cd_4_1);

            CDate cd_4_23 = new CDate();
            Calendar cl_4_23 = Calendar.getInstance();
            cl_4_23.set(2024, 4, 23);
            cd_4_23.HoildayDate = cl_4_23;
            cd_4_23.HolidayText = "\n\n\n23 बुद्ध पौर्णिमा";
            cd_4_23.HolidayText1 = " बुद्ध पौर्णिमा ";
            events.add(cd_4_23);
        }
        //June
        if (month == 5 || month == AllMonth) {
            CDate cd_5_17 = new CDate();
            Calendar cl_5_17 = Calendar.getInstance();
            cl_5_17.set(2024, 5, 17);
            cd_5_17.HoildayDate = cl_5_17;
            cd_5_17.HolidayText = "\n\n\n17 बकरी ईद (ईद-उल-झुआ)";
            cd_5_17.HolidayText1 = " बकरी ईद (ईद-उल-झुआ) ";
            events.add(cd_5_17);
        }
        //July
        if (month == 6 || month == AllMonth) {
            CDate cd_6_17 = new CDate();
            Calendar cl_6_17 = Calendar.getInstance();
            cl_6_17.set(2024, 6, 17);
            cd_6_17.HoildayDate = cl_6_17;
            cd_6_17.HolidayText = "\n\n\n17 मोहरम";
            cd_6_17.HolidayText1 = " मोहरम ";
            events.add(cd_6_17);
        }
        //Aug
        if (month == 7 || month == AllMonth) {
            CDate cd_7_15 = new CDate();
            Calendar cl_7_15 = Calendar.getInstance();
            cl_7_15.set(2024, 7, 15);
            cd_7_15.HoildayDate = cl_7_15;
            cd_7_15.HolidayText = "\n\n\n15 स्वातंत्र्य दिवस";
            cd_7_15.HolidayText1 = " स्वातंत्र्य दिवस";
            events.add(cd_7_15);

            CDate cd_7_16 = new CDate();
            Calendar cl_7_16 = Calendar.getInstance();
            cl_7_16.set(2024, 7, 15);
            cd_7_16.HoildayDate = cl_7_16;
            cd_7_16.HolidayText = "\n\n\n15 पारशी नववर्ष दिन (शहेनशाही)";
            cd_7_16.HolidayText1 = " पारशी नववर्ष दिन (शहेनशाही)";
            events.add(cd_7_16);
        }
        //Sept
        if (month == 8 || month == AllMonth) {
            CDate cd_8_7 = new CDate();
            Calendar cl_8_7 = Calendar.getInstance();
            cl_8_7.set(2024, 8, 7);
            cd_8_7.HoildayDate = cl_8_7;
            cd_8_7.HolidayText = "\n\n\n07 गणेशचतुर्थी";
            cd_8_7.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd_8_7);

            CDate cd_8_16 = new CDate();
            Calendar cl_8_16 = Calendar.getInstance();
            cl_8_16.set(2024, 8, 16);
            cd_8_16.HoildayDate = cl_8_16;
            cd_8_16.HolidayText = "\n\n\n16 ईद-ए-मिलाद";
            cd_8_16.HolidayText1 = " ईद-ए-मिलाद ";
            events.add(cd_8_16);
        }
        //Oct
        if (month == 9 || month == AllMonth) {
            CDate cd_9_2 = new CDate();
            Calendar cl_9_2 = Calendar.getInstance();
            cl_9_2.set(2024, 9, 2);
            cd_9_2.HoildayDate = cl_9_2;
            cd_9_2.HolidayText = "\n\n\n02 महात्मा गांधी जयंती";
            cd_9_2.HolidayText1 = " महात्मा गांधी जयंती ";
            events.add(cd_9_2);

            CDate cd_9_12 = new CDate();
            Calendar cl_9_12 = Calendar.getInstance();
            cl_9_12.set(2024, 9, 12);
            cd_9_12.HoildayDate = cl_9_12;
            cd_9_12.HolidayText = "\n\n\n12 दसरा";
            cd_9_12.HolidayText1 = " दसरा ";
            events.add(cd_9_12);
        }
        //Nov
        if (month == 10 || month == AllMonth) {
            CDate cd_10_1 = new CDate();
            Calendar cl_10_1 = Calendar.getInstance();
            cl_10_1.set(2024, 10, 1);
            cd_10_1.HoildayDate = cl_10_1;
            cd_10_1.HolidayText = "\n\n01 लक्ष्मीपूजन ";
            cd_10_1.HolidayText1 = "  लक्ष्मीपूजन ";
            events.add(cd_10_1);

            CDate cd_10_2 = new CDate();
            Calendar cl_10_2 = Calendar.getInstance();
            cl_10_2.set(2024, 10, 2);
            cd_10_2.HoildayDate = cl_10_2;
            cd_10_2.HolidayText = "\n\n02 बलिप्रतिपदा";
            cd_10_2.HolidayText1 = " बलिप्रतिपदा ";
            events.add(cd_10_2);

            CDate cd_10_15 = new CDate();
            Calendar cl_10_15 = Calendar.getInstance();
            cl_10_15.set(2024, 10, 15);
            cd_10_15.HoildayDate = cl_10_15;
            cd_10_15.HolidayText = "\n\n15 गुरुनानक जयंती";
            cd_10_15.HolidayText1 = " गुरुनानक जयंती ";
            events.add(cd_10_15);
        }
        //Dec
        if (month == 11 || month == AllMonth) {
            CDate cd_11_25 = new CDate();
            Calendar cl_11_25 = Calendar.getInstance();
            cl_11_25.set(2024, 11, 25);
            cd_11_25.HoildayDate = cl_11_25;
            cd_11_25.HolidayText = "\n\n\n25 ख्रिसमस";
            cd_11_25.HolidayText1 = "ख्रिसमस";
            events.add(cd_11_25);
        }
        return events;
    }
}