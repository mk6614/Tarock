package com.mojca.ca.tarockfinal;

/**
 * Created by mojca on 07/02/2016.
 */
class element {
    element yes;
    element no;
    String stavek;
    String l;
    String d;
    int kvalifikator;

    public element(String stavek){
        this.stavek = stavek;
    }

    public void setter(int k, element n, element y, String a, String b){
        yes = y;
        no = n;
        l=a;
        d=b;
        kvalifikator = k;
    }
}

public class valatDrevo {
    element koren;

    public valatDrevo() {
        element[] e = new element[21];
        e[0] = new element("DID THAT\nJUST HAPPEN?");
        e[1] = new element("AWSOME!\nWHO DID IT?");
        e[2] = new element("DID YOU\nDO IT ALONE?");
        e[3] = new element("REALLY?");
        e[4] = new element("OF COURSE NOT.\n WHO HELPED YOU?");
        e[5] = new element("I DO NOT BELIEVE YOU...");
        e[6] = new element("I KNEW IT!");
        e[7] = new element("DID YOU\nPREDICT IT?");
        e[8] = new element("IT WAS THE\nCOLORED ONE,\nWASN'T IT?");
        e[9] = new element("OF COURSE NOT.\nNOBODY EVER\nPREDICTS IT.");
        e[10] = new element("CONGRATS!!\n WANT TO TAKE AN EXTRA\nCOLORFULL SELFIE?");
        e[11] = new element("REALLY?\nDOES THAT HAPPEN?");
        e[12] = new element("WANT TO TAKE A\nVICTORIOUS SELFIE?");
        e[13] = new element("U DIDN'T LOOSE,\nDID YOU?");
        e[14] = new element("YOU IDIOT!\nNOW TAKE AN\nEBARESED SELFIE!");
        e[15] = new element("WHAT?\n YOU ARE EITHER\nTHE BEST PLAYER\nOR THE LUCKIEST DUMBASS");
        e[16] = new element("NICE GAME!\nYOU MAY ONLY\nBRAG ABOUT IT FOR\n3 MORE DAYS");
        e[17] = new element("YOU'RE IMAGE IS SAVED :D");
        e[18] = new element("");
        e[19] = new element("DID YOU LOOSE?");
        e[20] = new element("TOO BAD..");

        e[0].setter(1, null, e[1], "YES", "NO");
        e[1].setter(-1, null, e[2], "", "");
        e[2].setter(2, e[4], e[3], "YES", "NO");
        e[3].setter(1, e[4], e[5], "YES", "NO");
        e[4].setter(-1, null, e[7],"","");
        e[5].setter(1, e[6], e[7],"i did it :D", "just kidding");
        e[6].setter(0, null, e[4],"","");
        e[7].setter(3, e[9], e[8], "YES", "NO");
        e[8].setter(1, e[11], e[19], "YES", "NO");
        e[9].setter(0, e[12], e[12], "", "");
        e[10].setter(100, e[16], e[17], "YES", "NO");
        e[11].setter(1, e[9], e[13], "YES", "NO");
        e[12].setter(250, e[16], e[17], "YES", "NO");
        e[13].setter(1, e[15], e[14], "i lost :(","i won!!");
        e[14].setter(-500, null, e[17],"","");
        e[15].setter(500, null, e[17],"","");
        e[16].setter(-2, null, null, "", "");
        e[17].setter(-3, e[16], e[16], "", "");
        e[18].setter(6, null, null, "", "");
        e[19].setter(1, e[10], e[20], "i lost :(", "i won!!");
        e[20].setter(-100, null, null,"","");

        koren = e[0];
    }

    public element getleft(element x){
        return x.no;
    }

    public element getRight(element x){
        return x.yes;
    }

    public String getText(element x){
        return x.stavek;
    }

    public int getK(element x){
        return x.kvalifikator;
    }

    public String getLeftString(element x){
        return x.l;
    }

    public String getRightString(element x){
        return x.d;
    }



}
