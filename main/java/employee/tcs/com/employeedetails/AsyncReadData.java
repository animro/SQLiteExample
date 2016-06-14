package employee.tcs.com.employeedetails;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class AsyncReadData extends AsyncTask<Void,Void,Cursor>{

    DBHelper mHelper;
    Cursor mData;
    Context mContext;
    OnDataReceived mCallback;

    public AsyncReadData(Context mContext,OnDataReceived mCallback,DBHelper mHelper) {
        this.mCallback = mCallback;
        this.mContext = mContext;
        this.mHelper = mHelper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mHelper = DBHelper.getDB(mContext);
    }

    @Override
    protected Cursor doInBackground(Void... params) {
       mData = mHelper.getAllData();
        return mData;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

        if(cursor.getCount() == 0){
            Toast.makeText(mContext,"nothing in list",Toast.LENGTH_SHORT).show();
        }else {
            StringBuffer buffer = new StringBuffer();
            FragmentRead.mList.clear();
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String dept =  cursor.getString(1);
                String extn =  cursor.getString(2);
                String mobile =  cursor.getString(3);
                Employee employee = new Employee(name,dept,extn,mobile);
                FragmentRead.mList.add(employee);
            }
            mCallback.onDataReceived();

        }

    }
}
