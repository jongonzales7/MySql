package com.example.gonzales.mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap postData = new HashMap();
                postData.put("btnLogin", "Login");
                postData.put("mobile", "android");
                postData.put("txtUsername", etUsername.getText().toString());
                postData.put("txtPassword", etPassword.getText().toString() );

                PostResponseAsyncTask loginTask =
                        new PostResponseAsyncTask(MainActivity.this, postData, new AsyncResponse() {
                            @Override
                            public void processFinish(String output) {
                                if(output.equals("success")){
                                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                //Use 10.0.3.2 for GenyMotion
                //Use 10.0.2.2 for Android native Emulator
                //If you're going to use a real Android device, use your laptop's IP Address (type 'ipconfig' in CMD for details)
                loginTask.execute("http://<YOUR_IP_ADDRESS>/android/login.php");
            }
        });
    }

}
