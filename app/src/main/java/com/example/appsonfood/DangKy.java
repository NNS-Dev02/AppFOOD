package com.example.appsonfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

//        Tạo Tài Khoản
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser=true){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(DangKy.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DangKy.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(DangKy.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText(DangKy.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DangNhap.class);
                startActivity(intent);
            }
        });

    }
}