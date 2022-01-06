package com.xdtpl.helper;

import com.xdtpl.Service.CDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderHelper {

    public  static  final int AllMonth = 15;
    public String GetMonthName(int month){

        String monthName="";
        switch(month)
        {
            case 0:
                monthName ="जानेवारी";
                break;

            case 1:
                monthName ="फेब्रुवारी";
                break;

            case 2:
                monthName ="मार्च";
                break;

            case 3:
                monthName ="एप्रिल";
                break;

            case 4:
                monthName ="मे";
                break;

            case 5:
                monthName ="जून";
                break;

            case 6:
                monthName ="जुलै";
                break;

            case 7:
                monthName ="ऑगस्ट";
                break;

            case 8:
                monthName ="सप्टेंबर";
                break;

            case 9:
                monthName ="ऑक्टोबर";
                break;

            case 10:
                monthName ="नोव्हेंबर";
                break;

            case 11:
                monthName ="डिसेंबर";
                break;
        }
        return  monthName;
    }

    public List<CDate> GetHolidayList(int month) {

        List<CDate> events = new ArrayList<>();
         // jan
        if(month == 0 || month == AllMonth) {
            CDate cd = new CDate();
            Calendar cl = Calendar.getInstance();
            cl.set(2022, 00, 26);
            cd.HoildayDate = cl;
            cd.HolidayText = "\n\n26 प्रजासत्ताक दिन";
            cd.HolidayText1 = " प्रजासत्ताक दिन";
            events.add(cd);
        }
        //Feb
        if(month == 1 || month == AllMonth) {
            CDate cd = new CDate();
            Calendar cl = Calendar.getInstance();
            cl.set(2022, 1, 19);
            cd.HoildayDate = cl;
            cd.HolidayText = "\n19 छत्रपती शिवाजी महाराज जयंती ";
            cd.HolidayText1 = "छत्रपती शिवाजी महाराज जयंती";
            events.add(cd);
        }
        //March
        if(month == 2 || month == AllMonth) {
            CDate cd1 = new CDate();
            Calendar cl1 = Calendar.getInstance();
            cl1.set(2022, 2, 1);
            cd1.HoildayDate = cl1;
            cd1.HolidayText = "\n1 महाशिवरात्री";
            cd1.HolidayText1 = "महाशिवरात्री ";
            events.add(cd1);

            CDate cd2 = new CDate();
            Calendar cl2 = Calendar.getInstance();
            cl2.set(2022, 2, 18);
            cd2.HoildayDate = cl2;
            cd2.HolidayText = "\n18 होळी";
            cd2.HolidayText1 = "होळी (दुसरा दिवस) ";
            events.add(cd2);
        }
        //April
        if(month == 3 || month==AllMonth) {

            CDate cd3 = new CDate();
            Calendar cl3 = Calendar.getInstance();
            cl3.set(2022, 3, 2);
            cd3.HoildayDate = cl3;
            cd3.HolidayText = "\n2 गुढीपाडवा";
            cd3.HolidayText1 = " गुढीपाडवा ";
            events.add(cd3);

            CDate cd33 = new CDate();
            Calendar cl33 = Calendar.getInstance();
            cl33.set(2022, 3, 10);
            cd33.HoildayDate = cl33;
            cd33.HolidayText = "\n10 रामनवमी";
            cd33.HolidayText1 = " रामनवमी ";
            events.add(cd33);

            CDate cd333 = new CDate();
            Calendar cl333 = Calendar.getInstance();
            cl333.set(2022, 3, 14);
            cd333.HoildayDate = cl333;
            cd333.HolidayText = "\n14 महावीर जयंती ";
            cd333.HolidayText1 = " महावीर जयंती ";
            events.add(cd333);

            CDate cd333333 = new CDate();
            Calendar cl333333 = Calendar.getInstance();
            cl333333.set(2022, 3, 14);
            cd333333.HoildayDate = cl333333;
            cd333333.HolidayText = "\n14 डॉ.बाबासाहेब आंबेडकर जयंती ";
            cd333333.HolidayText1 = " डॉ.बाबासाहेब आंबेडकर जयंती ";
            events.add(cd333333);

            CDate cd3333 = new CDate();
            Calendar cl3333 = Calendar.getInstance();
            cl3333.set(2022, 3, 15);
            cd3333.HoildayDate = cl3333;
            cd3333.HolidayText = "\n15 गुड फ्रायडे ";
            cd3333.HolidayText1 = " गुड फ्रायडे ";
            events.add(cd3333);
        }
        // May

        if(month == 4 || month==AllMonth) {
            CDate cd41 = new CDate();
            Calendar cl41 = Calendar.getInstance();
            cl41.set(2022, 4, 1);
            cd41.HoildayDate = cl41;
            cd41.HolidayText = "\n1 महाराष्ट्र दिन ";
            cd41.HolidayText1 = " महाराष्ट्र दिन ";
            events.add(cd41);

            CDate cd5 = new CDate();
            Calendar cl5 = Calendar.getInstance();
            cl5.set(2022, 4, 3);
            cd5.HoildayDate = cl5;
            cd5.HolidayText = "\n3 रमझान ईद ";
            cd5.HolidayText1 = " रमझान ईद ";
            events.add(cd5);

            CDate cd44 = new CDate();
            Calendar cl44 = Calendar.getInstance();
            cl44.set(2022, 4, 16);
            cd44.HoildayDate = cl44;
            cd44.HolidayText = "\n16 बुद्ध पौर्णिमा ";
            cd44.HolidayText1 = " बुद्ध पौर्णिमा ";
            events.add(cd44);


        }
//        //June
//        if(month == 5 || month==AllMonth) {}
//            /*CDate cd5 = new CDate();
//            Calendar cl5 = Calendar.getInstance();
//            cl5.set(2019, 5, 5);
//            cd5.HoildayDate = cl5;
//            cd5.HolidayText = "5 रमझान ईद ";
//            cd5.HolidayText1 = " रमझान ईद ";
//            events.add(cd5);*/
//
        //July
        if(month == 6 || month==AllMonth) {
            CDate cd77 = new CDate();
            Calendar cl77 = Calendar.getInstance();
            cl77.set(2022, 6, 10);
            cd77.HoildayDate = cl77;
            cd77.HolidayText = "\n\n10 बकरी ईद (ईद-उल-झुआ)";
            cd77.HolidayText1 = "बकरी ईद (ईद-उल-झुआ)";
            events.add(cd77);

        }
        //Aug
        if(month == 7 || month==AllMonth) {
            CDate cd77777 = new CDate();
            Calendar cl77777 = Calendar.getInstance();
            cl77777.set(2022, 7, 9);
            cd77777.HoildayDate = cl77777;
            cd77777.HolidayText = "\n9 मोहरम";
            cd77777.HolidayText1 = " मोहरम ";
            events.add(cd77777);

            CDate cd7 = new CDate();
            Calendar cl7 = Calendar.getInstance();
            cl7.set(2022, 7, 15);
            cd7.HoildayDate = cl7;
            cd7.HolidayText = "\n15 स्वातंत्र्य दिवस";
            cd7.HolidayText1 = " स्वातंत्र्य दिवस";
            events.add(cd7);

            CDate cd777 = new CDate();
            Calendar cl777 = Calendar.getInstance();
            cl777.set(2022, 7, 16);
            cd777.HoildayDate = cl777;
            cd777.HolidayText = "\n16 पारशी नववर्ष ";
            cd777.HolidayText1 = " पारशी नववर्ष ";
            events.add(cd777);

            CDate cd7777 = new CDate();
            Calendar cl7777 = Calendar.getInstance();
            cl7777.set(2022, 7, 31);
            cd7777.HoildayDate = cl7777;
            cd7777.HolidayText = "\n31 गणेशचतुर्थी  ";
            cd7777.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd7777);

        }

        //Oct
        if(month == 9 || month==AllMonth) {
            CDate cd91 = new CDate();
            Calendar cl91 = Calendar.getInstance();
            cl91.set(2022, 9, 2);
            cd91.HoildayDate = cl91;
            cd91.HolidayText = "\n\n2 महात्मा गांधी जयंती";
            cd91.HolidayText1 = "महात्मा गांधी जयंती";
            events.add(cd91);

            CDate cd92 = new CDate();
            Calendar cl92 = Calendar.getInstance();
            cl92.set(2022, 9, 9);
            cd92.HoildayDate = cl92;
            cd92.HolidayText = "\n\n9 ईद-ए-मिलाद";
            cd92.HolidayText1 = "ईद-ए-मिलाद";
            events.add(cd92);

            CDate cd99 = new CDate();
            Calendar cl99 = Calendar.getInstance();
            cl99.set(2022, 9, 5);
            cd99.HoildayDate = cl99;
            cd99.HolidayText = "\n\n5 दसरा ";
            cd99.HolidayText1 = " दसरा ";
            events.add(cd99);

            CDate cd9 = new CDate();
            Calendar cl9 = Calendar.getInstance();
            cl9.set(2022, 9, 24);
            cd9.HoildayDate = cl9;
            cd9.HolidayText = "\n\n24 लक्ष्मीपूजन ";
            cd9.HolidayText1 = "  लक्ष्मीपूजन ";
            events.add(cd9);

            CDate cd10 = new CDate();
            Calendar cl10 = Calendar.getInstance();
            cl10.set(2022, 9, 26);
            cd10.HoildayDate = cl10;
            cd10.HolidayText = "\n\n26 बलिप्रतिपदा";
            cd10.HolidayText1 = " बलिप्रतिपदा";
            events.add(cd10);
        }

        //Nov
        if(month == 10 || month==AllMonth) {
            CDate cd100 = new CDate();
            Calendar cl100 = Calendar.getInstance();
            cl100.set(2022, 10, 8);
            cd100.HoildayDate = cl100;
            cd100.HolidayText = "\n8 गुरुनानक जयंती";
            cd100.HolidayText1 = "गुरुनानक जयंती";
            events.add(cd100);
        }
        //Dec

        if(month == 11 || month==AllMonth) {
            CDate cd12 = new CDate();
            Calendar cl12 = Calendar.getInstance();
            cl12.set(2022, 10, 25);
            cd12.HoildayDate = cl12;
            cd12.HolidayText = "\n25 ख्रिसमस";
            cd12.HolidayText1 = "ख्रिसमस";
            events.add(cd12);
        }
        return  events;
    }
}
