package com.shudik.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AgeCalculator extends AppCompatActivity {

    Toolbar toolbar;
    EditText dobEditText;
    DatePickerDialog datePickerDialog;
    Button ageBtn;
    String mBirth="",mToday;
    int currentYear,currentMonth,currentDay,birthDay,birthMonth,birthYear;
    TextView yearTv,montTv,dayTv,ageIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        dobEditText = findViewById(R.id.birthday);
        ageIn = findViewById(R.id.ageIn);
        ageBtn = findViewById(R.id.ageBtn);
        yearTv = findViewById(R.id.yearTv);
        montTv = findViewById(R.id.monthTv);
        dayTv = findViewById(R.id.dayTv);

        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DATE);
        currentMonth = currentMonth+1;
        ageIn.setText("Today");
        yearTv.setText(Integer.toString(currentYear));
        montTv.setText(Integer.toString(currentMonth));
        dayTv.setText(Integer.toString(currentDay));

        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar1 = Calendar.getInstance();
                final int year = calendar1.get(Calendar.YEAR);
                final int month = calendar1.get(Calendar.MONTH);
                final int day = calendar1.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AgeCalculator.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        dobEditText.setText(i2+"/"+i1+"/"+i);
                        mBirth = i2+"/"+i1+"/"+i;
                        birthDay = i2;
                        birthMonth= i1;
                        birthYear = i;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBirth.equals("")) {
                    Toast.makeText(AgeCalculator.this, "Please enter your Date of Birth", Toast.LENGTH_SHORT).show();
                } else {
                    ageIn.setText("Your Age is");
                    calculateAge();
                }
            }
        });

    }


    private void calculateAge() {

        Date now = new Date(currentYear, currentMonth, currentDay);

        Date dob = new Date(birthYear, birthMonth, birthDay);

        if (dob.after(now)) {
            Toast.makeText(this, "Birthday can't in future", Toast.LENGTH_SHORT).show();
//            Toasty.error(MainActivity.this, "Birthday can't in future", Toast.LENGTH_SHORT, true).show();
            return;
        }
        // days of every month
        int month[] = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};

        // if birth date is greater then current birth
        // month then do not count this month and add 30
        // to the date so as to subtract the date and
        // get the remaining days
        if (birthDay > currentDay) {
            currentDay = currentDay + month[birthMonth - 1];
            currentMonth = currentMonth - 1;
        }

        // if birth month exceeds current month, then do
        // not count this year and add 12 to the month so
        // that we can subtract and find out the difference
        if (birthMonth > currentMonth) {
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }

        // calculate date, month, year
        int calculated_date = currentDay - birthDay;
        int calculated_month = currentMonth - birthMonth;
        int calculated_year = currentYear - birthYear;

        dayTv.setText(String.valueOf(calculated_date));
        montTv.setText(String.valueOf(calculated_month));
        yearTv.setText(String.valueOf(calculated_year));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}