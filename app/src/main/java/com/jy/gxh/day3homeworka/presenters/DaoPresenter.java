package com.jy.gxh.day3homeworka.presenters;

import com.jy.gxh.day3homeworka.beans.DatasBean;
import com.jy.gxh.day3homeworka.models.DaoModel;
import com.jy.gxh.day3homeworka.models.NetModel;
import com.jy.gxh.day3homeworka.views.NetView;

import java.util.ArrayList;

/**
 * Created by GXH on 2019/9/20.
 */

public class DaoPresenter implements DaoModel.MyCallBack {
    private DaoModel daoModel;
    private NetView netView;

    public DaoPresenter(NetView netView) {
        this.netView=netView;
        this.daoModel = new DaoModel(this);
    }

    public void off(DatasBean datasBean) {
        daoModel.off(datasBean);
    }

    @Override
    public void onResult(int flag) {
        netView.onResult(flag);
    }

    @Override
    public void onDats(ArrayList<DatasBean> datasBeans) {
        netView.refresh(datasBeans);
    }

    @Override
    public void onNoDatas() {
        netView.onNoRefares();
    }

    public void pay(DatasBean datasBean) {
        daoModel.pay(datasBean);
    }

    public void loadall() {
        daoModel.loadAll();
    }
}
