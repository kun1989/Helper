package cn.xcom.helper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import cn.xcom.helper.R;
import cn.xcom.helper.fragment.BuyFragment;
import cn.xcom.helper.fragment.MapFragment;
import cn.xcom.helper.fragment.MeFragment;
import cn.xcom.helper.fragment.SaleFragment;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面
 */
public class HomeActivity extends BaseActivity{
    private Context mContext;
    private Button[] mTabs;
    private TextView unReadInformation,unReadFind,unReadChat,unReadMe;
    private MapFragment mapFragment;
    private BuyFragment buyFragment;
    private SaleFragment saleFragment;
    private MeFragment meFragment;
    private Fragment[] fragments;

    private int a=0;
    private int index;
    private int currentTanIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        mContext=this;
        initView();
        initFragment();
    }
    private void initView(){
        unReadInformation= (TextView) findViewById(R.id.tv_home_information_red);
        unReadFind= (TextView) findViewById(R.id.tv_home_find_red);
        unReadChat= (TextView) findViewById(R.id.tv_home_chat_red);
        unReadMe= (TextView) findViewById(R.id.tv_home_me_red);
        mTabs=new Button[4];
        mTabs[0]= (Button) findViewById(R.id.bt_home_information);
        mTabs[1]= (Button) findViewById(R.id.bt_home_find);
        mTabs[2]= (Button) findViewById(R.id.bt_home_chat);
        mTabs[3]= (Button) findViewById(R.id.bt_home_me);
        mTabs[a].setSelected(true);
    }
    private void initFragment(){
        mapFragment=new MapFragment();
        buyFragment=new BuyFragment();
        saleFragment=new SaleFragment();
        meFragment=new MeFragment();
        fragments=new Fragment[]{mapFragment,buyFragment,saleFragment,meFragment};
        switch (a){
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.rl_home_fragment_container,mapFragment)
                        .add(R.id.rl_home_fragment_container,buyFragment)
                        .hide(buyFragment)
                        .show(mapFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.rl_home_fragment_container,mapFragment)
                        .add(R.id.rl_home_fragment_container,buyFragment)
                        .add(R.id.rl_home_fragment_container,saleFragment)
                        .hide(mapFragment)
                        .show(buyFragment)
                        .hide(saleFragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.rl_home_fragment_container,buyFragment)
                        .add(R.id.rl_home_fragment_container,saleFragment)
                        .add(R.id.rl_home_fragment_container,meFragment)
                        .hide(buyFragment)
                        .show(saleFragment)
                        .hide(meFragment).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.rl_home_fragment_container,saleFragment)
                        .add(R.id.rl_home_fragment_container,meFragment)
                        .hide(saleFragment)
                        .show(meFragment).commit();
                break;

        }

    }
    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.bt_home_information:
                index=0;
                break;
            case R.id.bt_home_find:
                index=1;
                break;
            case R.id.bt_home_chat:
                index=2;
                break;
            case R.id.bt_home_me:
                index=3;
                break;
        }
        if (currentTanIndex!=index){
            FragmentTransaction trx =getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTanIndex]);
            if(!fragments[index].isAdded()){
                trx.add(R.id.rl_home_fragment_container,fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTanIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTanIndex=index;
    }



}
