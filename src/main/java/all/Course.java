package all;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Course {

    /* TODO: switch the "nap" field from String to enum
    private enum Day {
        HETFO,
        KEDD,
        SZERDA,
        CSUTORTOK,
        PENTEK,
        SZOMBAT
    }
     */

    private int felev;
    private String kar;
    private String szki;
    private String ti;
    private String tantargy;
    private String tanszek;
    private String eloado;
    private String csoport;
    private int fo;
    private int kezdes;
    private int hossz;
    private String terem;
    private String nap;
    private String tipus;

    public Course(int felev, String kar, String szki, String ti, String tantargy, String tanszek, String eloado, String csoport, int fo, String nap, int kezdes, int hossz, String tipus, String terem) {
        this.felev = felev;
        this.kar = kar;
        this.szki = szki;
        this.ti = ti;
        this.tantargy = tantargy;
        this.tanszek = tanszek;
        this.eloado = eloado;
        this.csoport = csoport;
        this.fo = fo;
        this.kezdes = kezdes;
        this.hossz = hossz;
        this.terem = terem;
        this.nap = nap;
        this.tipus = tipus;
    }

    public Course() {

    }

    public int getFelev() {
        return felev;
    }

    public void setFelev(int felev) {
        this.felev = felev;
    }

    public String getKar() {
        return kar;
    }

    public void setKar(String kar) {
        this.kar = kar;
    }

    public String getSzki() {
        return szki;
    }

    public void setSzki(String szki) {
        this.szki = szki;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getTantargy() {
        return tantargy;
    }

    public void setTantargy(String tantargy) {
        this.tantargy = tantargy;
    }

    public String getTanszek() {
        return tanszek;
    }

    public void setTanszek(String tanszek) {
        this.tanszek = tanszek;
    }

    public String getEloado() {
        return eloado;
    }

    public void setEloado(String eloado) {
        this.eloado = eloado;
    }

    public String getCsoport() {
        return csoport;
    }

    public void setCsoport(String csoport) {
        this.csoport = csoport;
    }

    public int getFo() {
        return fo;
    }

    public void setFo(int fo) {
        this.fo = fo;
    }

    public int getKezdes() {
        return kezdes;
    }

    public void setKezdes(int kezdes) {
        this.kezdes = kezdes;
    }

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public String getTerem() {
        return terem;
    }

    public void setTerem(String terem) {
        this.terem = terem;
    }

    public String getNap() {
        return nap;
    }

    public void setNap(String nap) {
        this.nap = nap;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "felev=" + felev +
                ", kar='" + kar + '\'' +
                ", szki='" + szki + '\'' +
                ", ti='" + ti + '\'' +
                ", tantargy='" + tantargy + '\'' +
                ", tanszek='" + tanszek + '\'' +
                ", eloado='" + eloado + '\'' +
                ", csoport='" + csoport + '\'' +
                ", fo=" + fo +
                ", kezdes=" + kezdes +
                ", hossz=" + hossz +
                ", terem='" + terem + '\'' +
                ", nap='" + nap + '\'' +
                ", tipus='" + tipus + '\'' +
                '}';
    }
}
