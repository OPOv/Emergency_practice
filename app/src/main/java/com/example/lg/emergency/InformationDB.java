package com.example.lg.emergency;

public class InformationDB {
    String name;
    String attribute;
    String attribute1;
    URLClass URL;

    public String getURL() { return URL.getURL(); }
    public String getName() { return name; }
    public String getAttribute() { return attribute; }
    public String getAttribute1() { return attribute1; }

    public InformationDB(String name, String attribute, String attribute1, URLClass URL) {
        this.name = name;
        this.attribute = attribute;
        this.attribute1 = attribute1;
        this.URL = URL;
    }
}
