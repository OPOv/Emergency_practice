package com.example.lg.emergency;

/**
 * Created by davki on 2018-09-17.
 * main recycler에서 사용되는 아이템
 */


public class KnowledgeItem {

    private String title, date, subtitle; // 제목, 날짜, 소제목
    private int image;


    public int getImage() { return image;}
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getSubtitle() { return subtitle; }

    public KnowledgeItem( int image,String title, String date, String subtitle){

        this.image = image;
        this.title = title;
        this.date = date;
        this.subtitle = subtitle;
    }
}