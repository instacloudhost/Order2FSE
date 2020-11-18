package com.instacloud.order2fse.ui.Item.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.Item.Activity.ExtraItemActivity;
import com.instacloud.order2fse.ui.Item.Model.DataAddMenu;

import java.util.ArrayList;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ViewHolder>{

    private ArrayList<DataAddMenu> mTestModel;
    private Context context;

    public ItemViewAdapter(Context context, ArrayList<DataAddMenu> mTestModel) {
        this.mTestModel=mTestModel;
        this.context=context;
    }


    @NonNull
    @Override
    public ItemViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_list_row,viewGroup,false);
        return new ItemViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter.ViewHolder viewHolder, int i) {

        viewHolder.sellerName.setText(mTestModel.get(i).getName());
        viewHolder.sellerMobile.setText("Rs."+mTestModel.get(i).getPrice() + "/-");
        viewHolder.addExtraItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTestModel.size()>i){
                    if (mTestModel.get(i)!= null){
                        Intent intent = new Intent(context, ExtraItemActivity.class);
                        //intent.putExtra("id", ""+ listInvoice.getId());
                        intent.putExtra("food_Id",mTestModel.get(i).getId());
                        intent.putExtra("food_Name",mTestModel.get(i).getName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTestModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView orderId, orderDateTime, orderValue, orderValueText, sellerName, sellerMobile;
        public ImageView imageViewSeller,sellerStatus;
        public Button addExtraItemButton;// init the item view's
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the reference of item view's
            orderId = (TextView) itemView.findViewById(R.id.orderId);
            orderDateTime = (TextView) itemView.findViewById(R.id.orderDateTime);
            orderValue = (TextView) itemView.findViewById(R.id.orderValue);
            orderValueText = (TextView) itemView.findViewById(R.id.orderValueText);
            sellerName = (TextView) itemView.findViewById(R.id.sellerName);
            sellerMobile = (TextView) itemView.findViewById(R.id.sellerMobile);
            sellerStatus = (ImageView) itemView.findViewById(R.id.sellerStatus);
            addExtraItemButton = (Button) itemView.findViewById(R.id.addExtraItemButton);
            imageViewSeller = (ImageView) itemView.findViewById(R.id.imageViewSeller);
        }
    }
}