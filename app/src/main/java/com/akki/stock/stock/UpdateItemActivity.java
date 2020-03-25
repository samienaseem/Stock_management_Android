package com.akki.stock.stock;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateItemActivity extends AppCompatActivity {

    String iname,unit,upl,lwl,cus;
    EditText in,uni,ul,ll,cs;
    SQLiteDatabase mydatabase;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        in=(EditText)findViewById(R.id.edittext_name);
        uni=(EditText)findViewById(R.id.edittext_unit);
        ul=(EditText)findViewById(R.id.edittext_ul);
        ll=(EditText)findViewById(R.id.edittext_ll);
        cs=(EditText)findViewById(R.id.edittext_cs);
        Button button=(Button)findViewById(R.id.button);
        id=getIntent().getIntExtra("id",0);

        mydatabase = openOrCreateDatabase("ypdb",MODE_PRIVATE,null);
        Cursor resultSet = mydatabase.rawQuery("Select * from stock where id=" + id +" ",null);
        resultSet.moveToFirst();

            int a = resultSet.getInt(0);
            String i = resultSet.getString(1);
            String u = resultSet.getString(2);
            int b = resultSet.getInt(3);
            int c = resultSet.getInt(4);
            int d = resultSet.getInt(5);

        in.setText(i);
        uni.setText(u);
        ll.setText(String.valueOf(b));
        ul.setText(String.valueOf(c));
        cs.setText(String.valueOf(d));




            //  Toast.makeText(getActivity(), a + " " + b + " " + c + " " + d + " " + i + " " + u, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(UpdateItemActivity.this,"Empty Credentials",Toast.LENGTH_LONG).show();
                }

                else {

                    if(cus.equals(""))
                    {
                        cus="0";
                    }

                    try {

                        mydatabase.execSQL("UPDATE stock set Iname = '" + iname + "' , unit = '" + unit + "' ,LL = " + lwl + " ,UL = " + upl + " , st = " + cus + " where id= " + id + ";");
                        Toast.makeText(UpdateItemActivity.this, "Item Updated", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(UpdateItemActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(UpdateItemActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

            }
        });



    }
}
