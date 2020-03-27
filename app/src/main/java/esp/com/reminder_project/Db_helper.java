package esp.com.reminder_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class Db_helper extends SQLiteOpenHelper {
    private static SQLiteDatabase database;
    public static final String DATABASE_NAME = "Reminder_db";
    public static final String TABLE_NAME = "Reminder_table";
    public static final String DB_COLUMN_ID = "id";
    public static final String heading = "heading";
    public static final String description = "description";
    public static final String date = "date";
    public static final String time = "time";

    public Db_helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "  +  TABLE_NAME  +  "("+
                DB_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        heading + " TEXT, " +
                        description + " TEXT, " +
                        date + " LONG, " +
                        time + " LONG)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public  boolean addData(String heading, String description, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Db_helper.heading, heading);
        contentValues.put(Db_helper.description, description);
        contentValues.put(Db_helper.date, date);
        contentValues.put(Db_helper.time, time);

        Log.d(TAG, "addData: Adding " + heading + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME  ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor fetch() {
        String[] columns = new String[] { Db_helper.DB_COLUMN_ID , Db_helper.heading, Db_helper.description, Db_helper.date, Db_helper.time };
        Cursor cursor = database.query(Db_helper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + DB_COLUMN_ID + " FROM " + TABLE_NAME +
                " WHERE " + heading + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean update(long id, String heading, String description, String time, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Db_helper.heading, heading);
        contentValues.put(Db_helper.description, description);
        contentValues.put(Db_helper.date, date);
        contentValues.put(Db_helper.time, time);
        return database.update(Db_helper.TABLE_NAME, contentValues, Db_helper.DB_COLUMN_ID + "=" + id, null) > 0;

    }

    public void delete(int _id, String selectedName, String selectdescription, String selecteddate, String selectedtime) {
        database.delete(Db_helper.TABLE_NAME, Db_helper.DB_COLUMN_ID + "=" + _id, null);
    }


}




