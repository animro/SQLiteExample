package employee.tcs.com.employeedetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns{

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE = "EMPLOYEE";
    public static final String COL1 = "NAME";
    public static final String COL2 = "EXTN";
    public static final String COL3 = "DEPT";
    public static final String COL4 = "MOBILE";



    private DBHelper(Context context) {
        super(context, TABLE, null, 3);
    }

    public void close() {
        try {
            getWritableDatabase().close();
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
    public static DBHelper getDB(Context context){
        DBHelper db = new DBHelper(context);
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + TABLE + "("
                        + _ID + " integer primary key autoincrement, "
                        + COL1 + " text not null ,"
                        + COL2 + " integer ,"
                        + COL3 + " text not null ,"
                        + COL4 + " integer);"
        );

    }

    public SQLiteDatabase getDB() {
        return getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
    public long insertData(ContentValues contentValues){

        SQLiteDatabase db = getDB();

        /*ContentValues mContents = new ContentValues();

        mContents.put(COL1,name);
        mContents.put(COL2,extn);
        mContents.put(COL3, dept);
        mContents.put(COL4, mobile);*/
        long result = db.insert(TABLE,null,contentValues);
        return result;
    }

    public Cursor getAllData (){


        Cursor res = getDB().rawQuery("select * from " + TABLE, null);
        return res;
    }
}
