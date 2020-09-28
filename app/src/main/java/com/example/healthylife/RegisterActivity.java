package com.example.healthylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class RegisterActivity extends AppCompatActivity {
    EditText idText;
    EditText passwordText;
    EditText mailText;
    RadioGroup radio_Sexual;
    File myFile;
    byte sexual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        idText = findViewById(R.id.register_id);
        passwordText = findViewById(R.id.register_password);
        mailText = findViewById(R.id.email_address);
        radio_Sexual = findViewById(R.id.radioGroup_gender);
    }


    public void onClickClear(View view) {
        //set three EditText to "" ,clear all user input.
        idText.setText("");
        passwordText.setText("");
        mailText.setText("");
    }

    public void onClickSubmit(View view) {
        String state = Environment.getExternalStorageState();
        File file = Environment.getExternalStorageDirectory();
        boolean isComplete = checkIsComplete();
        ObjectOutputStream os = null;

        //Process only isComplete is true;
        if (isComplete) {
            try {
                if (!state.equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(getBaseContext(), "请检查SD卡", Toast.LENGTH_SHORT).show();
                    return;
                }

                myFile = new File(file.getCanonicalPath(), "/UserData.txt");
                os = new ObjectOutputStream(new FileOutputStream(myFile));
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();
                String mailAddress = mailText.getText().toString();
                radio_Sexual.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.checkbox_male) {
                            sexual = 0;
                        } else {
                            sexual = 1;
                        }
                    }
                });
                UserData currentUser = new UserData(id, password, mailAddress, sexual);
                os.writeObject(currentUser);
                Intent finishRegister = new Intent(this, MainActivity.class);
                startActivity(finishRegister);
            } catch (Exception ex) {
                Toast.makeText(this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } else {
            //One or more EditText is empty,appearance a toast.
            Toast.makeText(this, "请完善信息后再次提交", Toast.LENGTH_SHORT).show();
        }
    }

    //This method is used to examine if the user completed the data.
    private boolean checkIsComplete() {
        boolean isComplete;

        //Does the user filled all the Edit?
        isComplete = (!TextUtils.isEmpty(idText.getText())) && (!TextUtils.isEmpty(passwordText.getText())) && (!TextUtils.isEmpty(mailText.getText()));
        return isComplete;
    }
}
