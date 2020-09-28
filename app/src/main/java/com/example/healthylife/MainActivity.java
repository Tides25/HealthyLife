package com.example.healthylife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    String identification;
    String password;
    EditText id_input;
    EditText password_input;
    CheckBox rem_pw, auto_login;
    private SharedPreferences sp;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkNeedPermissions();
        checkNeedPermissions();
        id_input = findViewById(R.id.user_id);
        password_input = findViewById(R.id.user_password);
        rem_pw = findViewById(R.id.cb_rememberPassword);
        auto_login = findViewById(R.id.cb_auto);
        btn_login = findViewById(R.id.login);
        sp = this.getSharedPreferences("userInfo", MODE_PRIVATE);

        if (sp.getBoolean("ISCHECK", false)) {
            rem_pw.setChecked(true);
            id_input.setText(sp.getString("USER_NAME", ""));
            password_input.setText(sp.getString("PASSWORD", ""));
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                auto_login.setChecked(true);
                Intent auto_log = new Intent(this, CenterActivity.class);
                startActivity(auto_log);
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickRegister(View view) {
        //Send a intent to launch the Register Application.
        Intent toRegister = new Intent(this, RegisterActivity.class);
        startActivity(toRegister);
    }

    public void onClickLogin(View view) {
        ObjectInputStream ois = null;
        File file = Environment.getExternalStorageDirectory();
        UserData userData;
        boolean inputIncorrect;
        String userNameValue, passwordValue;

        userNameValue = id_input.getText().toString();
        passwordValue = password_input.getText().toString();

        try {
            File dataFile = new File(file.getCanonicalPath(), "/UserData.txt");
            ois = new ObjectInputStream(new FileInputStream(dataFile));
            //get the direction to the path of the user file.

            userData = (UserData) ois.readObject();
            inputIncorrect = examineUserData(userData);
            if (inputIncorrect) {
                //inputIncorrect is true means that user input a wrong id or password.
                Toast.makeText(this, "输入的账号或密码错误", Toast.LENGTH_SHORT).show();
            } else {
                //  TODO: 2019/6/20 send a intent to prime activity to launch it.
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT);

                if (rem_pw.isChecked()) {
                    //remember password&Id.
                    Editor editor = sp.edit();
                    editor.putString("USER_NAME", userNameValue);
                    editor.putString("PASSWORD", passwordValue);
                    editor.commit();
                }

                Intent intent = new Intent(this, CenterActivity.class);
                startActivity(intent);
            }
        } catch (FileNotFoundException fex) {
            Toast.makeText(this, "未能找到本地文件", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                ois.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem_pw.isChecked()) {
                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    System.out.println("记住密码未选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });

        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    System.out.println("自动登录未选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
    }

    public void onClickClear(View view) {
        id_input.setText("");
        password_input.setText("");
    }

    private boolean examineUserData(UserData user) {
        identification = id_input.getText().toString();
        password = password_input.getText().toString();

        if ((identification.equals(user.getIdentification())) && (password.equals(user.getPassword()))) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reset:
                Intent resetPassword = new Intent(this, com.example.healthylife.resetPassword.class);
                startActivity(resetPassword);
                return true;
            case R.id.menu_exit:
                MainActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkNeedPermissions() {
        //6.0以上需要动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }
}
