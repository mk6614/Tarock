package com.mojca.ca.tarockfinal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import it.sephiroth.android.library.tooltip.Tooltip;


public class IzbiraIgre extends ActionBarActivity {
    Button igra, solo, klop, berac, valat;
    ImageButton l1, l2, r1, r2, back;
    static int igr, sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbira_igre);
        igr = 2;
        sl = 3;
        l1 = (ImageButton) findViewById(R.id.l1);
        l2 = (ImageButton) findViewById(R.id.l2);
        r1 = (ImageButton) findViewById(R.id.r1);
        r2 = (ImageButton) findViewById(R.id.r2);
        klop = (Button) findViewById(R.id.klop);
        berac = (Button) findViewById(R.id.berac);
        back = (ImageButton) findViewById(R.id.back);
        igra = (Button) findViewById(R.id.igra);
        solo = (Button) findViewById(R.id.solo);
        valat = (Button) findViewById(R.id.valat);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IzbiraIgre.this, Razpredelnica.class);
                startActivity(in);
                finish();
            }
        });

        View.OnClickListener c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IzbiraIgre.this, Razlika.class);
                int id = v.getId();
                if (id == R.id.igra){
                    switch (igr){
                        case 1:
                            MainActivity.tmpRezultat=30;
                            break;
                        case 2:
                            MainActivity.tmpRezultat=20;
                            break;
                        case 3:
                            MainActivity.tmpRezultat=10;
                            break;
                        default:
                            break;
                    }
                } else if (id == R.id.solo) {
                    MainActivity.tmpSoigralec=-1;
                    Log.i("problem", Integer.toString(sl));
                    switch (sl){
                        case 0:
                            Log.i("problem", "wtf0");
                            MainActivity.tmpRezultat=80;
                            break;
                        case 1:
                            Log.i("problem", "wtf1");
                            MainActivity.tmpRezultat=60;
                            break;
                        case 2:
                            Log.i("problem", "wtf2");
                            MainActivity.tmpRezultat=50;
                            break;
                        case 3:
                            Log.i("problem", "wtf3");
                            MainActivity.tmpRezultat=40;
                            break;
                        default:
                            break;
                    }
                }
                Log.i("problem", Integer.toString(MainActivity.tmpRezultat));
                in.putExtra("igra", MainActivity.tmpRezultat);
                startActivity(in);
                finish();
            }
        };

        solo.setOnClickListener(c);
        igra.setOnClickListener(c);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (igr>1){
                    igr--;
                }
                igra.setText(Integer.toString(igr)+ "   igra");
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sl>0){
                    sl--;
                }
                solo.setText(Integer.toString(sl)+ "   solo");
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (igr<3){
                    igr++;
                }
                igra.setText(Integer.toString(igr) + "   igra");
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sl<3){
                    sl++;
                }
                solo.setText(Integer.toString(sl)+ "   solo");
            }
        });

        klop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.tmpIgralec=-2;
                Intent in = new Intent(IzbiraIgre.this, klop.class);
                startActivity(in);
                finish();
            }
        });

        valat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.tmpIgralec=-2;
                Intent in = new Intent(IzbiraIgre.this, valat.class);
                startActivity(in);
                finish();
            }
        });

        berac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IzbiraIgre.this, Berac.class);
                startActivity(in);
                finish();
            }
        });

        //navodila();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_izbira_igre, menu);
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
    public void navodila() {
        Tooltip.TooltipView[] ttv = new Tooltip.TooltipView[5];
        View[] v = new View[5];
        v[0] = igra;
        v[1] = solo;
        v[2] = klop;
        v[3] = berac;
        v[4] = valat;
        for (int i=0; i<5; i++){
            ttv[i] = Tooltip.make(this,
                    new Tooltip.Builder(101)
                            .anchor(v[i], Tooltip.Gravity.BOTTOM)
                            .closePolicy(new Tooltip.ClosePolicy()
                                    .insidePolicy(true, false)
                                    .outsidePolicy(true, false), 1800)
                            .activateDelay(0)
                            .showDelay(300)
                            .text("izberite igro")
                            .maxWidth(500)
                            .withArrow(true)
                            .withOverlay(true)
                            .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                            .build()
            );
            ttv[i].show();
        }
        Tooltip.make(this,
                new Tooltip.Builder(101)
                        .anchor(l1, Tooltip.Gravity.BOTTOM)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 3600)
                        .activateDelay(0)
                        .showDelay(2100)
                        .text("pomanjšajte vrednost")
                        .maxWidth(500)
                        .withArrow(true)
                        .withOverlay(true)
                        .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                        .build()
        ).show();

        Tooltip.make(this,
                new Tooltip.Builder(101)
                        .anchor(r1, Tooltip.Gravity.BOTTOM)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 3600)
                        .activateDelay(0)
                        .showDelay(2100)
                        .text("povečajte vrednost")
                        .maxWidth(500)
                        .withArrow(true)
                        .withOverlay(true)
                        .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                        .build()
        ).show();
    }
}
