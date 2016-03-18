package com.mycompany.signin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.mycompany.signin.MyService.MyLocalBinder;


public class Home extends AppCompatActivity {

    EditText transactionid;
    EditText amount;
    EditText day;
    EditText month;
    EditText year;
    TextView transactionstext;
    MyDBHandler dbHandler;
    public String emailid;
    public String category;
    public String dbString;

    MyService dateService;
    boolean isBound = false;

    public void showTime(View view){
        String currentday = dateService.getCurrentDate("dd");
        String currentmonth = dateService.getCurrentDate("MM");
        String currentyear = dateService.getCurrentDate("yyyy");
        TextView day = (TextView) findViewById(R.id.day);
        day.setText(currentday);
        TextView month = (TextView) findViewById(R.id.month);
        month.setText(currentmonth);
        TextView year = (TextView) findViewById(R.id.year);
        year.setText(currentyear);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = new Intent(this, MyService.class);
        bindService(i, buckysConnection, Context.BIND_AUTO_CREATE);

        Spinner categoryddlist = (Spinner)findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Categories,android.R.layout.simple_spinner_item);
        categoryddlist.setAdapter(adapter);

        categoryddlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
              //  String imc_met=Spinner.getSelectedItem().toString();
                category= ((Spinner)findViewById(R.id.category)).getSelectedItem().toString();
                Toast.makeText(Home.this, category, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        Bundle mailid = getIntent().getExtras();
        if (mailid == null)
        {
            return;
        }
        emailid = mailid.getString("emailid");

        transactionid = (EditText) findViewById(R.id.transactionid);
        amount = (EditText) findViewById(R.id.amount);
        //category = (EditText) findViewById(R.id.category);
        day = (EditText) findViewById(R.id.day);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);
        transactionstext = (TextView) findViewById(R.id.transactionstext);
        dbHandler = new MyDBHandler(this, null, null, 2);
       // printDatabase();

    }


       //Add a product to the database
    public void addtransaction(View view){

        long futureTime = System.currentTimeMillis() + 3000;

        while(System.currentTimeMillis() < futureTime )
            synchronized (this)
            {
                try
                {
                    wait(futureTime - System.currentTimeMillis());
                }
                catch (InterruptedException e)
                {

                }
            }

        Transactions transaction = new Transactions(emailid,amount.getText().toString(),category,day.getText().toString(),month.getText().toString(),year.getText().toString());
        int result = dbHandler.addTransaction(transaction);
        if (result == -1)
        Toast.makeText(Home.this, "Error In Inserting Data", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Home.this, "Data Inserted", Toast.LENGTH_SHORT).show();
      //  printDatabase();
    }



    public void viewdata(View view)
    {
        Intent i = new Intent(this,ViewData.class);
        startActivity(i);
    }

    public void opendailygraph(View view)
    {
        Intent i = new Intent(this,dailygraph.class);
        startActivity(i);
    }
    public void openmonthlygraph(View view)
    {
        Intent i = new Intent(this,monthlygraph.class);
        startActivity(i);
    }

    public void openyearlygraph(View view)
    {
        Intent i = new Intent(this,yearlygraph.class);
        startActivity(i);
    }
    public void openotheroption(View view)
    {
        Intent i = new Intent(this,othergraph.class);
        startActivity(i);
    }

    private ServiceConnection buckysConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            dateService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };



}
