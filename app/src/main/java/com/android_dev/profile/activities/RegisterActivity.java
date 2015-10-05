package com.android_dev.profile.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android_dev.profile.R;
import com.android_dev.profile.daos.UserDAO;
import com.android_dev.profile.domain.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RegisterActivity extends ActionBarActivity {
    private static final int FOTO = 1;
    ImageButton imgBtn;

    SimpleDateFormat dateFormat;
    DatePickerDialog dateDialog;
    Calendar novaData;
    EditText dateText;
    EditText nameText;
    EditText descriptionText;

    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameText = (EditText) findViewById(R.id.nameEditText);
        descriptionText = (EditText) findViewById(R.id.descriptionEditText);

        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // create Intent to take a picture and return control to the calling application
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        // start the image capture Intent
                        startActivityForResult(intent, FOTO);
                    }
                }
        );
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        novaData = Calendar.getInstance();
        dateText = (EditText)findViewById(R.id.birthdateEditText);
        dateDialog = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar data = Calendar.getInstance();
                        data.set(year, monthOfYear, dayOfMonth);
                        dateText.setText(dateFormat.format(data.getTime()));
                    }
                },novaData.get(Calendar.YEAR), novaData.get(Calendar.MONTH),
                novaData.get(Calendar.DAY_OF_MONTH));

        registerBtn = (Button) findViewById(R.id.button3);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        dateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    dateDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==FOTO) {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            imgBtn.setImageBitmap(foto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    public void confirmDialog() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja realmente cadastrar?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //salvar no banco
                setResult(1);
                saveUser();
                finish();
            }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alerta.show();
    }

    public void saveUser(){
        User user = new User();
        user.setName(nameText.getText().toString());
        user.setDescription(descriptionText.getText().toString());
        new UserDAO(this).save(user);
    }
}
