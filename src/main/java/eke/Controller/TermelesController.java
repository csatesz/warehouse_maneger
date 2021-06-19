package eke.Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class TermelesController {
        private List<GyartasiListaElem> gyartasiLista = null;

        public TermelesController(List<GyartasiListaElem> lista){
            this.gyartasiLista = lista;
        }

        public TermelesController(String fileNev) throws FileNotFoundException {
            ListaLetrehozo stream = new GyartasiListaInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fileNev)));
            this.gyartasiLista = stream.ListaLetrehoz(new GyartasiListaAtalakito());
        }

        public void TermelesInditasa(){
            Termelo termelo = new Termelo(new KesztermekFactoryImp());
            termelo.gyart(  gyartasiLista,
                    AlapanyagRaktar.getInstance(),
                    KesztermekRaktar.getInstance(),
                    FelkeszTermekRaktar.getInstance());
        }

}
