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
 * Created by zhuchongkun on 16/6/3.
 * 身份认证页
 */
public class AuthorizedActivity extends BaseActivity implements View.OnClickListener{
    private String TAG="AuthorizedActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private ImageView iv_city,iv_handheld_ID_card,iv_ID_card,iv_driving_license;
    private TextView tv_city;
    private EditText et_name,et_ID,et_contact_name,et_contact_phone;
    private Button bt_next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_authorized);
        mContext=this;
        initView();
    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_authorized_back);
        rl_back.setOnClickListener(this);
        iv_city= (ImageView) findViewById(R.id.iv_authorized_city);
        iv_city.setOnClickListener(this);
        iv_handheld_ID_card= (ImageView) findViewById(R.id.iv_authorized_handheld_ID_card);
        iv_handheld_ID_card.setOnClickListener(this);
        iv_ID_card= (ImageView) findViewById(R.id.iv_authorized_ID_card);
        iv_ID_card.setOnClickListener(this);
        iv_driving_license= (ImageView) findViewById(R.id.iv_authorized_driving_license);
        iv_driving_license.setOnClickListener(this);
        tv_city= (TextView) findViewById(R.id.tv_authorized_city);
        et_name= (EditText) findViewById(R.id.et_authorized_name);
        et_ID= (EditText) findViewById(R.id.et_authorized_ID);
        et_contact_name= (EditText) findViewById(R.id.et_authorized_emergency_contact_person_name);
        et_contact_phone= (EditText) findViewById(R.id.et_authorized_emergency_contact_person_phone);
        bt_next= (Button) findViewById(R.id.bt_authorized_next);
        bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_authorized_back:
                finish();
                break;
            case R.id.iv_authorized_city:
                break;
            case R.id.iv_authorized_handheld_ID_card:
                break;
            case R.id.iv_authorized_ID_card:
                break;
            case R.id.iv_authorized_driving_license:
                break;
            case R.id.bt_authorized_next:
                startActivity(new Intent(mContext,SkillListActivity.class));
                break;
        }

    }
}
