package eke.Modell.GyartasiLista;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.List;

public class GyartasiListaInputStream extends FilterInputStream implements ListaLetrehozo {

    public GyartasiListaInputStream(InputStream in) {
        super(in);
    }

    public List<GyartasiListaElem> ListaLetrehoz(GyartasiListaAtalakito atalakito){
        return atalakito.Atalakit(in);
    }
}
