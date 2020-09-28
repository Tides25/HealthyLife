package com.example.healthylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Questionnaire extends AppCompatActivity {
    RadioGroup eat, sleep;

    private RadioButton brakefast1;
    private RadioButton brakefast2;
    private RadioButton brakefast3;
    private RadioButton brakefast4;
    private RadioButton sleep1;
    private RadioButton sleep2;
    private RadioButton sleep3;
    private RadioButton sleep4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        brakefast1 = findViewById(R.id.rb_breakfast1);
        brakefast2 = findViewById(R.id.rb_breakfast2);
        brakefast3 = findViewById(R.id.rb_breakfast3);
        brakefast4 = findViewById(R.id.rb_breakfast4);

        sleep1 = findViewById(R.id.cb_sleep_a);
        sleep2 = findViewById(R.id.cb_sleep_b);
        sleep3 = findViewById(R.id.cb_sleep_c);
        sleep4 = findViewById(R.id.cb_sleep_d);

        eat = findViewById(R.id.rg_breakfast);
        sleep = findViewById(R.id.rg_sleep);
    }
    private void suggestion(int summary){
        Intent bmi_result=new Intent(this,result.class);
        bmi_result.putExtra("advice","建议您做到以下几点： \n①食物多样、谷类为主、粗细搭配；\n②多吃蔬菜、水果和薯类； \n③每天喝奶类、大豆及其制品； \n④经常吃适量鱼、禽、蛋、瘦肉，少吃肥肉和荤油\n" +
                "⑤吃清淡少盐的膳食；\n ⑥进食量与体力活动要平衡，保持适宜的体重； \n⑦三餐分配要合理，零食要适当； \n⑧每天足量饮水，合理选择饮料； \n⑨吃新鲜卫生的食物，成年人每天应摄入250-400g的谷类食物。\n适宜运动指运动方式和运动量适合个人的身体状况，动则有益，贵在坚持。");
        bmi_result.putExtra("score",summary);
        startActivity(bmi_result);
    }

    public void onClickQuit(View view) {
        Questionnaire.this.finish();
    }

    public void examine(View view) {
        int sum=0;

        for (int i = 0; i < eat.getChildCount(); i++) {
            RadioButton radioButton=(RadioButton)eat.getChildAt(i);
            if (radioButton.isChecked()) {
                sum+=(5-i);
            }
        }
        for (int i = 0; i < sleep.getChildCount(); i++) {
            RadioButton radioButton2=(RadioButton)sleep.getChildAt(i);
            if (radioButton2.isChecked()) {
                sum+=(5-i);
            }
        }
        suggestion(sum);
    }
}
