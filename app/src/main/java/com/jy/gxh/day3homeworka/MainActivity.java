package com.jy.gxh.day3homeworka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.gxh.day3homeworka.adapters.MyRecyAdapter;
import com.jy.gxh.day3homeworka.beans.DatasBean;
import com.jy.gxh.day3homeworka.presenters.DaoPresenter;
import com.jy.gxh.day3homeworka.presenters.NetPresenter;
import com.jy.gxh.day3homeworka.views.NetView;

import java.util.ArrayList;

import ccom.jy.gxh.day3homeworka.db.DatasBeanDao;

public class MainActivity extends AppCompatActivity implements NetView, MyRecyAdapter.MyClickListener {

    private static final String TAG = "MainActivity";
    /**
     * 列表
     */
    private int index;
    private TextView mTitle;
    private RecyclerView mRecy;
    private ArrayList<DatasBean> mlist;
    private MyRecyAdapter adapter;
    private DaoPresenter daoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //找控件
        mTitle = (TextView) findViewById(R.id.title);
        mRecy = (RecyclerView) findViewById(R.id.recy);
        //初始化多布局
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();
        adapter = new MyRecyAdapter(this, mlist);
        mRecy.setAdapter(adapter);
        //这是获取网络数据的P层
        NetPresenter presenter = new NetPresenter(this);
        presenter.setDatas();
        adapter.setMyClickListener(this);
        //这是用来执行关于数据库操作的P层
        daoPresenter = new DaoPresenter(this);
    }

    @Override
    public void addDatas(ArrayList<DatasBean> datasBeans) {
        //网络解析获取的数据 添加到集合中
        this.mlist.addAll(datasBeans);
        //去查看数据库中的数据 用来刷新标识
        daoPresenter.loadall();
    }

    @Override
    public void showToast(String string) {
        //吐司内容
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(int flag) {
        //不管是关注 还是 取消关注  回调此方法
        Log.i(TAG, "onResult: " + index);
        //把上次点击的条目获取
        DatasBean bean = mlist.get(index);
        //修改标识  刷新适配器
        bean.setFlag(flag);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refresh(ArrayList<DatasBean> datasBeans) {
        //查找数据库后回调的方法
        for (int i = 0; i < datasBeans.size(); i++) {
            //循环查到的条目
            if (mlist.size() > 0) {
                //判断 网络解析的数据是否为空
                for (int j = 0; j < mlist.size(); j++) {
                    //循环网络解析的数据

                    //判断获取的数据 和 数据库中的数据   是否相等
                    if (mlist.get(j).getId() == datasBeans.get(i).getId()) {
                        //获取网络解析的集合数据
                        DatasBean bean = mlist.get(j);
                        //获取数据库中的集合数据
                        DatasBean daobean = datasBeans.get(i);
                        //更改网络解析的数据 与 数据库数据的标识保持一致
                        bean.setFlag(daobean.getFlag());
                    }
                }
            }
        }
        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNoRefares() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickListener(int position) {
        Log.i(TAG, "onClickListener: " + position);
        index = position;
        //接口回调的点击监听
        if (mlist.get(position).getFlag() == 1) {
            //  已关注 所以取消关注
            daoPresenter.off(mlist.get(position));
        } else {
            // 没关注 所以添加
            daoPresenter.pay(mlist.get(position));
        }
    }
}
