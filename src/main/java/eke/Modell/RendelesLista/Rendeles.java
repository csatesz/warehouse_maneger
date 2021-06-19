package eke.Modell.RendelesLista;

import ProgTech.Model.Vevo;

import java.util.Date;
import java.util.List;

public class Rendeles {
    Vevo vevo;
    Date szallitasiHatarido;
    List<RendelesListaElem> rendelesiLista;

    public Vevo getVevo(){
        return vevo;
    }
    public Date getSzallitasiHatarido(){
        return szallitasiHatarido;
    }
    public List<RendelesListaElem> getRendelesiLista(){
        return rendelesiLista;
    }
}
