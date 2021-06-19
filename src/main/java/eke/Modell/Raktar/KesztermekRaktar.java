package eke.Modell.Raktar;

public class KesztermekRaktar extends Raktar implements Kiado, Bevetelezo {

    private static KesztermekRaktar instance = null;

    private KesztermekRaktar(){}

    public static synchronized KesztermekRaktar getInstance(){
        if (instance == null){
            instance = new KesztermekRaktar();
        }
        return instance;
    }


    @Override
    public void Kiadas(Object o) {
        //...
    }

    @Override
    public void Bevetel(Object o) {

    }
}
