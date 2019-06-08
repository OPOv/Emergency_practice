package com.example.lg.emergency;

public class KnowledgeItem {

    private String title, date, subtitle, image; // 제목, 날짜, 소제목


    public String getImage() { return image;}
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getSubtitle() { return subtitle; }

    public KnowledgeItem(String image, String title, String date, String subtitle){

        this.image = image;
        this.title = title;
        this.date = date;
        this.subtitle = subtitle;
    }
}