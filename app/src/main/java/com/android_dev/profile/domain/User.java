package com.android_dev.profile.domain;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adelinosegundo on 9/28/15.
 */
public class User implements Parcelable{

    public static String[] COLUMNS = new String[]{"id", "name", "description"};

    private int id;
    private String name;
    private String description;
    private Bitmap avatar;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        avatar.writeToParcel(dest, 0);
    }
}
