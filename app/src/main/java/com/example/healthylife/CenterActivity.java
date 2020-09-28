package com.example.healthylife;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class CenterActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);


        RadioGroup mRadioGroup = findViewById(R.id.main_tab);
        mRadioGroup.setOnCheckedChangeListener(this);
        mTabHost = getTabHost();
        initTabs();
    }

    private void initTabs() {
        mTabHost.setup(this.getLocalActivityManager());
        mTabHost.addTab(mTabHost.newTabSpec("tab_cal")
                .setIndicator(getResources().getString(R.string.menu_cal), getResources().getDrawable(R.drawable.calcu_selector))
                .setContent(new Intent(this, jisuan.class)));

        mTabHost.addTab(mTabHost.newTabSpec("tab_question")
                .setIndicator(getResources().getString(R.string.menu_que), getResources().getDrawable(R.drawable.question_selector))
                .setContent(new Intent(this, Questionnaire.class)));

        mTabHost.addTab(mTabHost.newTabSpec("tab_tips")
                .setIndicator(getResources().getString(R.string.menu_tips), getResources().getDrawable(R.drawable.tips_selector))
                .setContent(new Intent(this, LittleTips.class)));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.calculator:
                this.mTabHost.setCurrentTabByTag("tab_cal");
                break;
            case R.id.question:
                this.mTabHost.setCurrentTabByTag("tab_question");
                break;
            case R.id.tips:
                this.mTabHost.setCurrentTabByTag("tab_tips");
                break;
            default:
                break;
        }
    }
}
