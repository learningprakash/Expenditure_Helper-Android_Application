package com.mycompany.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void OnSignIn(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void OnSignUp(View view)
    {
        String method = "register";
        final EditText mailid = (EditText)findViewById(R.id.emailid);
        String emailid = mailid.getText().toString();
        final EditText pwd = (EditText)findViewById(R.id.pwd);
        String password = pwd.getText().toString();
        final EditText cpwd = (EditText)findViewById(R.id.cpwd);
        String cpassword = cpwd.getText().toString();
        final EditText phnno = (EditText)findViewById(R.id.phn);
        String phone = phnno.getText().toString();

        if (!password.equals(cpassword))
        {
            cpwd.setText("");
            Toast.makeText(SignUp.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else
        {
           BackgroundTask backgroundtask1 = new BackgroundTask(this);
            backgroundtask1.execute(method,emailid,password,phone);
            finish();
            // Pass the Values to DB
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
