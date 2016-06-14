package employee.tcs.com.employeedetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {


    private List<Employee> mList;


    public EmployeeAdapter(List<Employee> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_employee,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Employee employee = mList.get(position);
        holder.name.setText(employee.getName());
        holder.dept.setText(employee.getDept());
        holder.extn.setText(employee.getExtn());
        holder.mobile.setText(employee.getMobileno());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, extn, dept, mobile;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_row_name);
            dept = (TextView)itemView.findViewById(R.id.tv_row_dept);
            extn = (TextView)itemView.findViewById(R.id.tv_row_extn);
            mobile = (TextView)itemView.findViewById(R.id.tv_row_mobile);

        }
    }

}
