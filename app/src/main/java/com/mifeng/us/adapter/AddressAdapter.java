package com.mifeng.us.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mifeng.us.bean.AreaBean;
import com.mifeng.us.bean.CityBean;
import com.mifeng.us.bean.ProviceBean;
import com.mifeng.us.citythreelevel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by night_slight on 2019/4/15.
 */

public class AddressAdapter<T> extends RecyclerView.Adapter<AddressAdapter<T>.VHolder> {

    private ArrayList<T> mList;
    private Context context;
    private LayoutInflater mInflate;
    private OnAddressListener onAddressListener;

    public AddressAdapter(ArrayList<T> mList, Context context) {
        this.mList = mList;
        this.context = context;
        mInflate = LayoutInflater.from(context);
        this.mList = mList;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflate.inflate(R.layout.address_item, viewGroup, false);
        VHolder vh = new VHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, int i) {
        T addressBean = mList.get(i);
        if (addressBean instanceof ProviceBean){
            ProviceBean proviceBean = (ProviceBean) addressBean;
            vHolder.tName.setText(proviceBean.getValue());

            if (proviceBean.isSelect()) {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_selected));
            } else {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_gray_color));
            }
        }else if (addressBean instanceof CityBean){
            CityBean cityBean = (CityBean) addressBean;
            vHolder.tName.setText(cityBean.getValue());

            //vHolder.tName.setSelected(cityBean.isSelect());
            if (cityBean.isSelect()) {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_selected));
            } else {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_gray_color));
            }
        }else{
            AreaBean areaBean = (AreaBean) addressBean;
            vHolder.tName.setText(areaBean.getValue());
            //vHolder.tName.setSelected(areaBean.isSelect());
            if (areaBean.isSelect()) {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_selected));
            } else {
                vHolder.tName.setTextColor(ContextCompat.getColor(context, R.color.text_gray_color));
            }
        }

        vHolder.tName.setOnClickListener(view -> {
            if (null != onAddressListener) {
                onAddressListener.onItemClick(view, mList.get(i), i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VHolder extends RecyclerView.ViewHolder {

        private TextView tName;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.mName);
        }
    }

    public interface OnAddressListener<T> {
        void onItemClick(View view, T cateBd, int postion);
    }

    public void setOnTimeListener(OnAddressListener onAddressListener) {
        this.onAddressListener = onAddressListener;
    }
}
