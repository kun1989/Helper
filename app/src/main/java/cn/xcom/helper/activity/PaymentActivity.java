package cn.xcom.helper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/6/4.
 * 支付页
 */
public class PaymentActivity extends BaseActivity implements View.OnClickListener{
    private String TAG="PaymentActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private CheckBox cb_zhifubao,cb_weixin,cb_yinhangka;
    private Button bt_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);
        mContext=this;
        initView();

    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_payment_back);
        rl_back.setOnClickListener(this);
        cb_zhifubao= (CheckBox) findViewById(R.id.cb_payment_zhifubao);
        cb_weixin= (CheckBox) findViewById(R.id.cb_payment_weixin);
        cb_yinhangka= (CheckBox) findViewById(R.id.cb_payment_yinhangka);
        bt_submit= (Button) findViewById(R.id.bt_payment_submit);
        bt_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_payment_back:
                finish();
                break;
            case R.id.bt_payment_submit:
                Toast.makeText(mContext,"未开发",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
