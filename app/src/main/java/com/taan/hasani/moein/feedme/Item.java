package com.taan.hasani.moein.feedme;

/**
 * Created by Moein on 7/27/2017.
 */

public class Item {

    String title;
    String description;
    String pubdate;
    String imageURL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return  "title=" + title + '\n' +
                ", pubdate=" + pubdate + '\n' +
                ", imageURL=" + imageURL + '\n' ;
    }
}
