package com.mycompany.signin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "transactionsDB.db";
    public static final String TABLE_TRANSACTIONS = "Transactions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAILID = "emailid";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY= "category";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_YEAR = "year";

    int[] x = new int[31];
    float[] y = new float[31];

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_TRANSACTIONS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAILID + " TEXT, " +
                COLUMN_AMOUNT + " TEXT ," +
                COLUMN_CATEGORY + " TEXT ," +
                COLUMN_DAY + " TEXT," +
                COLUMN_MONTH + " TEXT, " +
                COLUMN_YEAR + " TEXT " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);
    }

    //-----------------Add a new row data to the database-----------//
    public int addTransaction(Transactions transaction){
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAILID, transaction.get_emailid());
        values.put(COLUMN_AMOUNT, transaction.get_amount());
        values.put(COLUMN_CATEGORY, transaction.get_category());
        values.put(COLUMN_DAY, transaction.get_day());
        values.put(COLUMN_MONTH, transaction.get_month());
        values.put(COLUMN_YEAR, transaction.get_year());
        SQLiteDatabase db = getWritableDatabase();
        int result = (int)db.insert(TABLE_TRANSACTIONS, null, values);
        db.close();
        return result;
    }

    //--------------Delete a product from the database----//

    public void deleteProduct(int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TRANSACTIONS + " WHERE " + COLUMN_ID + "=\"" + _id + "\";");
    }


 //-----------------Daily Data ----------------//

    public float [] Amount_get_daily_data(int usermonth)
    {
        // ArrayList<ArrayList<String>> MonthData = new ArrayList<ArrayList<String>>();
        for (int w = 1; 31 > w; w++) {
            x[w-1] = w;
            y[w] = 0.0f;
        }
        String [][] DailyData1 = new String[100][100];
        int currentyear= Calendar.getInstance().get(Calendar.YEAR);
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id,day,month,year,sum(amount) AS total FROM " + TABLE_TRANSACTIONS  + " WHERE month = "+usermonth+" AND year="+ currentyear+" GROUP BY day" ;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int i=1;
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null)
            {
                x[i] =Integer.parseInt (c.getString(c.getColumnIndex("day")))-1;
                y[i] = Float.parseFloat(c.getString(c.getColumnIndex("total")));
                i++;
            }
            c.moveToNext();
        }
        db.close();
        return y;
    }
    public int [] Daily_get_day_data()
    {
        return x;
    }


//---------------------------- Ravalika Logic And Function---------//
/*
    public HashMap<String, String> Amountgetdailydata(){
        String dbString = "";
        String date="";
        String amount="";
        String year = "2015"; // String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = "12";
        SQLiteDatabase db = getWritableDatabase();
       // String query1 = "SELECT day,month,year,sum(amount) FROM " + TABLE_TRANSACTIONS  + " WHERE " + "YEAR =" + year +"and MONTH=" + month +"GROUP BY DAY";
        String query = "SELECT _id,day,month,year,sum(amount) AS total FROM " + TABLE_TRANSACTIONS  + " WHERE year = 12 GROUP BY day" ;

        HashMap<String, String> dayamount = new HashMap<String,String>();
        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {
                //dbString += c.getString(c.getColumnIndex("day"))+" "+ c.getString(c.getColumnIndex("emailid"))+ " " +" $"+c.getString(c.getColumnIndex("amount"))+"  " +
                //        c.getString(c.getColumnIndex("month"))+"/" +c.getString(c.getColumnIndex("year"));
                //dbString += "\n";
                date += c.getString(c.getColumnIndex("day"))+"/"+c.getString(c.getColumnIndex("month"))+"/"+c.getString(c.getColumnIndex("year"));
                amount= " $"+c.getString(c.getColumnIndex("amount"));
                dayamount.put(date,amount);
            }
            c.moveToNext();
        }
        db.close();
        return dayamount;
    }

*/

//-----------------Monthly Data ----------------//
    public float [] Amountgetmonthlydata()
    {
       // ArrayList<ArrayList<String>> MonthData = new ArrayList<ArrayList<String>>();
        for (int w = 1; 13 > w; w++) {
            x[w-1] = w;
            y[w] = 0.0f;
        }
        String [][] MonthData1 = new String[100][100];
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id,day,month,year,sum(amount) AS total FROM " + TABLE_TRANSACTIONS  + " WHERE year = 2015 GROUP BY month" ;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int i=1;
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {
                x[i] =Integer.parseInt (c.getString(c.getColumnIndex("month")))-1;
                y[i] = Float.parseFloat(c.getString(c.getColumnIndex("total")));
                i++;
             }
            c.moveToNext();
        }
        db.close();
        return y;
    }
    public int [] Monthgetmonthlydata()
    {
        return x;
    }


// -----------------Yearly Data -----------//


    public float [] Amountgetyearlydata()
    {
        // ArrayList<ArrayList<String>> MonthData = new ArrayList<ArrayList<String>>();
        for (int w = 0; 6 > w; w++) {
            x[w] = w;
            y[w] = 0.0f;
        }
        String [][] YearData1 = new String[100][100];
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id, sum(amount) AS total ,year  FROM " + TABLE_TRANSACTIONS  + " Group By year" ;
        // SELECT `ID`, sum(`Amount`) from `trans`  Group By `Year`
        // String query = "SELECT MONTH,SUM(AMOUNT) FROM " + TABLE_TRANSACTIONS  + " WHERE " + COLUMN_YEAR + "=\"" + 2015 + "\" + GROUP By"+ COLUMN_MONTH;
        // SELECT NAME, SUM(SALARY) FROM CUSTOMERS GROUP BY NAME;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int i=0;
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {
                String year= c.getString(c.getColumnIndex("year"));
                switch (year) {
                    case "2011":
                        i = 0;
                        break;
                    case "2012":
                        i = 1;
                        break;
                    case "2013":
                        i = 2;
                        break;
                    case "2014":
                        i = 3;
                        break;
                    case "2015":
                        i = 4;
                        break;
                   // default:
                    //    throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
                }

               // x[i] = i;
                y[i] = Float.parseFloat(c.getString(c.getColumnIndex("total")));
            }
            c.moveToNext();
        }
        db.close();
        return y;
    }
    public int [] Yeargetyearlydata()
    {
        return x;
    }


//  -------------------View Data ------------//

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id, category,amount,month,day  FROM " + TABLE_TRANSACTIONS  + " WHERE 1" ;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {
                dbString += c.getString(c.getColumnIndex("_id"))+" "+ c.getString(c.getColumnIndex("category"))+ " " +" $"+c.getString(c.getColumnIndex("amount"))+"  " +c.getString(c.getColumnIndex("month"))+"/" +c.getString(c.getColumnIndex("day"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
/*

          public ArrayList<String> databaseToStringArray() {
        ArrayList<String> products = new ArrayList<String>();
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1"; //1 = where every condition is met (all rows)

        //Cursor point to a location in results
        Cursor cursor = db.rawQuery(query, null);
        //move to first point in results
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("productname")) != null) {
                dbString = cursor.getString(cursor.getColumnIndex("productname"));
                products.add(dbString);
            }
            cursor.moveToNext();
        }
        db.close();

        return products;
    }

----------------------------------
    public ArrayList<Product> databaseToProductArray() {
        ArrayList<Product> products = new ArrayList<Product>();
        Product product;
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("productname")) != null) {
                product = new Product(cursor.getString(cursor.getColumnIndex("productname")));
                products.add(product);
            }
            cursor.moveToNext();
        }

        db.close();

        return products;
    } */



}