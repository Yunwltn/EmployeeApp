package com.yunwltn98.employeeapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
        ImageView imgDelete;
        int deleteIndex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = getAdapterPosition();
                    Employee employee = employeeList.get(index);

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("index", index);
                    intent.putExtra("employee", employee);

                    // 메인 액티비티 코드 가져오기
                    ((MainActivity)context).launcher.launch(intent);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 삭제할 인덱스 값
                    deleteIndex = getAdapterPosition();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("직원 정보 삭제");
                    builder.setMessage("정말 삭제하시겠습니까");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 알러트 다이얼로그에서 Yes 눌렀을때 정보를 리스트에서 삭제
                            employeeList.remove(deleteIndex);

                            // 데이터가 변경되었으니 화면 갱신하라고 함수 호출
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("NO", null);
                    builder.show();
                }
            });

        }
    }
}
