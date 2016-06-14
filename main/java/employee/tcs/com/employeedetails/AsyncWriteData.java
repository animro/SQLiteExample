package employee.tcs.com.employeedetails;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class AsyncWriteData extends AsyncTask<Void,Void,Long> {


    Context mContext;
    String name;
    String extn;
    String dept;
    String mobile;
    DBHelper helper;
    ContentValues mCV;

    public AsyncWriteData(Context mContext,ContentValues contentValues,DBHelper helper) {
        this.dept = dept;
        this.extn = extn;
        mCV = contentValues;
        this.helper = helper;
        this.mContext = mContext;
        this.mobile = mobile;
        this.name = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
        Long result = helper.insertData(mCV);

        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);


        helper.close();
        if(aLong == -1){
            Toast.makeText(mContext, "data not inserted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "data inserted", Toast.LENGTH_SHORT).show();
        }
    }
}
