package eke.Controller;

public class BeszallitoController {
    Beszallito beszallito = null;
    int mennyiseg;

    public BeszallitoController(Alapanyag alapanyag, int mennyiseg){
        this.beszallito = alapanyag.getBeszallito();
        this.mennyiseg = mennyiseg;
    }

    public void Beszallit(){
        beszallito.beszallit(AlapanyagRaktar.getInstance(), mennyiseg);
    }
}
