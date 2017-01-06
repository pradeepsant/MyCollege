package com.example.pradeep.mycollage.staff.model;

/**
 * Created by pradeep on 29/07/2016.
 */
public class CardDataModel
{
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CardDataModel(String title, String description, int mImage)
    {
        this.titel=title;
        this.description=description;
        this.mImage=mImage;
    }
    public String titel;
    public int mImage;
    public String description;
}
