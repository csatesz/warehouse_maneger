package eke.Controller;

public class VevoController {
    public Szamla Kiszallit(Rendeles rendeles){
        rendeles.getVevo().megvesz(KesztermekRaktar.getInstance(), rendeles);
        SzamlaController szamla = new SzamlaController(rendeles);
        return szamla.Kiallit();
    }
}
