package com.jy.gxh.day3homeworka.views;

import com.jy.gxh.day3homeworka.beans.DatasBean;

import java.util.ArrayList;

/**
 * Created by GXH on 2019/9/20.
 */

public interface NetView {
    void addDatas(ArrayList<DatasBean> datasBeans);

    void showToast(String string);

    void onResult(int flag);

    void refresh(ArrayList<DatasBean> datasBeans);

    void onNoRefares();

}
