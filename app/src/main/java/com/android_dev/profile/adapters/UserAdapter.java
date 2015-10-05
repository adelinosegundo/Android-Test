package com.android_dev.profile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android_dev.profile.R;
import com.android_dev.profile.domain.User;

import java.util.List;

/**
 * Created by adelinosegundo on 9/28/15.
 */
public class UserAdapter extends ArrayAdapter<User>{

    static class ViewHolder{
        TextView nameText;
        TextView descriptionText;
    }

    public UserAdapter(Context context, int id, List<User> objects) {
        super(context, id, objects);
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.user_row, null);
            holder = new ViewHolder();

            holder.nameText =  (TextView) view.findViewById(R.id.nameTextView);
            holder.descriptionText =  (TextView) view.findViewById(R.id.descriptionTextView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        User user = getItem(position);
        if (user!= null) {
            holder.nameText.setText(user.getName());
            holder.descriptionText.setText(user.getDescription());
        }
        return view;
    }

}
