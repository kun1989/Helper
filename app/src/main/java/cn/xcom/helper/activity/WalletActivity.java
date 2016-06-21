package cn.xcom.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/6/12.
 * 钱包页
 */
public class WalletActivity extends BaseActivity implements View.OnClickListener {
    private String TAG="WalletActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private TextView tv_momeny,tv_month_singular,tv_month_income,tv_all_singular,tv_all_income;
    private Button bt_present;
    private LinearLayout ll_present,ll_income;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wallet);
        mContext=this;
        initView();
    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_wallet_back);
        rl_back.setOnClickListener(this);
        tv_momeny= (TextView) findViewById(R.id.tv_wallet_money);
        tv_month_singular= (TextView) findViewById(R.id.tv_wallet_month_singular_number);
        tv_month_income= (TextView) findViewById(R.id.tv_wallet_month_income);
        tv_all_singular= (TextView) findViewById(R.id.tv_wallet_all_singular_number);
        tv_all_income= (TextView) findViewById(R.id.tv_wallet_all_income);
        ll_present= (LinearLayout) findViewById(R.id.ll_wallet_present_records);
        ll_present.setOnClickListener(this);
        ll_income= (LinearLayout) findViewById(R.id.ll_wallet_income_records);
        ll_income.setOnClickListener(this);
        bt_present= (Button) findViewById(R.id.bt_wallet_present);
        bt_present.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_wallet_back:
                finish();
                break;
            case R.id.ll_wallet_present_records:
                startActivity(new Intent(mContext,PresentRecordActivity.class));
                break;
            case R.id.ll_wallet_income_records:
                startActivity(new Intent(mContext,IncomeRecordsActivity.class));
                break;
            case R.id.bt_wallet_present:
                startActivity(new Intent(mContext,BindAccountActivity.class));
                break;
        }
    }
}
