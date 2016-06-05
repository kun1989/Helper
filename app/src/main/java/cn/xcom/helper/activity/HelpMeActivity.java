package cn.xcom.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/6/4.
 * 帮我页
 */
public class HelpMeActivity extends BaseActivity implements View.OnClickListener {
    private String TAG="HelpMeActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private EditText et_content,et_phone,et_site_location,et_service_location,et_wages,et_validity_period;
    private TextView tv_time_unit,tv_service_charge;
    private ImageView iv_site_location,iv_service_location,iv_validity_period;
    private Button bt_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help_me);
        mContext=this;
        initView();
    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_help_me_back);
        rl_back.setOnClickListener(this);
        et_content= (EditText) findViewById(R.id.et_help_me_content);
        et_phone= (EditText) findViewById(R.id.et_help_me_phone);
        et_site_location= (EditText) findViewById(R.id.et_help_me_site_location);
        et_service_location= (EditText) findViewById(R.id.et_help_me_service_location);
        et_wages= (EditText) findViewById(R.id.et_help_me_wages);
        et_validity_period= (EditText) findViewById(R.id.et_help_me_validity_period);
        tv_time_unit= (TextView) findViewById(R.id.tv_help_me_time_unit);
        tv_time_unit.setOnClickListener(this);
        tv_service_charge= (TextView) findViewById(R.id.tv_help_me_service_charge);
        iv_site_location= (ImageView) findViewById(R.id.iv_help_me_site_location);
        iv_site_location.setOnClickListener(this);
        iv_service_location= (ImageView) findViewById(R.id.iv_help_me_service_location);
        iv_service_location.setOnClickListener(this);
        iv_validity_period= (ImageView) findViewById(R.id.iv_help_me_validity_period);
        iv_validity_period.setOnClickListener(this);
        bt_submit= (Button) findViewById(R.id.bt_help_me_submit);
        bt_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_help_me_back:
                finish();
                break;
            case R.id.iv_help_me_site_location:
                break;
            case R.id.iv_help_me_service_location:
                break;
            case R.id.tv_help_me_time_unit:
                break;
            case R.id.iv_help_me_validity_period:
                break;
            case R.id.bt_help_me_submit:
                startActivity(new Intent(mContext,UploadContractActivity.class));
                break;
        }

    }
}
