package com.yunwltn98.employeeapp;

import static com.yunwltn98.employeeapp.AddActivity.SAVE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yunwltn98.employeeapp.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText editAge;
    EditText editSalary;
    Button btnSave;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editAge = findViewById(R.id.editAge);
        editSalary = findViewById(R.id.editSalary);
        btnSave = findViewById(R.id.btnSave);

        employee = (Employee) getIntent().getSerializableExtra("employee");
        editAge.setText(employee.age+"");
        editSalary.setText(employee.salary+"");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strAge = editAge.getText().toString().trim();
                String strSalary = editSalary.getText().toString().trim();

                if (strAge.isEmpty() || strSalary.isEmpty()) {
                    Toast.makeText(EditActivity.this,"모두 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                // 입력한 데이터 저장
                int salary = Integer.valueOf(strSalary).intValue();
                int age = Integer.valueOf(strAge).intValue();
                Employee employee = new Employee(salary, age);

                Intent intent = new Intent();
                intent.putExtra("employee", employee);
                setResult(0, intent);

                // 창 종료 메인으로 돌아가기
                Toast.makeText(EditActivity.this, "직원 정보가 수정되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}