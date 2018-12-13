package com.example.hien.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsListAdapter extends BaseAdapter {
    private Context adapContext;
    private ArrayList<Product> adapProArr;

    public ProductsListAdapter() {
    }

    public ProductsListAdapter(Context adapContext, ArrayList<Product> adapProArr) {
        this.adapContext = adapContext;
        this.adapProArr = adapProArr;
    }

    @Override
    public int getCount() {
        return adapProArr.size();
    }

    @Override
    public Object getItem(int position) {
        return adapProArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(adapContext).inflate(R.layout.products_list,null);
            holder.txtProCustomListID = convertView.findViewById(R.id.txt_pro_custom_list_id);
            holder.txtProCustomListName = convertView.findViewById(R.id.txt_pro_custom_list_name);
            holder.getTxtProCustomListCatName = convertView.findViewById(R.id.txt_pro_custom_list_catalog_name);
            holder.imgProCustomListShow = convertView.findViewById(R.id.img_pro_custom_list_show);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Product temptPro;
        temptPro = adapProArr.get(position);
        holder.txtProCustomListID.setText(temptPro.getProID()+"");
        holder.txtProCustomListName.setText(temptPro.getProName());
        holder.getTxtProCustomListCatName.setText(temptPro.getProCatName());
        switch (temptPro.getProImgName()){
            case "apple":
                holder.imgProCustomListShow.setImageResource(R.drawable.apple);
                break;
            case "samsung":
                holder.imgProCustomListShow.setImageResource(R.drawable.samsung);
                break;
            case "oppo":
                holder.imgProCustomListShow.setImageResource(R.drawable.oppo);
                break;
            case "huawei":
                holder.imgProCustomListShow.setImageResource(R.drawable.huawei);
                break;
            case "xiaomi":
                holder.imgProCustomListShow.setImageResource(R.drawable.xiaomi);
                break;
            case "vivo":
                holder.imgProCustomListShow.setImageResource(R.drawable.vivo);
                break;
            case "blackberry":
                holder.imgProCustomListShow.setImageResource(R.drawable.blackberry);
                break;
            case "lg":
                holder.imgProCustomListShow.setImageResource(R.drawable.lg);
                break;
            case "microsoft":
                holder.imgProCustomListShow.setImageResource(R.drawable.windowsphone);
                break;
            case "demo":
                holder.imgProCustomListShow.setImageResource(R.drawable.demo);
                break;
        }
        return convertView;
    }
    class ViewHolder{
        TextView txtProCustomListID,txtProCustomListName,getTxtProCustomListCatName;
        ImageView imgProCustomListShow;
    }
}
