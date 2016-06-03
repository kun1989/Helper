package cn.xcom.helper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——我
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    private String TAG="MeFragment";
    private Context mContext;
    private ImageView iv_head,iv_gender;
    private TextView tv_name,tv_phone,tv_realName,tv_wallet,tv_sign,tv_message,
            tv_bill,tv_coupon,tv_order,tv_collection,tv_shoppingCart,tv_share,tv_orderTaking,
            tv_insure,tv_serviceConsulting,tv_moreService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
        initView();
    }

    private void initView(){
        iv_head= (ImageView) getView().findViewById(R.id.iv_fragment_me_head);
        iv_gender= (ImageView) getView().findViewById(R.id.iv_fragment_me_gender);
        tv_name= (TextView) getView().findViewById(R.id.tv_fragment_me_name);
        tv_phone= (TextView) getView().findViewById(R.id.tv_fragment_me_phone);
        tv_realName= (TextView) getView().findViewById(R.id.tv_fragment_me_real_name);
        tv_realName.setOnClickListener(this);
        tv_wallet= (TextView) getView().findViewById(R.id.tv_fragment_me_wallet);
        tv_wallet.setOnClickListener(this);
        tv_sign= (TextView) getView().findViewById(R.id.tv_fragment_me_sign);
        tv_sign.setOnClickListener(this);
        tv_message= (TextView) getView().findViewById(R.id.tv_fragment_me_message);
        tv_message.setOnClickListener(this);
        tv_bill= (TextView) getView().findViewById(R.id.tv_fragment_me_bill);
        tv_bill.setOnClickListener(this);
        tv_coupon= (TextView) getView().findViewById(R.id.tv_fragment_me_coupon);
        tv_coupon.setOnClickListener(this);
        tv_order= (TextView) getView().findViewById(R.id.tv_fragment_me_order);
        tv_order.setOnClickListener(this);
        tv_collection= (TextView) getView().findViewById(R.id.tv_fragment_me_collection);
        tv_collection.setOnClickListener(this);
        tv_shoppingCart= (TextView) getView().findViewById(R.id.tv_fragment_me_shopping_cart);
        tv_shoppingCart.setOnClickListener(this);
        tv_share= (TextView) getView().findViewById(R.id.tv_fragment_me_share);
        tv_share.setOnClickListener(this);
        tv_orderTaking= (TextView) getView().findViewById(R.id.tv_fragment_me_order_taking);
        tv_orderTaking.setOnClickListener(this);
        tv_insure= (TextView) getView().findViewById(R.id.tv_fragment_me_insure);
        tv_insure.setOnClickListener(this);
        tv_serviceConsulting= (TextView) getView().findViewById(R.id.tv_fragment_me_service_consulting);
        tv_serviceConsulting.setOnClickListener(this);
        tv_moreService= (TextView) getView().findViewById(R.id.tv_fragment_me_more_service);
        tv_moreService.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_fragment_me_real_name:

                break;
            case R.id.tv_fragment_me_wallet:

                break;
            case R.id.tv_fragment_me_sign:

                break;
            case R.id.tv_fragment_me_message:

                break;
            case R.id.tv_fragment_me_bill:

                break;
            case R.id.tv_fragment_me_coupon:

                break;
            case R.id.tv_fragment_me_order:

                break;
            case R.id.tv_fragment_me_collection:

                break;
            case R.id.tv_fragment_me_shopping_cart:

                break;
            case R.id.tv_fragment_me_share:

                break;
            case R.id.tv_fragment_me_order_taking:

                break;
            case R.id.tv_fragment_me_insure:

                break;
            case R.id.tv_fragment_me_service_consulting:

                break;
            case R.id.tv_fragment_me_more_service:

                break;
        }

    }
}
