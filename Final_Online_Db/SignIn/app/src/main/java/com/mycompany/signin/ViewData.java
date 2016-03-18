package com.mycompany.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {

    public String trandata;
    public String dbString;
    TextView transactions;
    EditText transactionid;
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        transactions = (TextView) findViewById(R.id.transactionstext);
        transactionid = (EditText) findViewById(R.id.transactionid);
        dbHandler = new MyDBHandler(this, null, null, 2);
        printDatabase();
    }

    public void DeleteRecord(View view)
    {
        int inputText;
        inputText = Integer.parseInt(transactionid.getText().toString());
        dbHandler.deleteProduct(inputText);
        //setContentView(R.layout.activity_view_data);
        printDatabase();
    }

    public void printDatabase()
    {
        dbString = dbHandler.databaseToString();
        transactions.setText(dbString);
    }


    }
