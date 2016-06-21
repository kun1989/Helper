package cn.xcom.helper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import cn.xcom.helper.R;
import cn.xcom.helper.adapter.SignCouponAdapter;

/**
 * Created by zhuchongkun on 16/6/12.
 * 签到页
 */
public class SignActivity extends BaseActivity implements View.OnClickListener{
    private String TAG="";
    private Context mContext;
    private RelativeLayout rl_back;
    private TextView tv_integral;
    private Button bt_sign;
    private XRecyclerView mRecyclerView;
    private SignCouponAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign);
        mContext=this;
        initView();
    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_sign_back);
        rl_back.setOnClickListener(this);
        tv_integral= (TextView) findViewById(R.id.tv_sign_integral);
        bt_sign= (Button) findViewById(R.id.bt_sign_sign);
        bt_sign.setOnClickListener(this);
        mRecyclerView = (XRecyclerView)this.findViewById(R.id.lv_sign);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        mAdapter=new SignCouponAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_sign_back:
                finish();
                break;
            case R.id.bt_sign_sign:
                Toast.makeText(mContext,"未开发",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
