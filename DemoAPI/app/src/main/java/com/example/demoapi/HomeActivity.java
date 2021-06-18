package com.example.demoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.demoapi.API.API_Implement;
import com.example.demoapi.API.DataService;
import com.example.demoapi.Adapter.AdapterListview;
import com.example.demoapi.model.Loan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    AdapterListview adapterListview;
    ArrayList<Loan> arrayList = null;
    public static int idLoan;
    String name;
    Button btn;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listview);
        if(MainActivity.loginDto != null) {
            name = MainActivity.loginDto.getUsername();
            getData(name);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    idLoan = arrayList.get(position).getId();
                    startActivity(new Intent(HomeActivity.this, LoanActivity.class));
                }
            });
        }


    }
    public void getData(String  user){
        DataService dataService = API_Implement.getService();
        Call<List<Loan>> callback = dataService.getUserLoan(user);
        callback.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                arrayList = (ArrayList<Loan>) response.body();
                adapterListview = new AdapterListview(arrayList);
                listView.setAdapter(adapterListview);
            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {

            }
        });

    }
}