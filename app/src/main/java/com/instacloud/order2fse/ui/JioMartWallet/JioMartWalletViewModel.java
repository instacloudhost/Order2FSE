package com.instacloud.order2fse.ui.JioMartWallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JioMartWalletViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JioMartWalletViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}