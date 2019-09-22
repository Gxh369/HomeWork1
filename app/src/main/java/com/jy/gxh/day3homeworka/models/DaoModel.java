package com.jy.gxh.day3homeworka.models;

import com.jy.gxh.day3homeworka.BaseApp;
import com.jy.gxh.day3homeworka.beans.DatasBean;

import java.util.ArrayList;
import java.util.List;

import ccom.jy.gxh.day3homeworka.db.DatasBeanDao;

/**
 * Created by GXH on 2019/9/20.
 */

public class DaoModel {
    private MyCallBack myCallBack;

    private final DatasBeanDao beanDao;
    public DaoModel(MyCallBack myCallBack) {
        beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        this.myCallBack=myCallBack;
    }

    public void off(DatasBean datasBean) {
        try {
            datasBean.setFlag(0);
            beanDao.delete(datasBean);
            myCallBack.onResult(0);
        } catch (Exception e) {
            e.printStackTrace();
            myCallBack.onResult(1);
        }
    }

    public void pay(DatasBean datasBean) {
        try {
            datasBean.setFlag(1);
            beanDao.insertOrReplace(datasBean);
            myCallBack.onResult(1);
        } catch (Exception e) {
            e.printStackTrace();
            myCallBack.onResult(0);
        }
    }

    public void loadAll() {
        //查看数据库所有数据
        ArrayList<DatasBean> datasBeans = (ArrayList<DatasBean>) beanDao.loadAll();
        if(datasBeans.size()>0){
            myCallBack.onDats(datasBeans);
        }else {
            //此方法返回到View层只是用来 刷新适配器
            myCallBack.onNoDatas();
        }
    }

    public interface MyCallBack{
        void onResult(int flag);
        void onDats(ArrayList<DatasBean> datasBeans);
        void onNoDatas();
    }
}
