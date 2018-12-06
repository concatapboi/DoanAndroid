package com.example.hien.project;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {
    Button btnProDetailOk;
    EditText txtProDetailName, txtProDetailPrice, txtProDetailDiscount;
    Spinner spinnerProDetailCatName;
    ImageView imgProDetailShow;
    TextView txtProDetailID;
    ArrayAdapter<String> spinnerAdap;
    Intent detailIntentForOk;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        addControls();
        addEvents();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addControls() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        btnProDetailOk = findViewById(R.id.btn_pro_detail_ok);
        txtProDetailID = findViewById(R.id.txt_pro_detail_id);
        txtProDetailName = findViewById(R.id.txt_pro_detail_name);
        spinnerProDetailCatName = findViewById(R.id.spinner_pro_detail_catalog_name);
        txtProDetailPrice = findViewById(R.id.txt_pro_detail_price);
        txtProDetailDiscount = findViewById(R.id.txt_pro_detail_discount);
        imgProDetailShow = findViewById(R.id.img_pro_detail_show);
        ArrayList<String> arrCatName = new ArrayList<>();
        for(Catalog c : Data.catArrList){
            arrCatName.add(c.getCatName());
        }
        spinnerAdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrCatName);
        spinnerAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProDetailCatName.setAdapter(spinnerAdap);
        detailIntentForOk = new Intent(this, ProductsActivity.class);
        Intent data = getIntent();
        for(Product p : Data.proArrList){
            check=data.getIntExtra("ProitemID",-1);
            if(check==-1) {
                int i= Data.maxProArrId()+1;
                txtProDetailID.setText(i+"");
                txtProDetailPrice.setText("0");
                txtProDetailDiscount.setText("0");
                spinnerProDetailCatName.setSelection(0);
                break;
            }else{
                if(p.getProID()==check) {
                    txtProDetailID.setText(check + "");
                    txtProDetailName.setText(p.getProName());
                    txtProDetailPrice.setText(String.valueOf(p.getProPrice()));
                    txtProDetailDiscount.setText(String.valueOf(p.getProDiscount()));
                    spinnerProDetailCatName.setSelection(spinnerAdap.getPosition(p.getProCatName()));
                    switch (p.getProImgName()){
                        case "apple":
                            imgProDetailShow.setImageDrawable(getDrawable(R.drawable.apple));
                            break;
                        case "samsung":
                            imgProDetailShow.setImageDrawable(getDrawable(R.drawable.samsung));
                            break;
                        case "oppo":
                            imgProDetailShow.setImageDrawable(getDrawable(R.drawable.oppo));
                            break;
                        case "huawei":
                            imgProDetailShow.setImageDrawable(getDrawable(R.drawable.huawei));
                            break;
                        case "xiaomi":
                            imgProDetailShow.setImageDrawable(getDrawable(R.drawable.xiaomi));
                            break;
                    }
                    break;
                }
            }
        }
    }

    private void addEvents() {
        btnProDetailOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product newPro;
                int temptID = Integer.parseInt(txtProDetailID.getText().toString());
                String temptName = txtProDetailName.getText().toString();
                String temptCatName =spinnerProDetailCatName.getSelectedItem().toString();
                String temptImgName="";
                switch(temptCatName){
                    case "IPhone":
                        temptImgName = "apple";
                        break;
                    case "SamSung":
                        temptImgName = "samsung";
                        break;
                    case "Oppo":
                        temptImgName = "oppo";
                        break;
                    case "Huawei":
                        temptImgName = "huawei";
                        break;
                    case "Xiaomi":
                        temptImgName = "xiaomi";
                        break;
                    case "LG":
                        temptImgName = "lg";
                        break;
                }
                int temptPrice = Integer.parseInt(txtProDetailPrice.getText().toString());
                int temptDiscount = Integer.parseInt(txtProDetailDiscount.getText().toString());
                newPro = new Product(temptID,temptName,temptCatName,temptImgName,temptPrice,temptDiscount);
                if(check==-1)
                        Data.proArrList.add(newPro);
                else {
                    Product old = new Product();
                    for (Product p : Data.proArrList){
                        if(p.getProID()==check) {
                            old = p;
                            break;
                        }
                    }
                    Data.proArrList.set(Data.proArrList.indexOf(old),newPro);

                }
                startActivity(detailIntentForOk);
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
