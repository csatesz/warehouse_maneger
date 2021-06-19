package eke.Modell;

public class Vevo {
    public void megvesz(Kiado kesztermekRaktar, Rendeles rendeles){
        for (RendelesListaElem elem : rendeles.getRendelesiLista()) {
            kesztermekRaktar.Kiadas(elem.getKesztermekID());
        }
    }
}
