package com.del.a2b.recyclesample.model;

public class Trackable {
    private int Id;
    private String Name;
    private String Description;
    private String URL;
    private String Category;

    public Trackable()
    {

    }

    public Trackable(int id, String name, String description, String url, String category) {
        Id = id;
        Name = name;
        Description = description;
        URL = url;
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
