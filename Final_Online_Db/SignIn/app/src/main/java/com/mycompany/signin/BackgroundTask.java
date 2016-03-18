package com.mycompany.signin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Prakash Chourasia on 12/6/2015.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    String Login_Id;
    public BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected String doInBackground(String... params)
    {
        String reg_url = "http://10.0.2.2/ExH/register.php";
        String login_url = "http://10.0.2.2/ExH/login.php";
        String method = params[0];

        if (method.equals("register"))
        {
            String name = params[1];
            String user_name = params[1];
            String user_pass = params[2];
            String user_phn_no = params[3];
        try {
            URL url = new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                    URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                    URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8")+"&"+
                    URLEncoder.encode("user_phn_no","UTF-8")+"="+URLEncoder.encode(user_phn_no,"UTF-8");
                    bufferWriter.write(data);
                    bufferWriter.flush();
                    bufferWriter.close();
                    OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            return "Registration Sucess...";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
        else if (method.equals("login"))
        {
            Login_Id = params[1];
            String Login_Pass = params[2];
            URL url = null;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("Login_Id","UTF-8")+"="+URLEncoder.encode(Login_Id,"UTF-8")+"&"+
                        URLEncoder.encode("Login_Pass","UTF-8")+"="+URLEncoder.encode(Login_Pass,"UTF-8");
                bufferWriter.write(data);
                bufferWriter.flush();
                bufferWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String Response = "";
                String Line ="";
                while ((Line = bufferReader.readLine())!= null)
                {
                    Response+= Line;
                }
                bufferReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return Response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }  catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    protected  void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Lgin Information..");
    }
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
    protected void onPostExecute(String result)
    {
        if (result.equals("Registration Sucess..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {

                alertDialog.setMessage(result);
                alertDialog.show();
            if(!result.equals("Log In Failed Try Again!!"))
            {
                Intent i = new Intent(this.ctx,Home.class);
                i.putExtra("emailid", Login_Id);
                ctx.startActivity(i);
               // Intent intent = new Intent(this.ctx,Home.class);
                //ctx.startActivity(intent);
            }

        }

    }
}
