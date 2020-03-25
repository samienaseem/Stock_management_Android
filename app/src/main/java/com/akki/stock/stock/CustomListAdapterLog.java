package com.akki.stock.stock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Akki on 01-05-2017.
 */

public class CustomListAdapterLog extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<Integer> id;
    private final ArrayList<String> log;
    private final ArrayList<String> date;
    PopupMenu popupMenu;

    public CustomListAdapterLog(Activity context, ArrayList<Integer> id, ArrayList<String> log, ArrayList<String> date) {
        super(context, R.layout.listviewlayout, log);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.id=id;
        this.log=log;
        this.date=date;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.loglistviewlayout, null,true);

        TextView in = (TextView) rowView.findViewById(R.id.iname);
        TextView css = (TextView) rowView.findViewById(R.id.currents);
        ImageView menubutton=(ImageView)rowView.findViewById(R.id.dotimgid);

        String data=log.get(position);
        String data1=date.get(position);
        data1=data1.substring(0,10);

        in.setText(data);
        css.setText(data1);

        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     Toast.makeText(getContext(), " Hello ", Toast.LENGTH_SHORT).show();

                popupMenu = new PopupMenu(getContext(), view);
                popupMenu.inflate(R.menu.log_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int mid=item.getItemId();
                        Intent i;
                        //    Toast.makeText(getContext(), "  " + id.get(position) + " ", Toast.LENGTH_SHORT).show();

                         if(mid==R.id.delete_item)
                        {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("DELETE LOG ?");
                            builder.setMessage("Are You Sure?");
                            builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    int i=id.get(position).intValue();

                                    SQLiteDatabase mydatabase = getContext().openOrCreateDatabase("ypdb",MODE_PRIVATE,null);
                                    // mydatabase.execSQL("Drop Table alog");

                                    // mydatabase.execSQL("INSERT INTO alog ('Iname','unit','LL','UL','st') VALUES('" + ak + "','kg',12,23,14);");

                                    mydatabase.execSQL("DELETE from alog where id= "+ i +";");
                                    id.remove(position);
                                    log.remove(position);
                                    date.remove(position);
                                    notifyDataSetChanged();

                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();

                        }

                        return false;
                    }
                });
            }
        });

        return rowView;
    };

}
