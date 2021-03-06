package com.haack.leaveapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListS extends ListActivity {

    String classes[]={"SickLeave","CasualLeave","MedicalLeave ","MaternityLeave","PaternityLeave"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter< String >(ListS.this,android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese=classes[position];
        try {
            Class select = Class.forName("com.haack.leaveapp."+cheese);
            Intent intent = new Intent(ListS.this , select);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}