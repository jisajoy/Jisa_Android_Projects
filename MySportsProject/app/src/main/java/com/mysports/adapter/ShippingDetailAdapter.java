package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.ShippingDetails;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/15/2018.
 */

public class ShippingDetailAdapter extends RecyclerView.Adapter<ShippingDetailAdapter.ShippingDetailHolder> {
    Context activity;
    ArrayList<ShippingDetails> mAddressList;
    ArrayList<Boolean> checkList;
    static boolean firstTimeLoading;

    public ShippingDetailAdapter(Context activity, ArrayList<ShippingDetails> mAddressList) {
        this.activity = activity;
        this.mAddressList = mAddressList;
        checkList = new ArrayList<>();
        firstTimeLoading = true;
    }

    @Override
    public ShippingDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.shipping_detail_list, parent, false);
        ShippingDetailHolder shippingDetailHolder = new ShippingDetailHolder(view);
        return shippingDetailHolder;
    }

    @Override
    public void onBindViewHolder(final ShippingDetailHolder holder, final int position) {
        ShippingDetails shippingDetails = mAddressList.get(position);
        holder.personName.setText(shippingDetails.getPersonName());
        holder.mPersonMobile.setText(shippingDetails.getPersonMobileNo());
        holder.mStreetAddress.setText(shippingDetails.getPersonAddress());
        holder.mPersonCity.setText(shippingDetails.getPersonCity());
        holder.mCityArea.setText(shippingDetails.getPersonArea());
        holder.mPersonCountry.setText(shippingDetails.getPersonCountry());
        holder.mAddressNo.setText(shippingDetails.getAddressNo());
        int addressNo = Integer.parseInt(shippingDetails.getAddressNo());
        checkList.add(position, false);// initializing list with complete false value in the checkbox index
        if (firstTimeLoading) {
            if (shippingDetails.getAddressActive()) {
                checkList.add(position, true);
                holder.mAddressSelecter.setChecked(true);
            }
        }else {
            if(checkList.get(position)) {
                holder.mAddressSelecter.setChecked(true);
            }
        }
        holder.mAddressSelecter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firstTimeLoading = false;
                    if (!checkList.get(position)) {
                        checkList.add(position, true);
                        notifyItemChanged(position, mAddressList.size());
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ShippingDetailHolder extends RecyclerView.ViewHolder {
        TextView personName;
        TextView mPersonMobile;
        TextView mStreetAddress;
        TextView mPersonCity;
        TextView mCityArea;
        TextView mPersonCountry;
        TextView mAddressNo;
        AppCompatCheckBox mAddressSelecter;

        public ShippingDetailHolder(View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.person_name);
            mPersonMobile = itemView.findViewById(R.id.person_mobile);
            mStreetAddress = itemView.findViewById(R.id.person_address);
            mPersonCity = itemView.findViewById(R.id.person_city);
            mCityArea = itemView.findViewById(R.id.person_area);
            mPersonCountry = itemView.findViewById(R.id.person_country);
            mAddressNo = itemView.findViewById(R.id.address_no);
            mAddressSelecter = itemView.findViewById(R.id.select_address);
        }
    }
}
