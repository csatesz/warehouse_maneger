package eke.Modell.Raktar;

public class FelkeszTermekRaktar extends Raktar implements Kiado, Bevetelezo{
    private static FelkeszTermekRaktar instance = null;

    private FelkeszTermekRaktar(){}

    public static synchronized FelkeszTermekRaktar getInstance(){
        if (instance == null){
            instance = new FelkeszTermekRaktar();
        }
        return instance;
    }


    @Override
    public void Kiadas(Object o) {

    }

    @Override
    public void Bevetel(Object o) {

    }
}
