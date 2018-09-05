package com.palabs.pricebook;

import android.content.Intent;
import android.icu.math.BigDecimal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetails extends AppCompatActivity {

    TextView ttl,iname,maxRetail,inv,hsnc,sp,cp,tax;
    EditText quantity,margin,gst;
    Button calculate,refreash;

    Items receiveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent r3=getIntent();
        receiveItem=(Items)r3.getSerializableExtra("keyItem");

        ttl=(TextView)findViewById(R.id.textTitle);
        iname=(TextView)findViewById(R.id.textName);
        maxRetail=(TextView)findViewById(R.id.textMRP);
        inv=(TextView)findViewById(R.id.textInvoice);
        hsnc=(TextView)findViewById(R.id.textHSN);
        sp=(TextView)findViewById(R.id.textSP);
        cp=(TextView)findViewById(R.id.textCP);
        tax=(TextView)findViewById(R.id.textTax);

        quantity=(EditText)findViewById(R.id.editQuantity);
        margin=(EditText)findViewById(R.id.editMargin);
        gst=(EditText)findViewById(R.id.editGST);

        calculate=(Button)findViewById(R.id.buttonCalculate);
        refreash=(Button)findViewById(R.id.buttonRefreash);

        iname.append(receiveItem.getItemName());
        maxRetail.append(receiveItem.getMrp());
        inv.append(receiveItem.getInvoice());
        hsnc.append(receiveItem.getHsn());

    }

    public void calc(View view) {

        sp.setText("SP   :   ");
        cp.setText("CP   :   ");
        tax.setText("Tax   :   ");

            try {

                double invc=Double.parseDouble(receiveItem.getInvoice().toString());
                double qty=Double.parseDouble(quantity.getText().toString());
                double mrgn=Double.parseDouble(margin.getText().toString());
                double g=Double.parseDouble(gst.getText().toString());

                double spCal=( qty*(invc + ((mrgn/100)*invc) ) );
                spCal+=(g/100)*spCal;
                spCal=(double)Math.round(spCal* 100) / 100;

                double cpCal=( spCal - ((g/100)*spCal) );
                cpCal=(double)Math.round(cpCal* 100) / 100;

                double gstCal=(spCal-cpCal);
                gstCal=(double)Math.round(gstCal* 100) / 100;

                sp.append(Double.toString(spCal));
                cp.append(Double.toString(cpCal));
                tax.append(Double.toString(gstCal));

            }
            catch (NumberFormatException e) {

                Toast.makeText(this,"Please Fill All Fields",Toast.LENGTH_LONG).show();

            }
    }


    public void refrsh(View view) {

        sp.setText("SP   :   ");
        cp.setText("CP   :   ");
        tax.setText("Tax   :   ");

        quantity.setText("");
        margin.setText("");
        gst.setText("");


    }


}
