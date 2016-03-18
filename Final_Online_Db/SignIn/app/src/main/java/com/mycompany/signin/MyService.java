package com.mycompany.signin;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    private final IBinder buckysBinder = new MyLocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return buckysBinder;
    }

    public String getCurrentDate(String format){
        Date date = new Date();
        String DATE_FORMAT = format;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        //System.out.println("Today is " + sdf.format(date));

        //SimpleDateFormat df = new SimpleDateFormat("DD:MM:YY", Locale.US);
        return (sdf.format(date));
    }


    public class MyLocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

}