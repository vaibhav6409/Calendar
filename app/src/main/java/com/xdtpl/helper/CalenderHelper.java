package com.xdtpl.helper;

import com.xdtpl.Service.CDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderHelper {

    public  static  final int AllMonth=15;
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
        if(month == 0 || month==AllMonth) {
            CDate cd = new CDate();
            Calendar cl = Calendar.getInstance();

            cl.set(2021, 0, 26);
            cd.HoildayDate = cl;
            cd.HolidayText = "26 प्रजासत्ताक दिन";
            cd.HolidayText1 = " प्रजासत्ताक दिन";
            events.add(cd);

        }
        //Feb
        if((month == 1) || (month == AllMonth)) {

            CDate cd2 = new CDate();
            Calendar cl2 = Calendar.getInstance();
            cl2.set(2021, 1, 19);
            cd2.HoildayDate = cl2;
            cd2.HolidayText = "19 छत्रपती शिवाजी महाराज जयंती ";
            cd2.HolidayText1 = " छत्रपती  शिवाजी  महाराज जयंती";
            events.add(cd2);

           /* CDate cd1 = new CDate();
            Calendar cl1 = Calendar.getInstance();
            cl1.set(2020, 1, 21);
            cd1.HoildayDate = cl1;
            cd1.HolidayText = "21 महाशिवरात्री";
            cd1.HolidayText1 = "महाशिवरात्री ";
            events.add(cd1);*/


        }
        //March


        if(month == 2 || month==AllMonth) {

            CDate cd1 = new CDate();
            Calendar cl1 = Calendar.getInstance();
            cl1.set(2021, 2, 11);
            cd1.HoildayDate = cl1;
            cd1.HolidayText = "11 महाशिवरात्री";
            cd1.HolidayText1 = "महाशिवरात्री ";
            events.add(cd1);

            CDate cd2 = new CDate();
            Calendar cl2 = Calendar.getInstance();
            cl2.set(2021, 2, 29);
            cd2.HoildayDate = cl2;
            cd2.HolidayText = "29 होळी (दुसरा दिवस) ";
            cd2.HolidayText1 = "होळी (दुसरा दिवस) ";
            events.add(cd2);

            /*CDate cd22 = new CDate();
            Calendar cl22 = Calendar.getInstance();
            cl22.set(2020, 2, 25);
            cd22.HoildayDate = cl22;
            cd22.HolidayText = "25 गुढीपाडवा ";
            cd22.HolidayText1 = " गुढीपाडवा ";
            events.add(cd22);*/
        }
        //April
        if(month == 3 || month==AllMonth) {


            CDate cd3333 = new CDate();
            Calendar cl3333 = Calendar.getInstance();
            cl3333.set(2021, 3, 2);
            cd3333.HoildayDate = cl3333;
            cd3333.HolidayText = "2 गुड फ्रायडे ";
            cd3333.HolidayText1 = " गुड फ्रायडे ";
            events.add(cd3333);

            CDate cd22 = new CDate();
            Calendar cl22 = Calendar.getInstance();
            cl22.set(2021, 3, 13);
            cd22.HoildayDate = cl22;
            cd22.HolidayText = "13 गुढीपाडवा ";
            cd22.HolidayText1 = " गुढीपाडवा ";
            events.add(cd22);

            CDate cd333333 = new CDate();
            Calendar cl333333 = Calendar.getInstance();
            cl333333.set(2021, 3, 14);
            cd333333.HoildayDate = cl333333;
            cd333333.HolidayText = "14 डॉ.बाबासाहेब आंबेडकर जयंती ";
            cd333333.HolidayText1 = " डॉ.बाबासाहेब आंबेडकर जयंती ";
            events.add(cd333333);

            CDate cd3 = new CDate();
            Calendar cl3 = Calendar.getInstance();
            cl3.set(2021, 3, 21);
            cd3.HoildayDate = cl3;
            cd3.HolidayText = "21 राम नवमी ";
            cd3.HolidayText1 = " राम नवमी ";
            events.add(cd3);

            CDate cd333 = new CDate();
            Calendar cl333 = Calendar.getInstance();
            cl333.set(2021, 3, 25);
            cd333.HoildayDate = cl333;
            cd333.HolidayText = "25 महावीर जयंती ";
            cd333.HolidayText1 = " महावीर जयंती ";
            events.add(cd333);

            /*CDate cd33333 = new CDate();
            Calendar cl33333 = Calendar.getInstance();
            cl33333.set(2019, 3, 14);
            cd33333.HoildayDate = cl33333;
            cd33333.HolidayText = "19 गुड फ्रायडे";
            cd33333.HolidayText1 = " गुड फ्रायडे";
            events.add(cd33333);*/
        }
        // May

        if(month == 4 || month==AllMonth) {
            CDate cd4 = new CDate();
            Calendar cl4 = Calendar.getInstance();
            cl4.set(2021, 4, 1);
            cd4.HoildayDate = cl4;
            cd4.HolidayText = "1 महाराष्ट्र दिन";
            cd4.HolidayText1 = " महाराष्ट्र दिन";
            events.add(cd4);

            CDate cd5 = new CDate();
            Calendar cl5 = Calendar.getInstance();
            cl5.set(2021, 4, 13);
            cd5.HoildayDate = cl5;
            cd5.HolidayText = "13 रमझान ईद ";
            cd5.HolidayText1 = " रमझान ईद ";
            events.add(cd5);

            CDate cd44 = new CDate();
            Calendar cl44 = Calendar.getInstance();
            cl44.set(2021, 4, 26);
            cd44.HoildayDate = cl44;
            cd44.HolidayText = "26 बुद्ध पौर्णिमा ";
            cd44.HolidayText1 = " बुद्ध पौर्णिमा ";
            events.add(cd44);


        }
        //June
        if(month == 5 || month==AllMonth) {}
            /*CDate cd5 = new CDate();
            Calendar cl5 = Calendar.getInstance();
            cl5.set(2019, 5, 5);
            cd5.HoildayDate = cl5;
            cd5.HolidayText = "5 रमझान ईद ";
            cd5.HolidayText1 = " रमझान ईद ";
            events.add(cd5);*/

        //July
        if(month == 6 || month==AllMonth) {
            CDate cd77 = new CDate();
            Calendar cl77 = Calendar.getInstance();
            cl77.set(2021, 6, 21);
            cd77.HoildayDate = cl77;
            cd77.HolidayText = "21 बकरी ईद ";
            cd77.HolidayText1 = " बकरी ईद ";
            events.add(cd77);

        }
        //Aug
        if(month == 7 || month==AllMonth) {

            CDate cd7 = new CDate();
            Calendar cl7 = Calendar.getInstance();
            cl7.set(2021, 7, 15);
            cd7.HoildayDate = cl7;
            cd7.HolidayText = "15 स्वातंत्र्य दिवस";
            cd7.HolidayText1 = " स्वातंत्र्य दिवस";
            events.add(cd7);

            CDate cd777 = new CDate();
            Calendar cl777 = Calendar.getInstance();
            cl777.set(2021, 7, 16);
            cd777.HoildayDate = cl777;
            cd777.HolidayText = "16 पारशी नववर्ष ";
            cd777.HolidayText1 = " पारशी नववर्ष ";
            events.add(cd777);

            CDate cd77777 = new CDate();
            Calendar cl77777 = Calendar.getInstance();
            cl77777.set(2021, 7, 19);
            cd77777.HoildayDate = cl77777;
            cd77777.HolidayText = "19 मोहरम";
            cd77777.HolidayText1 = " मोहरम ";
            events.add(cd77777);

            /*CDate cd7777 = new CDate();
            Calendar cl7777 = Calendar.getInstance();
            cl7777.set(2020, 7, 22);
            cd7777.HoildayDate = cl7777;
            cd7777.HolidayText = "22 गणेशचतुर्थी  ";
            cd7777.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd7777);

            */
        }
        //Sept
       /* if(month == 8 || month==AllMonth) {
            CDate cd7777 = new CDate();
            Calendar cl7777 = Calendar.getInstance();
            cl7777.set(2021, 8, 10);
            cd7777.HoildayDate = cl7777;
            cd7777.HolidayText = "10 गणेशचतुर्थी  ";
            cd7777.HolidayText1 = " गणेशचतुर्थी  ";
            events.add(cd7777);

        }
        //Oct
        if(month == 9 || month==AllMonth) {
            CDate cd9 = new CDate();
            Calendar cl9 = Calendar.getInstance();
            cl9.set(2021, 9, 2);
            cd9.HoildayDate = cl9;
            cd9.HolidayText = "2 महात्मा गांधी जयंती ";
            cd9.HolidayText1 = " महात्मा गांधी जयंती ";
            events.add(cd9);

            CDate cd99 = new CDate();
            Calendar cl99 = Calendar.getInstance();
            cl99.set(2021, 9, 15);
            cd99.HoildayDate = cl99;
            cd99.HolidayText = "15 दसरा ";
            cd99.HolidayText1 = " दसरा ";
            events.add(cd99);

            CDate cd10 = new CDate();
            Calendar cl10 = Calendar.getInstance();
            cl10.set(2021, 9, 19);
            cd10.HoildayDate = cl10;
            cd10.HolidayText = " 19 ईद-ए -मिलाद";
            cd10.HolidayText1 = "  ईद-ए -मिलाद";
            events.add(cd10);


           *//* CDate cd99999 = new CDate();
             Calendar cl99999 = Calendar.getInstance();
             cl99999.set(2019, 9, 29);
             cd99999.HoildayDate = cl99999;
             cd99999.HolidayText = "29 भाऊबीज ";
             cd99999.HolidayText1 = " भाऊबीज ";
             events.add(cd99999);*//*
        }
        //Nov
        if(month == 10 || month==AllMonth) {
            CDate cd999 = new CDate();
            Calendar cl999 = Calendar.getInstance();
            cl999.set(2021, 10, 4);
            cd999.HoildayDate = cl999;
            cd999.HolidayText = " 4 लक्ष्मीपूजन ";
            cd999.HolidayText1 = "  लक्ष्मीपूजन ";
            events.add(cd999);

            CDate cd9999 = new CDate();
            Calendar cl9999 = Calendar.getInstance();
            cl9999.set(2021, 10, 5);
            cd9999.HoildayDate = cl9999;
            cd9999.HolidayText = "5 बलिप्रतिपदा";
            cd9999.HolidayText1 = " बलिप्रतिपदा";
            events.add(cd9999);

             *//*CDate cd99999 = new CDate();
             Calendar cl99999 = Calendar.getInstance();
             cl99999.set(2020, 10, 16);
             cd99999.HoildayDate = cl99999;
             cd99999.HolidayText = "16 भाऊबीज ";
             cd99999.HolidayText1 = " भाऊबीज ";
             events.add(cd99999);*//*

            CDate cd100 = new CDate();
            Calendar cl100 = Calendar.getInstance();
            cl100.set(2021, 10, 19);
            cd100.HoildayDate = cl100;
            cd100.HolidayText = " 19 गुरुनानक जयंती";
            cd100.HolidayText1 = "  गुरुनानक जयंती";
            events.add(cd100);
        }
        //Dec
        if(month == 11 || month==AllMonth) {
            CDate cd11 = new CDate();
            Calendar cl11 = Calendar.getInstance();
            cl11.set(2021, 11, 25);
            cd11.HoildayDate = cl11;
            cd11.HolidayText = "25 ख्रिसमस ";
            cd11.HolidayText1 = " ख्रिसमस ";
            events.add(cd11);
        }*/
        return  events;

    }
}
