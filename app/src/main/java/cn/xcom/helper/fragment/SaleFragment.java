package cn.xcom.helper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import cn.xcom.helper.R;
import cn.xcom.helper.adapter.SaleAdapter;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——特卖
 *
 */
public class SaleFragment extends Fragment implements View.OnClickListener{
    private String TAG="SaleFragment";
    private Context mContext;
    private RelativeLayout rl_classification,rl_release;
    private XRecyclerView mRecyclerView;
    private SaleAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sale,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
        initView();
    }

    private void initView(){
        rl_classification= (RelativeLayout) getView().findViewById(R.id.rl_fragment_sale_classification);
        rl_classification.setOnClickListener(this);
        rl_release= (RelativeLayout) getView().findViewById(R.id.rl_fragment_sale_release);
        rl_release.setOnClickListener(this);
        mRecyclerView= (XRecyclerView)getView().findViewById(R.id.lv_fragment_sale);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SaleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        
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

//        mRecyclerView.setRefreshing(true);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_fragment_sale_classification:

                break;
            case R.id.rl_fragment_sale_release:

                break;
        }

    }
}
