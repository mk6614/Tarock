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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class NovaIgra extends ActionBarActivity {

    EditText[] ime = new EditText[4];
    ImageButton back, forward;
    EditText imeIgre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_igra);

        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        ime[0] = (EditText) findViewById(R.id.ime1);
        ime[1] = (EditText) findViewById(R.id.ime2);
        ime[2] = (EditText) findViewById(R.id.ime3);
        ime[3] = (EditText) findViewById(R.id.ime4);
        imeIgre = (EditText) findViewById(R.id.imeIgre);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NovaIgra.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imeIgre.getText().toString().trim().length()!=0) {
                    Log.i("problem", "tu nej bi se igra shranla");
                    String[] x = new String[4];
                    for (int i = 0; i < 4; i++) {
                        x[i] = ime[i].getText().toString();
                    }
                    MainActivity.igra = new Igra(x, imeIgre.getText().toString());
                    MainActivity.list.add(imeIgre.getText().toString());
                    SharedPreferences ss = getSharedPreferences("imenaIger", Context.MODE_PRIVATE);
                    MainActivity.saveImenaIger(ss);
                    MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                    Log.i("problem", "tu nej bi se igra shranla");
                    Intent in = new Intent(NovaIgra.this, Pravila.class);
                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(NovaIgra.this, "pozabili ste vpisati ime igre", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nova_igra, menu);
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
