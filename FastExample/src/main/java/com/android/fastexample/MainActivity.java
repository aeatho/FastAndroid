package com.android.fastexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fastlibrary.ui.activity.FastFragmentActivity;
import com.android.fastlibrary.ui.fragment.BaseFragment;


public class MainActivity extends FastFragmentActivity {


    @Override
    public void changeFragment(final BaseFragment targetFragment) {
        changeFragment(R.id.container, targetFragment);
    }

    @Override
    protected void initData() {
        super.initData();
        changeFragment(new PlaceholderFragment());
    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main2);
    }

    public static class PlaceholderFragment extends BaseFragment {

        public PlaceholderFragment() {
        }

        @Override
        protected View setRootView(final LayoutInflater inflater, final ViewGroup container,
                                   final Bundle bundle) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }
}
