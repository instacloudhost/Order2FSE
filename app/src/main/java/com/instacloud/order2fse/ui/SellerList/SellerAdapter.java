package com.instacloud.order2fse.ui.SellerList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.MyViewHolder> {

    private List<SellerListModel> sellerListModel;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId, orderDateTime, orderValue, orderValueText, sellerName, sellerMobile, sellerStatus;
        public CircularImageView imageViewSeller;


        public MyViewHolder(View view) {
            super(view);
            orderId = (TextView) view.findViewById(R.id.orderId);
            orderDateTime = (TextView) view.findViewById(R.id.orderDateTime);
            orderValue = (TextView) view.findViewById(R.id.orderValue);
            orderValueText = (TextView) view.findViewById(R.id.orderValueText);
            sellerName = (TextView) view.findViewById(R.id.sellerName);
            sellerMobile = (TextView) view.findViewById(R.id.sellerMobile);
            sellerStatus = (TextView) view.findViewById(R.id.sellerStatus);

            imageViewSeller = (CircularImageView) view.findViewById(R.id.imageViewSeller);
        }
    }


    public SellerAdapter(List<SellerListModel> sellerListModel) {
        this.sellerListModel = sellerListModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SellerListModel sellerListModel = this.sellerListModel.get(position);
        holder.orderId.setText(sellerListModel.getOrderId());
        holder.orderDateTime.setText(sellerListModel.getOrderDateTime());
        holder.orderValue.setText(sellerListModel.getOrderValue());
        holder.orderValueText.setText(sellerListModel.getOrderValueText());
        holder.sellerName.setText(sellerListModel.getSellerName());
        holder.sellerMobile.setText(sellerListModel.getSellerMobile());
        holder.sellerStatus.setText(sellerListModel.getSellerStatus());

    }

    @Override
    public int getItemCount() {
        return sellerListModel.size();
    }
}