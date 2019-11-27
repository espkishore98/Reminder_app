package esp.com.reminder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TimePicker;

import java.util.Calendar;


public class Create_reminder extends AppCompatActivity implements View.OnClickListener{
        Button button_date,button_time,create_reminder;
        EditText heading, description;
        EditText txtDate,txtTime;
        private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reminder);

        button_date=(Button)findViewById(R.id.button_date);
        button_time=(Button)findViewById(R.id.button_time);
        heading=(EditText) findViewById(R.id.heading);
        description=(EditText) findViewById(R.id.Description);
        txtDate =(EditText) findViewById(R.id.txtDate);
        txtTime =(EditText) findViewById(R.id.txtTime);

        button_date.setOnClickListener(this);
        button_time.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == button_date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {
                            txtDate.setText(dayofMonth + "-" + (monthofYear + 1) + "-" + year);
                        }

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == button_time) {


                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hoursofDay, int minutes) {
                                txtTime.setText(hoursofDay + ":" + minutes);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        if (view == heading){
            String msg_head = heading.getText().toString();


        }
        if (view == description)
        {
            String msg_description = description.getText().toString();
        }

        if (view == create_reminder){

            Intent intent1 = new Intent(getApplicationContext(),Reminder_list.class);
            startActivity(intent1);




        }


        }



}
