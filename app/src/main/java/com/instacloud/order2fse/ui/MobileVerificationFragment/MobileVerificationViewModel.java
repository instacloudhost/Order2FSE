package com.instacloud.order2fse.ui.MobileVerificationFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MobileVerificationViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    private MutableLiveData<String> mText;

    public MobileVerificationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
