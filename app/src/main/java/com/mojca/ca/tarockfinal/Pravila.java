package com.mojca.ca.tarockfinal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;


public class Pravila extends ActionBarActivity {

    EditText mf, barvni, r;
    RadioButton b, b2;
    ImageButton forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravila);

        mf = (EditText) findViewById(R.id.mf);
        barvni = (EditText) findViewById(R.id.barvni);
        r = (EditText) findViewById(R.id.renons);
        b = (RadioButton) findViewById(R.id.yesRadelciKlop);
        b2 = (RadioButton) findViewById(R.id.yesPolnaKlop);
        forward = (ImageButton) findViewById(R.id.forward);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean napaka= false;
                int m=25,bv=250, renons=70;
                boolean y, y2;
                try {
                    m = Integer.parseInt(mf.getText().toString());
                    if (m>0){
                        m*=-1;
                    }
                    renons = Integer.parseInt(r.getText().toString());
                    if (renons>0){
                        renons*=-1;
                    }
                } catch (Exception e){
                    napaka = true;
                    Toast.makeText(Pravila.this, "mondfang vrednost napa?na", Toast.LENGTH_SHORT);
                }
                try {
                    bv = Integer.parseInt(barvni.getText().toString());
                } catch (Exception e){
                    napaka = true;
                    Toast.makeText(Pravila.this, "vrednost za barvnega valata\nni veljavna", Toast.LENGTH_SHORT);
                }
                if (b.isChecked()) {
                    y = true;
                } else {
                    y = false;
                }
                if (b2.isChecked()){
                    y2= true;
                } else {
                    y2 = false;
                }
                if(!napaka){
                    MainActivity.igra.setPravila(m, bv ,y, y2, renons);
                    Intent in = new Intent(Pravila.this, Razpredelnica.class);
                    startActivity(in);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pravila, menu);
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
