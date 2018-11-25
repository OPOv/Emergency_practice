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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        args.putString("longitude", longitude);
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
                if(!phoneNum.getText().equals("")) {
                    startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNum.getText())));
                } else
                    Toast.makeText(getContext(), "전화번호 정보가 없습니다", Toast.LENGTH_SHORT).show();
            }
        });

        // 길찾기 웹뷰로 넘어가는 인텐트
        btnPath.setOnClickListener(new View.OnClickListener() {
//            String nameCon;
            @Override
            public void onClick(View v) {
                Intent navigate = new Intent(getContext(), NavigateActivity.class);
//                if(title.getText().toString().contains(" ")) {
//                    ArrayList<String> name = new ArrayList<>();
//                    Collections.addAll(name, title.getText().toString().split(" "));
//                    nameCon = "";
//                    for (int i = 0; i < name.size(); i++) {
//                        if (i == 0)
//                            nameCon = name.get(i);
//                        else
//                            nameCon = nameCon + "%20" + name.get(i);
//                    }
//                }

                navigate.putExtra("url",
                        Uri.parse("http://map.daum.net/link/to/" + title.getText() + "," + getArguments().getString("latitude")
                        + "," + getArguments().getString("longitude")).toString());
                startActivity(navigate);
//                startActivity(new Intent("android.intent.action.VIEW",
//                        Uri.parse("http://map.daum.net/link/to/" + title.getText() + "," + getArguments().getString("latitude")
//                         + "," + getArguments().getString("longitude"))));
                Log.e("11d","http://map.daum.net/link/to/" + title.getText() + "," + getArguments().getString("latitude")
                         + "," + getArguments().getString("longitude"));
            }
        });


//        title.setText(String.format("Card %d", getArguments().getInt("position")));

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}