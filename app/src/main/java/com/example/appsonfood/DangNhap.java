package com.example.appsonfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhap extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        //        Đăng Nhập
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =  username.getText().toString();
                String pass =  password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(DangNhap.this, "Sai thông tin tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}