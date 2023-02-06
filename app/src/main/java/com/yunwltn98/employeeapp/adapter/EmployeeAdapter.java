package com.yunwltn98.employeeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yunwltn98.employeeapp.AddActivity;
import com.yunwltn98.employeeapp.EditActivity;
import com.yunwltn98.employeeapp.MainActivity;
import com.yunwltn98.employeeapp.R;
import com.yunwltn98.employeeapp.model.Employee;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    Context context;
    ArrayList<Employee> employeeList;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.txtName.setText(employee.name);
        holder.txtAge.setText("나이 : " + employee.age + "세");
        DecimalFormat decFormat = new DecimalFormat("###,###");
        String strSalary = decFormat.format(employee.salary);
        holder.txtSalary.setText("연봉 : $" + strSalary);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtName;
        TextView txtAge;
        TextView txtSalary;
        ImageView ingDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            ingDelete = itemView.findViewById(R.id.ingDelete);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = getAdapterPosition();
                    Employee employee = employeeList.get(index);

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("employee", employee);
                    context.startActivity(intent);
                }
            });
        }
    }
}
