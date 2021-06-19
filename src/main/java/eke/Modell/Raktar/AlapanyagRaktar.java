package eke.Modell.Raktar;

import java.util.Map;

public class AlapanyagRaktar extends Raktar implements AlapanyagKiado, Bevetelezo{

    private static AlapanyagRaktar instance = null;

    private AlapanyagRaktar(){}

    public static synchronized AlapanyagRaktar getInstance(){
        if (instance == null)
            instance = new AlapanyagRaktar();
        return instance;
    }

    @Override
    public void Kiadas(Object o) {
        //..
    }

    @Override
    public void Bevetel(Object o) {
        //..
    }

    public boolean AlapanyagRendelkezesreAll(Map BOM){
        return true;
    }
}
