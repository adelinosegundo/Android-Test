package com.android_dev.profile.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android_dev.profile.R;
import com.android_dev.profile.adapters.UserAdapter;
import com.android_dev.profile.daos.UserDAO;
import com.android_dev.profile.domain.User;

import java.util.List;


public class ListUsersActivity extends ActionBarActivity {

    private ListView listClientes;
    private UserAdapter adapter;
    private User clienteRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        UserDAO clienteDao = new UserDAO(this);
        List<User> clientes = clienteDao.all();

        listClientes = (ListView) findViewById(R.id.listView);
        adapter = new UserAdapter(this, R.layout.user_row, clientes);
        listClientes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
