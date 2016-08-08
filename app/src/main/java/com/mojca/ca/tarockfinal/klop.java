package com.mojca.ca.tarockfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;


public class klop extends ActionBarActivity {

    ImageButton[] l5 = new ImageButton[4];
    ImageButton[] l10 = new ImageButton[4];
    ImageButton[] r5 = new ImageButton[4];
    ImageButton[] r10 = new ImageButton[4];
    int rezultat[] = new int[4];
    TextView[] imena = new TextView[4];
    TextView[] rez = new TextView[4];
    ImageButton back, forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klop);

        imena[0] = (TextView) findViewById(R.id.ime1);
        imena[1] = (TextView) findViewById(R.id.ime2);
        imena[2] = (TextView) findViewById(R.id.ime3);
        imena[3] = (TextView) findViewById(R.id.ime4);

        l5[0] = (ImageButton) findViewById(R.id.l15);
        l5[1] = (ImageButton) findViewById(R.id.l25);
        l5[2] = (ImageButton) findViewById(R.id.l35);
        l5[3] = (ImageButton) findViewById(R.id.l45);

        l10[0] = (ImageButton) findViewById(R.id.l110);
        l10[1] = (ImageButton) findViewById(R.id.l210);
        l10[2] = (ImageButton) findViewById(R.id.l310);
        l10[3] = (ImageButton) findViewById(R.id.l410);

        r5[0] = (ImageButton) findViewById(R.id.r15);
        r5[1] = (ImageButton) findViewById(R.id.r25);
        r5[2] = (ImageButton) findViewById(R.id.r35);
        r5[3] = (ImageButton) findViewById(R.id.r45);


        r10[0] = (ImageButton) findViewById(R.id.r110);
        r10[1] = (ImageButton) findViewById(R.id.r210);
        r10[2] = (ImageButton) findViewById(R.id.r310);
        r10[3] = (ImageButton) findViewById(R.id.r410);

        rez[0] = (TextView) findViewById(R.id.razlika1);
        rez[1] = (TextView) findViewById(R.id.razlika2);
        rez[2] = (TextView) findViewById(R.id.razlika3);
        rez[3] = (TextView) findViewById(R.id.razlika4);

        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);

        View.OnClickListener less5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId()==l5[i].getId()){
                        rezultat[i]-=5;
                        rez[i].setText(Integer.toString(rezultat[i]));
                        if (rezultat[i]<0){
                            rezultat[i]=-5;
                            rez[i].setText("prazen");
                        } else if (rezultat[i]>35){
                            rezultat[i]=40;
                            rez[i].setText("polen");
                        }
                    }
                }
            }
        };

        View.OnClickListener less10 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId()==l10[i].getId()){
                        rezultat[i]-=10;
                        rez[i].setText(Integer.toString(rezultat[i]));
                        if (rezultat[i]<0){
                            rezultat[i]=-5;
                            rez[i].setText("prazen");
                        } else if (rezultat[i]>35){
                            rezultat[i]=40;
                            rez[i].setText("polen");
                        }
                    }
                }
            }
        };

        View.OnClickListener more5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId()==r5[i].getId()){
                        rezultat[i]+=5;
                        rez[i].setText(Integer.toString(rezultat[i]));
                        if (rezultat[i]<0){
                            rezultat[i]=-5;
                            rez[i].setText("prazen");
                        } else if (rezultat[i]>35){
                            rezultat[i]=40;
                            rez[i].setText("polen");
                        }
                    }
                }
            }
        };

        View.OnClickListener more10 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if (v.getId()==r10[i].getId()){
                        rezultat[i]+=10;
                        rez[i].setText(Integer.toString(rezultat[i]));
                        if (rezultat[i]<0){
                            rezultat[i]=-5;
                            rez[i].setText("prazen");
                        } else if (rezultat[i]>35){
                            rezultat[i]=40;
                            rez[i].setText("polen");
                        }
                    }
                }
            }
        };


        for (int i=0; i<4; i++){
            l5[i].setOnClickListener(less5);
            l10[i].setOnClickListener(less10);
            r5[i].setOnClickListener(more5);
            r10[i].setOnClickListener(more10);
            rezultat[i]=0;
            imena[i].setText(MainActivity.igra.getIme(i));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(klop.this, IzbiraIgre.class);
                startActivity(in);
                finish();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="";
                for (int i=0; i<4; i++){
                    s+= MainActivity.igra.getIme(i) + ": " + rez[i].getText().toString()+"\n";
                }
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popout, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT);
                TextView izpis = (TextView) popupView.findViewById(R.id.opis);
                izpis.setText(s);
                ImageButton btnDismiss = (ImageButton)popupView.findViewById(R.id.back);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ImageButton ok = (ImageButton) popupView.findViewById(R.id.forward);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.igra.setRazveljavi();
                        for (int j=0; j<4; j++){
                            if (rezultat[j]<0) {
                                rezultat[j]= -70;
                                Log.i("problem:",Integer.toString(j)+": "+Integer.toString(rezultat[j]));
                            } else if (rezultat[j]>35) {
                                rezultat[j] = 70;
                                Log.i("problem:",Integer.toString(j)+": "+Integer.toString(rezultat[j]));
                            }
                            if (MainActivity.igra.radelciKlop()) {
                                if (MainActivity.igra.radelc(j)) {
                                    rezultat[j] *= 2;
                                    if (rezultat[j] < 0) {
                                        MainActivity.igra.brisiRadelc(j);
                                    }
                                }
                            }
                            Log.i("problem:",Integer.toString(j)+": "+Integer.toString(rezultat[j]));
                            MainActivity.igra.setRezultat(j, rezultat[j]*-1);

                        }
                        MainActivity.tmpRezultat=0;
                        MainActivity.tmpIgralec=-2;
                        MainActivity.tmpSoigralec=-2;
                        MainActivity.igra.dodajRadelce();
                        MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                        Intent in = new Intent(klop.this, Razpredelnica.class);
                        popupWindow.dismiss();
                        startActivity(in);
                        finish();
                    }
                });

                popupWindow.showAtLocation(forward, Gravity.CENTER, 0, 0);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_klop, menu);
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
