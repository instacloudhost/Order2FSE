package com.instacloud.order2fse.ui.SellerList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.instacloud.order2fse.R;

import java.util.ArrayList;
import java.util.List;


public class SellerListFragment extends Fragment {

    private List<SellerListModel> sellerListModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new SellerAdapter(sellerListModelList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        return view;

    }

    private void prepareMovieData() {
        SellerListModel sellerListModel = new SellerListModel("Order Id :#1", "05-11-2020 | 02:55pm", "$17.59", "Pay on PickUp", "Extremes Pay", "9586554554", "Active");
        sellerListModelList.add(sellerListModel);

        sellerListModel = new SellerListModel("Order Id :#2", "05-11-2020 | 02:55pm", "$20.11", "Pay on PickUp", "Phone Pay", "9586554554", "Inactive");
        sellerListModelList.add(sellerListModel);

        sellerListModel = new SellerListModel("Order Id :#3", "05-11-2020 | 02:55pm", "$50.01", "Pay on PickUp", "Google  Pay", "9586554554", "Pending");
        sellerListModelList.add(sellerListModel);

        sellerListModel = new SellerListModel("Order Id :#4", "05-11-2020 | 02:55pm", "$10.59", "Pay on PickUp", "Bharat Pay", "9586554554", "Cancel");
        sellerListModelList.add(sellerListModel);


        mAdapter.notifyDataSetChanged();
    }
}