package com.example.demoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.demoapi.model.LoginFacebookDto;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demoapi.API.API_Implement;
import com.example.demoapi.API.DataService;
import com.example.demoapi.Adapter.AdapterListview;
import com.example.demoapi.model.Loan;
import com.example.demoapi.model.LoginDto;
import com.example.demoapi.model.MonthLoan;
import com.example.demoapi.model.ResponseData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    EditText user , pass;
    Button login;
    AccessToken accessToken;
    String idFacebook;
    boolean checking;
    public ResponseData responseData;
    public static LoginDto loginDto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.editTextTextPersonName2);
        pass = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.Cardview);
        loginButton = findViewById(R.id.login_button);
        checking = false;
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginDto = null;
                accessToken = AccessToken.getCurrentAccessToken();
                String code = accessToken + "";
                LoginFacebookDto loginFacebookDto = new LoginFacebookDto(idFacebook,code);
                DataService dataService = API_Implement.getService();
                Call<ResponseData> callback = dataService.LoginFacebook(loginFacebookDto);
                callback.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        if(response != null){
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, " Login Cancel",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_LONG).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData(user.getText().toString(),pass.getText().toString());
                if(checking){
                    Toast.makeText(MainActivity.this,"Hello "+user.getText().toString(),Toast.LENGTH_LONG).show();
                    loginDto = new LoginDto(user.getText().toString(),pass.getText().toString());
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else {
                    //Toast.makeText(MainActivity.this,"Sai ten dang nhap hoac mat khau..",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                idFacebook = object.getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();

    }
    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                LoginManager.getInstance().logOut();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    public void getData(String  user, String pass){
        LoginDto lg = new LoginDto(user,pass);
        DataService dataService = API_Implement.getService();
        Call<ResponseData> callback = dataService.checkLogin(lg);
        callback.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.body() != null ){
                    checking = true;
                    Toast.makeText(MainActivity.this,"Call API success..",Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });

    }
}