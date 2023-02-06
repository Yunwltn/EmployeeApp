package com.yunwltn98.employeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yunwltn98.employeeapp.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText editAge;
    EditText editSalary;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");
        editAge.setText(employee.age+"");
        editSalary.setText(employee.salary+"");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 데이터 저장

                // 창 종료 메인으로 돌아가기
                Toast.makeText(EditActivity.this, "직원 정보가 저장되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}