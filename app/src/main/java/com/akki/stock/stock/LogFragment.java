package com.akki.stock.stock;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class LogFragment extends Fragment {

    CustomListAdapterLog ca;
    ArrayList<Integer> id= new ArrayList<Integer>();
    ArrayList<String> log=new ArrayList<String>();
    ArrayList<String> date=new ArrayList<String>();
    ProgressDialog mProgress;
    ListView l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        l=(ListView)view.findViewById(R.id.listview);

        load(l);

        return view;
    }

    void load(ListView l)
    {
        ca=new CustomListAdapterLog(getActivity(),id,log,date);
        ca.clear();
        mProgress = new ProgressDialog(getActivity());
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
        SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("ypdb",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS alog(id INTEGER PRIMARY KEY autoincrement ,log VARCHAR ,date DATETIME DEFAULT CURRENT_TIMESTAMP);");
        // mydatabase.execSQL("Drop Table alog");
        //  mydatabase.execSQL("INSERT INTO stock ('Iname','unit','LL','UL','st') VALUES('" + ak + "','kg',12,23,14);");

        Cursor resultSet = mydatabase.rawQuery("Select * from alog ORDER BY date DESC limit 600",null);
        resultSet.moveToFirst();
        int j=0;
        while(resultSet.isAfterLast() == false) {
            int a = resultSet.getInt(0);
            String i = resultSet.getString(1);
            String u = resultSet.getString(2);
            j=1;
            id.add(a);
            log.add(i);
            date.add(u);

          //  Toast.makeText(getActivity(), a + " " + i + " " + u, Toast.LENGTH_SHORT).show();
            resultSet.moveToNext();
        }

        if(j==0)
        {
            Toast.makeText(getActivity(),"No Logs Found",Toast.LENGTH_LONG).show();
        }
        l.setAdapter(ca);

        mProgress.dismiss();

    }

}
