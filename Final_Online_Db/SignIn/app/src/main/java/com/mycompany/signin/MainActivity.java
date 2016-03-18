package com.mycompany.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button)findViewById(R.id.signinbtn);
        Button signup = (Button)findViewById(R.id.signupbtn);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView t1 = (TextView)findViewById(R.id.logintxt);
                        t1.setText("Happy");
                        OnSignIn(v);
                    }
                }
        );


        signup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView t1 = (TextView) findViewById(R.id.logintxt);
                        t1.setText("Log In");
                        OnSignUp(v);
                    }
                }
        );

        button.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView t1 = (TextView) findViewById(R.id.logintxt);
                        t1.setText("This is Long One for Sign In");
                        return true;
                    }
                }
        );

        signup.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView t1 = (TextView) findViewById(R.id.logintxt);
                        t1.setText("This is Long One for Sign Up");
                        return true;
                    }
                }
        );


    }
    public void OnSignUp(View view)
    {
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
    }
    public void OnSignIn(View view)
    {
        final EditText mailid = (EditText)findViewById(R.id.transactionid);
        final EditText pwd = (EditText)findViewById(R.id.password);
        String emailid,password;
        emailid = mailid.getText().toString();
        password = pwd.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,emailid,password);


    }

}
