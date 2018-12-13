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

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    Button btnProCatalogMenu;
    TextView txtAbout;
    ListView proList;
    ProductsListAdapter proAdap;
    ArrayList<Product> proArr;
    Intent proIntentForMenu, proIntent; int pos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
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
        btnProCatalogMenu = findViewById(R.id.btn_pro_catalog_menu);
        proList = findViewById(R.id.pro_lv);
        proArr = new ArrayList<>();
        createProArr();
        proAdap = new ProductsListAdapter(this,proArr);
        proList.setAdapter(proAdap);
        proIntentForMenu = new Intent(this,CatalogActivity.class);
    }

    private void createProArr() {
        for(Product p : Data.proArrList){
            proArr.add(p);
        }
    }

    private void addEvents() {
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbout = new Intent(ProductsActivity.this,MapsActivity.class);
                startActivity(intentAbout);
            }
        });
        btnProCatalogMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(proIntentForMenu);
                finish();
            }
        });
        proList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });
        proList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                proIntent = new Intent(ProductsActivity.this, ProductDetailActivity.class);
                proIntent.putExtra("ProitemID",Data.proArrList.get(position).getProID());
                startActivity(proIntent);
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
                proIntent = new Intent(this, ProductDetailActivity.class);
                startActivity(proIntent);
                finish();
                break;
            case R.id.menu_update:
                proIntent = new Intent(this, ProductDetailActivity.class);
                if(pos>=0) {
                    proIntent.putExtra("ProitemID",Data.proArrList.get(pos).getProID());
                    startActivity(proIntent);
                    finish();
                }else Toast.makeText(this, R.string.itemCheck, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                if(pos>=0) {
                        showDeleteMess(pos);
                        pos = -1;
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
                Toast.makeText(ProductsActivity.this, R.string.noMess, Toast.LENGTH_SHORT).show();
            }
        });
        buil.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ProductsActivity.this, R.string.yesMess, Toast.LENGTH_SHORT).show();
                Data.proArrList.remove(proAdap.getItem(position));
                proArr.remove(position);
                proAdap.notifyDataSetChanged();
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
//        Intent intent = new Intent(ProductsActivity.this,CatalogActivity.class);
//        startActivity(intent);
        finish();
    }
}
