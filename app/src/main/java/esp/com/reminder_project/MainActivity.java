package esp.com.reminder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public void create_reminder(View view){
        Intent intent = new Intent(this,Create_reminder.class);
        startActivity(intent);



    }

    public void  view_reminder(View view){
        Intent intent = new Intent(this,Reminder_list.class);
        startActivity(intent);


    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}
