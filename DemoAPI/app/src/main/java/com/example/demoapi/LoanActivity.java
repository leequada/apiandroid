package com.example.demoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapi.API.API_Implement;
import com.example.demoapi.API.DataService;
import com.example.demoapi.Adapter.AdapterListViewLoan;
import com.example.demoapi.model.MonthLoan;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanActivity extends AppCompatActivity {
    TextView cmonth,cmoney;
    Button btnSubmit;
    ListView listView;
    public static MonthLoan monthLoan;
    AdapterListViewLoan adapterListViewLoan;
    ArrayList<MonthLoan> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        cmoney = findViewById(R.id.money);
        cmonth = findViewById(R.id.month);
        btnSubmit = findViewById(R.id.btnloan);
        listView = findViewById(R.id.listviewloan);


        getData(HomeActivity.idLoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cmoney.setText(list.get(position).getAmmount()+"VNĐ");
                cmonth.setText("Tháng: " +list.get(position).getMonth()+"");
                monthLoan = list.get(position);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = API_Implement.getService();
                Call<JsonObject> callback = dataService.donglai(monthLoan.getId());
                callback.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body() != null){
                            Toast.makeText(LoanActivity.this,"Success", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void getData(int id){
        DataService dataService = API_Implement.getService();
        Call<List<MonthLoan>> callback = dataService.getMonthLoan(id);
        callback.enqueue(new Callback<List<MonthLoan>>() {
            @Override
            public void onResponse(Call<List<MonthLoan>> call, Response<List<MonthLoan>> response) {
                list = (ArrayList<MonthLoan>) response.body();
                adapterListViewLoan = new AdapterListViewLoan(list);
                listView.setAdapter(adapterListViewLoan);
            }

            @Override
            public void onFailure(Call<List<MonthLoan>> call, Throwable t) {

            }
        });
    }
}