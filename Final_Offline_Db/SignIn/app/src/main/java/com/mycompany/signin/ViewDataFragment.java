package com.mycompany.signin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class ViewDataFragment extends Fragment {

        private static EditText tid;
        TextView transactions;
        ViewDataListener activityCommander;

        public interface ViewDataListener{
            public void deleterecord(String top);
        }



        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_view_data, container, false);

            tid = (EditText)view.findViewById(R.id.usrmnth);
            transactions = (TextView)view.findViewById(R.id.transactionstext);
            tid.setText("Helloe");
            final Button button = (Button)view.findViewById(R.id.delete);

            button.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            buttonClicked(v);
                        }
                    }
            );

            return view;
        }

        //Calls this when button clicked
        public void buttonClicked(View view){
            transactions.setText("Helloe");
           // activityCommander.deleterecord(tid.getText().toString());
        }



    }