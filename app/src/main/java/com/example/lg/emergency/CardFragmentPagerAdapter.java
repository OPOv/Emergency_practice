package com.example.lg.emergency;

/**
 * Created by davki on 2018-09-28.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private float baseElevation;
    private ArrayList<DataItem> infoList = new ArrayList<>();

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, int size, ArrayList<DataItem> infoList) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.infoList.addAll(infoList);
        for(int i = 0; i< size; i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        CardView returnCard;
        try{
            returnCard = fragments.get(position).getCardView();
        } catch (IndexOutOfBoundsException e){
            return null;
        }
        return returnCard;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.getInstance(position, infoList.get(position).getName(), infoList.get(position).getPhoneNum(),
                infoList.get(position).getLatitude() + "", infoList.get(position).getLongitude() + "");
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }

}
