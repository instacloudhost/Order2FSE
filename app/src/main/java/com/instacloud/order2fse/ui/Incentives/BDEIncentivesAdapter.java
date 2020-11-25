package com.instacloud.order2fse.ui.Incentives;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.home.DataShopID;

import java.util.ArrayList;

public class BDEIncentivesAdapter extends RecyclerView.Adapter<BDEIncentivesAdapter.ViewHolder>{

    private ArrayList<DataShopID> mTestModel;
    private Context context;

    public BDEIncentivesAdapter(Context context, ArrayList<DataShopID> mTestModel) {
        this.mTestModel=mTestModel;
        this.context=context;
    }

    @NonNull
    @Override
    public BDEIncentivesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bde_incentive_list,viewGroup,false);
        return new BDEIncentivesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BDEIncentivesAdapter.ViewHolder viewHolder, int i) {

//        viewHolder.date_seller_on_boarding.setText(mTestModel.get(i).getId());
//        viewHolder.gold_incentive_collected.setText(mTestModel.get(i).getAgentId());
//        viewHolder.platinum_incentive_collected.setText(mTestModel.get(i).getAgentType());
//        viewHolder.diamond_incentive_collected.setText(mTestModel.get(i).getName());
//        viewHolder.date_advertising.setText(mTestModel.get(i).getMobile());
//        viewHolder.advertisement_incentive_collected.setText("OnBoarded At - " + mTestModel.get(i).getCreatedAt());
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
        return mTestModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date_seller_on_boarding, date_advertising, gold_incentive_collected, platinum_incentive_collected,
                diamond_incentive_collected, advertisement_incentive_collected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the reference of item view's
            date_seller_on_boarding = (TextView) itemView.findViewById(R.id.date_seller_on_boarding);
            gold_incentive_collected = (TextView) itemView.findViewById(R.id.gold_incentive_collected);
            platinum_incentive_collected = (TextView) itemView.findViewById(R.id.platinum_incentive_collected);
            diamond_incentive_collected = (TextView) itemView.findViewById(R.id.diamond_incentive_collected);
            date_advertising = (TextView) itemView.findViewById(R.id.date_advertising);
            advertisement_incentive_collected = (TextView) itemView.findViewById(R.id.advertisement_incentive_collected);
        }
    }
}