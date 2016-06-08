package cn.xcom.helper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cn.xcom.helper.R;
import cn.xcom.helper.constant.NetConstant;
import cn.xcom.helper.net.HelperAsyncHttpClient;
import cn.xcom.helper.utils.LogUtils;
import cn.xcom.helper.utils.RegexUtil;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhuchongkun on 16/5/27.
 * 修改密码页
 */
public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener{
    private String TAG="ResetPasswordActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private EditText et_phone,et_password,et_password_confirm,et_verification;
    private TextView tv_getVerification;
    private ImageView iv_password,iv_password_confirm;
    private Button bt_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reset_password);
        mContext=this;
        initView();

    }
    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_reset_password_back);
        rl_back.setOnClickListener(this);
        et_phone= (EditText) findViewById(R.id.et_reset_password_phone);
        et_verification= (EditText) findViewById(R.id.et_reset_password_verification);
        et_password= (EditText) findViewById(R.id.et_reset_password_password);
        et_password_confirm= (EditText) findViewById(R.id.et_reset_password_password_confirm);
        tv_getVerification= (TextView) findViewById(R.id.tv_reset_password_verification_get);
        tv_getVerification.setOnClickListener(this);
        bt_submit= (Button) findViewById(R.id.bt_reset_password_submit);
        bt_submit.setOnClickListener(this);
        iv_password= (ImageView) findViewById(R.id.iv_reset_password_password);
        iv_password.setOnClickListener(this);
        iv_password_confirm= (ImageView) findViewById(R.id.iv_reset_password_password_confirm);
        iv_password_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_reset_password_back:
                finish();
                break;
            case R.id.tv_reset_password_verification_get:
                getVerification();
                break;
            case R.id.iv_reset_password_password:
                break;
            case R.id.iv_reset_password_password_confirm:
                break;
            case R.id.bt_reset_password_submit:
                resetPassword();
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
    private void resetPassword(){
//        http://bang.xiaocool.net/index.php?g=apps&m=index&a=forgetpwd&phone=18653503680&code=726189&password=1234
        String phone=et_phone.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        String password_confirm=et_password_confirm.getText().toString().trim();
        String verification=et_verification.getText().toString().trim();
        if (!RegexUtil.checkMobile(phone)) {
            Toast.makeText(mContext,"请正确输入手机号！",Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length()<6){
            Toast.makeText(mContext,"密码至少6位！",Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(password_confirm)){
            Toast.makeText(mContext,"两次密码输入不一致！",Toast.LENGTH_LONG).show();
            return;
        }
        RequestParams params=new RequestParams();
        params.put("phone",phone);
        params.put("code",verification);
        params.put("password",password);
        HelperAsyncHttpClient.get(NetConstant.NET_RESET_PASSWORD,params,new JsonHttpResponseHandler(){
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
