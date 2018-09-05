package com.palabs.pricebook;

import android.net.Uri;

/**
 * Created by White Wolf on 7/26/2017.
 */

public class Util {

    // Symbolic Constants

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "pricebook.db";

    public static final String TAB_NAME = "Items";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_MRP = "MRP";
    public static final String COL_INVOICE = "INVOICE";
    public static final String COL_HSN = "HSN";


    public static final String CREATE_TAB_QUERY = "create table Items(" +
            "_ID integer primary key autoincrement," +
            "NAME text," +
            "MRP text," +
            "INVOICE text," +
            "HSN text" +
            ")";

    public static final Uri USER_URI = Uri.parse("content://com.palabs.pricebook.itemcontentprovider/"+TAB_NAME);

}
