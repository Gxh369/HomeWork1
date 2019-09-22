package com.jy.gxh.day3homeworka.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jy.gxh.day3homeworka.R;
import com.jy.gxh.day3homeworka.beans.DatasBean;

import java.util.ArrayList;

/**
 * Created by GXH on 2019/9/20.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.MyHolder> {
    private Context context;
    private ArrayList<DatasBean> datas;

    public MyRecyAdapter(Context context, ArrayList<DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        Glide.with(context).load(datas.get(position).getThumbnail()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.item_img);
        holder.item_author.setText(datas.get(position).getAuthor());
        holder.item_title.setText(datas.get(position).getTitle());
        //如果标识为1  就着证明已关注 修改按钮状态为取消
        if(datas.get(position).getFlag()==1){
            holder.btn_play.setText("取消");
        }else {
            //否则就是无关注  就设置状态为关注
            holder.btn_play.setText("关注");
        }
        //只给条目的按钮设置点击监听
        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView item_img;
        private final TextView item_author;
        private final TextView item_title;
        private final Button btn_play;
        public MyHolder(View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_author = itemView.findViewById(R.id.item_author);
            item_title = itemView.findViewById(R.id.item_title);
            btn_play = itemView.findViewById(R.id.btn_play);
        }
    }
    public interface MyClickListener{
        void onClickListener(int position);
    }
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
}
