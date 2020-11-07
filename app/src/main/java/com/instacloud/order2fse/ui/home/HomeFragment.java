package com.instacloud.order2fse.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.SellerList.SellerAdapter;
import com.instacloud.order2fse.ui.SellerList.SellerListModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private List<SellerListModel> sellerListModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);

//        ViewPager viewPager = root.findViewById(R.id.viewPager);
//        ViewPager viewPager2 = root.findViewById(R.id.viewpager2);
        //  ImageAdapter adapter = new ImageAdapter(getContext());
        //  ImageAdapter2 adapter2 = new ImageAdapter2(getContext());
//        viewPager.setAdapter(adapter);
//        viewPager2.setAdapter(adapter2);


//            }
//        });


        // data to populate the RecyclerView with
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");
//
//
//        ArrayList<String> animalNames2 = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");

//        // set up the RecyclerView
//        RecyclerView recyclerView =root.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new MyRecyclerViewAdapter(getActivity(), animalNames,animalNames2);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        mAdapter = new SellerAdapter(sellerListModelList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        return root;
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