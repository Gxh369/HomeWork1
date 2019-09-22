package com.jy.gxh.day3homeworka.presenters;

import com.jy.gxh.day3homeworka.beans.DatasBean;
import com.jy.gxh.day3homeworka.models.NetModel;
import com.jy.gxh.day3homeworka.views.NetView;

import java.util.ArrayList;

/**
 * Created by GXH on 2019/9/20.
 */

public class NetPresenter implements NetModel.MyCallBack {
    private NetView netView;
    private NetModel netModel;
    public NetPresenter(NetView netView) {
        this.netView = netView;
        this.netModel=new NetModel(this);
    }

    public void setDatas() {
        netModel.setDatas();
    }

    @Override
    public void onSucceed(ArrayList<DatasBean> datasBeans) {
        netView.addDatas(datasBeans);
    }

    @Override
    public void onField(String string) {
        netView.showToast(string);
    }
}
