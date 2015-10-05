package com.android_dev.profile.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android_dev.profile.domain.User;
import com.android_dev.profile.helpers.UserOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adelinosegundo on 9/28/15.
 */
public class UserDAO {

    private SQLiteDatabase db;

    public UserDAO(Context context){
        db = new UserOpenHelper(context).getWritableDatabase();
    }


    public void save(User user){
        ContentValues content = new ContentValues();

        content.put("name", user.getName());
        content.put("description", user.getDescription());

        db.insert("users", null, content);
    }

    public List<User> all(){
        List<User> users = new ArrayList<User>();
        Cursor c = db.query("users", User.COLUMNS,
                null, null, null, null, "name");
        if (c.moveToFirst()){
            do{
                User user = new User();
                user.setId(c.getInt(0));
                user.setName(c.getString(1));
                user.setDescription(c.getString(2));
                users.add(user);
            }while(c.moveToNext());
        }
        c.close();
        return users;
    }
}
