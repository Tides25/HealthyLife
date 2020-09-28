package com.example.healthylife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class resetPassword extends Activity {
    EditText ed_originPassword;
    EditText ed_new1, ed_new2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ed_originPassword = findViewById(R.id.ed_old);
        ed_new1 = findViewById(R.id.ed_new1);
        ed_new2 = findViewById(R.id.ed_new2);
    }

    public void onClickExamine(View view) {
        File file = Environment.getExternalStorageDirectory();
        String input1, input2;
        String original_password;
        String user_inputPassword;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        UserData userData;


        user_inputPassword = ed_originPassword.getText().toString();
        input1 = ed_new1.getText().toString();
        input2 = ed_new2.getText().toString();

        try {
            File dataFile = new File(file.getCanonicalPath(), "/UserData.txt");
            objectInputStream = new ObjectInputStream(new FileInputStream(dataFile));
            //get connection.
            userData = (UserData) objectInputStream.readObject();
            original_password = userData.getPassword();

            if (!original_password.equals(user_inputPassword)) {
                Toast.makeText(this, "原始密码输入错误", Toast.LENGTH_SHORT).show();
            } else if (!input1.equals(input2)) {
                Toast.makeText(this, "两次新密码输入不一致", Toast.LENGTH_SHORT).show();
            } else {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFile));
                userData.resetPassword(input1);
                objectOutputStream.writeObject(userData);
                //reset successful,perform a Toast to user.
                Toast.makeText(this, "密码修改完成，请重新登录", Toast.LENGTH_LONG).show();
            }
        } catch (IOException ioe1) {
            Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException cnf) {
            Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ioe2) {
                ioe2.printStackTrace();
            }
        }
    }
}
