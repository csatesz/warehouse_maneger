package eke.Modell.Szamla;

import ProgTech.Model.RendelesLista.RendelesListaElem;
import ProgTech.Model.Vevo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SzamlaBuilderImpl implements SzamlaBuilder {

    List<Map> szamlaelemek = new ArrayList<Map>();

    @Override
    public SzamlaBuilder fejlecHozzaad(Vevo vevo) {
        //...
        return this;
    }

    @Override
    public SzamlaBuilder törzsreszHozzaad(List<RendelesListaElem> rendeles) {
        //...
        return this;
    }

    @Override
    public SzamlaBuilder fizetesiHataridoHozzaad(Date datum) {
        //...
        return this;
    }

    @Override
    public Szamla Build() {

        return new Szamla(szamlaelemek);
    }
}
