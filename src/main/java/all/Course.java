package all;

public class Course {

    private enum Day {
        HETFO,
        KEDD,
        SZERDA,
        CSUTORTOK,
        PENTEK,
        SZOMBAT
    }

    private int felev;
    private String kar;
    private String szki;
    private String ti;
    private String tantargy;
    private String tsz;
    private String eloado;
    private String csop;
    private int fo;
    private int kezd;
    private int tart;
    private String terem;
    private Day nap;

    public Course(int felev, String kar, String szki, String ti, String tantargy, String tsz, String eloado, String csop, int fo, int kezd, int tart, String terem, Day nap) {
        this.felev = felev;
        this.kar = kar;
        this.szki = szki;
        this.ti = ti;
        this.tantargy = tantargy;
        this.tsz = tsz;
        this.eloado = eloado;
        this.csop = csop;
        this.fo = fo;
        this.kezd = kezd;
        this.tart = tart;
        this.terem = terem;
        this.nap = nap;
    }
}
