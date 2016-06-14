package employee.tcs.com.employeedetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class FragmentRead extends Fragment implements OnDataReceived {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Context mContext;
    DBHelper helper;
    OnFragmentInteractionListener mListener;

    public FragmentRead() {
    }



    public static List<Employee> mList = new ArrayList<Employee>();
    RecyclerView mRecyclerView;
    EmployeeAdapter mAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        helper=DBHelper.getDB(mContext);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_read, container, false);

        // here starting the async task for getting data from the DB
        new AsyncReadData(mContext,this,FragmentAddEmployee.mHelper).execute();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        // recycler view
        mAdapter = new EmployeeAdapter(mList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDataReceived() {
        mAdapter.notifyDataSetChanged();

    }
}
