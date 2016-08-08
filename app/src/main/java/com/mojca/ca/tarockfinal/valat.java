package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class valat extends ActionBarActivity {
    AutoResizeTextView text;
    Button yes, no;
    RadioGroup group;
    RadioButton[] igralci = new RadioButton[4];
    valatDrevo d;
    element e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valat);
        MainActivity.tmpIgralec=-2;

        igralci[0] = (RadioButton) findViewById(R.id.igr1);
        igralci[1] = (RadioButton) findViewById(R.id.igr2);
        igralci[2] = (RadioButton) findViewById(R.id.igr3);
        igralci[3] = (RadioButton) findViewById(R.id.igr4);
        text =(AutoResizeTextView) findViewById(R.id.text);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);


        group = (RadioGroup) findViewById(R.id.group_igralec);

        for (int i=0; i<4; i++){
            igralci[i].setText(MainActivity.igra.getIme(i));
        }

        group.setVisibility(View.INVISIBLE);
        d = new valatDrevo();
        e = d.koren;
        text.setText(d.getText(e));

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == yes.getId()) {
                    e = d.getRight(e);
                } else if (v.getId() == no.getId() ){
                    e = d.getleft(e);
                }

                if (e == null) {
                    Log.i("valatx", "exit");
                    Intent ix = new Intent(valat.this, Razpredelnica.class);
                    MainActivity.tmpIgralec=-2;
                    MainActivity.tmpSoigralec=-2;
                    MainActivity.tmpRezultat=0;
                    startActivity(ix);
                    finish();
                    return;
                }
                if(d.getText(e).equals("YOU'RE IMAGE IS SAVED :D")) {
                    Intent in = new Intent(valat.this, Selfie.class);
                    startActivity(in);

                }
                text.setText(d.getText(e));
                int k = d.getK(e);
                if (k>0) {
                    if (k==2){
                        for (int i=0; i<4; i++){
                            if(igralci[i].isChecked()){
                                MainActivity.tmpIgralec=i;
                                Log.i("valatx", "igralec " + i);
                            }
                            igralci[i].setChecked(false);
                        }
                    } else if (k==3){
                        for (int i=0; i<4; i++){
                            if(igralci[i].isChecked()){
                                MainActivity.tmpSoigralec=i;
                                Log.i("valatx", "soigralec " + i);
                            }
                            igralci[i].setChecked(false);
                        }
                    }
                    group.setVisibility(View.INVISIBLE);
                    yes.setText(d.getLeftString(e));
                    no.setText(d.getRightString(e));
                    yes.setBackgroundResource(android.R.color.transparent);
                    no.setBackgroundResource(android.R.color.transparent);
                    if (k== 250 || k == 500) {
                        MainActivity.tmpRezultat=k;
                        if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        MainActivity.igra.dodajRadelce();
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                    } else if(k==100){
                        MainActivity.tmpRezultat=MainActivity.igra.getBarvniValat();
                        if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        MainActivity.igra.dodajRadelce();
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                    }
                } else {
                    if (k==-1){
                        group.setVisibility(View.VISIBLE);
                    } else {
                        group.setVisibility(View.INVISIBLE);
                    }
                    if (k== -250 || k == -500) {
                        MainActivity.tmpRezultat=k;
                        if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        MainActivity.igra.dodajRadelce();
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                    } else if (k==-100){
                        MainActivity.tmpRezultat=MainActivity.igra.getBarvniValat();
                        if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        MainActivity.igra.dodajRadelce();
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                    }
                    yes.setText("");
                    no.setText("");
                    yes.setBackgroundResource(R.drawable.arrow_right);
                    no.setBackgroundResource(R.drawable.arrow_left);
                }/*
                    case 2:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("");
                        no.setText("");
                        yes.setBackgroundResource(R.drawable.arrow_right);
                        no.setBackgroundResource(R.drawable.arrow_left);
                        break;
                    case 3:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("I lost...");
                        no.setText("I won!");
                        yes.setBackgroundResource(android.R.color.transparent);
                        no.setBackgroundResource(android.R.color.transparent);
                        break;
                    case 4:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("I really did it!!");
                        no.setText("just kidding...");
                        yes.setBackgroundResource(android.R.color.transparent);
                        no.setBackgroundResource(android.R.color.transparent);
                        break;
                    case 5:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("yes");
                        no.setText("no");
                        yes.setBackgroundResource(android.R.color.transparent);
                        no.setBackgroundResource(android.R.color.transparent);
                        if (MainActivity.tmpIgralec >= 0) {
                            int x = group.getCheckedRadioButtonId();
                            for (int i = 0; i < 4; i++) {
                                if (x == igralci[i].getId()) {
                                    MainActivity.tmpSoigralec = i;
                                    Log.i("problem", "soigralec: "+Integer.toString(i));
                                    igralci[i].setChecked(false);
                                }
                            }

                        } else {
                            int x = group.getCheckedRadioButtonId();
                            for (int i = 0; i < 4; i++) {
                                if (x == igralci[i].getId()) {
                                    MainActivity.tmpIgralec = i;
                                    igralci[i].setChecked(false);
                                    Log.i("problem", "igralec: "+Integer.toString(i));
                                }
                            }
                        }
                        break;
                    case -1:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("");
                        no.setText("");
                        yes.setBackgroundResource(R.drawable.arrow_right);
                        no.setBackgroundResource(R.drawable.arrow_left);
                        /*if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        break;
                    case -2:
                        //e = d.getRight(e);
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("");
                        no.setText("");
                        yes.setBackgroundResource(R.drawable.arrow_right);
                        no.setBackgroundResource(R.drawable.arrow_left);
                        break;
                    case 6:
                        Intent in = new Intent(valat.this, Razpredelnica.class);
                        startActivity(in);
                        finish();
                        break;
                    default:
                        group.setVisibility(View.INVISIBLE);
                        yes.setText("yes");
                        no.setText("no");
                        yes.setBackgroundResource(android.R.color.transparent);
                        no.setBackgroundResource(android.R.color.transparent);
                        MainActivity.tmpRezultat = d.getK(e);
                        MainActivity.igra.setRazveljavi();
                        if (MainActivity.igra.radelc((MainActivity.tmpIgralec))) {
                            MainActivity.tmpRezultat *= 2;
                            if (MainActivity.tmpRezultat > 0) {
                                MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                            }
                        }
                        MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                        if (MainActivity.tmpSoigralec >= 0) {
                            MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                        }
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                        MainActivity.tmpRezultat = 0;
                        MainActivity.tmpSoigralec = -2;
                        MainActivity.tmpIgralec = -2;
                        break;
                }*/
            }
        };

        yes.setOnClickListener(l);
        no.setOnClickListener(l);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_valat, menu);
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
