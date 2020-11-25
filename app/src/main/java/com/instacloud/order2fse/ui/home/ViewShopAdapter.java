package com.instacloud.order2fse.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.Item.Activity.ExtraItemActivity;
import com.instacloud.order2fse.ui.ShopDetails.AboutShopActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewShopAdapter extends RecyclerView.Adapter<ViewShopAdapter.ViewHolder>{

    private ArrayList<DataShopID> mTestModel;
    private Context context;

    public ViewShopAdapter(Context context, ArrayList<DataShopID> mTestModel) {
        this.mTestModel=mTestModel;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.seller_list_row,viewGroup,false);
        return new ViewShopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewShopAdapter.ViewHolder viewHolder, int i) {




        viewHolder.sellerName.setText(mTestModel.get(i).getName());
        viewHolder.sellerMobile.setText("Contact No. "+mTestModel.get(i).getMobile());
        viewHolder.orderDateTime.setText("OnBoarded At - " + mTestModel.get(i).getCreatedAt());

        if (mTestModel.get(i).getMedia().toString() == "[]") {
            //viewHolder.cx_name_rx4.setText("No Media Available");
        } else {
            Picasso.get()
                    .load(String.valueOf(mTestModel.get(i).getMedia()))
                    .into(viewHolder.imageViewSeller);

        }


        viewHolder.card_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTestModel.size()>i){
                    if (mTestModel.get(i)!= null){
                        Intent intent = new Intent(context, AboutShopActivity.class);
                        //intent.putExtra("id", ""+ listInvoice.getId());
                        intent.putExtra("name",mTestModel.get(i).getName());
                        intent.putExtra("description",mTestModel.get(i).getDescription());
                        intent.putExtra("address",mTestModel.get(i).getAddress());
                        intent.putExtra("latitude",mTestModel.get(i).getLatitude());
                        intent.putExtra("longitude",mTestModel.get(i).getLongitude());
                        intent.putExtra("phone",mTestModel.get(i).getPhone());
                        intent.putExtra("mobile",mTestModel.get(i).getMobile());
                        intent.putExtra("information",mTestModel.get(i).getInformation());
                        intent.putExtra("admin_commission",mTestModel.get(i).getAdminCommission());
                        intent.putExtra("delivery_fee",mTestModel.get(i).getDeliveryFee());
                        intent.putExtra("delivery_range",mTestModel.get(i).getDeliveryRange());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }

            }
        });





//        viewHolder.addExtraItemButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(mTestModel.size()>i){
//                    if (mTestModel.get(i)!= null){
//                        Intent intent = new Intent(context, ExtraItemActivity.class);
//                        //intent.putExtra("id", ""+ listInvoice.getId());
//                        intent.putExtra("food_Id",mTestModel.get(i).getId());
//                        intent.putExtra("food_Name",mTestModel.get(i).getName());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                }
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView orderId, orderDateTime, orderValue, orderValueText, sellerName, sellerMobile;
        public ImageView imageViewSeller,sellerStatus;
        public Button addExtraItemButton;// init the item view's
        public CardView card_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the reference of item view's
            orderId = (TextView) itemView.findViewById(R.id.orderId);
            orderDateTime = (TextView) itemView.findViewById(R.id.orderDateTime);
            orderValue = (TextView) itemView.findViewById(R.id.orderValue);
            orderValueText = (TextView) itemView.findViewById(R.id.orderValueText);
            sellerName = (TextView) itemView.findViewById(R.id.sellerName);
            sellerMobile = (TextView) itemView.findViewById(R.id.sellerMobile);
          //  sellerStatus = (ImageView) itemView.findViewById(R.id.sellerStatus);
          //  addExtraItemButton = (Button) itemView.findViewById(R.id.addExtraItemButton);
            imageViewSeller = (ImageView) itemView.findViewById(R.id.imageViewSeller);
            card_layout = (CardView) itemView.findViewById(R.id.card_layout);
        }
    }
}