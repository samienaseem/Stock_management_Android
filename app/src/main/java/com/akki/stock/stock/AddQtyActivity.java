package com.akki.stock.stock;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddQtyActivity extends AppCompatActivity {

    EditText q,b,c;
    int id;
    String in,iu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qty);
        Button bt=(Button)findViewById(R.id.button);
        b=(EditText)findViewById(R.id.byedittext);
        c=(EditText)findViewById(R.id.costedittext);
        q=(EditText)findViewById(R.id.qtyedittext);
        id=getIntent().getIntExtra("id",0);
        in=getIntent().getStringExtra("item");
        iu=getIntent().getStringExtra("iunit");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
                     public void onClick(View view) {

                String aq = q.getText().toString();


                if (aq.equals("")) {
                    Toast.makeText(AddQtyActivity.this, "No Value Provided To Add", Toast.LENGTH_LONG);
                }

                else {
                    String by = b.getText().toString();
                    String cost = c.getText().toString();

                    if(by.equals(""))
                    {
                        by="unknown";
                    }

                    if(cost.equals(""))
                    {
                        cost="unknown";
                    }

                    int cs = Integer.parseInt(cost);
                    int qt = Integer.parseInt(aq);
                    String log ="" + in + " Added " + aq + " " + iu + " by " + by + " of ₹ " + cost;

                    SQLiteDatabase mydatabase = openOrCreateDatabase("ypdb", MODE_PRIVATE, null);
                    // mydatabase.execSQL("Drop Table stock");
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS alog(id INTEGER PRIMARY KEY autoincrement ,log VARCHAR ,date DATETIME DEFAULT CURRENT_TIMESTAMP);");

                    // mydatabase.execSQL("INSERT INTO alog ('Iname','unit','LL','UL','st') VALUES('" + ak + "','kg',12,23,14);");

                    mydatabase.execSQL("UPDATE stock set st=st+" + qt + " where id= " + id + ";");
                    mydatabase.execSQL("INSERT INTO alog ('log') VALUES('" + log + "');");
                    Intent i = new Intent(AddQtyActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);


                }

            }

        });


    }
}
