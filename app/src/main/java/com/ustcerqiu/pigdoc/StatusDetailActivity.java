package com.ustcerqiu.pigdoc;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StatusDetailActivity extends BaseClass {
    //属性区域
    mCom.mRateBarData barData;
    List<mCom.mRateBarData> barDataList;

///////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    //定义启动办法,和所需要的数据，方便其它程序转调;
    public static void actionStart(Context context, String titleName){
        Intent intent = new Intent(context, StatusDetailActivity.class);
        intent.putExtra("titleName", titleName); //传入标题，必须
        //intent.putExtra("parcelableItemList", parcelableItemList); //传入对应项的item相关数据
        context.startActivity(intent);
    }

    //定义获取数据的方法
    private List<mCom.mRateBarData> getBarDataList(){
        List<mCom.mRateBarData> dataList = new ArrayList<>();
        mCom.mRateBarData barData;
        int num = 13;
        for(int i=0; i<num+1; i++){
            barData = new mCom.mRateBarData("胎龄"+i+"年", ((float)i)/num, "this is test"  );
            dataList.add(barData);
        }
        return dataList;
    }//getBarDataList


 ///--##############################################################################-----/////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail);

        String titleName = "猪博士";
        //Intent中信息处理
        Intent intent = getIntent(); //getIntent 用以取得启动此活动的intent
        if(intent != null){ //intent中有数据; 如不符合一下数据类型，就直接bug了；
            titleName = intent.getStringExtra("titleName");
        }//if  intent

        //设置标题行的显示标题，替换掉layout中的默认标题
        ((TextView) this.findViewById(R.id.title_name_textView)).setText(titleName);  //this仅仅是强调本活动的调用环境
        //设置返回键的操作
        (findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {  //无需限制view的具体形式，所以没有强制类型转化
            @Override
            public void onClick(View v) {
                //定义返回前的操作，返回数据到前一活动用 setResult方法
                finish(); //
                overridePendingTransition(R.anim.translate_in_back, R.anim.translate_out_back);  //添加返回切换动画
            }
        });


        //处理中间栏目
        barDataList = getBarDataList(); //获取数据
        LinearLayout barGroup = (LinearLayout) findViewById(R.id.collect_bars_linear_layout);
        mCom.insertRateBars(barDataList, barGroup);



    }//onCreate



}//end activity
