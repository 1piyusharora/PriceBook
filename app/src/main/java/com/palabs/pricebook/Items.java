package com.palabs.pricebook;

import java.io.Serializable;

/**
 * Created by White Wolf on 7/26/2017.
 */

public class Items implements Serializable{

    public int id;
    public String itemName,mrp,invoice,hsn;


    public Items() {

    }

    public Items(int id, String itemName, String mrp, String invoice, String hsn) {
        this.id = id;
        this.itemName = itemName;
        this.mrp = mrp;
        this.invoice = invoice;
        this.hsn=hsn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

}
