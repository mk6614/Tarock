package com.mojca.ca.tarockfinal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.tooltip.Tooltip;


public class Razpredelnica extends ActionBarActivity {
    Button[] imena = new Button[4];
    EditText[] radelci = new EditText[4];
    EditText[] rezultati = new EditText[4];
    Tooltip.TooltipView[][] ttv = new Tooltip.TooltipView[4][4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razpredelnica);


        MainActivity.tmpRezultat = 0;
        MainActivity.tmpIgralec = -2;
        MainActivity.tmpSoigralec = -2;

        imena[0] = (Button) findViewById(R.id.ime1);
        imena[1] = (Button) findViewById(R.id.ime2);
        imena[2] = (Button) findViewById(R.id.ime3);
        imena[3] = (Button) findViewById(R.id.ime4);
        radelci[0] = (EditText) findViewById(R.id.radelc1);
        radelci[1] = (EditText) findViewById(R.id.radelc2);
        radelci[2] = (EditText) findViewById(R.id.radelc3);
        radelci[3] = (EditText) findViewById(R.id.radelc4);
        rezultati[0] = (EditText) findViewById(R.id.rezultat1);
        rezultati[1] = (EditText) findViewById(R.id.rezultat2);
        rezultati[2] = (EditText) findViewById(R.id.rezultat3);
        rezultati[3] = (EditText) findViewById(R.id.rezultat4);






        View.OnLongClickListener l = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent in = new Intent(Razpredelnica.this, Mondfang.class);
                MainActivity.igra.setRazveljavi();
                int id = v.getId();
                switch (id) {
                    case (R.id.ime1):
                        MainActivity.igra.setRezultat(0, MainActivity.igra.getMondfang());
                        break;
                    case (R.id.ime2):
                        MainActivity.igra.setRezultat(1, MainActivity.igra.getMondfang());
                        break;
                    case (R.id.ime3):
                        MainActivity.igra.setRezultat(2, MainActivity.igra.getMondfang());
                        break;
                    case (R.id.ime4):
                        MainActivity.igra.setRezultat(3, MainActivity.igra.getMondfang());
                        break;
                    default:
                        break;
                }
                startActivity(in);
                finish();
                return true;
            }
        };
        View.OnClickListener c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case (R.id.ime1):
                        MainActivity.tmpIgralec = 0;
                        break;
                    case (R.id.ime2):
                        MainActivity.tmpIgralec = 1;
                        break;
                    case (R.id.ime3):
                        MainActivity.tmpIgralec = 2;
                        break;
                    case (R.id.ime4):
                        MainActivity.tmpIgralec = 3;
                        break;

                }
                Intent in = new Intent(Razpredelnica.this, IzbiraIgre.class);
                startActivity(in);
                finish();
            }
        };

        for (int i = 0; i < 4; i++) {
            imena[i].setText(MainActivity.igra.getIme(i));
            imena[i].setOnClickListener(c);
            imena[i].setOnLongClickListener(l);
            radelci[i].setFocusable(false);
            radelci[i].setClickable(false);
            rezultati[i].setFocusable(false);
            rezultati[i].setClickable(false);
            radelci[i].setText(MainActivity.igra.getRadelc(i));
            rezultati[i].setText(MainActivity.igra.getRezultat(i));
            rezultati[i].scrollTo(0, rezultati[i].getBottom());
            Log.i("vpis", "klic");
        }

        int klop = MainActivity.igra.isKlop();
        if(klop==4){
            final Dialog dialog = new Dialog(Razpredelnica.this);
            dialog.setContentView(R.layout.dialog);
            dialog.setTitle("KLOP");

            // set the custom dialog components - text, image and button
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText("Za?etek s klopom?");

            Button k = (Button) dialog.findViewById(R.id.yes);
            Button s = (Button) dialog.findViewById(R.id.no);

            k.setText("DA");
            s.setText("NE");

            k.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Razpredelnica.this, klop.class);
                    startActivity(in);
                    dialog.dismiss();
                    finish();
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });



            dialog.show();
        }else if(klop>=0 && klop<4 && MainActivity.igra.getKlop()){
            final Dialog dialog = new Dialog(Razpredelnica.this);
            dialog.setContentView(R.layout.dialog);
            dialog.setTitle("KLOP");

            // set the custom dialog components - text, image and button
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText(MainActivity.igra.getIme(klop) + ": " + MainActivity.igra.getRezultatI(klop) + "\n KLOP ali SOLO BREZ?");

            Button k = (Button) dialog.findViewById(R.id.yes);
            Button s = (Button) dialog.findViewById(R.id.no);

            k.setText("KLOP");
            s.setText("SOLO");

            k.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Razpredelnica.this, klop.class);
                    startActivity(in);
                    dialog.dismiss();
                    finish();
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Razpredelnica.this, Razlika.class);
                    MainActivity.tmpRezultat = 80;
                    MainActivity.tmpSoigralec = -1;
                    startActivity(in);
                    dialog.dismiss();
                    finish();
                }
            });



            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_razpredelnica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.uredi) {
            Intent in = new Intent(Razpredelnica.this, Uredi.class);
            startActivity(in);
            this.finish();
            return true;
        }
        if (id == R.id.razveljavi) {
            MainActivity.igra.razveljavi();
            for (int i = 0; i < 4; i++) {
                radelci[i].setFocusable(false);
                radelci[i].setClickable(false);
                rezultati[i].setFocusable(false);
                rezultati[i].setClickable(false);
                radelci[i].setText(MainActivity.igra.getRadelc(i));
                rezultati[i].setText(MainActivity.igra.getRezultat(i));
                rezultati[i].scrollTo(0, rezultati[i].getBottom());
            }
        }
        if (id == R.id.koncaj) {
            for(int i=0; i<4; i++) {
                MainActivity.igra.setRezultat(i, MainActivity.igra.getRadelcInt(i)*-1);
                for (int j=0; j<MainActivity.igra.getRadelcInt(i); j++){
                    MainActivity.igra.brisiRadelc(i);
                }
            }
            for (int i = 0; i < 4; i++) {
                imena[i].setText(MainActivity.igra.getIme(i));
                radelci[i].setFocusable(false);
                radelci[i].setClickable(false);
                rezultati[i].setFocusable(false);
                rezultati[i].setClickable(false);
                radelci[i].setText(MainActivity.igra.getRadelc(i));
                rezultati[i].setText(MainActivity.igra.getRezultat(i));
                rezultati[i].scrollTo(0, rezultati[i].getBottom());
                Log.i("vpis", "klic");
            }
        }
        if (id==R.id.shrani){
            MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
            Toast.makeText(Razpredelnica.this, "igra se shranjuje\ntudi sama", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(Razpredelnica.this, MainActivity.class);
        startActivity(in);
        this.finish();
    }


}
