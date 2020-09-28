package com.example.healthylife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LittleTips extends Activity {
    int randomIndex;
    TextView tv_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_tips);


        tv_ad = findViewById(R.id.tv_sugg);
        randomIndex = (int) (Math.random() * 9);
        randomFill();
    }


    private void randomFill() {
        String ad0 = "研究显示，经过冷藏的水果所含有的营养物质要少于在室温下保存的水果。比如，储存在碗中的番茄和辣椒，要比储存在冰箱里的同类果蔬含有双倍的β胡萝卜素和高达20倍的番茄红素。摄入这两种抗氧化剂，有利于心脏健康状况，能降低某些癌症的发病率。";
        String ad1 = "苹果汁有助于大脑保持年轻状态。每天只需要喝2杯苹果汁就能减少认知障碍症患者大脑中黏性斑块的形成。";
        String ad2 = "对家族成员的健康史有清晰的了解，可以预测自己未来的健康状况，因为多种疾病都与遗传有关，特别是心脏病和癌症。";
        String ad3 = "茶中富含的抗氧化剂(多酚)有助于人体抵御心脏病、癌症和过早老化。适量饮茶的人比不经常饮茶的人要活得长。需要记住的是：喝茶之前要充分搅拌茶水，这种方法能让茶水多释放出15%的抗衰老成分。";
        String ad4 = "缺乏维生素D会增加人们患上许多潜在致命性疾病的风险。建议每天中午前后晒太阳15-30分钟，同时确保自己不会被晒伤，否则健康功效就会前功尽弃。";
        String ad5 = "作为成年人，我们平均每天只笑5~15次，而儿童很容易就达到每天笑100次的数量。笑声可以增强免疫系统的功能，减少压力激素的释放，从而起到抵御癌症的作用。";
        String ad6 = "吸烟不仅会让人的寿命缩短约10年，还会降低老年阶段的生活质量，因为吸烟者更容易患上让身体感觉虚弱的疾病。";
        String ad7 = "体检能够在疾病仍处于可以治疗的阶段及早确诊。无论是涂片检查、血压测试或是乳房X光造影检查，都应引起足够的重视。";
        String ad8 = "将鲜姜片装在小塑料袋内，搭车的时候随时放在鼻孔上面闻，使其辛辣味吸入鼻中。姜能够防备晕车，由于它能够吸取胃酸，制止恶心。";
        String ad9 = "打包食物必须回锅：冰箱温度只能抑制细菌繁殖，不能彻底杀灭它们。故冰箱中食物取出后必须回锅。否则会造成不适，例如痢疾或腹泻";

        String[] suggestions = {ad0, ad1, ad2, ad3, ad4, ad5, ad6, ad7, ad8, ad9,};
        tv_ad.setText(suggestions[randomIndex]);
    }
}
