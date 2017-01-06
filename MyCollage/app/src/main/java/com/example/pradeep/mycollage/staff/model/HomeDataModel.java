package com.example.pradeep.mycollage.staff.model;

/**
 * Created by pradeep on 05/08/2016.
 */
public class HomeDataModel {
    public HomeDataModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String description;

}
