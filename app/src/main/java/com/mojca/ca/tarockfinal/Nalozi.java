package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;


public class Nalozi extends ActionBarActivity {
    ListView l;
    ArrayAdapter a;
    // ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nalozi);
        /*for (int i=0; i<MainActivity.IGRE.length; i++){
            list.add(MainActivity.IGRE[i]);
            if (!MainActivity.IGRE[i].equals(""));
            Log.i("sharni", "string "+Integer.toString(i)+": >"+MainActivity.IGRE[i]+"<");
        }*/
        l = (ListView) findViewById(R.id.list);
        a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.list);
        l.setAdapter(a);



        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.popout, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT);
                ImageButton btnDismiss = (ImageButton)popupView.findViewById(R.id.back);
                TextView izpis = (TextView) popupView.findViewById(R.id.opis);

                izpis.setText("izbrisi igro?");

                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }
                });
                ImageButton ok = (ImageButton) popupView.findViewById(R.id.forward);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //SharedPreferences s = getSharedPreferences()

                        popupWindow.dismiss();
                        SharedPreferences s = getSharedPreferences(MainActivity.list.get(position), Context.MODE_PRIVATE);
                        SharedPreferences.Editor e = s.edit();
                        e.clear();
                        e.commit();
                        MainActivity.list.remove(position);
                        SharedPreferences ss = getSharedPreferences("imenaIger", Context.MODE_PRIVATE);
                        MainActivity.saveImenaIger(ss);
                        Log.i("problem", "tu nej bi se igra shranla");
                        a.notifyDataSetChanged();

                    }
                });

                popupWindow.showAtLocation(l, Gravity.CENTER, 0, 0);

                return true;
            }
        });

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences s = getSharedPreferences( MainActivity.list.get(position), Context.MODE_PRIVATE);
                if (MainActivity.igra == null) {
                    MainActivity.igra = new Igra(null, "");
                }
                MainActivity.igra.naloziIgro(s, MainActivity.list.get(position));
                Log.i("problem", MainActivity.list.get(position));
                Intent in = new Intent(Nalozi.this, Razpredelnica.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nalozi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
