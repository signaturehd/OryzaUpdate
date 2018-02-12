package com.mackoandyelomartinezgmail.oryzaapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mackoandyelomartinezgmail.oryzaapp.TabFragmentation.LogFragment;
import com.mackoandyelomartinezgmail.oryzaapp.TabFragmentation.SacksFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SacksFragment tab1 = new SacksFragment();
                return tab1;
            case 1:
                LogFragment tab2 = new LogFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}