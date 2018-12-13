package com.example.hien.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {
    Button btnLoginOk;
    EditText txtLoginUsername, txtLoginPass;
    Intent loginIntent;
    TextView txtAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addControls() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.welcome));
        createData();
        txtAbout = findViewById(R.id.txtAbout);
        txtAbout.setText(android.text.Html.fromHtml("<a href=\"#\">"+getResources().getString(R.string.about)+"</a>"));
        btnLoginOk = findViewById(R.id.btn_login_ok);
        txtLoginPass = findViewById(R.id.txt_login_pass);
        txtLoginUsername = findViewById(R.id.txt_login_username);
        loginIntent = new Intent(this, CatalogActivity.class);
    }

    private void createData() {
        //Catalog
        Catalog ca1 = new Catalog(1,"IPhone");
        Catalog ca2 = new Catalog(2,"SamSung");
        Catalog ca3 = new Catalog(3,"Oppo");
        Catalog ca4 = new Catalog(4,"Huawei");
        Catalog ca5 = new Catalog(5,"Xiaomi");
        Catalog ca6 = new Catalog(6,"LG");
        Data.catArrList.add(ca1);
        Data.catArrList.add(ca2);
        Data.catArrList.add(ca3);
        Data.catArrList.add(ca4);
        Data.catArrList.add(ca5);
        Data.catArrList.add(ca6);

        //Products
        Product pro1 = new Product(1,"IPhone 6S 64GB","IPhone","apple",12000000,10000000);
        Product pro2 = new Product(2,"IPhone X 64GB","IPhone","apple",17990000,15150000);
        Product pro3 = new Product(3,"SamSung Galaxy Note 9 512GB","SamSung","samsung",28490000,0);
        Product pro4 = new Product(4,"Oppo Find X","Oppo","oppo",20990000,0);
        Product pro5 = new Product(5,"Oppo F9 RAM 6GB","Oppo","oppo",8490000,0);
        Product pro6 = new Product(6,"Huawei Mate 20 Pro","Huawei","huawei",21990000,0);
        Data.proArrList.add(pro1);
        Data.proArrList.add(pro2);
        Data.proArrList.add(pro3);
        Data.proArrList.add(pro4);
        Data.proArrList.add(pro5);
        Data.proArrList.add(pro6);

    }

    private void addEvents() {
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbout = new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(intentAbout);
            }
        });
        btnLoginOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)     {
                if(txtLoginUsername.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,R.string.nullUsername,Toast.LENGTH_LONG).show();
                    txtLoginUsername.requestFocus();
                } else if(txtLoginPass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,R.string.nullPassword,Toast.LENGTH_LONG).show();
                    txtLoginPass.requestFocus();
                } else if(txtLoginPass.getText().toString().length()<5){
                    Toast.makeText(LoginActivity.this,R.string.errorPasswordLength,Toast.LENGTH_LONG).show();
                    txtLoginPass.requestFocus();
                }else {
                    if(txtLoginUsername.getText().toString().trim().compareTo("daica97")==0 && txtLoginPass.getText().toString().compareTo("123456")==0){
                        startActivity(loginIntent);
                    } else {
                        Toast.makeText(LoginActivity.this,R.string.errorUssernameAndPassword,Toast.LENGTH_LONG).show();
                        txtLoginUsername.requestFocus();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
