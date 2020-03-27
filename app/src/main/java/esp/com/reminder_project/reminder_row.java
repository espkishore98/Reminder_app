package esp.com.reminder_project;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;
import java.util.zip.Checksum;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;


public class reminder_row  extends AppCompatActivity implements View.OnClickListener {
    Db_helper mDatabaseHelper;
    Button buttondate, buttontime,button3,button4;
    EditText remindername,reminderdescription;
    EditText remindertime, reminderdate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int selectedID;
    private String selectedName, selectdescription, selectedtime, selecteddate ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_row);


        buttondate = findViewById(R.id.button_date);
        buttontime = findViewById(R.id.button_time);
        remindername = (EditText) findViewById(R.id.heading);
        reminderdescription = (EditText) findViewById(R.id.Description);
        reminderdate = (EditText) findViewById(R.id.txtDate);
        remindertime = (EditText) findViewById(R.id.txtTime);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        buttondate.setOnClickListener(this);
        buttontime.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        mDatabaseHelper = new Db_helper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        selectdescription = receivedIntent.getStringExtra( "description");

        selectedtime = receivedIntent.getStringExtra("time");

        selecteddate = receivedIntent.getStringExtra("date");


        remindername.setText(selectedName);
        reminderdescription.setText(selectdescription);
        remindertime.setText(selectedtime);
        reminderdate.setText(selecteddate);


    }

    @Override
    public void onClick(View view) {
        if (view == buttondate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {
                            reminderdate.setText(selecteddate);
                        }

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (view == buttontime) {


            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hoursofDay, int minutes) {
                            remindertime.setText(selectedtime);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();


        }
        if (view == remindername) {
            Intent receivedIntent = getIntent();
            selectedName = receivedIntent.getStringExtra("name");
            remindername.setText(selectedName);


        }
        if (view == reminderdescription) {

            reminderdescription.setText(selectdescription);
        }

        if (view == button3) {
            final int id = selectedID;
            final String name = remindername.getText().toString();
            final String desc = reminderdescription.getText().toString();
            final String time = remindertime.getText().toString();
            final String date = reminderdate.getText().toString();
            if (!name.equals("") && desc.equals("") && time.equals("") && date.equals("")) {

                mDatabaseHelper.update(id,name,desc,time,date);
            }
            else {
                toastMessage("You must enter a name");
            }
        }

        if (view== button4){
            mDatabaseHelper.delete(selectedID,selectedName,selectdescription,selecteddate,selectedtime);
            remindername.setText("");
            reminderdescription.setText("");
            reminderdate.setText("");
            remindertime.setText("");

        }




    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}