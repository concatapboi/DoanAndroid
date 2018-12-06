package com.example.hien.project;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CatalogDataActivity extends AppCompatActivity {
    Button btnCatDataOk;
    EditText txtCatDataName;
    TextView txtCatDataShow,txtCatDataId;
    int check;
    Intent dataIntentForOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_data);
        addControls();
        addEvents();
    }

    private void addControls() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        btnCatDataOk = findViewById(R.id.btn_cat_data_ok);
        txtCatDataId = findViewById(R.id.txt_cat_data_id);
        txtCatDataName = findViewById(R.id.txt_cat_data_name);
        txtCatDataShow = findViewById(R.id.txt_cat_data_show);
        dataIntentForOk = new Intent(this, CatalogActivity.class);
        Intent data = getIntent();
        txtCatDataShow.setText(data.getStringExtra("Choice"));
        for(Catalog c : Data.catArrList){
            check=data.getIntExtra("CatitemID",-1);
            if(check==-1) {
                int i= Data.maxCatArrId()+1;
                txtCatDataId.setText(i+"");
                break;
            }else{
                if(c.getCatId()==check) {
                    txtCatDataId.setText(check + "");
                    txtCatDataName.setText(c.getCatName());
                    break;
                }
            }
        }
    }

    private void addEvents() {
        btnCatDataOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catalog newCat = new Catalog(Integer.parseInt(txtCatDataId.getText().toString()),txtCatDataName.getText().toString());
                if(check==-1)
                    Data.catArrList.add(newCat);
                else {
                    Catalog old = new Catalog();
                    for (Catalog c : Data.catArrList) {
                        if (c.getCatId() == check) {
                            old = c;
                            break;
                        }
                    }
                    Data.catArrList.set(Data.catArrList.indexOf(old),newCat);
                    Data.updateCatNameInProductsArr(old,newCat);
                }
                startActivity(dataIntentForOk);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
