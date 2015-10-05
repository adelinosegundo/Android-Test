package com.android_dev.profile.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android_dev.profile.R;


public class MainActivity extends ActionBarActivity {
    private final int REGISTER_SUCCESS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ( (Button) findViewById(R.id.button2)).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resgisterIntent = new Intent(v.getContext(), RegisterActivity.class);
                        startActivityForResult(resgisterIntent, REGISTER_SUCCESS);
                    }
                }
        );
        ( (Button) findViewById(R.id.listUsersButton)).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent listUsersIntent = new Intent(v.getContext() , ListUsersActivity.class);
                        startActivityForResult(listUsersIntent, 1);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REGISTER_SUCCESS) {
            Intent listIntent = new Intent(this, ListUsersActivity.class);
            startActivity(listIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
