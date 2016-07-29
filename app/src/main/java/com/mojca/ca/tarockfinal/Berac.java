package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Berac extends ActionBarActivity {
    RadioGroup igralci, odprtG;
    RadioButton[] igr = new RadioButton[4];
    RadioButton odprt, zaprt, pikolo;
    Button win, lose;
    ImageButton back;
    CheckBox kontre[] = new CheckBox[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berac);

        odprtG = (RadioGroup) findViewById(R.id.group_odprt);
        igralci = (RadioGroup) findViewById(R.id.group_igralec);
        igr[0] = (RadioButton) findViewById(R.id.igr1);
        igr[1] = (RadioButton) findViewById(R.id.igr2);
        igr[2] = (RadioButton) findViewById(R.id.igr3);
        igr[3] = (RadioButton) findViewById(R.id.igr4);
        kontre[0] = (CheckBox) findViewById(R.id.kontra);
        kontre[1] = (CheckBox) findViewById(R.id.re);
        kontre[2] = (CheckBox) findViewById(R.id.sub);
        kontre[3] = (CheckBox) findViewById(R.id.mort);

        odprt = (RadioButton) findViewById(R.id.odprt);
        zaprt = (RadioButton) findViewById(R.id.zaprt);
        pikolo = (RadioButton) findViewById(R.id.pikolo);

        win = (Button) findViewById(R.id.win);
        lose = (Button) findViewById(R.id.lose);

        back = (ImageButton) findViewById(R.id.back);

        for (int i=0; i<4; i++) {
            igr[i].setText(MainActivity.igra.getIme(i));
        }

        View.OnClickListener c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                for (int i=0; i<4; i++){
                    if (id!=kontre[i].getId()){
                        kontre[i].setChecked(false);
                    }
                }
            }
        };

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == win.getId()){
                    if (odprt.isChecked()) {
                        MainActivity.tmpRezultat = 90;
                    } else if (zaprt.isChecked()){
                        MainActivity.tmpRezultat = 70;
                    } else {
                        MainActivity.tmpRezultat = 35;
                    }
                } else {
                    if (odprt.isChecked()) {
                        MainActivity.tmpRezultat = -90;
                    } else if (zaprt.isChecked()){
                        MainActivity.tmpRezultat = -70;
                    } else {
                        MainActivity.tmpRezultat = -35;
                    }
                }
                int id = igralci.getCheckedRadioButtonId();
                for (int i=0; i<4; i++) {
                    if (id == igr[i].getId()){
                        MainActivity.tmpIgralec = i;
                        break;
                    }
                }
                if (MainActivity.igra.radelc(MainActivity.tmpIgralec)){
                    MainActivity.tmpRezultat*=2;
                    if (MainActivity.tmpRezultat>0){
                        MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                    }
                }
                for (int i=0; i<4;i++){
                    if (kontre[i].isChecked()){
                        MainActivity.tmpRezultat*=(int)Math.pow(2,i+1);
                        break;
                    }
                }
                MainActivity.igra.setRazveljavi();
                MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                MainActivity.igra.dodajRadelce();
                MainActivity.tmpRezultat=0;
                MainActivity.tmpIgralec=-2;
                MainActivity.tmpSoigralec=-2;
                MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                Intent in = new Intent(Berac.this, Razpredelnica.class);
                startActivity(in);
                finish();
            }
        };

        win.setOnClickListener(l);
        lose.setOnClickListener(l);

        igr[0].setOnClickListener(c);
        igr[1].setOnClickListener(c);
        igr[2].setOnClickListener(c);
        igr[3].setOnClickListener(c);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.tmpRezultat=0;
                MainActivity.tmpIgralec=-2;
                MainActivity.tmpSoigralec=-2;
                Intent in = new Intent(Berac.this, Razpredelnica.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_berac, menu);
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
