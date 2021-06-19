package eke.Modell;

import java.util.List;

public class Termelo {
    private final KesztermekFactory kesztermekFactory;
    public Termelo(KesztermekFactory kesztermekFactory){
        this.kesztermekFactory = kesztermekFactory;
    }
    public void gyart(List<GyartasiListaElem> gyartasiLista,
                      AlapanyagKiado alapanyagRaktar,
                      Bevetelezo kesztermekRaktar,
                      Bevetelezo felkeszTermekRaktar){

        for (GyartasiListaElem gyartandoTermek: gyartasiLista) {
            Kesztermek kesztermek = null;
            try{
                kesztermek = kesztermekFactory.Keszit(gyartandoTermek.getKesztermekID());
                if(alapanyagRaktar.AlapanyagRendelkezesreAll(kesztermek.getBOM())){
                    alapanyagRaktar.Kiadas(kesztermek.getBOM());
                    kesztermekRaktar.Bevetel(kesztermek);
                }
                else{
                    felkeszTermekRaktar.Bevetel(kesztermek);
                }
            }
            catch (Exception e){
            }
        }

    }
}
