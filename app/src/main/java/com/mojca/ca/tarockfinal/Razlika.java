package com.mojca.ca.tarockfinal;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Razlika extends ActionBarActivity {
    RadioButton igralec[] = new RadioButton[4];
    RadioButton soigralec[] = new RadioButton[4];
    Button napovedi;
    CheckBox razlika;
    ImageButton l5, l10,r5, r10, back, forward;
    TextView izpis;
    RadioGroup igralci, soigralci;
    public static String sizpis="";
    public static String sizpisst="";
    public static int napovediVsota = 0;
    int igra;
    int razlikaint = 0;
    int plus=0;
    public static int kontra=0;
    public static int[] nap = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razlika);
        //izpis = (TextView) findViewById(R.id.izpis);

        Intent intent = getIntent();
        igra = intent.getIntExtra("igra", 0);

        for (int i=0; i<4;i++){
            nap[i]=0;
        }
        nap[4]=-1;

        igralec[0] = (RadioButton) findViewById(R.id.igr1);
        igralec[1] = (RadioButton) findViewById(R.id.igr2);
        igralec[2] = (RadioButton) findViewById(R.id.igr3);
        igralec[3] = (RadioButton) findViewById(R.id.igr4);
        soigralec[0] = (RadioButton) findViewById(R.id.soigr1);
        soigralec[1] = (RadioButton) findViewById(R.id.soigr2);
        soigralec[2] = (RadioButton) findViewById(R.id.soigr3);
        soigralec[3] = (RadioButton) findViewById(R.id.soigr4);
        napovedi = (Button) findViewById(R.id.napovedi);

        razlika = (CheckBox) findViewById(R.id.razlika);
        l5 = (ImageButton) findViewById(R.id.l5);
        l10 = (ImageButton) findViewById(R.id.l10);
        r5 = (ImageButton) findViewById(R.id.r5);
        r10 = (ImageButton) findViewById(R.id.r10);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        igralci = (RadioGroup) findViewById(R.id.group_igralec);
        soigralci = (RadioGroup) findViewById(R.id.group_soigralec);

        for (int i=0; i<4; i++){
            igralec[i].setText(MainActivity.igra.getIme(i));
            soigralec[i].setText(MainActivity.igra.getIme(i));
        }

        napovedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Razlika.this, Napovedi.class);
                startActivityForResult(in, 1);

            }
        });

        if (MainActivity.tmpIgralec>=0) igralec[MainActivity.tmpIgralec].setChecked(true);

        if(MainActivity.tmpSoigralec==-1){
            soigralci.setFocusable(false);
            soigralci.setClickable(false);
            for(int i=0; i<4; i++) {
                soigralec[i].setVisibility(View.INVISIBLE);
            }

        }

        View.OnClickListener pluss = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.l5){
                    plus = -5;
                } else if (v.getId() == R.id.l10){
                    plus = -10;
                } else if (v.getId() == R.id.r5){
                    plus = 5;
                } else if (v.getId() == R.id.r10){
                    plus = 10;
                }
                razlikaint += plus;
                if (razlikaint<-35){
                    razlikaint=-35;
                } else if (razlikaint>35){
                    razlikaint=35;
                }

                razlika.setText(Integer.toString(razlikaint));
                razlika.setTextColor(Color.BLACK);

                if (razlikaint==0){
                    //razlika.setEnabled(true);
                    Log.i("problem:", "tocke: enabled");
                }
                if (razlikaint<0) {
                    razlika.setChecked(true);
                    // razlika.setEnabled(false);
                } else {
                    razlika.setChecked(false);
                    //razlika.setEnabled(false);
                }

            }
        };



        l5.setOnClickListener(pluss);
        l10.setOnClickListener(pluss);
        r5.setOnClickListener(pluss);
        r10.setOnClickListener(pluss);

        razlika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("problem:", "clicked!!  " + Boolean.toString(razlika.isEnabled()));
                Log.i("problem:", Boolean.toString(razlika.isChecked()));
                if (razlikaint != 0) razlika.toggle();
                Log.i("problem:", Boolean.toString(razlika.isChecked()));

            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("problem:", "clicked forward!!");

                for (int i = 0; i < 4; i++) {
                    if (igralec[i].isChecked()) {
                        MainActivity.tmpIgralec = i;
                    }
                    if (soigralec[i].isChecked()) {
                        MainActivity.tmpSoigralec = i;
                    }
                }
                if (MainActivity.tmpSoigralec!=-2) {
                    if (razlikaint < 0 || (razlikaint == 0 && razlika.isChecked())) {
                        MainActivity.tmpRezultat *= -1;
                    }
                    Log.i("problem:", "vsota napovedi: "+Integer.toString(napovediVsota));

                    Log.i("problem:", "vsota : "+Integer.toString(MainActivity.tmpRezultat));

                    LayoutInflater layoutInflater
                            = (LayoutInflater)getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View popupView = layoutInflater.inflate(R.layout.popout, null);
                    final PopupWindow popupWindow = new PopupWindow(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT);
                    izpis = (TextView) popupView.findViewById(R.id.opis);
                     TextView izpisst = (TextView) popupView.findViewById(R.id.opisst);
                    String s = "";
                    String st="";

                    //ime igralca
                    s+=String.format("%-20s%n", "igralec");
                    st+=String.format("%10s%n", MainActivity.igra.getIme(MainActivity.tmpIgralec));
                    //ime soigralca
                    if (MainActivity.tmpSoigralec >= 0 && MainActivity.tmpIgralec != MainActivity.tmpSoigralec) {
                        s+=String.format("%-20s%n","soigralec");
                        st+=String.format("%10s%n", MainActivity.igra.getIme(MainActivity.tmpSoigralec));
                    } else if (MainActivity.tmpIgralec == MainActivity.tmpSoigralec){
                        s+=String.format("%-20s%n","");
                        st+=String.format("%10s%n","zarufan");
                    }
                    s+=String.format("%-20s%n", "igra");
                    st+=String.format("%10d%n", igra);
                    s+=String.format("%-20s%n","razlika");
                    st+=String.format("%10d%n", razlikaint);
                    s+=sizpis;
                    st+=sizpisst;
                    if (MainActivity.igra.radelc(MainActivity.tmpIgralec)) {
                        s +=String.format("%-20s%n", "rezultat (radelc)");
                        st+=String.format("%10d%n",((MainActivity.tmpRezultat + razlikaint + napovediVsota)* (int)Math.pow(2, (double)(kontra+1))));
                    } else {
                        s+=String.format("%-20s%n","rezultat");
                        st+=String.format("%10d%n", ((MainActivity.tmpRezultat + razlikaint + napovediVsota)* (int)Math.pow(2, (double)(kontra))));
                    }
                    izpis.setText(s);
                    izpisst.setText(st);
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
                            MainActivity.tmpRezultat+=razlikaint;
                            MainActivity.tmpRezultat+=napovediVsota;
                            if (MainActivity.igra.radelc(MainActivity.tmpIgralec)) {
                                MainActivity.tmpRezultat*=(int)Math.pow(2, (double) (kontra + 1));
                                if (razlikaint > 0 || (razlikaint == 0 && !razlika.isChecked())) {
                                    MainActivity.igra.brisiRadelc(MainActivity.tmpIgralec);
                                }
                            } else {
                                MainActivity.tmpRezultat *= (int) Math.pow(2, (double) (kontra));
                            }
                            MainActivity.igra.setRezultat(MainActivity.tmpIgralec, MainActivity.tmpRezultat);
                            if (MainActivity.tmpSoigralec >= 0 && MainActivity.tmpIgralec != MainActivity.tmpSoigralec) {
                                MainActivity.igra.setRezultat(MainActivity.tmpSoigralec, MainActivity.tmpRezultat);
                            }
                            Log.i("problem:", "rezultat: " + MainActivity.tmpRezultat);
                            Log.i("problem: ", "igr in soigr:" + MainActivity.tmpIgralec + ", " + MainActivity.tmpSoigralec);
                            MainActivity.tmpSoigralec = -2;
                            MainActivity.tmpIgralec = -2;
                            MainActivity.tmpRezultat = 0;
                            if(igra==80){
                                MainActivity.igra.dodajRadelce();
                            }
                            kontra=0;
                            sizpis="";
                            sizpisst="";
                            napovediVsota=0;
                            popupWindow.dismiss();
                            for (int i=0; i<4;i++){
                                nap[i]=0;
                            }
                            nap[4]=-1;

                            MainActivity.igra.shraniIgro(getSharedPreferences(MainActivity.igra.getImeIgre(), Context.MODE_PRIVATE));
                            Intent in = new Intent(Razlika.this, Razpredelnica.class);
                            startActivity(in);
                            finish();
                        }
                    });

                    popupWindow.showAtLocation(forward, Gravity.CENTER, 0, 0);

                } else {
                    Toast.makeText(getApplicationContext(), "izberi soigralca", Toast.LENGTH_SHORT).show();
                }

            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizpis="";
                sizpisst="";
                MainActivity.tmpSoigralec = -2;
                MainActivity.tmpRezultat = 0;
                kontra=0;
                Intent in = new Intent(Razlika.this, IzbiraIgre.class);
                startActivity(in);
                finish();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_razlika, menu);
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
