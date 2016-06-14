package employee.tcs.com.employeedetails;


import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class FragmentAddEmployee extends Fragment {


    //private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText mName;
    EditText mExtn;
    EditText mDept;
    EditText mMobile;
    Button mSave;
    Button mUpdate;
    Context mContext;
    OnCallbackFragment mCallback;
    static DBHelper mHelper;

    private OnFragmentInteractionListener mListener;

    public FragmentAddEmployee() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallback = (OnCallbackFragment)mContext;
        View view  = inflater.inflate(R.layout.fragment_write,container,false);


        mName = (EditText)view.findViewById(R.id.et_write_name);
        mExtn = (EditText)view.findViewById(R.id.et_write_extn);
        mDept = (EditText)view.findViewById(R.id.et_write_dept);
        mMobile = (EditText)view.findViewById(R.id.et_write_mobile);

        mSave = (Button)view.findViewById(R.id.btn_write_save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = mName.getText().toString();
            String extn = mExtn.getText().toString();
            String dept = mDept.getText().toString();
            String mobile = mMobile.getText().toString();

                ContentValues mContents = new ContentValues();

                mContents.put(mHelper.COL1,name);
                mContents.put(mHelper.COL2,extn);
                mContents.put(mHelper.COL3, dept);
                mContents.put(mHelper.COL4, mobile);

                new AsyncWriteData(mContext,mContents,mHelper).execute();
                mCallback.onCallback();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
        mHelper = DBHelper.getDB(mContext);


    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
