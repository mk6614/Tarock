package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.prefs.Preferences;


public class MainActivity extends ActionBarActivity {
    Button nadaljuj, novaIgra, nalozi, opcije;
    public static Igra igra;
    public static int tmpIgralec, tmpSoigralec, tmpRezultat;
    public static ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tmpIgralec=-2;
        tmpSoigralec=-2;
        tmpRezultat=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nadaljuj = (Button) findViewById(R.id.nadaljuj);
        novaIgra = (Button) findViewById(R.id.nova_igra);
        nalozi = (Button) findViewById(R.id.nalozi);
        opcije = (Button) findViewById(R.id.opcije);





        novaIgra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(MainActivity.this, NovaIgra.class);
                startActivity(in);
            }
        });

        nalozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Nalozi.class);
                startActivity(in);
            }
        });
        list = new ArrayList<String>();
        Log.i("shrani", "pred nalaganjem");
        SharedPreferences s = getSharedPreferences("imenaIger", Context.MODE_PRIVATE);
        loadImenaIger(s);
        Log.i("shrani", "po nalaganju: >" + list.get(0) + "<");

        nadaljuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() >= 0) {
                    MainActivity.igra = new Igra(null, list.get(list.size() - 1));
                    SharedPreferences ss = getSharedPreferences(list.get(list.size() - 1), Context.MODE_PRIVATE);
                    igra.naloziIgro(ss, list.get(list.size() - 1));
                    Intent in = new Intent(MainActivity.this, Razpredelnica.class);
                    startActivity(in);
                } else {
                    Toast.makeText(MainActivity.this, "ni shranjenih iger", Toast.LENGTH_SHORT).show();
                }
            }
        });

        opcije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Moznosti.class);
                startActivity(in);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static void saveImenaIger(SharedPreferences s) {
        SharedPreferences.Editor e = s.edit();
        e.clear();
        String str="";
        for (int i=0; i<list.size()-1; i++){
            str+=list.get(i)+"-";
        }
        if (list.size()>=0) {
            str += list.get(list.size() - 1);
        }
        e.putString("igre", str);

        e.commit();
    }
    public static void loadImenaIger(SharedPreferences s){
        try {
            Log.i("shrani", "ni problema");
            String[] IGRE = s.getString("igre", "").split("-");
            for (int i=0; i<IGRE.length; i++){
                list.add(IGRE[i]);
            }
        } catch (Exception e){
            Log.i("shrani", "problem");
            Log.i("problem", e.toString());

        }
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
