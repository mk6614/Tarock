package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Uredi extends ActionBarActivity {

    EditText[] imena = new EditText[4];
    EditText[] radelci = new EditText[4];
    EditText[] rezultati = new EditText[4];
    ImageButton forward, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uredi);

        imena[0] = (EditText) findViewById(R.id.ime1);
        imena[1] = (EditText) findViewById(R.id.ime2);
        imena[2] = (EditText) findViewById(R.id.ime3);
        imena[3] = (EditText) findViewById(R.id.ime4);
        radelci[0] = (EditText) findViewById(R.id.radelc1);
        radelci[1] = (EditText) findViewById(R.id.radelc2);
        radelci[2] = (EditText) findViewById(R.id.radelc3);
        radelci[3] = (EditText) findViewById(R.id.radelc4);
        rezultati[0] = (EditText) findViewById(R.id.rezultat1);
        rezultati[1] = (EditText) findViewById(R.id.rezultat2);
        rezultati[2] = (EditText) findViewById(R.id.rezultat3);
        rezultati[3] = (EditText) findViewById(R.id.rezultat4);

        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);

        for (int i=0; i<4; i++){
            imena[i].setText(MainActivity.igra.getIme(i));
            radelci[i].setText(MainActivity.igra.getRadelc(i));
            rezultati[i].setText(MainActivity.igra.getRezultat(i));
            rezultati[i].scrollTo(0, rezultati[i].getBottom());
            }

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    MainActivity.igra.setIme(i, imena[i].getText().toString());
                    if (!MainActivity.igra.setRadelc(i, radelci[i].getText().toString())){
                        Toast.makeText(Uredi.this, "vpisite stevilo", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (!MainActivity.igra.setRezultat(i, rezultati[i].getText().toString())){
                        Toast.makeText(Uredi.this, "vpisite stevilo", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (i==3){

                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                        Intent in = new Intent(Uredi.this, Razpredelnica.class);
                        startActivity(in);
                        finish();
                    }

                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Uredi.this, Razpredelnica.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uredi, menu);
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
