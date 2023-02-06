package com.yunwltn98.employeeapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yunwltn98.employeeapp.adapter.EmployeeAdapter;
import com.yunwltn98.employeeapp.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EmployeeAdapter adapter;
    ArrayList<Employee> employeeList = new ArrayList<>();
    final String URL = "https://block1-image-test.s3.ap-northeast-2.amazonaws.com";

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // 액티비티 실행한 후 이 액티비티로 돌아왔을때 할일
            // Addactibity가 넘겨준 Employee 객체 받아서 리스트에 넣고 화면 갱신
            if (result.getResultCode() == AddActivity.SAVE) {
                Employee employee = (Employee) result.getData().getSerializableExtra("employee");
                employeeList.add(0, employee);
                adapter.notifyDataSetChanged();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // 직원 추가 버튼 클릭
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                launcher.launch(intent);
            }
        });

        // 네트워크 통신해서 데이터 가져오기
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, URL + "/employees.json", null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                // 리스트 데이터 가져오기
                try {
                    JSONArray responseData = response.getJSONArray("data");
                    for ( int i = 0; i < responseData.length(); i++ ) {
                        JSONObject data = responseData.getJSONObject(i);

                        int id = data.getInt("id");
                        String name = data.getString("employee_name");
                        int salary = data.getInt("employee_salary");
                        int age = data.getInt("employee_age");

                        Employee employee = new Employee(id, name, salary, age);
                        employeeList.add(employee);
                    }

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,"에러발생",Toast.LENGTH_SHORT).show();
                }

                adapter = new EmployeeAdapter(MainActivity.this, employeeList);
                recyclerView.setAdapter(adapter);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,"서버에러발생",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // 네트워크로부터 받아오기 전에 프로그래스바 보여주기
        progressBar.setVisibility(View.VISIBLE);
        queue.add(request);
    }
}