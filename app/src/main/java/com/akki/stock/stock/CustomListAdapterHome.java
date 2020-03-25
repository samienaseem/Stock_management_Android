package com.akki.stock.stock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Akki on 30-04-2017.
 */

public class CustomListAdapterHome extends ArrayAdapter<String> {

private final Activity context;
private final ArrayList<Integer> id;
private final ArrayList<String> iname;
private final ArrayList<String> unit;
private final ArrayList<Integer> ll;
private final  ArrayList<Integer> ul;
private final ArrayList<Integer> cs;
    PopupMenu popupMenu;

public CustomListAdapterHome(Activity context, ArrayList<Integer> id, ArrayList<String> iname, ArrayList<String> unit, ArrayList<Integer> ll , ArrayList<Integer> ul, ArrayList<Integer> cs) {
        super(context, R.layout.listviewlayout,iname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.id=id;
        this.iname=iname;
        this.unit=unit;
        this.ll=ll;
        this.ul=ul;
        this.cs=cs;
        }

public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listviewlayout, null,true);

        TextView in = (TextView) rowView.findViewById(R.id.iname);
        TextView per = (TextView) rowView.findViewById(R.id.percentage);
        TextView css = (TextView) rowView.findViewById(R.id.currents);
        ProgressBar pb=(ProgressBar) rowView.findViewById(R.id.progressBar);
        ImageView menubutton=(ImageView)rowView.findViewById(R.id.dotimgid);



        String data=iname.get(position);
        String data1=unit.get(position);
        int data2=(cs.get(position)).intValue();
        int l1=(ll.get(position)).intValue();
        int l2=(ul.get(position)).intValue();
        float l=(float)data2/(float)l2*100;
        int p=Math.round(l);

       // Toast.makeText(getContext(), "" + l + "", Toast.LENGTH_SHORT).show();

        String data4=String.valueOf(data2);


        in.setText(data);
        per.setText(p + " %");
        css.setText("Available : " + data4 + " " +data1);
        pb.setProgress(p);
        if(p>=75)
        {
           pb.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.progressgreen)));
        }

        else if(data2<l1){
            pb.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.progressred)));
        }

        else if(p<75)
        {
                pb.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.progressyellow)));
        }

        menubutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //     Toast.makeText(getContext(), " Hello ", Toast.LENGTH_SHORT).show();

                    popupMenu = new PopupMenu(getContext(), view);
                    popupMenu.inflate(R.menu.list_menu);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            int mid=item.getItemId();
                            Intent i;
                        //    Toast.makeText(getContext(), "  " + id.get(position) + " ", Toast.LENGTH_SHORT).show();

                            if(mid==R.id.addqty)
                            {

                                i=new Intent(getContext(),AddQtyActivity.class);
                                i.putExtra("id",id.get(position));
                                i.putExtra("item",iname.get(position));
                                i.putExtra("iunit",unit.get(position));
                                getContext().startActivity(i);
                            }

                            else if(mid==R.id.usedqty)
                            {


                                i=new Intent(getContext(),DecQtyActivity.class);
                                i.putExtra("id",id.get(position));
                                i.putExtra("item",iname.get(position));
                                i.putExtra("iunit",unit.get(position));
                                getContext().startActivity(i);
                            }

                            else if(mid==R.id.edit_item)
                            {


                                i=new Intent(getContext(),UpdateItemActivity.class);
                                i.putExtra("id",id.get(position));
                                getContext().startActivity(i);
                            }

                            else if(mid==R.id.delete_item)
                            {

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("DELETE ITEM ?");
                                builder.setMessage("Are You Sure?");
                                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String log="Item " + iname.get(position) + " was Deleted";
                                        int i=id.get(position).intValue();

                                        SQLiteDatabase mydatabase = getContext().openOrCreateDatabase("ypdb",MODE_PRIVATE,null);
                                        // mydatabase.execSQL("Drop Table stock");

                                        // mydatabase.execSQL("INSERT INTO alog ('Iname','unit','LL','UL','st') VALUES('" + ak + "','kg',12,23,14);");

                                        mydatabase.execSQL("DELETE from stock where id= "+ i +";");
                                        mydatabase.execSQL("INSERT INTO alog ('log') VALUES('" + log + "');");
                                        id.remove(position);
                                        iname.remove(position);
                                        ll.remove(position);
                                        ul.remove(position);
                                        cs.remove(position);
                                        unit.remove(position);
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
