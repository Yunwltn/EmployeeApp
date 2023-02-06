package com.yunwltn98.employeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yunwltn98.employeeapp.model.Employee;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText editName;
    EditText editAge;
    EditText editSalary;
    Button btnSave;
    public static final int SAVE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("직원 추가");
        // 아래 코드는 백버튼(돌아갈 수 있는 화살표)만 화면에 보여준다
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editSalary = findViewById(R.id.editSalary);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 입력한 데이터 가져오기
                        String name = editName.getText().toString().trim();
                        String strAge = editAge.getText().toString().trim();
                        String strSalary = editSalary.getText().toString().trim();

                        // 데이터 확인
                        if (name.isEmpty() || strAge.isEmpty() || strSalary.isEmpty()) {
                            Toast.makeText(AddActivity.this,"모두 입력해주세요",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 입력한 데이터 저장
                        int salary = Integer.valueOf(strSalary).intValue();
                        int age = Integer.valueOf(strAge).intValue();
                        Employee employee = new Employee(name, salary, age);

                        Intent intent = new Intent();
                        intent.putExtra("employee", employee);
                        setResult(SAVE, intent);

                        // 창 종료 메인으로 돌아가기
                        Toast.makeText(AddActivity.this, "직원 정보가 저장되었습니다", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
            }
        });

    }

    // 액션바의 백버튼을 눌렀을때의 이벤트를 처리하는 함수 오버라이딩하기
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}