package cn.xcom.helper.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.xcom.helper.R;
import cn.xcom.helper.activity.AuthorizedActivity;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——抢单
 */
public class BuyFragment extends Fragment implements View.OnClickListener{
    private String TAG="BuyFragment";
    private Context mContext;
    private Button bt_authorized;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buy,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
        initView();
    }
    private void initView(){
        bt_authorized= (Button) getView().findViewById(R.id.bt_fragment_buy_authorized);
        bt_authorized.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_fragment_buy_authorized:
                startActivity(new Intent(mContext, AuthorizedActivity.class));

                break;
        }

    }
}
