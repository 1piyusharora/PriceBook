package com.palabs.pricebook;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class AllItems extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;

    ContentResolver resolver;

    ArrayList<Items> itemList;

    ItemAdapter adapter;
    Items item;

    int pos;

    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);

        Intent r1=getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2=new Intent(AllItems.this,ItemInsertion.class);
                startActivity(i2);
            }
        });


        resolver = getContentResolver();

        listView=(ListView)findViewById(R.id.listView);

        search=(EditText)findViewById(R.id.editTextSearch);

        retrieveUsers();


    }

    @Override
    protected void onStart() {
        super.onStart();

        retrieveUsers();

        search.setText("");

    }

    void retrieveUsers(){

        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_MRP,Util.COL_INVOICE,Util.COL_HSN};

        Cursor cursor = resolver.query(Util.USER_URI,projection,null,null,Util.COL_NAME+" ASC");

        if(cursor!=null){

            itemList = new ArrayList<>();

            int id=0;
            String n="",m="",i="",h="";
            while (cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                n = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                m = cursor.getString(cursor.getColumnIndex(Util.COL_MRP));
                i = cursor.getString(cursor.getColumnIndex(Util.COL_INVOICE));
                h = cursor.getString(cursor.getColumnIndex(Util.COL_HSN));


                itemList.add(new Items(id,n,m,i,h));
            }

            adapter = new ItemAdapter(this,R.layout.list_item,itemList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(this);

            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    adapter.filter(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {


                }
            });

        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        pos=i;
        item=itemList.get(i);

        showOptions();

    }

    void showOptions(){

        String[] choices = {"View Item","Update Item","Delete Item"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i){
                    case 0:
                        showUser();
                        break;

                    case 1:
                        Intent intent = new Intent(AllItems.this,ItemInsertion.class);
                        intent.putExtra("keyItem",item);
                        startActivity(intent);
                        break;

                    case 2:
                        askForDeletion();
                        break;
                }

            }
        });
        builder.create().show();

        builder.setCancelable(true);

    }

    void showUser(){

        Intent i3=new Intent(AllItems.this,ItemDetails.class);
        i3.putExtra("keyItem",item);
        startActivity(i3);

    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+item.getItemName());
        builder.setMessage("Confirm Deletion ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteUser();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
        builder.setCancelable(true);
    }

    void deleteUser(){

        String where = Util.COL_ID+" = "+item.getId();
        int i = resolver.delete(Util.USER_URI,where,null);
        if(i>0){
            Toast.makeText(this,item.getItemName()+ " deleted from Database",Toast.LENGTH_LONG).show();
            itemList.remove(pos);
            adapter.notifyDataSetChanged();
        }

    }


}
