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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<Integer> id= new ArrayList<Integer>();
    ArrayList<String> iname=new ArrayList<String>();
    ArrayList<String> unit=new ArrayList<String>();
    ArrayList<Integer> ll=new ArrayList<Integer>();
    ArrayList<Integer> ul=new ArrayList<Integer>();
    ArrayList<Integer> cs=new ArrayList<Integer>();
    ProgressDialog mProgress;
    CustomListAdapterHome ca;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        ListView l=(ListView)view.findViewById(R.id.listview);


        load(l);


        l.setAdapter(ca);





        return view;
    }

    void load(ListView l)
    {
        ca=new CustomListAdapterHome(getActivity(),id,iname,unit,ll,ul,cs);
        ca.clear();
        mProgress = new ProgressDialog(getActivity());
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
        SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("ypdb",MODE_PRIVATE,null);
       // mydatabase.execSQL("Drop Table stock");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS stock(id INTEGER PRIMARY KEY autoincrement ,Iname VARCHAR, unit VARCHAR ,LL INT, UL INT , st INT );");
        //  mydatabase.execSQL("INSERT INTO stock ('Iname','unit','LL','UL','st') VALUES('" + ak + "','kg',12,23,14);");

        int j=0;
        Cursor resultSet = mydatabase.rawQuery("Select * from stock",null);
        resultSet.moveToFirst();

        while(resultSet.isAfterLast() == false) {
            int a = resultSet.getInt(0);
            String i = resultSet.getString(1);
            String u = resultSet.getString(2);
            int b = resultSet.getInt(3);
            int c = resultSet.getInt(4);
            int d = resultSet.getInt(5);

            j=1;

            id.add(a);
            iname.add(i);
            ll.add(b);
            unit.add(u);
            ul.add(c);
            cs.add(d);

          //  Toast.makeText(getActivity(), a + " " + b + " " + c + " " + d + " " + i + " " + u, Toast.LENGTH_SHORT).show();
            resultSet.moveToNext();
        }

        if(j==0)
        {
            Toast.makeText(getActivity(),"Nothing Added Yet",Toast.LENGTH_LONG).show();
        }

        mProgress.dismiss();

    }

}
