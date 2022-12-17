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
            cl_0_26.set(2023, 0, 26);
            cd_0_26.HoildayDate = cl_0_26;
            cd_0_26.HolidayText = "\n\n\n26 प्रजासत्ताक दिन";
            cd_0_26.HolidayText1 = " प्रजासत्ताक दिन ";
            events.add(cd_0_26);
        }
        //Feb
        if (month == 1 || month == AllMonth) {
            CDate cd_1_18 = new CDate();
            Calendar cl_1_18 = Calendar.getInstance();
            cl_1_18.set(2023, 1, 18);
            cd_1_18.HoildayDate = cl_1_18;
            cd_1_18.HolidayText = "\n18 महाशिवरात्री";
            cd_1_18.HolidayText1 = " महाशिवरात्री ";
            events.add(cd_1_18);

            CDate cd_1_19 = new CDate();
            Calendar cl_1_19 = Calendar.getInstance();
            cl_1_19.set(2023, 1, 19);
            cd_1_19.HoildayDate = cl_1_19;
            cd_1_19.HolidayText = "\n19 छत्रपती शिवाजी महाराज जयंती";
            cd_1_19.HolidayText1 = " छत्रपती शिवाजी महाराज जयंती ";
            events.add(cd_1_19);
        }
        //March
        if (month == 2 || month == AllMonth) {
            CDate cd_2_7 = new CDate();
            Calendar cl_2_7 = Calendar.getInstance();
            cl_2_7.set(2023, 2, 7);
            cd_2_7.HoildayDate = cl_2_7;
            cd_2_7.HolidayText = "\n07 होळी";
            cd_2_7.HolidayText1 = " होळी (दुसरा दिवस) ";
            events.add(cd_2_7);

            CDate cd_2_22 = new CDate();
            Calendar cl_2_22 = Calendar.getInstance();
            cl_2_22.set(2023, 2, 22);
            cd_2_22.HoildayDate = cl_2_22;
            cd_2_22.HolidayText = "\n22 गुढीपाडवा";
            cd_2_22.HolidayText1 = " गुढीपाडवा ";
            events.add(cd_2_22);

            CDate cd_2_30 = new CDate();
            Calendar cl_2_30 = Calendar.getInstance();
            cl_2_30.set(2023, 2, 30);
            cd_2_30.HoildayDate = cl_2_30;
            cd_2_30.HolidayText = "\n\n30 रामनवमी";
            cd_2_30.HolidayText1 = " रामनवमी ";
            events.add(cd_2_30);
        }
        //April
        if (month == 3 || month == AllMonth) {
            CDate cd_3_4 = new CDate();
            Calendar cl_3_4 = Calendar.getInstance();
            cl_3_4.set(2023, 3, 4);
            cd_3_4.HoildayDate = cl_3_4;
            cd_3_4.HolidayText = "\n04 महावीर जयंती";
            cd_3_4.HolidayText1 = " महावीर जयंती ";
            events.add(cd_3_4);

            CDate cd_3_7 = new CDate();
            Calendar cl_3_7 = Calendar.getInstance();
            cl_3_7.set(2023, 3, 7);
            cd_3_7.HoildayDate = cl_3_7;
            cd_3_7.HolidayText = "\n07 गुड फ्रायडे";
            cd_3_7.HolidayText1 = " गुड फ्रायडे ";
            events.add(cd_3_7);

            CDate cd_3_14 = new CDate();
            Calendar cl_3_14 = Calendar.getInstance();
            cl_3_14.set(2023, 3, 14);
            cd_3_14.HoildayDate = cl_3_14;
            cd_3_14.HolidayText = "\n\n14 डॉ.बाबासाहेब आंबेडकर जयंती";
            cd_3_14.HolidayText1 = " डॉ.बाबासाहेब आंबेडकर जयंती ";
            events.add(cd_3_14);
        }
        // May
        if (month == 4 || month == AllMonth) {
            CDate cd_4_1 = new CDate();
            Calendar cl_4_1 = Calendar.getInstance();
            cl_4_1.set(2023, 4, 1);
            cd_4_1.HoildayDate = cl_4_1;
            cd_4_1.HolidayText = "\n01 महाराष्ट्र दिन";
            cd_4_1.HolidayText1 = " महाराष्ट्र दिन ";
            events.add(cd_4_1);

//            CDate cd5 = new CDate();
//            Calendar cl5 = Calendar.getInstance();
//            cl5.set(2022, 4, 3);
//            cd5.HoildayDate = cl5;
//            cd5.HolidayText = "\n3 रमझान ईद ";
//            cd5.HolidayText1 = " रमझान ईद ";
//            events.add(cd5);

            CDate cd_4_5 = new CDate();
            Calendar cl_4_5 = Calendar.getInstance();
            cl_4_5.set(2023, 4, 5);
            cd_4_5.HoildayDate = cl_4_5;
            cd_4_5.HolidayText = "\n05 बुद्ध पौर्णिमा";
            cd_4_5.HolidayText1 = " बुद्ध पौर्णिमा ";
            events.add(cd_4_5);
        }
        //June
        if (month == 5 || month == AllMonth) {
            CDate cd_5_28 = new CDate();
            Calendar cl_5_28 = Calendar.getInstance();
            cl_5_28.set(2023, 5, 28);
            cd_5_28.HoildayDate = cl_5_28;
            cd_5_28.HolidayText = "\n28 बकरी ईद (ईद-उल-झुआ)";
            cd_5_28.HolidayText1 = "बकरी ईद (ईद-उल-झुआ)";
            events.add(cd_5_28);
        }
        //July
        if (month == 6 || month == AllMonth) {
            CDate cd_6_29 = new CDate();
            Calendar cl_6_29 = Calendar.getInstance();
            cl_6_29.set(2023, 6, 29);
            cd_6_29.HoildayDate = cl_6_29;
            cd_6_29.HolidayText = "\n\n\n29 मोहरम";
            cd_6_29.HolidayText1 = " मोहरम ";
            events.add(cd_6_29);
        }
        //Aug
        if (month == 7 || month == AllMonth) {
            CDate cd_7_15 = new CDate();
            Calendar cl_7_15 = Calendar.getInstance();
            cl_7_15.set(2023, 7, 15);
            cd_7_15.HoildayDate = cl_7_15;
            cd_7_15.HolidayText = "\n15 स्वातंत्र्य दिवस";
            cd_7_15.HolidayText1 = " स्वातंत्र्य दिवस";
            events.add(cd_7_15);

            CDate cd_7_16 = new CDate();
            Calendar cl_7_16 = Calendar.getInstance();
            cl_7_16.set(2023, 7, 16);
            cd_7_16.HoildayDate = cl_7_16;
            cd_7_16.HolidayText = "\n16 पारशी नववर्ष दिन (शहेनशाही)";
            cd_7_16.HolidayText1 = " पारशी नववर्ष दिन (शहेनशाही)";
            events.add(cd_7_16);
        }
        //Sept
        if (month == 8 || month == AllMonth) {
            CDate cd_8_19 = new CDate();
            Calendar cl_8_19 = Calendar.getInstance();
            cl_8_19.set(2023, 8, 19);
            cd_8_19.HoildayDate = cl_8_19;
            cd_8_19.HolidayText = "\n19 गणेशचतुर्थी";
            cd_8_19.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd_8_19);

            CDate cd_8_28 = new CDate();
            Calendar cl_8_28 = Calendar.getInstance();
            cl_8_28.set(2023, 8, 28);
            cd_8_28.HoildayDate = cl_8_28;
            cd_8_28.HolidayText = "\n28 ईद-ए-मिलाद";
            cd_8_28.HolidayText1 = " ईद-ए-मिलाद ";
            events.add(cd_8_28);
        }
        //Oct
        if (month == 9 || month == AllMonth) {
            CDate cd_9_2 = new CDate();
            Calendar cl_9_2 = Calendar.getInstance();
            cl_9_2.set(2023, 9, 2);
            cd_9_2.HoildayDate = cl_9_2;
            cd_9_2.HolidayText = "\n\n\n2 महात्मा गांधी जयंती";
            cd_9_2.HolidayText1 = " महात्मा गांधी जयंती ";
            events.add(cd_9_2);

            CDate cd_9_24 = new CDate();
            Calendar cl_9_28 = Calendar.getInstance();
            cl_9_28.set(2023, 9, 24);
            cd_9_24.HoildayDate = cl_9_28;
            cd_9_24.HolidayText = "\n\n\n24 दसरा";
            cd_9_24.HolidayText1 = " दसरा ";
            events.add(cd_9_24);
        }
        //Nov
        if (month == 10 || month == AllMonth) {
            CDate cd_10_12 = new CDate();
            Calendar cl_10_12 = Calendar.getInstance();
            cl_10_12.set(2023, 10, 12);
            cd_10_12.HoildayDate = cl_10_12;
            cd_10_12.HolidayText = "\n12 लक्ष्मीपूजन ";
            cd_10_12.HolidayText1 = "  लक्ष्मीपूजन ";
            events.add(cd_10_12);

            CDate cd_10_14 = new CDate();
            Calendar cl_10_14 = Calendar.getInstance();
            cl_10_14.set(2023, 10, 14);
            cd_10_14.HoildayDate = cl_10_14;
            cd_10_14.HolidayText = "\n14 बलिप्रतिपदा";
            cd_10_14.HolidayText1 = " बलिप्रतिपदा ";
            events.add(cd_10_14);

            CDate cd_10_27 = new CDate();
            Calendar cl_10_27 = Calendar.getInstance();
            cl_10_27.set(2023, 10, 27);
            cd_10_27.HoildayDate = cl_10_27;
            cd_10_27.HolidayText = "\n\n27 गुरुनानक जयंती";
            cd_10_27.HolidayText1 = " गुरुनानक जयंती ";
            events.add(cd_10_27);
        }
        //Dec
        if (month == 11 || month == AllMonth) {
            CDate cd_11_25 = new CDate();
            Calendar cl_11_25 = Calendar.getInstance();
            cl_11_25.set(2023, 11, 25);
            cd_11_25.HoildayDate = cl_11_25;
            cd_11_25.HolidayText = "\n25 ख्रिसमस";
            cd_11_25.HolidayText1 = "ख्रिसमस";
            events.add(cd_11_25);
        }
        return events;
    }
}
