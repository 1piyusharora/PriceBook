package com.palabs.pricebook;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemInsertion extends AppCompatActivity implements View.OnClickListener {


    TextView textTitle;
    EditText editItemName,editMrp,editInvoice,editHSN;
    Button btnSubmit;

    Items item,rcvItem;

    ContentResolver resolver;

    boolean updateMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_insertion);

        Intent r2=getIntent();

        resolver = getContentResolver();

        item=new Items();

        textTitle=(TextView)findViewById(R.id.textViewItemTitle);
        editItemName=(EditText)findViewById(R.id.editTextItemName);
        editMrp=(EditText)findViewById(R.id.editTextMRP);
        editInvoice=(EditText)findViewById(R.id.editTextInvoice);
        editHSN=(EditText)findViewById(R.id.editTextHSN);
        btnSubmit=(Button)findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);

        Intent rcv=getIntent();
        updateMode = rcv.hasExtra("keyItem");

        if(updateMode){

            rcvItem = (Items) rcv.getSerializableExtra("keyItem");

            editItemName.setText(rcvItem.getItemName());
            editMrp.setText(rcvItem.getMrp());
            editInvoice.setText(rcvItem.getInvoice());
            editHSN.setText(rcvItem.getHsn());

            btnSubmit.setText("UPDATE");

        }

    }

    @Override
    public void onClick(View view) {

        String itemName=editItemName.getText().toString().trim();
        item.itemName=itemName;

        String mrp=editMrp.getText().toString().trim();
        item.mrp=mrp;

        String invoice=editInvoice.getText().toString().trim();
        item.invoice=invoice;

        String hsn=editHSN.getText().toString().trim();
        item.hsn=hsn;

        insertUser();

    }

    void insertUser(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,item.getItemName());
        values.put(Util.COL_MRP,item.getMrp());
        values.put(Util.COL_INVOICE,item.getInvoice());
        values.put(Util.COL_HSN,item.getHsn());

        if(updateMode) {

            String where = Util.COL_ID+" = "+rcvItem.getId();
            int i = resolver.update(Util.USER_URI,values,where,null);
            if(i>0){
                Toast.makeText(this,item.getItemName()+ " updated successfully",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this,item.getItemName()+ " could not be updated",Toast.LENGTH_LONG).show();
            }

        }
        else {

            Uri uri = resolver.insert(Util.USER_URI,values);
            Toast.makeText(this,item.getItemName()+" entered successfully with id "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();
            clearFields();

        }

    }

    void clearFields(){

        editItemName.setText("");
        editMrp.setText("");
        editInvoice.setText("");
        editHSN.setText("");

    }

}
