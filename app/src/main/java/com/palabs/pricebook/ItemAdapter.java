package com.palabs.pricebook;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by White Wolf on 7/26/2017.
 */

public class ItemAdapter extends ArrayAdapter<Items>{

    Context context;
    int resource;
    ArrayList<Items> itemList,tempList;

    TextView txtName;

    String ch=null;

    public ItemAdapter(Context context, int resource, ArrayList<Items> objects) {

        super(context,resource,objects);

        this.context = context;
        this.resource = resource;
        itemList = objects;

        tempList = new ArrayList<>();
        tempList.addAll(itemList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        txtName = (TextView)view.findViewById(R.id.textViewName);

        Items item = itemList.get(position);

        set(item,ch);

        return view;
    }

    public void filter(String str){

        itemList.clear();

        if(str.length()==0){
            itemList.addAll(tempList);
            ch=null;
        }else{
            ch=str;
            for(Items item : tempList){
                if(item.getItemName().toLowerCase().contains(str.toLowerCase())){
                    itemList.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }



    void set(Items item,String str) {

        txtName.setText(item.getItemName());

        if(str!=null) {

            SpannableString string = new SpannableString(txtName.getText().toString());
            string.setSpan(new ForegroundColorSpan(Color.RED), txtName.getText().toString().toLowerCase().indexOf(str.toLowerCase()),txtName.getText().toString().toLowerCase().indexOf(str.toLowerCase())+str.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            txtName.setText(string);

        }

    }

}
