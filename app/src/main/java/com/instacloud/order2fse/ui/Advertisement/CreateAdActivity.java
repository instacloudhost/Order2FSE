package com.instacloud.order2fse.ui.Advertisement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.instacloud.order2fse.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CreateAdActivity extends AppCompatActivity {


    String[] AD_TYPE = {"GROUP A", "GROUP B", "GROUP C", "GROUP D"};
    String[] PLAN_TYPE = {"SILVER", "GOLD", "PLATINUM", "DIAMOND"};
    TextView how_many_days;
    TextView total_price,start_date,end_date;
    BetterSpinner adSpinner, plan_type_Spinner;
    Button submit_btn, calculate_btn;
    String totalPrice, adTypeId, planTypeId,price;
    String howManyDays;

    String StartDate,EndDate;
    Date sd,ed;
    long numberOfDays = 0;

    TimeUnit unit;

    public static String SLASH_MM_DD_YYYY = "dd-MM-yyyy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Create Ad");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //EditText
        how_many_days = (TextView) findViewById(R.id.how_many_days);
        //TextView
        total_price = (TextView) findViewById(R.id.total_price);
        start_date = (TextView) findViewById(R.id.start_date);
        end_date = (TextView) findViewById(R.id.end_date);



        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDate(start_date);
            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEndDate(end_date);

            }
        });

        //Button
        submit_btn = (Button) findViewById(R.id.submit_btn);
        calculate_btn = (Button) findViewById(R.id.calculate_btn);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartDate = start_date.getText().toString().trim();
                EndDate = end_date.getText().toString().trim();
                if (StartDate != null && EndDate != null){
                    getNumberOfDays();
                }
                howManyDays = how_many_days.getText().toString().trim();
                int days = Integer.parseInt(howManyDays);
                price = String.valueOf(days * 300);
                total_price.setText("Rs. "+price+"/-");
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    //onNextFailed();
                    Snackbar.make(v, "Please fill above details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }


            }
        });


        //Spinner
        adSpinner = (BetterSpinner) findViewById(R.id.adSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateAdActivity.this, android.R.layout.simple_dropdown_item_1line, AD_TYPE);
        adSpinner.setAdapter(adapter);


        plan_type_Spinner = (BetterSpinner) findViewById(R.id.plan_type_Spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateAdActivity.this, android.R.layout.simple_dropdown_item_1line, PLAN_TYPE);
        plan_type_Spinner.setAdapter(adapter2);
    }


    public Boolean validate() {
        boolean valid = true;
        adTypeId = adSpinner.getText().toString().trim();
        planTypeId = plan_type_Spinner.getText().toString().trim();
        totalPrice = total_price.getText().toString().trim();


        if (howManyDays.trim().isEmpty()) {
            how_many_days.setError("Enter Days");
            valid = false;
        } else {
            how_many_days.setError(null);
        }

        return valid;
    }


    public void getCurrentDate(TextView start_date) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAdActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                start_date.setText(simpleDateFormat.format(calendar.getTime()));
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void getEndDate(TextView end_date) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAdActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                end_date.setText(simpleDateFormat.format(calendar.getTime()));

            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void getNumberOfDays(){



        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {

            sd = dateFormat.parse(StartDate);
            ed = dateFormat.parse(EndDate);
            long difference = Math.abs(sd.getTime() - ed.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            String dayDifference = Long.toString(differenceDates);
            how_many_days.setText(dayDifference);
        } catch (Exception exception) {
            Toast.makeText(this, "Unable to find difference", Toast.LENGTH_SHORT).show();
        }

//        try {
//            sd = dateFormat.parse(StartDate);
//            ed = dateFormat.parse(EndDate);
//            // numberOfDays = getUnitBetweenDates(sd, ed, TimeUnit.DAYS);
//            long timeDiff = ed.getTime() - sd.getTime();
//            numberOfDays = unit.convert(timeDiff, TimeUnit.MILLISECONDS);
//
//            int days = Integer.parseInt(String.valueOf(numberOfDays));
//            String numbers = String.valueOf(days);
//            how_many_days.setText(numbers);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }
}