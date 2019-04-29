package com.mifeng.us.citythreelevel;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mifeng.us.adapter.AddressAdapter;
import com.mifeng.us.bean.AddressBean;
import com.mifeng.us.bean.AreaBean;
import com.mifeng.us.bean.CityBean;
import com.mifeng.us.bean.ProviceBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private RecyclerView mProviceRecyclerView;
    private RecyclerView mCityRecyclerView;
    private RecyclerView mAreaRecyclerView;

    /*默认第一个为选中*/
    private int index = 0;
    private ArrayList<ProviceBean> mProvices = new ArrayList<>();
    private ArrayList<CityBean> mCitys = new ArrayList<>();
    private ArrayList<AreaBean> mAreas = new ArrayList<>();
    private AddressAdapter<ProviceBean> mProviceAdapter;
    private AddressAdapter<CityBean> mCityAdapter;
    private AddressAdapter<AreaBean> mAreaAdapter;
    private int proviceIndex;
    private int cityIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.mTextView);
        mProviceRecyclerView = findViewById(R.id.mProviceRecyclerView);
        mCityRecyclerView = findViewById(R.id.mCityRecyclerView);
        mAreaRecyclerView = findViewById(R.id.mAreaRecyclerView);

        mProviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAreaRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mProviceAdapter = new AddressAdapter<>(mProvices, this);
        mCityAdapter = new AddressAdapter<>(mCitys, this);
        mAreaAdapter = new AddressAdapter<>(mAreas, this);

        mProviceRecyclerView.setAdapter(mProviceAdapter);
        mCityRecyclerView.setAdapter(mCityAdapter);
        mAreaRecyclerView.setAdapter(mAreaAdapter);

        mProviceAdapter.setOnTimeListener(new AddressAdapter.OnAddressListener<ProviceBean>() {
            @Override
            public void onItemClick(View view, ProviceBean cateBd, int postion) {
                for (int i = 0; i < mProvices.size(); i++) {
                    mProvices.get(i).setSelect(false);
                }
                mProvices.get(postion).setSelect(true);
                mTextView.setText(mProvices.get(postion).getValue());
                proviceIndex = postion;
                mCitys.clear();
                mCitys.addAll(cateBd.getChildren());
                for (int i = 0; i < mCitys.size(); i++) {
                    mCitys.get(i).setSelect(false);
                }
                cityIndex = 0;
                mCitys.get(0).setSelect(true);

                mAreas.clear();
                mAreas.addAll(mCitys.get(0).getChildren());
                for (int i = 0; i < mAreas.size(); i++) {
                    mAreas.get(i).setSelect(false);
                }
                mAreas.get(0).setSelect(true);
                mAreaAdapter.notifyDataSetChanged();
                mCityAdapter.notifyDataSetChanged();
                mProviceAdapter.notifyDataSetChanged();


            }
        });
        mCityAdapter.setOnTimeListener(new AddressAdapter.OnAddressListener<CityBean>() {
            @Override
            public void onItemClick(View view, CityBean cateBd, int postion) {
                for (int i = 0; i < mCitys.size(); i++) {
                    mCitys.get(i).setSelect(false);
                }
                mCitys.get(postion).setSelect(true);
                if (TextUtils.equals(mCitys.get(postion).getValue(),"市辖区")){
                    mTextView.setText(mProvices.get(proviceIndex).getValue());
                }else{
                    mTextView.setText(mProvices.get(proviceIndex).getValue()+"-"+mCitys.get(postion).getValue());
                }
                cityIndex = postion;
                mAreas.clear();
                mAreas.addAll(cateBd.getChildren());
                for (int i = 0; i < mAreas.size(); i++) {
                    mAreas.get(i).setSelect(false);
                }
                mAreas.get(0).setSelect(true);
                mAreaAdapter.notifyDataSetChanged();
                mCityAdapter.notifyDataSetChanged();
            }
        });
        mAreaAdapter.setOnTimeListener(new AddressAdapter.OnAddressListener<AreaBean>() {
            @Override
            public void onItemClick(View view, AreaBean cateBd, int postion) {

                for (int i = 0; i < mAreas.size(); i++) {
                    mAreas.get(i).setSelect(false);
                }
                mAreas.get(postion).setSelect(true);
                mAreaAdapter.notifyDataSetChanged();

                String provice = mProvices.get(proviceIndex).getValue();
                String city = mCitys.get(cityIndex).getValue();
                String area = mAreas.get(postion).getValue();
                String result;
                if (TextUtils.equals(city,"市辖区")){
                    result =  provice+"-" + area;
                }else{
                    result = provice+"-" + city+"-" + area;
                }
                mTextView.setText(result);
            }
        });
        initData();
    }


    protected void initData() {
        AssetManager assets = getAssets();
        try {
            InputStream open = assets.open("address_select.txt");
            StringBuilder sb = new StringBuilder();
            byte[] inby = new byte[2048];
            int length = -1;
            while ((length = open.read(inby)) != -1) {
                sb.append(new String(inby, 0, length));
            }
            String json = new String(sb);
            AddressBean addressBean = new Gson().fromJson(json, AddressBean.class);
            if (addressBean.getCode() == 1) {
                mProvices.addAll(addressBean.getData());
                mCitys.addAll(mProvices.get(index).getChildren());
                mAreas.addAll(mCitys.get(index).getChildren());
                mProvices.get(0).setSelect(true);
                mCitys.get(0).setSelect(true);
                mAreas.get(0).setSelect(true);

                mProviceAdapter.notifyDataSetChanged();
                mCityAdapter.notifyDataSetChanged();
                mAreaAdapter.notifyDataSetChanged();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
