package eke.Modell.Kesztermek;

public class KesztermekFactoryImp implements KesztermekFactory{
    @Override
    public Kesztermek Keszit(String termekID){
        Kesztermek kesztermek = null;
        if (termekID.equals("termék_1")){
            kesztermek = new Termek_1();
        }
        else if (termekID.equals("termék_2")){
            kesztermek = new Termek_2();
        }
        else if (termekID.equals("termék_3")){
                kesztermek = new Termek_3();
        }
        else{
            throw new RuntimeException("Nem támogatott termék!");
        }
        return kesztermek;
    }
}
