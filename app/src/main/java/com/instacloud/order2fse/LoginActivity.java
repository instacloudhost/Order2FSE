package com.instacloud.order2fse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.instacloud.order2fse.ui.AddSeller.AddSellerFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressDialog progressDialog;
   // private TabLayout tabLayout;
   // private ViewPager viewPager,viewPager2;

    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        submitButton = findViewById(R.id.login);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

       // toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // viewPager = (ViewPager) findViewById(R.id.viewpager);
      //  viewPager2 = (ViewPager) findViewById(R.id.viewpager2);
      //  setupViewPager1(viewPager);
     //   setupViewPager(viewPager2);
     //   tabLayout = (TabLayout) findViewById(R.id.tabs);
      //  tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager1(ViewPager viewPager) {
        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getSupportFragmentManager());
        adapter1.addFrag(new LoginFragment(), "Login");
        adapter1.addFrag(new AddSellerFragment(), "Sign Up");
        adapter1.addFrag(new OtpVerificationFragment(),"OTP");

        viewPager.setAdapter(adapter1);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LoginTextFragment(), "Login");
        adapter.addFrag(new SignUpTextFragment(), "Sign Up");
        adapter.addFrag(new OTPTextFragment(),"OTP");

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
