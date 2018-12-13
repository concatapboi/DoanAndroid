package com.example.hien.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {
    Button btnCatProductsMenu;
    TextView txtAbout;
    ListView catList;
    ArrayAdapter<Catalog> catAdap;
    ArrayList<Catalog> catArr;
    Intent catIntentForMenu, catIntent; int pos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        addControls();
        addEvents();
    }

    private void addControls() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        txtAbout = findViewById(R.id.txtAbout);
        txtAbout.setText(android.text.Html.fromHtml("<a href=\"#\">"+getResources().getString(R.string.about)+"</a>"));
        btnCatProductsMenu = findViewById(R.id.btn_cat_products_menu);
        catList = findViewById(R.id.cat_lv);
        catArr = new ArrayList<>();
        createCatArr();
        catAdap = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,catArr);
        catList.setAdapter(catAdap);
    }

    private void createCatArr() {
        for (Catalog c : Data.catArrList){
            catArr.add(c);
        }
    }

    private void addEvents() {
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbout = new Intent(CatalogActivity.this,MapsActivity.class);
                startActivity(intentAbout);
            }
        });
        btnCatProductsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catIntentForMenu = new Intent(CatalogActivity.this, ProductsActivity.class);
                startActivity(catIntentForMenu);
            }
        });
        catList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });
        catList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                catIntent = new Intent(CatalogActivity.this, CatalogDataActivity.class);
                catIntent.putExtra("CatitemID",catArr.get(pos).getCatId());
                catIntent.putExtra("Choice",getResources().getString(R.string.info));
                startActivity(catIntent);
                finish();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_insert:
                catIntent = new Intent(this, CatalogDataActivity.class);
                catIntent.putExtra("Choice", getResources().getString(R.string.insert));
                startActivity(catIntent);
                finish();
                break;
            case R.id.menu_update:
                catIntent = new Intent(this, CatalogDataActivity.class);
                if(pos>=0) {
                    catIntent.putExtra("CatitemID", catArr.get(pos).getCatId());
                    catIntent.putExtra("Choice", getResources().getString(R.string.update));
                    pos=-1;
                    startActivity(catIntent);
                    finish();
                }else Toast.makeText(this, R.string.itemCheck, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                if(pos>=0) {
                    if (Data.checkDataCat(pos)) {
                        Toast.makeText(CatalogActivity.this, R.string.enable, Toast.LENGTH_LONG).show();
                    } else {
                        showDeleteMess(pos);
                        pos = -1;
                    }
                }else Toast.makeText(this, R.string.itemCheck, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteMess(final int position) {
        AlertDialog.Builder buil = new AlertDialog.Builder(this);
        buil.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CatalogActivity.this, R.string.noMess, Toast.LENGTH_SHORT).show();
            }
        });
        buil.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CatalogActivity.this, R.string.yesMess, Toast.LENGTH_SHORT).show();
                Data.catArrList.remove(catAdap.getItem(position));
                catArr.remove(position);
                catAdap.notifyDataSetChanged();
            }
        });
        buil.setMessage(R.string.questionMess);
        buil.create().show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Data.catArrList.clear();
        Data.proArrList.clear();
//        Intent intentBack = new Intent(this, LoginActivity.class);
//        startActivity(intentBack);
        finish();
    }
}
