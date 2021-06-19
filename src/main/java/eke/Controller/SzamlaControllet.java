package eke.Controller;


public class SzamlaControllet {

    Rendeles rendeles;

    public SzamlaController(Rendeles rendeles){
        this.rendeles = rendeles;
    }

    public Szamla Kiallit(){
        SzamlaBuilder szamlaBuilder = new SzamlaBuilderImpl();
        return szamlaBuilder.
                fejlecHozzaad(rendeles.getVevo()).
                törzsreszHozzaad(rendeles.getRendelesiLista()).
                fizetesiHataridoHozzaad(rendeles.getSzallitasiHatarido()).
                Build();
    }
