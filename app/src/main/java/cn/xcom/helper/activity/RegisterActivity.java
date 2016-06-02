package cn.xcom.helper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONObject;
import cn.xcom.helper.R;
import cn.xcom.helper.constant.HelperConstant;
import cn.xcom.helper.constant.NetConstant;
import cn.xcom.helper.net.HelperAsyncHttpClient;
import cn.xcom.helper.utils.LogUtils;
import cn.xcom.helper.utils.RegexUtil;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhuchongkun on 16/5/27.
 * 注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private String TAG="RegisterActivity";
    private RelativeLayout rl_back;
    private EditText et_name,et_ID,et_address,et_phone,et_verification,et_password;
    private TextView tv_getVerification;
    private ImageView iv_password;
    private Button bt_submit;
    private RadioGroup rg_gender;
    private RadioButton rb_male,rb_female;
    private Context mContext;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        mContext=this;
        initView();
    }
    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_register_back);
        rl_back.setOnClickListener(this);
        rg_gender= (RadioGroup) findViewById(R.id.radioGroup_register_gender);
        rb_male= (RadioButton) findViewById(R.id.radioButton_register_man);
        rb_female= (RadioButton) findViewById(R.id.radioButton_register_woman);
        rg_gender.setOnCheckedChangeListener(this);
        et_name= (EditText) findViewById(R.id.et_register_name);
        et_ID= (EditText) findViewById(R.id.et_register_ID);
        et_address= (EditText) findViewById(R.id.et_register_address);
        et_phone= (EditText) findViewById(R.id.et_register_phone);
        et_verification= (EditText) findViewById(R.id.et_register_verification);
        et_password= (EditText) findViewById(R.id.et_register_password);
        tv_getVerification= (TextView) findViewById(R.id.tv_register_verification_get);
        tv_getVerification.setOnClickListener(this);
        bt_submit= (Button) findViewById(R.id.bt_register_submit);
        bt_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_register_back:
                finish();
                break;
            case R.id.tv_register_verification_get:
                getVerification();
                break;
            case R.id.bt_register_submit:
                toRegister();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case R.id.radioButton_register_man:
                break;
            case R.id.radioButton_register_woman:
                break;
        }
    }
    private void getVerification(){
//      http://bang.xiaocool.net/index.php?g=apps&m=index&a=SendMobileCode&phone=18653503680
        String phone=et_phone.getText().toString().trim();
        if (!RegexUtil.checkMobile(phone)) {
            Toast.makeText(mContext,"请正确输入手机号！",Toast.LENGTH_LONG).show();
            return;
        }
        RequestParams params=new RequestParams();
        params.put("a","SendMobileCode");
        params.put("phone",phone);
        HelperAsyncHttpClient.get(NetConstant.NET_HOST_PREFIX,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+responseString);
            }
        });

    }
    private void toRegister(){
//      http://bang.xiaocool.net/index.php?g=apps&m=index&a=AppRegister&name=我的昵称&avatar=1234.jpg&phone=18653503680&password=123456&code=2345&devicestate=1
        String phone=et_phone.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        String ID=et_ID.getText().toString().trim();
        String address=et_address.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        String verification=et_verification.getText().toString().trim();
        if (!RegexUtil.IsChineseOrEnglish(name)){
            Toast.makeText(mContext,"请正确填写姓名！",Toast.LENGTH_LONG).show();
            return;
        }
//        if (!RegexUtil.checkIdCard(ID)){
//            return;
//        }
//        if (RegexUtil.checkMobile(phone)){
//            return;
//        }
        RequestParams params=new RequestParams();
        params.put("a","AppRegister");
        params.put("name",name);
//        params.put("avatar","AppRegister");
        params.put("phone",phone);
//        params.put("",ID);
//        params.put("",address);
        params.put("password",password);
        params.put("code",verification);
        params.put("devicestate", HelperConstant.DEVICE_STATE);
        HelperAsyncHttpClient.get(NetConstant.NET_HOST_PREFIX,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+responseString);
            }
        });




    }
}