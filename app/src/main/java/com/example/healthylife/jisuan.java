package com.example.healthylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class jisuan extends AppCompatActivity {
    double bmi;
    TextView tv_bmi;
    EditText ed_height;
    EditText ed_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jisuan);
        tv_bmi = findViewById(R.id.bmiNumber);
        ed_height = findViewById(R.id.textView3);
        ed_weight = findViewById(R.id.textView1);
    }

    public void onClickCal(View view) {
        double height;
        double weight;

        height = Double.parseDouble(ed_height.getText().toString());
        weight = Double.parseDouble(ed_weight.getText().toString());

        bmi = (height - 80) * 0.7;

        if (weight > bmi) {
            String result = "您的标准体重为" + bmi + ", 实际体重为" + weight + "，体型偏胖";
            tv_bmi.setText(result);
        } else {
            String result = "您的标准体重为" + bmi + ", 实际体重为" + weight + ",体型偏瘦";
            tv_bmi.setText(result);
        }
    }
}
