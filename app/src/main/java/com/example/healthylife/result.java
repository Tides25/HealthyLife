package com.example.healthylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class result extends Activity {
    String advice;
    int bmi_score;
    TextView tv_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tv_advice=findViewById(R.id.tv_advice);

        Intent intent=getIntent();
        advice=intent.getStringExtra("advice");
        bmi_score=intent.getIntExtra("score",0);
        tv_advice.setText("您的健康评分为: "+bmi_score+"/10\n"+advice);
    }
}
