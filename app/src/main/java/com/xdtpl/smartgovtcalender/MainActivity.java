package com.xdtpl.smartgovtcalender;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.xdtpl.Service.CDate;
import com.xdtpl.Service.ValueModel;
import com.xdtpl.helper.CalenderHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class MainActivity extends AppCompatActivity implements WSCallerVersionListener {

    CalenderHelper calHelper;

    com.applandeo.materialcalendarview.CalendarView calendarView;
    DatabaseHelper myDb;
    EditText Id;
    EditText from;
    EditText to;
    EditText TimeFrom;
    EditText TimeTo;
    EditText distance;
    EditText TA, Total, Stay;
    EditText DA;
    Spinner select;
    Button button;
    Button next, back;
    String date;
    TextView holiday, holiday1, holiday2, holiday3, holiday4, Sat;
    TextView DisplayDate;
    DrawerLayout mDrawerLayout;
    String mode[] = {"   ---", "--- ( वाहनाचा  प्रकार निवडा )", "बस", "रेल्वे", "शासकीय वाहन ", "खाजगी वाहन"};
    int currentMonth;
    Calendar calendar;
    //private Calendar _calendar;
    //private int month, year;
    boolean isAddoneMore = false;
    private static final int YOUR_PERMISSION_STATIC_CODE_IDENTIFIER = 100;
    private static boolean activityVisible;
    boolean isForceUpdate = true;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    YOUR_PERMISSION_STATIC_CODE_IDENTIFIER);

            calHelper = new CalenderHelper();
            setContentView(R.layout.activity_main);
            myDb = new DatabaseHelper(this);
            calendar = Calendar.getInstance();
            currentMonth = calendar.get(MONTH);
            //calendar.setFirstDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)-1);

            final Button next = findViewById(R.id.next);
            final Button back = findViewById(R.id.back);
            final Button MainPage = findViewById(R.id.MainPage);
            holiday = findViewById(R.id.holiday);
            holiday1 = findViewById(R.id.holiday1);
            holiday2 = findViewById(R.id.holiday2);
            holiday3 = findViewById(R.id.holiday3);
            holiday4 = findViewById(R.id.holiday4);

            Sat = findViewById((R.id.sat));

            MainPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });

            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

            mDrawerLayout = findViewById(R.id.drawer_layout);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(4).setActionView(R.layout.menu_item); //Adding text at right side in menuItem

            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            if (id == R.id.nav_titlesponsors) {
                                Intent intent = new Intent(MainActivity.this, TitleSponsorActivity.class);
                                startActivity(intent);
                                return true;
                            }else if (id == R.id.nav_groupsponsors) {
                                Intent intent = new Intent(MainActivity.this, SponsorActivity.class);
                                startActivity(intent);
                                return true;
                            }else if (id == R.id.nav_profile) {
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_holiday) {
                                Intent intent = new Intent(MainActivity.this, HolidaysListActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_billgenerate) {
                                try {
                                    Intent intent = new Intent(MainActivity.this, TABillGenerteReports.class);
                                    startActivity(intent);
                                    return true;
                                } catch (Exception ex) {
                                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else if (id == R.id.nav_generate) {
                                try {
                                    Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                                    startActivity(intent);
                                    return true;
                                } catch (Exception ex) {
                                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else if (id == R.id.nav_diary) {
                                Intent intent = new Intent(MainActivity.this, ViewPDFActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_bill) {
                                Intent intent = new Intent(MainActivity.this, ViewTaBillPDFActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_link) {
                                Intent intent = new Intent(MainActivity.this, WebLinkActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_contact) {
                                Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_payment) {
                                Intent intent = new Intent(MainActivity.this, DonationActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_user) {
                                Intent intent = new Intent(MainActivity.this, UserManualActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (id == R.id.nav_share) {
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setType("text/plain");
                                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SMART Govt Calendar");
                                String shareMessage = "\n Download SMART Govt. Calendar App by following link -  \n\n";
                                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.xdtpl.smartgovtcalender";
                                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                                startActivity(Intent.createChooser(shareIntent, "choose one"));
                                return true;
                            }
//                            else if (id == R.id.nav_FreeToPro) {
//                                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//                                try {
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.xdtpl.smartgovtcalenderpro")));
//                                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                                } catch (android.content.ActivityNotFoundException anfe) {
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.xdtpl.smartgovtcalenderpro")));
//                                }
//
//                                return true;
//                            }//
                            else if (id == R.id.nav_impDoc) {
                                Intent intent = new Intent(MainActivity.this, ImpDocFiles.class);
                                startActivity(intent);
                                return true;
                            }
                            else if (id == R.id.nav_medicalbill) {
                                Intent intent = new Intent(MainActivity.this, Medicalbill.class);
                                startActivity(intent);
                                return true;
                            }

                            mDrawerLayout.closeDrawers();

                            return true;
                        }
                    });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (v == next) {
                            currentMonth++;
                            Calendar c = Calendar.getInstance();
                            c.set(MONTH, currentMonth);
                            UpdateButton(c, next, back);
                            com.applandeo.materialcalendarview.CalendarView cView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);
                            PopulateEventsOnCalendar(cView);
                            try {
                                cView.setDate(c);
                            } catch (OutOfDateRangeException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (v == back) {
                        currentMonth--;
                        Calendar c = Calendar.getInstance();
                        c.set(MONTH, currentMonth);
                        UpdateButton(c, next, back);
                        com.applandeo.materialcalendarview.CalendarView cView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);
                        PopulateEventsOnCalendar(cView);

                        try {
                            cView.setDate(c);
                        } catch (OutOfDateRangeException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Bundle extras = this.getIntent().getExtras();
            if (extras != null) {
                Object values = extras.get("value");
                if (values != null) {
                    switch (values.toString()) {
                        case "0":
                            calendar.set(MONTH, 0);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "1":

                            calendar.set(MONTH, 1);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "2":
                            calendar.set(MONTH, 2);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "3":
                            calendar.set(MONTH, 3);
                            currentMonth = calendar.get(MONTH);

                            break;

                        case "4":
                            calendar.set(MONTH, 4);
                            currentMonth = calendar.get(MONTH);

                            break;

                        case "5":
                            calendar.set(MONTH, 5);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "6":
                            calendar.set(MONTH, 6);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "7":
                            calendar.set(MONTH, 7);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "8":
                            calendar.set(MONTH, 8);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "9":
                            calendar.set(MONTH, 9);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "10":
                            calendar.set(MONTH, 10);
                            currentMonth = calendar.get(MONTH);
                            break;

                        case "11":
                            calendar.set(MONTH, 11);
                            currentMonth = calendar.get(MONTH);
                            break;
                    }
                }

            }
            //com.applandeo.materialcalendarview.CalendarView
            calendarView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);

            Calendar cal = Calendar.getInstance();
            cal.set(2021, 11, 31);

            calendarView.setMaximumDate(cal);

            Calendar cal1 = Calendar.getInstance();
            cal1.set(2020, 11, 31);

            calendarView.setMinimumDate(cal1);

            calendar.set(MONTH, currentMonth);
            UpdateButton(calendar, next, back);
            PopulateEventsOnCalendar(calendarView);

            try {
                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }

            //calendarView.setWeekNumberColor(Color.RED);
            final com.applandeo.materialcalendarview.CalendarView finalCalendarView2 = calendarView;
            calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
                @Override
                public void onChange() {
                    currentMonth--;
                    Calendar c = Calendar.getInstance();
                    c.set(MONTH, currentMonth);

                    UpdateButton(c, next, back);
                    PopulateEventsOnCalendar(finalCalendarView2);
                }
            });

            calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
                @Override
                public void onChange() {
                    currentMonth++;
                    Calendar c = Calendar.getInstance();
                    c.set(MONTH, currentMonth);
                    UpdateButton(c, next, back);
                    PopulateEventsOnCalendar(finalCalendarView2);
                }
            });

            calendarView = findViewById(R.id.calendarView);

            /*Locale locale = new Locale("Marathi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            calendarView.getResources().updateConfiguration(config, null);*/
            //calendarView.getResources().getString(R.string.app_name);
            final ArrayList listData = new ArrayList<>();

            final com.applandeo.materialcalendarview.CalendarView finalCalendarView = calendarView;
            final com.applandeo.materialcalendarview.CalendarView finalCalendarView1 = calendarView;

            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    final View mview = getLayoutInflater().inflate(R.layout.dialod_event, null);
                    final String date = eventDay.getCalendar().get(DATE) + "/" + (eventDay.getCalendar().get(MONTH) + 1) + "/" + eventDay.getCalendar().get(YEAR);

                    Calendar clickedDate = Calendar.getInstance();
                    clickedDate.set(eventDay.getCalendar().get(YEAR), eventDay.getCalendar().get(MONTH) + 1, eventDay.getCalendar().get(DATE));
                    Calendar maxDate = Calendar.getInstance();
                    maxDate.set(2021, 12, 31);
                    Calendar minDate = Calendar.getInstance();
                    minDate.set(2021, 1, 1);
                    if (clickedDate.after(maxDate) || clickedDate.before(minDate)) {

                        //Toast.makeText(getBaseContext(), "Do not allowed to enter data.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    // Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();
                    final EditText Id = mview.findViewById(R.id.txtId);
                    final EditText from = mview.findViewById(R.id.txtFrom);
                    final EditText TimeFrom = mview.findViewById(R.id.txtFromTime);
                    final EditText to = mview.findViewById(R.id.txtTo);
                    final EditText TimeTo = mview.findViewById(R.id.txtToTime);
                    final Spinner select = mview.findViewById(R.id.spinner);
                    final EditText TA = mview.findViewById(R.id.txtTA);
                    final EditText DA = mview.findViewById(R.id.txtDA);
                    final EditText purpose = mview.findViewById(R.id.txtPurpose);
                    final EditText distance = mview.findViewById(R.id.txtDistance);
                    final TextView DisplayDate = mview.findViewById(R.id.DisplayDate);
                    final EditText Experiment = mview.findViewById(R.id.exp);
                    // final EditText Total = mview.findViewById(R.id.total);
                    // final EditText Absent = mview.findViewById(R.id.absent);
                    // final EditText Rate = mview.findViewById(R.id.rate);


                    TimeFrom.setInputType(InputType.TYPE_NULL);
                    TimeFrom.setBackgroundColor(Color.TRANSPARENT);
                    TimeTo.setInputType(InputType.TYPE_NULL);
                    TimeTo.setBackgroundColor(Color.TRANSPARENT);
                    //Experiment.setInputType(InputType.TYPE_NULL);
                    ///Experiment.setBackgroundColor(Color.TRANSPARENT);


                    final View mview1 = getLayoutInflater().inflate(R.layout.activity_list, null);


                    final ListView list = mview1.findViewById(R.id.list);

                    DisplayDate.setText(date);

                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, mode);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    select.setAdapter(aa);

                    final ArrayList itemIds = new ArrayList();
                    ArrayList itemTexts = new ArrayList();
                    ArrayList<ValueModel> model = myDb.GetDateData(date, getBaseContext());
                    //  final ValueModel model = myDb.GetDateData(date, getBaseContext());
                    if (model != null && model.size() > 0) {
                        final AlertDialog mBuilder = new AlertDialog.Builder(MainActivity.this)
                                .setView(mview1)
                                .show();

                        try {
                            listData.clear();
                            for (int i = 0; i < model.size(); i++) {
                                itemIds.add(model.get(i).Id);
                                itemTexts.add(model.get(i).Purpose);
                                //listData.add(model.get(i).Id);
                                //listData.add(model.get(i).Purpose);
                                //listData.add(model.get(i).Date);
                          /*  listData.add(model.get(i).DisFrom);
                            listData.add(model.get(i).DisTo);
                            listData.add(model.get(i).FromTime);
                            listData.add(model.get(i).ToTime);
                            listData.add(model.get(i).Distance);
                            listData.add(model.get(i).Mode);
                            listData.add(model.get(i).TA);
                            listData.add(model.get(i).DA);
                            listData.add(model.get(i).Experiment);
                            listData.add(model.get(i).Absent);
                            listData.add(model.get(i).TotalDaysHours);
                            listData.add(model.get(i).Rate);*/
                            }
                            ListAdapter a = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listData);
                            list.setAdapter(a);

                        } catch (Exception ex) {
                            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, itemIds, itemTexts);
                        list.setAdapter(adapter);


                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                mBuilder.dismiss();

                                String value = itemIds.get(position).toString();//list.getAdapter().getItem(position).toString();

                                final ValueModel model = myDb.GetIdData(value, getBaseContext());

                                Id.setText(model.Id);
                                purpose.setText(model.Purpose);
                                from.setText(model.DisFrom);
                                to.setText(model.DisTo);
                                TimeFrom.setText(model.FromTime);
                                TimeTo.setText(model.ToTime);
                                distance.setText(model.Distance);
                                select.setSelection(((ArrayAdapter<String>) select.getAdapter()).getPosition(model.Mode));
                                TA.setText(model.TA);
                                DA.setText(model.DA);
                                Experiment.setText(model.Experiment);

                                //  Rate.setText(model.Rate);
                                TimeFrom.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Calendar mcurrentTime = Calendar.getInstance();
                                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                        int minute = mcurrentTime.get(Calendar.MINUTE);
                                        TimePickerDialog mTimePicker;
                                        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                if (selectedMinute == 0) {
                                                    TimeFrom.setText(selectedHour + ":00");
                                                } else if (selectedMinute < 10) {
                                                    TimeFrom.setText(selectedHour + ":0" + selectedMinute);
                                                } else {
                                                    TimeFrom.setText(selectedHour + ":" + selectedMinute);
                                                }
                                            }
                                        }, hour, minute, true);//Yes 24 hour time
                                        mTimePicker.setTitle("Select Time");
                                        mTimePicker.show();

                                    }
                                });

                                TimeTo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Calendar mcurrentTime = Calendar.getInstance();
                                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                        int minute = mcurrentTime.get(Calendar.MINUTE);
                                        TimePickerDialog mTimePicker;
                                        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                if (selectedMinute == 0) {
                                                    TimeTo.setText(selectedHour + ":00");
                                                } else if (selectedMinute < 10) {
                                                    TimeTo.setText(selectedHour + ":0" + selectedMinute);
                                                } else {
                                                    TimeTo.setText(selectedHour + ":" + selectedMinute);
                                                }
                                            }
                                        }, hour, minute, true);//Yes 24 hour time
                                        mTimePicker.setTitle("Select Time");
                                        mTimePicker.show();

                                    }
                                });

                                final AlertDialog mBuilder = new AlertDialog.Builder(MainActivity.this)

                                        .setPositiveButton("अपडेट आणि सेव ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (isAddoneMore == true) {
                                                    ValueModel vm = new ValueModel();

                                                    vm.Date = date;
                                                    vm.Purpose = purpose.getText().toString();
                                                    vm.DisFrom = from.getText().toString();
                                                    vm.DisTo = to.getText().toString();
                                                    vm.FromTime = TimeFrom.getText().toString();
                                                    vm.ToTime = TimeTo.getText().toString();
                                                    vm.Distance = distance.getText().toString();
                                                    vm.Mode = select.getSelectedItem().toString();
                                                    vm.TA = TA.getText().toString();
                                                    vm.DA = DA.getText().toString();
                                                    vm.Experiment = Experiment.getText().toString();
                                                    //   vm.Rate = Rate.getText().toString();

                                                    listData.add(vm);


                                                    final boolean isInserted = myDb.insertDataList(listData);

                                                    try {
                                                        if (isInserted)
                                                            Toast.makeText(getBaseContext(), " यशस्वीरित्या जतन केले", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                                                    } catch (Exception e) {
                                                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                    }

                                                    PopulateEventsOnCalendar(finalCalendarView1);
                                                    listData.clear();

                                                } else {
                                                    boolean isUpdate = myDb.UpdateData(Id.getText().toString(), date, purpose.getText().toString(), from.getText().toString(), to.getText().toString(), TimeFrom.getText().toString(), TimeTo.getText().toString(), distance.getText().toString(), select.getSelectedItem().toString(), TA.getText().toString(), DA.getText().toString(), Experiment.getText().toString());

                                                    try {
                                                        if (isUpdate == true)
                                                            Toast.makeText(getBaseContext(), " यशस्वीरित्या बदल केले", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                                                    } catch (Exception e) {
                                                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                isAddoneMore = false;
                                            }

                                        })

                                        .setNeutralButton("डिलीट", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                                                dialog1.setTitle("Delete");
                                                dialog1.setMessage("Do you want to delete?");
                                                dialog1.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Integer deleteRows = myDb.DeleteData(Id.getText().toString());
                                                        if (deleteRows > 0)
                                                            Toast.makeText(getBaseContext(), " यशस्वीरित्या हटवले", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                                                        PopulateEventsOnCalendar(finalCalendarView1);

                                                    }
                                                });
                                                dialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                dialog1.show();

                                            }
                                        })

                                        .setNegativeButton(" अॅड वन मोअर", null)
                                        .setView(mview)
                                        .show();

                                Button negativebutton = mBuilder.getButton(AlertDialog.BUTTON_NEGATIVE);
                                negativebutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        from.setText("");
                                        TimeFrom.setText("");
                                        to.setText("");
                                        TimeTo.setText("");
                                        purpose.setText("");
                                        distance.setText("");
                                        TA.setText("");
                                        DA.setText("");
                                        Experiment.setText("");
                                        // Rate.setText("");
                                        isAddoneMore = true;

                                        TimeFrom.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Calendar mcurrentTime = Calendar.getInstance();
                                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                                TimePickerDialog mTimePicker;
                                                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                                    @Override
                                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                        if (selectedMinute == 0) {
                                                            TimeFrom.setText(selectedHour + ":00");
                                                        } else if (selectedMinute < 10) {
                                                            TimeFrom.setText(selectedHour + ":0" + selectedMinute);
                                                        } else {
                                                            TimeFrom.setText(selectedHour + ":" + selectedMinute);
                                                        }
                                                    }
                                                }, hour, minute, true);//Yes 24 hour time
                                                mTimePicker.setTitle("Select Time");
                                                mTimePicker.show();
                                            }
                                        });

                                        TimeTo.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Calendar mcurrentTime = Calendar.getInstance();
                                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                                TimePickerDialog mTimePicker;
                                                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                                    @Override
                                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                        if (selectedMinute == 0) {
                                                            TimeTo.setText(selectedHour + ":00");
                                                        } else if (selectedMinute < 10) {
                                                            TimeTo.setText(selectedHour + ":0" + selectedMinute);
                                                        } else {
                                                            TimeTo.setText(selectedHour + ":" + selectedMinute);
                                                        }
                                                    }
                                                }, hour, minute, true);//Yes 24 hour time
                                                mTimePicker.setTitle("Select Time");
                                                mTimePicker.show();

                                            }
                                        });


                                    }
                                });
                            }
                        });

                    } else {
                        TimeFrom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        if (selectedMinute == 0) {
                                            TimeFrom.setText(selectedHour + ":00");
                                        } else if (selectedMinute < 10) {
                                            TimeFrom.setText(selectedHour + ":0" + selectedMinute);
                                        } else {
                                            TimeFrom.setText(selectedHour + ":" + selectedMinute);
                                        }
                                    }
                                }, hour, minute, true);//Yes 24 hour time
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();

                            }
                        });

                        TimeTo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        if (selectedMinute == 0) {
                                            TimeTo.setText(selectedHour + ":00");
                                        } else if (selectedMinute < 10) {
                                            TimeTo.setText(selectedHour + ":0" + selectedMinute);
                                        } else {
                                            TimeTo.setText(selectedHour + ":" + selectedMinute);
                                        }
                                    }
                                }, hour, minute, true);//Yes 24 hour time
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();


                            }
                        });


                        final AlertDialog mBuilder1 = new AlertDialog.Builder(MainActivity.this)

                                .setPositiveButton(" सेव", null)
                                .setNeutralButton("कॅन्सल", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("अॅड वन मोअर", null)
                                .setView(mview)
                                .show();

                        purpose.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if (TextUtils.isEmpty(s)) {
                                    // Disable ok button
                                    ((AlertDialog) mBuilder1).getButton(
                                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                } else {
                                    // Something into edit text. Enable the button.
                                    ((AlertDialog) mBuilder1).getButton(
                                            AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                }
                            }
                        });
                        purpose.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if (TextUtils.isEmpty(s)) {
                                    // Disable ok button
                                    ((AlertDialog) mBuilder1).getButton(
                                            AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
                                } else {
                                    // Something into edit text. Enable the button.
                                    ((AlertDialog) mBuilder1).getButton(
                                            AlertDialog.BUTTON_NEGATIVE).setEnabled(false); //false for addonemore button disable
                                }
                            }
                        });

                        Button NegativeButton = mBuilder1.getButton(AlertDialog.BUTTON_NEGATIVE);
                        ((AlertDialog) mBuilder1).getButton(AlertDialog.BUTTON_POSITIVE)
                                .setEnabled(false);
                        ((AlertDialog) mBuilder1).getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setEnabled(false);
                        Button positiveButton = mBuilder1.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ValueModel vm = new ValueModel();

                                vm.Date = date;
                                vm.Purpose = purpose.getText().toString();
                                vm.DisFrom = from.getText().toString();
                                vm.DisTo = to.getText().toString();
                                vm.FromTime = TimeFrom.getText().toString();
                                vm.ToTime = TimeTo.getText().toString();
                                vm.Distance = distance.getText().toString();
                                vm.Mode = select.getSelectedItem().toString();
                                vm.TA = TA.getText().toString();
                                vm.DA = DA.getText().toString();
                                vm.Experiment = Experiment.getText().toString();
                                //  vm.Rate = Rate.getText().toString();

                                listData.add(vm);


                                final boolean isInserted = myDb.insertDataList(listData);


                                try {
                                    if (isInserted)
                                        Toast.makeText(getBaseContext(), " यशस्वीरित्या जतन केले", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                //   int validation = Integer.parseInt(TIME24HOURS_PATTERN);
                                // TimeFrom.setInputType(validation);

                                PopulateEventsOnCalendar(finalCalendarView1);
                                listData.clear();

                                ((AlertDialog) mBuilder1).getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setEnabled(false);

                                //after adding one field addonemore button is disable
                                ((AlertDialog) mBuilder1).getButton(
                                        AlertDialog.BUTTON_NEGATIVE).setEnabled(true);

                            }
                        });
                        NegativeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //  SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
                                // java.util.Date d1 =(java.util.Date)format.parse(TimeFrom);

                                //  int i = Integer.parseInt(TimeFrom.getText().toString());
                                //   int j = Integer.parseInt(TimeTo.getText().toString());
                           /* if(TimeFrom.getText().toString().equals(TimeTo.getText().toString()))
                          //  if(i >= j)
                            {
                                Toast.makeText(getBaseContext(),"Time must be different",Toast.LENGTH_LONG).show();
                               // from.setText("Value must be different");
                              //  TimeTo.setText("Value must be different");
                            }*/
                                try {

                                    from.setText("");
                                    TimeFrom.setText("");
                                    to.setText("");
                                    TimeTo.setText("");
                                    purpose.setText("");
                                    distance.setText("");
                                    TA.setText("");
                                    DA.setText("");
                                    Experiment.setText("");

                                    //   Rate.setText("");
                                    ((AlertDialog) mBuilder1).getButton(AlertDialog.BUTTON_NEGATIVE)
                                            .setEnabled(false);
                                    ((AlertDialog) mBuilder1).getButton(AlertDialog.BUTTON_POSITIVE)
                                            .setEnabled(false);

                                } catch (Exception ex) {
                                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                        });
                    }
                }

            });
        } catch (Exception ex) {
            ShowToast(ex.getMessage(), this);
        }


        //App Update Notifivation code
        new GooglePlayStoreAppVersionNameLoader(getApplicationContext(), this).execute();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(MainActivity.this,"resume app.",Toast.LENGTH_SHORT);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // App Update Notification code
    @Override
    public void onGetResponse(boolean isUpdateAvailable) {
        Log.e("ResultAPPMAIN", String.valueOf(isUpdateAvailable));
        /*if (isUpdateAvailable) {
            showUpdateDialog();
        }*/
    }

    /**
     * Method to show update dialog
     */
    /*public void showUpdateDialog() {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Update");
        alertDialogBuilder.setMessage("Your App Update is Available. \nDo you Want to update app?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Update Now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isForceUpdate) {
                    //finish();
                }
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();
    }*/
    private void UpdateButton(Calendar c, Button next1, Button prev1) {

        int Nmonth = ((c.get(MONTH) < 11) ? (c.get(MONTH) + 1) : 0);
        int Pmonth = ((c.get(MONTH) > 0) ? (c.get(MONTH) - 1) : 11);
        if (Pmonth == 11) {
            prev1.setVisibility(View.GONE);
        } else {
            prev1.setVisibility(View.VISIBLE);
            prev1.setText(calHelper.GetMonthName(Pmonth));
        }
        if (Nmonth == 0) {
            next1.setVisibility(View.GONE);
        } else {
            next1.setVisibility(View.VISIBLE);
            next1.setText(calHelper.GetMonthName(Nmonth));
        }

        /*int Nmonth = ((c.get(MONTH) < 11) ? (c.get(MONTH) + 1) : 0);
        int Pmonth = ((c.get(MONTH) > 0) ? (c.get(MONTH) - 1) : 11);
        if (Pmonth == 11) {
            prev1.setVisibility(View.GONE);
        } else {
            prev1.setVisibility(View.VISIBLE);
            prev1.setText(calHelper.GetMonthName(Pmonth));
        }
        if (Nmonth == 0) {
            next1.setVisibility(View.GONE);
        } else {
            next1.setVisibility(View.VISIBLE);
            next1.setText(calHelper.GetMonthName(Nmonth));
        }*/
    }

    private void PopulateEventsOnCalendar(com.applandeo.materialcalendarview.CalendarView calendarView) {

        try {
            List<EventDay> events = new ArrayList<>();

            DateFormat df;
            df = new SimpleDateFormat("dd/MM/yyyy");
            //final ValueModel model =  myDb.GetDateData(date,getBaseContext());
            Cursor data = myDb.GetData();

            while (data.moveToNext()) {
                date = data.getString(1);
                Date newDate = df.parse(date);
                Calendar cl = Calendar.getInstance();
                cl.setTime(newDate);
                int day = cl.get(DATE);
                int month = cl.get(MONTH);
                int year = cl.get(YEAR);
                cl.set(year, month, day);
                events.add(new EventDay(cl, R.drawable.ic_launcher_background));
            }

            List<CDate> holidays = calHelper.GetHolidayList(currentMonth);

            holiday.setText("");
            holiday1.setText("");
            holiday2.setText("");
            holiday3.setText("");
            holiday4.setText("");

            for (int i = 0; i < holidays.size(); i++) {

                events.add(new EventDay(holidays.get(i).HoildayDate, R.drawable.ic_launcher_background_red));

                if (i == 0) {
                    holiday.setText(holidays.get(i).HolidayText);

                } else if (i == 1) {
                    holiday1.setText(holidays.get(i).HolidayText);

                } else if (i == 2) {
                    holiday2.setText(holidays.get(i).HolidayText);

                } else if (i == 3) {
                    holiday3.setText(holidays.get(i).HolidayText);

                }else if (i == 4) {
                    holiday4.setText(holidays.get(i).HolidayText);

                }
            }

            List<Date> disable = new ArrayList<>();

            Calendar cale = Calendar.getInstance();

            int day = cale.get(DATE);
            int mn = cale.get(MONTH);
            int yr = cale.get(YEAR);

            int dw = cale.get(Calendar.DAY_OF_WEEK);
            int d = cale.get(DATE);

            SimpleDateFormat sd = new SimpleDateFormat("EEEE");
            String dwstr = sd.format(cale.getTime());

            cale.set(Calendar.MONTH, currentMonth);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            cale.setMinimalDaysInFirstWeek(2);

            int month = cale.get(Calendar.MONTH);
            int numberOfSat =0;
            do {
                int dayOfWeek = cale.get(Calendar.DAY_OF_WEEK);
                int weekOfMonth = cale.get(Calendar.WEEK_OF_MONTH);
                // if ((dayOfWeek == Calendar.SATURDAY && weekOfMonth == 4) ||
                //   (dayOfWeek == Calendar.SATURDAY && weekOfMonth == 2) ||
                //      dayOfWeek == Calendar.SUNDAY  ) {
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    disable.add(cale.getTime());
                    Calendar eCal = Calendar.getInstance();
                    eCal.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH));
                    events.add(new EventDay(eCal, R.drawable.ic_launcher_background_red));
                    if(dayOfWeek == Calendar.SATURDAY )
                    {
                        numberOfSat++;
                    }
                }
                cale.add(Calendar.DAY_OF_MONTH, 1);
            } while (cale.get(Calendar.MONTH) == month);

            if(numberOfSat == 4)
            {
                Sat.setText("शनिवार , रविवार - सुट्टी");
            }else{
                Sat.setText("शनिवार , रविवार - सुट्टी");
            }
            calendarView.setEvents(events);

        } catch (Exception ex) {
            ShowToast(ex.getMessage(), this);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowToast(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }


}



