package com.mojca.ca.tarockfinal;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by mojca on 03/02/2016.
 */
public class Igra {

    private String imeIgre;
    private String[] igralci = new String[4];
    private String[] rezultati = new String[4];
    private String[] radelci = new String[4];
    private int radelc[] = new int[4];
    private int[] rezultat = new int[4];
    private String[] razveljaviRez = new String[4];
    private String[] razveljaviRad = new String[4];
    private int mondfang, barvniValat;
    private boolean klop;

    public Igra(String[] imena, String imeIgre){
        if (imena != null) {
            igralci = imena;
        }
        this.imeIgre = imeIgre;
        for (int i=0; i<4; i++){
            rezultati[i]="0";
            rezultat[i]=0;
            radelci[i]="0";
            razveljaviRad[i]="0";
            razveljaviRez[i]="0";
            radelc[i]=0;
        }
        mondfang = 25;
        barvniValat = 250;
        klop = true;
    }

    public void setIme(int i, String ime){
        igralci[i] = ime;
    }

    public boolean setRadelc(int i, String r){
        radelci[i] = r.trim();
        try {
            radelc[i] = Integer.parseInt(r.trim());
            return true;
        } catch (Exception e){
            Log.i("problem", e.toString());
            return false;
        }
    }

    public void setRazveljavi(){
        for (int i=0; i<4; i++){
            razveljaviRez[i] = rezultati[i];
            razveljaviRad[i] = radelci[i];
        }
    }

    public void razveljavi() {
        for (int i=0; i<4; i++){
            rezultati[i] = razveljaviRez[i];
            radelci[i] = razveljaviRad[i];
            radelc[i] = Integer.parseInt(radelci[i]);
            try {
                String[] tmp = rezultati[i].split("\n");
                rezultat[i] = Integer.parseInt(tmp[tmp.length-1].trim());
            } catch (Exception e){
                rezultat[i] = Integer.parseInt(rezultati[i].trim());
            }
        }
    }

    public boolean setRezultat(int i, String r){
        razveljaviRez[i] = rezultati[i];
        razveljaviRad[i] = razveljaviRad[i];
        rezultati[i] = r.trim();
        try{
            try {
                String[] tmp = r.split("\n");
                rezultat[i] = Integer.parseInt(tmp[tmp.length-1].trim());
            } catch (Exception e){
                rezultat[i] = Integer.parseInt(r.trim());
            }
            return true;
        } catch (Exception e){
            Log.i("problem", e.toString());
            return false;
        }
    }

    public String getIme(int i){
        return igralci[i];
    }

    public String getImeIgre(){
        return imeIgre;
    }

    public String getRadelc(int i){
        return radelci[i];
    }

    public String getRezultat(int i){
        return rezultati[i];
    }

    public void setRezultat(int i, int r){
        rezultat[i]+=r;
        rezultati[i]+="\n"+Integer.toString(rezultat[i]);
    }
    public void brisiRadelc(int i){
        radelc[i]--;
        radelci[i]=Integer.toString(radelc[i]);
    }

    public void dodajRadelce(){
        for (int i=0; i<4; i++){
            radelc[i]++;
            radelci[i]= Integer.toString(radelc[i]);
        }
    }

    public boolean radelc(int i){
        if (radelc[i]>0){
            return true;
        }
        return false;
    }


    public void shraniIgro(SharedPreferences s){
        SharedPreferences.Editor e = s.edit();
        //string rezultati
        e.putString("rezultati0", rezultati[0]);
        e.putString("rezultati1", rezultati[1]);
        e.putString("rezultati2", rezultati[2]);
        e.putString("rezultati3", rezultati[3]);
        //int rezultati
        e.putInt("rezultat0", rezultat[0]);
        e.putInt("rezultat1", rezultat[1]);
        e.putInt("rezultat2", rezultat[2]);
        e.putInt("rezultat3", rezultat[3]);
        //int radelci
        e.putInt("radelc0", radelc[0]);
        e.putInt("radelc1", radelc[1]);
        e.putInt("radelc2", radelc[2]);
        e.putInt("radelc3", radelc[3]);
        //string radelci
        e.putString("radelci0", radelci[0]);
        e.putString("radelci1", radelci[1]);
        e.putString("radelci2", radelci[2]);
        e.putString("radelci3", radelci[3]);
        //string imena
        e.putString("ime0", igralci[0]);
        e.putString("ime1", igralci[1]);
        e.putString("ime2", igralci[2]);
        e.putString("ime3", igralci[3]);
        //pravila
        e.putInt("mf", mondfang);
        e.putInt("bv", barvniValat);
        e.putBoolean("k", klop);

        e.commit();
    }

    public void naloziIgro(SharedPreferences s, String imeIgre) {


        this.imeIgre=imeIgre;

        igralci[0] = s.getString("ime0", "");
        igralci[1] = s.getString("ime1", "");
        igralci[2] = s.getString("ime2", "");
        igralci[3] = s.getString("ime3", "");

        rezultati[0] = s.getString("rezultati0", "0");
        rezultati[1] = s.getString("rezultati1", "0");
        rezultati[2] = s.getString("rezultati2", "0");
        rezultati[3] = s.getString("rezultati3", "0");

        rezultat[0] = s.getInt("rezultat0", 0);
        rezultat[1] = s.getInt("rezultat1", 0);
        rezultat[2] = s.getInt("rezultat2", 0);
        rezultat[3] = s.getInt("rezultat3", 0);

        radelci[0] = s.getString("radelci0", "0");
        radelci[1] = s.getString("radelci1", "0");
        radelci[2] = s.getString("radelci2", "0");
        radelci[3] = s.getString("radelci3", "0");

        radelc[0] = Integer.parseInt(radelci[0]);
        radelc[1] = Integer.parseInt(radelci[1]);
        radelc[2] = Integer.parseInt(radelci[2]);
        radelc[3] = Integer.parseInt(radelci[3]);

        mondfang = s.getInt("mf", 25);
        barvniValat = s.getInt("bv", 125);
        klop = s.getBoolean("k", true);

        setRazveljavi();
    }

    public void setPravila(int mf, int bv, boolean k){
        mondfang = mf;
        barvniValat = bv;
        klop = k;
    }

    public boolean getKlop(){return klop;}

    public int getBarvniValat() {
        return barvniValat;
    }

    public int getMondfang(){
        return mondfang;
    }
}
