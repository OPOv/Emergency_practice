package com.example.lg.emergency;

/**
 * Created by davki on 2018-09-28.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardFragment extends Fragment {

    private CardView cardView;
    private FrameLayout btnCall, btnPath;
    private TextView title, phoneNum;
    public static Fragment getInstance(int position, String name, String phoneNum, String latitude, String longitude) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("name", name);
        args.putString("phoneNum", phoneNum);
        args.putString("latitude", latitude);
        args.putString("longitude", latitude);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_hospital_card, container, false);

        cardView = view.findViewById(R.id.card_map_detail);
        btnCall = view.findViewById(R.id.btn_call);
        btnPath = view.findViewById(R.id.btn_path);
        title = view.findViewById(R.id.title);
        phoneNum = view.findViewById(R.id.txt_phone_number);

        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        title.setText(getArguments().getString("name"));
        phoneNum.setText(getArguments().getString("phoneNum"));

        // 전화 연결 인텐트
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNum.getText())));
            }
        });

        // 길찾기 웹뷰로 넘어가는 인텐트
        btnPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW",
                        Uri.parse("http://m.map.daum.net/link/to/" + title.getText() + "," + getArguments().getString("latitude")
                         + "," + getArguments().getString("longitude"))));
            }
        });


//        title.setText(String.format("Card %d", getArguments().getInt("position")));

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}