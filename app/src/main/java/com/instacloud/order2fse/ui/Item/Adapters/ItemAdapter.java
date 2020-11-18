package com.instacloud.order2fse.ui.Item.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.Item.Activity.ExtraItemActivity;
import com.instacloud.order2fse.ui.Item.Model.ItemListModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<ItemListModel> itemListModelList;


    private OnItemClicked onClick;

    private Context context;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId, orderDateTime, orderValue, orderValueText, sellerName, sellerMobile;
        public ImageView imageViewSeller,sellerStatus;
        public Button addExtraItemButton;


        public MyViewHolder(View view) {
            super(view);
            orderId = (TextView) view.findViewById(R.id.orderId);
            orderDateTime = (TextView) view.findViewById(R.id.orderDateTime);
            orderValue = (TextView) view.findViewById(R.id.orderValue);
            orderValueText = (TextView) view.findViewById(R.id.orderValueText);
            sellerName = (TextView) view.findViewById(R.id.sellerName);
            sellerMobile = (TextView) view.findViewById(R.id.sellerMobile);
            sellerStatus = (ImageView) view.findViewById(R.id.sellerStatus);
            addExtraItemButton = (Button) view.findViewById(R.id.addExtraItemButton);
            imageViewSeller = (ImageView) view.findViewById(R.id.imageViewSeller);

        }
    }


    public ItemAdapter(List<ItemListModel> itemListModel) {
        this.context = context;
        this.itemListModelList = itemListModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemListModel itemListModel = this.itemListModelList.get(position);
        holder.orderId.setText(itemListModel.getOrderId());
        holder.orderDateTime.setText(itemListModel.getOrderDateTime());
        holder.orderValue.setText(itemListModel.getOrderValue());
        holder.orderValueText.setText(itemListModel.getOrderValueText());
        holder.sellerName.setText(itemListModel.getSellerName());
        holder.sellerMobile.setText(itemListModel.getSellerMobile());

        holder.addExtraItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ExtraItemActivity.class);
               // intent.putInt("id", a);
                context.startActivity(intent);

            }
        });


       // holder.sellerStatus.setText(menuListModel.getSellerStatus());

    }

    @Override
    public int getItemCount() {
        return itemListModelList.size();
    }
}