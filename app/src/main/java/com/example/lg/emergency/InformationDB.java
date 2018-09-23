package com.example.lg.emergency;

public class InformationDB {
    String name;
    String attribute;
    String attribute1;
    String URL;

    public String getURL() { return URL; }
    public String getName() { return name; }
    public String getAttribute() { return attribute; }
    public String getAttribute1() { return attribute1; }

    public InformationDB(String name, String attribute, String attribute1, String URL) {
        this.name = name;
        this.attribute = attribute;
        this.attribute1 = attribute1;
        this.URL = URL;
    }
}
