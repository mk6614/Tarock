package com.mojca.ca.tarockfinal;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


public class Napovedi extends ActionBarActivity {
    CheckBox[] napoved = new CheckBox[4];
    CheckBox[] win = new CheckBox[4];
    CheckBox[] lose = new CheckBox[4];
    ImageButton forward, back;
    int[] vrednost = new int[4];
    String[] imena = new String[4];
    CheckBox[] kontra = new CheckBox[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napovedi);



        vrednost[0]= 10;
        vrednost[1] = 10;
        vrednost[2] = 25;
        vrednost[3] = 10;

        imena[0] = "kralji: ";
        imena[1] = "kralj ultima: ";
        imena[2] = "pagat ultima: ";
        imena[3] = "trula: ";

        kontra[0]= (CheckBox) findViewById(R.id.kontra);
        kontra[1]= (CheckBox) findViewById(R.id.re);
        kontra[2]= (CheckBox) findViewById(R.id.sub);
        kontra[3]= (CheckBox) findViewById(R.id.mort);
        napoved[0] = (CheckBox) findViewById(R.id.nKralji);
        napoved[1] = (CheckBox) findViewById(R.id.nKraljU);
        napoved[2] = (CheckBox) findViewById(R.id.nPagatU);
        napoved[3] = (CheckBox) findViewById(R.id.nTrula);
        win[0] = (CheckBox) findViewById(R.id.winKralji);
        win[1] = (CheckBox) findViewById(R.id.winKraljU);
        win[2] = (CheckBox) findViewById(R.id.winPagatU);
        win[3] = (CheckBox) findViewById(R.id.winTrula);
        lose[0] = (CheckBox) findViewById(R.id.loseKralji);
        lose[1] = (CheckBox) findViewById(R.id.loseKraljU);
        lose[2] = (CheckBox) findViewById(R.id.losePagatU);
        lose[3] = (CheckBox) findViewById(R.id.loseTrula);

        for (int i=0; i<4; i++){
            switch (Razlika.nap[i]){
                case 0:
                    break;
                case 1:
                    win[i].setChecked(true);
                    break;
                case 2:
                    win[i].setChecked(true);
                    napoved[i].setChecked(true);
                    break;
                case 3:
                    lose[i].setChecked(true);
                    break;
                case 4:
                    lose[i].setChecked(true);
                    napoved[i].setChecked(true);
                    break;
                default:
                    break;
            }
        }
        if (Razlika.nap[4]!=-1){
            kontra[Razlika.nap[4]].setChecked(true);
        }

        forward = (ImageButton) findViewById(R.id.forward);
        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4;i++) {
                    kontra[i].setChecked(false);
                    napoved[i].setChecked(false);
                    win[i].setChecked(false);
                    lose[i].setChecked(false);
                    Razlika.sizpis="";
                    Razlika.sizpisst="";
                    Intent in = new Intent(Napovedi.this, Razlika.class);
                    startActivity(in);
                    finish();
                }
            }
        });

        View.OnClickListener listenerk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId() != kontra[i].getId()){
                        kontra[i].setChecked(false);
                    }
                }
            }
        };

        View.OnClickListener listenerw = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId() == win[i].getId()) {
                        lose[i].setChecked(false);
                    }
                }
            }
        };

        View.OnClickListener listenerl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId() == lose[i].getId()) {
                        win[i].setChecked(false);
                    }
                }
            }
        };

        for (int i=0; i<4; i++){
            win[i].setOnClickListener(listenerw);
            lose[i].setOnClickListener(listenerl);
        }

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Razlika.sizpis = "";
                Razlika.sizpisst = "";
                Razlika.napovediVsota=0;
                for (int i=0; i<4;i++){

                    if (win[i].isChecked()){
                        if (napoved[i].isChecked()){
                            Razlika.sizpis+=String.format("%-20s%n",imena[i]);
                            Razlika.sizpisst+=String.format("%10d%n",vrednost[i]*2);
                            Razlika.napovediVsota+=(vrednost[i]*2);
                            Razlika.nap[i]=2;
                        } else {
                            Razlika.napovediVsota+=(vrednost[i]);
                            Razlika.sizpis+=String.format("%-20s%n",imena[i]);
                            Razlika.sizpisst+=String.format("%10d%n",vrednost[i]);
                            Razlika.nap[i]=1;
                        }
                    } else if (lose[i].isChecked()) {
                        if (napoved[i].isChecked()){
                            Razlika.sizpis+=String.format("%-20s%n",imena[i]);
                            Razlika.sizpisst+=String.format("%10d%n",vrednost[i]*-2);
                            Razlika.napovediVsota+=(vrednost[i]*-2);
                            Razlika.nap[i]=4;
                        } else {
                            Razlika.napovediVsota+=(vrednost[i]*-1);
                            Razlika.sizpis+=String.format("%-20s%n",imena[i]);
                            Razlika.sizpisst+=String.format("%10d%n",vrednost[i]*-1);
                            Razlika.nap[i]=3;
                        }
                    }
                    if (kontra[i].isChecked()){
                        Razlika.sizpis+=String.format("%-20s%n","kontra");
                        Razlika.sizpisst+=String.format("%10s%n", kontra[i].getText().toString());
                        Razlika.kontra=i+1;
                        Razlika.nap[4]=i;
                    }
                }
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_napovedi, menu);
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
