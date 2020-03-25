package com.akki.stock.stock;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddItem extends Fragment {

    String iname,unit,upl,lwl,cus;
    EditText in,uni,ul,ll,cs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_item, container, false);

         in=(EditText)view.findViewById(R.id.edittext_name);
         uni=(EditText)view.findViewById(R.id.edittext_unit);
         ul=(EditText)view.findViewById(R.id.edittext_ul);
         ll=(EditText)view.findViewById(R.id.edittext_ll);
         cs=(EditText)view.findViewById(R.id.edittext_cs);
        Button button=(Button)view.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iname=in.getText().toString().toUpperCase();
                unit=uni.getText().toString().toUpperCase();
                lwl=ll.getText().toString();
                upl=ul.getText().toString();
                cus=cs.getText().toString();

                if(in.equals("") || unit.equals("") || lwl.equals("") || upl.equals(""))
                {
                    Toast.makeText(getActivity(),"Empty Credentials",Toast.LENGTH_LONG).show();
                }

                else {

                    if (cus.equals("")) {
                        cus = "0";
                    }

                    try {

                        String log="" + iname + " Added To List";

                        SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("ypdb", MODE_PRIVATE, null);
                        mydatabase.execSQL("INSERT INTO stock ('Iname','unit','LL','UL','st') VALUES('" + iname + "','" + unit + "'," + lwl + ", " + upl + "," + cus + ");");
                        mydatabase.execSQL("INSERT INTO alog ('log') VALUES('" + log + "');");

                        Toast.makeText(getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });




        return view;
    }

}
