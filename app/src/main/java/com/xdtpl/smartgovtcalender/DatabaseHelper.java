package com.xdtpl.smartgovtcalender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.xdtpl.Service.CDate;
import com.xdtpl.Service.Registration;
import com.xdtpl.Service.ValueModel;
import com.xdtpl.helper.CalenderHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Comparator.comparing;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int AllMonth = 15;
    public static final String DATABASE_NAME = "Calender.db";
    public static final String TABLE_NAME = "calender_table";
    public static final String col_1 = "Id";
    public static final String col_2 = "Date";
    public static final String col_3 = "Purpose";
    public static final String col_4 = "DisFrom";
    public static final String col_5 = "DisTo";
    public static final String col_6 = "TimeFrom";
    public static final String col_7 = "TimeTo";
    public static final String col_8 = "Distance";
    public static final String col_9 = "Mode";
    public static final String col_10 = "TA";
    public static final String col_11 = "DA";
    public static final String col_12 = "Experiment";
    //  public static final String col_13 = "Absent";
    // public static final String col_14 = "TotalDaysHours";
    public static final String col_15 = "Rate";
    public static final String col_16 = "Difference";
    public static final String col_17 = "Addition";


    public static final String SECOND_TABLE_NAME = "Registration";
    public static final String col_no_1 = "ID";
    public static final String col_no_2 = "Name";
    public static final String col_no_3 = "WorkPlace";
    public static final String col_no_4 = "Designation";
    public static final String col_no_5 = "MainOffice";
    public static final String col_no_6 = "MainSalary";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT , Date DATE , Purpose TEXT , DisFrom TEXT , DisTo TEXT , TimeFrom TIME  , TimeTo TIME , Distance FLOAT , Mode TEXT , TA FLOAT , DA FLOAT , Experiment FLOAT ,  Rate FLOAT  ,  Difference FLOAT , Addition FLOAT)");
        db.execSQL("create table " + SECOND_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , Designation TEXT , WorkPlace TEXT , MainOffice TEXT , MainSalary TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SECOND_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, String purpose, String from, String to, String fromtime, String totime, String distance, String mode, String ta, String da, String exp, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, date);
        contentValues.put(col_3, purpose);
        contentValues.put(col_4, from);
        contentValues.put(col_5, to);
        contentValues.put(col_6, fromtime);
        contentValues.put(col_7, totime);
        contentValues.put(col_8, distance);
        contentValues.put(col_9, mode);
        contentValues.put(col_10, ta);
        contentValues.put(col_11, da);
        contentValues.put(col_12, exp);
        contentValues.put(col_15, rate);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean SecondinsertData(Registration re) {
        //  Registration re = new Registration();
        Context ctx = null;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //  contentValues.put(col_no_1,re.Id);
        contentValues.put(col_no_2, re.UserName);
        contentValues.put(col_no_3, re.WorkName);
        contentValues.put(col_no_4, re.Designation);
        contentValues.put(col_no_5, re.MainOffice);
        contentValues.put(col_no_6, re.MainSalary);

        try {
            long result = db.insert(SECOND_TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_LONG).show();

        }
        return true;

    }

    public boolean insertDataList(ArrayList datalist) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();
            for (int i = 0; i < datalist.size(); i++) {
                ValueModel vm = ((ValueModel) datalist.get(i));
                ContentValues contentValues = new ContentValues();
                contentValues.put(col_2, vm.Date);
                contentValues.put(col_3, vm.Purpose);
                contentValues.put(col_4, vm.DisFrom);
                contentValues.put(col_5, vm.DisTo);
                contentValues.put(col_6, vm.FromTime);
                contentValues.put(col_7, vm.ToTime);
                contentValues.put(col_8, vm.Distance);
                contentValues.put(col_9, vm.Mode);
                contentValues.put(col_10, vm.TA);
                contentValues.put(col_11, vm.DA);
                contentValues.put(col_12, vm.Experiment);
                contentValues.put(col_15, vm.Rate);
                db.insert(TABLE_NAME, null, contentValues);

            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean UpdateData(String id, String date, String purpose, String from, String to, String timeFrom, String timeTo, String distance, String mode, String ta, String da, String exp) {
        final SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, id);
        contentValues.put(col_2, date);
        contentValues.put(col_3, purpose);
        contentValues.put(col_4, from);
        contentValues.put(col_5, to);
        contentValues.put(col_6, timeFrom);
        contentValues.put(col_7, timeTo);
        contentValues.put(col_8, distance);
        contentValues.put(col_9, mode);
        contentValues.put(col_10, ta);
        contentValues.put(col_11, da);
        contentValues.put(col_12, exp);
        // contentValues.put(col_15,rate);


        db.update(TABLE_NAME, contentValues, "Id = ? ", new String[]{id});
        return true;
    }

    public boolean SecondUpdateData(Registration re, String id) {
        final SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_no_1, re.Id);
        contentValues.put(col_no_2, re.UserName);
        contentValues.put(col_no_3, re.WorkName);
        contentValues.put(col_no_4, re.Designation);
        contentValues.put(col_no_5, re.MainOffice);
        contentValues.put(col_no_6, re.MainSalary);

        db.update(SECOND_TABLE_NAME, contentValues, "ID = ? ", new String[]{id});
        return true;
    }

    public Registration GetRegisterData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " select * from " + SECOND_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        Registration re = new Registration();
        if (data.getCount() > 0) {
            data.moveToFirst();
            re.Id = data.getString(0);
            re.UserName = data.getString(1);
            re.Designation = data.getString(2);
            re.WorkName = data.getString(3);

            re.MainOffice = data.getString(4);
            re.MainSalary = data.getString(5);
            return re;
        }
        return null;
    }

    public Integer DeleteData(String id) {
        final SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Id = ?", new String[]{id});
    }

    public ValueModel GetIdData(String Id, Context ctx) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = " select * from " + TABLE_NAME + " Where Id=" + "'" + Id + "'";
            Cursor data = db.rawQuery(query, null);
            ValueModel vm = new ValueModel();
            data.moveToFirst();

            vm.Id = data.getString(0);
            vm.Date = data.getString(1);
            vm.Purpose = data.getString(2);
            vm.DisFrom = data.getString(3);
            vm.DisTo = data.getString(4);
            vm.FromTime = data.getString(5);
            vm.ToTime = data.getString(6);
            vm.Distance = data.getString(7);
            vm.Mode = data.getString(8);
            vm.TA = data.getString(9);
            vm.DA = data.getString(10);
            vm.Experiment = data.getString(11);
            vm.Rate = data.getString(14);

            return vm;
        } catch (Exception ex) {
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT);
            return null;
        }
    }


    public ArrayList<ValueModel> GetDateData(String date, Context ctx) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = " select * from " + TABLE_NAME + " Where Date=" + "'" + date + "'";
            Cursor data = db.rawQuery(query, null);
            ArrayList<ValueModel> vmList = new ArrayList<ValueModel>();
            if (data.getCount() > 0) {
                data.moveToFirst();
                do {
                    ValueModel vm = new ValueModel();
                    vm.Id = data.getString(0);
                    vm.Date = data.getString(1);
                    vm.Purpose = data.getString(2);
                    vm.DisFrom = data.getString(3);
                    vm.DisTo = data.getString(4);
                    vm.FromTime = data.getString(5);
                    vm.ToTime = data.getString(6);
                    vm.Distance = data.getString(7);
                    vm.Mode = data.getString(8);
                    vm.TA = data.getString(9);
                    vm.DA = data.getString(10);
                    vm.Experiment = data.getString(11);
                    vm.Rate = data.getString(14);

                    vmList.add(vm);
                } while (data.moveToNext());

            }

            return vmList;
        } catch (Exception ex) {
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT);
            return null;
        }
    }


    public Cursor GetData() {
        ValueModel vm = new ValueModel();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " select * from " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public ArrayList<String[]> GetDataList() {

        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " select * from " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);


        while (data.moveToNext()) {
            String[] vm = new String[10];
            vm[0] = data.getString(3);
            vm[1] = data.getString(1);
            vm[2] = data.getString(5);
            //vm.Price = data.getString(4);
            vm[3] = data.getString(4);
            vm[4] = data.getString(1);
            vm[5] = data.getString(6);
            vm[6] = data.getString(7);
            vm[7] = data.getString(8);
            vm[8] = data.getString(2);

            vmList.add(vm);
        }
        return vmList;
    }


    public ArrayList<String[]> ReportDataList(String d1, String d2) {
        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = df.parse(d1);
            Date endDate = df.parse(d2);
            String query1 = " select * from " + TABLE_NAME;
            ArrayList<Date> arrDate = new ArrayList<Date>();

            ArrayList<ValueModel> vmDatabaseList = new ArrayList<>();
            Cursor data = db.rawQuery(query1, null);
            while (data.moveToNext()) {
                Date ActualDate = df.parse(data.getString(1));

                if (ActualDate.equals(startDate) || ActualDate.equals(endDate) || (ActualDate.after(startDate) && ActualDate.before(endDate))) {
                    ValueModel vm = new ValueModel();
                    vm.Id = data.getString(0);
                    vm.Date = data.getString(1);
                    vm.Purpose = data.getString(2);
                    vm.DisFrom = data.getString(3);
                    vm.DisTo = data.getString(4);
                    vm.FromTime = data.getString(5);
                    vm.ToTime = data.getString(6);
                    vm.Distance = data.getString(7);
                    vm.Mode = data.getString(8);
                    vm.TA = data.getString(9);
                    vm.DA = data.getString(10);
                    vm.Experiment = data.getString(11);
                    vm.Rate = data.getString(14);

                    vmDatabaseList.add(vm);
                }
            }

            Collections.sort(vmDatabaseList, new Comparator<ValueModel>() {
                @Override
                public int compare(ValueModel o1, ValueModel o2) {
                    try {
                        Date o1Date = df.parse(o1.Date);
                        Date o2Date = df.parse(o2.Date);
                        return o1Date.compareTo(o2Date);
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });

            int index = 0;
            if(vmDatabaseList.size() == 0)
            {
                //Toast.makeText(get(), "Record not found for selected dates", Toast.LENGTH_LONG).show();
                return vmList;
            }
            ValueModel vmData = vmDatabaseList.get(index);
            Date dbDate = df.parse(vmData.Date);

            CalenderHelper calHelper = new CalenderHelper();
            List<CDate> holidays = calHelper.GetHolidayList(AllMonth);

            for (Date curDate = startDate; endDate.after(curDate) || endDate.equals(curDate); curDate = addDays(curDate, 1)) {

                if (curDate.equals(dbDate)) {
                    do {
                        String[] vm = new String[10];
                        vm[0] = df.format(dbDate);
                        vm[1] = vmData.FromTime;
                        vm[2] = vmData.ToTime;
                        vm[3] = vmData.DisFrom;
                        vm[4] = vmData.DisTo;
                        vm[5] = vmData.Mode;
                        vm[6] = vmData.Distance;
                        vm[7] = vmData.Purpose;

                        vmList.add(vm);
                        index++;
                        if (vmDatabaseList.size() == index)
                            break;
                        vmData = vmDatabaseList.get(index);
                        dbDate = df.parse(vmData.Date);
                    } while (dbDate.equals(curDate));
                } else {
                    boolean foundInHoliday = false;
                    for (int i = 0; i < holidays.size(); i++) {

                        Calendar hDate = Calendar.getInstance();
                        int year = holidays.get(i).HoildayDate.get(Calendar.YEAR);
                        int month = holidays.get(i).HoildayDate.get(Calendar.MONTH);
                        int day = holidays.get(i).HoildayDate.get(Calendar.DAY_OF_MONTH);
                        hDate.set(year, month, day);

                        Calendar cDate = Calendar.getInstance();
                        cDate.setTime(curDate);
                        System.out.print(hDate.getTime() + "-" + cDate.getTime());

                        if (getDateWithoutTimeUsingCalendar(hDate).equals(getDateWithoutTimeUsingCalendar(cDate))) {
                            String[] vm = new String[10];
                            vm[0] = df.format(curDate);
                            vm[1] = "---";
                            vm[2] = "---";
                            vm[3] = "---";
                            vm[4] = "---";
                            vm[5] = "---";
                            vm[6] = "---";
                            //vm[4] = df.format(curDate);
                            vm[7] = holidays.get(i).HolidayText1;
                            vmList.add(vm);
                            foundInHoliday = true;
                            break;
                        }
                    }

                    if (foundInHoliday) {
                        continue;
                    } else {
                        Calendar cale = Calendar.getInstance();
                        cale.setTime(curDate);
                        int dayOfWeek = cale.get(Calendar.DAY_OF_WEEK);
                        int weekOfMonth = cale.get(Calendar.WEEK_OF_MONTH);


                        if ((dayOfWeek == Calendar.SATURDAY && weekOfMonth == 2)) {
                            String[] vm = new String[10];

                            vm[0] = df.format(curDate);
                            vm[1] = "---";
                            vm[2] = "---";
                            vm[3] = "---";
                            vm[4] = "---";
                            vm[5] = "---";
                            vm[6] = "---";
                            //vm[4] = df.format(curDate);
                            vm[7] = "दुसरा शनिवार";
                            vmList.add(vm);
                        } else if ((dayOfWeek == Calendar.SATURDAY && weekOfMonth == 4)) {
                            String[] vm = new String[10];

                            vm[0] = df.format(curDate);
                            vm[1] = "---";
                            vm[2] = "---";
                            vm[3] = "---";
                            vm[4] = "---";
                            vm[5] = "---";
                            vm[6] = "---";
                            // vm[4] = df.format(curDate);
                            vm[7] = " चौथा शनिवार";
                            vmList.add(vm);
                        } else if ((dayOfWeek == Calendar.SUNDAY)) {
                            String[] vm = new String[10];

                            vm[0] = df.format(curDate);
                            vm[1] = "---";
                            vm[2] = "---";
                            vm[3] = "---";
                            vm[4] = "---";
                            vm[5] = "---";
                            vm[6] = "---";
                            //vm[4] = df.format(curDate);
                            vm[7] = " रविवार";
                            vmList.add(vm);
                        } else {
                            String[] vm = new String[10];
                            vm[0] = df.format(curDate);
                            vm[1] = "---";
                            vm[2] = "---";
                            vm[3] = "---";
                            vm[4] = "---";
                            vm[5] = "---";
                            vm[6] = "---";
                            // vm[4] = df.format(curDate);
                            vm[7] = "कार्यालयीन कामकाज";

                            vmList.add(vm);
                        }

                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vmList;
    }

    public static Date getDateWithoutTimeUsingCalendar(Calendar calendar) {
        //Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }


    public ArrayList<String[]> TABillDataList2(String d1, String d2) {
        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat df ,df2;
        df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = df.parse(d1);
            Date endDate = df.parse(d2);
            String query1 = " select * from " + TABLE_NAME;
            ArrayList<Date> arrDate = new ArrayList<Date>();

            ArrayList<ValueModel> vmDatabaseList = new ArrayList<>();
            Cursor data = db.rawQuery(query1, null);
            while (data.moveToNext()) {
                Date ActualDate = df.parse(data.getString(1));

                df2 = new SimpleDateFormat("dd/MM/yy");
                Date d = df2.parse(data.getString(1));

                if (ActualDate.equals(startDate) || ActualDate.equals(endDate) || (ActualDate.after(startDate) && ActualDate.before(endDate))) {

                    if ((data.getString(9) != null && !data.getString(9).trim().equals("")) && (data.getString(10) != null && !data.getString(10).trim().equals(""))) {

                        ValueModel vm = new ValueModel();
                        vm.Id = data.getString(0);
                        vm.Date = df2.format(d);
                        vm.Purpose = data.getString(2);
                        vm.DisFrom = data.getString(3);
                        vm.DisTo = data.getString(4);
                        vm.FromTime = data.getString(5);
                        vm.ToTime = data.getString(6);
                        vm.Distance = data.getString(7);
                        vm.Mode = data.getString(8);
                        vm.TA = data.getString(9);
                        vm.DA = data.getString(10);
                        vm.Experiment = data.getString(11);
                        vm.Rate = data.getString(14);

                        vmDatabaseList.add(vm);
                    }
                }
            }

            Collections.sort(vmDatabaseList, new Comparator<ValueModel>() {
                @Override
                public int compare(ValueModel o1, ValueModel o2) {
                    try {
                        Date o1Date = df.parse(o1.Date);
                        Date o2Date = df.parse(o2.Date);
                        return o1Date.compareTo(o2Date);
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });

            for (int index = 0; index < vmDatabaseList.size(); index++) {
                ValueModel vmData = vmDatabaseList.get(index);


                    String[] vm = new String[15];

                    vm[0] = vmData.DisFrom;
                    vm[1] = vmData.Date;
                    vm[2] = vmData.FromTime;
                    vm[3] = vmData.DisTo;
                    vm[4] =vmData.Date;
                    vm[5] = vmData.ToTime;
                    vm[6] = vmData.Distance;
                    vm[7] = vmData.Mode;
                    vm[9] = vmData.TA;
                    vm[10] = vmData.Experiment;
                    vm[12] = vmData.Experiment;

                    vmList.add(vm);
                }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vmList;
    }

    public ArrayList<String[]> TABillDataList3(String d1, String d2) {
        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = df.parse(d1);
            Date endDate = df.parse(d2);
            String query1 = " select * from " + TABLE_NAME;
            //  ArrayList<Date> arrDate = new ArrayList<Date>();

            ArrayList<ValueModel> vmDatabaseList = new ArrayList<>();
            Cursor data = db.rawQuery(query1, null);
            while (data.moveToNext()) {
                Date ActualDate = df.parse(data.getString(1));

                if (ActualDate.equals(startDate) || ActualDate.equals(endDate) || (ActualDate.after(startDate) && ActualDate.before(endDate))) {
                    if ((data.getString(9) != null && !data.getString(9).trim().equals("")) && (data.getString(10) != null && !data.getString(10).trim().equals(""))) {

                        ValueModel vm = new ValueModel();

                        vm.Id = data.getString(0);
                        vm.Date = data.getString(1);
                        vm.Purpose = data.getString(2);
                        vm.TA = data.getString(9);
                        vm.DA = data.getString(10);
                        vm.Rate = data.getString(14);

                        vmDatabaseList.add(vm);
                    }
                }
            }

            Collections.sort(vmDatabaseList, new Comparator<ValueModel>() {
                @Override
                public int compare(ValueModel o1, ValueModel o2) {
                    try {
                        Date o1Date = df.parse(o1.Date);
                        Date o2Date = df.parse(o2.Date);
                        return o1Date.compareTo(o2Date);
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });


            for (int index = 0; index < vmDatabaseList.size(); index++) {
                ValueModel vmData = vmDatabaseList.get(index);
                        String[] vm = new String[500];

                        vm[0] = vmData.DA;
                        // vm[4] = data1.getString(15);
                        vm[5] = vmData.TA;
                        vm[6] = vmData.Purpose;

                        vmList.add(vm);
                }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vmList;
    }

  /*  public ArrayList<String[]> TABillDataList2(String startDate, String endDate) {

        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate1 = df.parse(startDate);
            Date endDate1 = df.parse(endDate);


            String query1 = " select * from " + TABLE_NAME;
            Cursor data1 = db.rawQuery(query1, null);
            while (data1.moveToNext()) {
                String[] vm = new String[20];
                String date = data1.getString(1);
                try {
                    Date newDate = df.parse(date);

                    if (newDate.equals(startDate1) || newDate.equals(endDate1) || newDate.after(startDate1) && newDate.before(endDate1)) {
                        vm[0] = data1.getString(3);
                        vm[1] = data1.getString(1);
                        vm[2] = data1.getString(5);
                        vm[3] = data1.getString(4);
                        vm[4] = data1.getString(1);
                        vm[5] = data1.getString(6);
                        vm[6] = data1.getString(7);
                        vm[7] = data1.getString(8);
                        vm[9] = data1.getString(9);
                        vm[10] = data1.getString(11);
                        vm[12] = data1.getString(13);
                        vm[13] = data1.getString(14);


                        vmList.add(vm);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vmList;
    }*/


   /* public ArrayList<String[]> TABillDataList3(String startDate, String endDate) {
        ArrayList<String[]> vmList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate1 = df.parse(startDate);
            Date endDate1 = df.parse(endDate);


            String query1 = " select * from " + TABLE_NAME;
            Cursor data1 = db.rawQuery(query1, null);
            while (data1.moveToNext()) {
                String[] vm = new String[25];
                String date = data1.getString(1);
                try {
                    Date newDate = df.parse(date);

                    if (newDate.equals(startDate1) || newDate.equals(endDate1) || newDate.after(startDate1) && newDate.before(endDate1)) {
                        vm[0] = data1.getString(10);
                        // vm[4] = data1.getString(15);
                        vm[5] = data1.getString(9);
                        vm[6] = data1.getString(2);
                        vmList.add(vm);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vmList;
    }*/


    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }


}


