package eke.Modell.Szamla;

import ProgTech.Model.RendelesLista.RendelesListaElem;
import ProgTech.Model.Vevo;

import java.util.Date;
import java.util.List;

public interface SzamlaBuilder {
     SzamlaBuilder fejlecHozzaad(Vevo vevo);
     SzamlaBuilder törzsreszHozzaad(List<RendelesListaElem> rendeles);
     SzamlaBuilder fizetesiHataridoHozzaad(Date datum);

     Szamla Build();
}
