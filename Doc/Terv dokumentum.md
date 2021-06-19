# Programozás Technológiák gyakorlati beadandó feladat.

## Feladat leírása:
Raktár manager program tervezése, amelynek a következő funkcionalitások és alrendszerek megtervezett intefészeit, és "csontvázát" kell tartalmaznia: 
Több inhomogén raktárrendszerrel való kommunikáció, és közös menedzsment felület biztosítása. 
Árú menedzsment 
Rendelés menedzsment 
Beszállítók és Vásárlók menedzsmentje.

## Feladat megoldása:
Képzeletbeli termelő vállalat megrendelést adott ki egy raktár management program megtervezésére. A képzeletbeli vállalat raktárfolyamatait a következő ábra szemlélteti.

![folyamatabra](https://github.com/JakabZsolt/ProgTechGyak/blob/main/Doc/img/1_abra.png)

A vállalatnak három raktár rendszere létezik. Az alapanyag raktár, a késztermék raktár és a félkésztermék raktár. Mindegyik raktárba az anyagok be és ki áramolnak. Az alapanyag raktárba befelé a beszállító felől érkezik, kifelé pedig a termelés felé áramolnak az anyagok. A termelés az alapanyagokból késztermékeket állít elő. A késztermékek a késztermék raktárba mennek be, ahonnan kifele a vevő felé továbbítódik a termék a vevői rendelések alapján. Ha az alapanyag nem áll rendelkezésre, akkor a késztermék a félkésztermék raktárba kerül.
-	Azt, hogy milyen készterméket kell gyártani, egy gyártási sorrend lista határozza meg,
-	a vevőnek számla kerül kiállításra, amikor a késztermékek a raktárból kiszállításra kerülnek,
-	az alapanyagraktárban lévő anyagok megrendelhetőek a beszállítótól, 


### Program leírása:
#### UML ábra:

![folyamatabra](https://github.com/JakabZsolt/ProgTechGyak/blob/main/Doc/img/2_abra.png)

A programban három raktárrendszert kerül megvalósításra, melyeknek hasonlóak a viselkedései. Ezért létrehoztam egy abstrakt osztályt, ebből származnak az egyes gyermek raktárosztályok.
Minden raktár adatbázis műveletet valósít meg. Az adatbázisban tároljuk az anyagok adatait. Az adatbázis kapcsolat miatt, az egyes implementált raktárosztályok singleton osztályoknak kell lenniük. Így biztosítható, hogy egyszerre csak egy művelet végrehajtás legyen az adatbázisban.
```
public class AlapanyagRaktar extends Raktar implements AlapanyagKiado, Bevetelezo{

    private static AlapanyagRaktar instance = null;

    private AlapanyagRaktar(){}

    public static synchronized AlapanyagRaktar getInstance(){
        if (instance == null)
            instance = new AlapanyagRaktar();
        return instance;
    }
```

#### Termelés
A  TermelésController osztály fogadja a gyártási listát melyet kétféle módon lehet megadni:
-	manuálisan: UI felületen keresztül (UI felület nincs implementálva)
-	file-ból: a listát txt file-ból lehet betölteni (file olvasása nincs implementálva)

Ennek az osztálynak ezért két konstruktora van. 
-	Az első paramétere közvetlenül egy lista a gyártási lista elemeivel. 
-	A másik paramétere egy file, amiből be lehet tölteni a listát.
o	A második konstruktorban a fileból beolvasott adatok gyártási listává alakítását dekorátor tervezési mintával oldottam meg. 
```
public TermelesController(String fileNev) throws FileNotFoundException {
    ListaLetrehozo stream = new GyartasiListaInputStream(
                              new BufferedInputStream(
                                new FileInputStream(fileNev)));
    this.gyartasiLista = stream.ListaLetrehoz(new GyartasiListaAtalakito());
}
```

A GyártásiListaAtalakito osztály feladata a beolvasott file sorok gyártási listává alakítása. Csak ezt az egy feladatot látja el. Mivel a gyártási lista változékony (bővülhet vagy változhat, hogy milyen mezőket tartalmazzon), ezért ez ki lett emelve a file beolvasását irányító GyártásiLista osztályból, mely így egy kontroller lett (szétválasztás elve).

A TermelésController másik feladata, hogy egy Termelő példányt létrehozzon és  a három singleton raktár rendszer példányával meghívja a gyart() metódust.
A Termelo osztályban a késztermékek gyártását factory tervezési mintával oldottam meg. A termelő osztály konstruktorán keresztül kap egy factory osztályt, mely a késztermékeket állítja elő.
```
public void gyart(List<GyartasiListaElem> gyartasiLista,
                  AlapanyagKiado alapanyagRaktar,
                  Bevetelezo kesztermekRaktar,
                  Bevetelezo felkeszTermekRaktar){

    for (GyartasiListaElem gyartandoTermek: gyartasiLista) {
            Kesztermek kesztermek = null;
            try{
                kesztermek = kesztermekFactory.Keszit(gyartandoTermek.getKesztermekID());
```

A Termelő osztály az AlapanyagRaktár példány AlapanyagRendelkezesreAll() metódusát meghívva ellenőrzi le, hogy a raktárban rendelkezésre áll-e a megfelelő mennyiségű alapanyag. Ha igen akkor a KésztermékRaktár pédány bevetel() metódusával bekerül a raktárba. Ha nincs elég alapanyag, akkor a FélkészTermékRaktár példány bevetel() metódusát hívja meg.
```
if(alapanyagRaktar.AlapanyagRendelkezesreAll(kesztermek.getBOM())){
    alapanyagRaktar.Kiadas(kesztermek.getBOM());
    kesztermekRaktar.Bevetel(kesztermek);
}
else{
    felkeszTermekRaktar.Bevetel(kesztermek);
}
```

#### Vevő
A VevoController osztály fogadja a rendelést, amelyet ki kell szállítani a vevőnek. A vevő megvesz() metódusát hívja meg, mely levonja a késztermékraktárban lévő késztermékeket készletről. Ezután a számlát állítja ki a vevőnek. Ehhez létrehoz egy SzamlaController példányt, és meghívja a Kiallit() metódust.

#### Számla
A SzamlaController osztály a számla előállításáért felel. Ezt builder tervezési mintával oldottam meg. Ehhez egy SzamlaBuilder osztályt példányosít és visszatér a kész számlával.
```
public Szamla Kiallit(){
    SzamlaBuilder szamlaBuilder = new SzamlaBuilderImpl();
    return szamlaBuilder.
                fejlecHozzaad(rendeles.getVevo()).
                törzsreszHozzaad(rendeles.getRendelesiLista()).
                fizetesiHataridoHozzaad(rendeles.getSzallitasiHatarido()).
                Build();
}
```

#### Beszállító
A BeszallitoContrroller osztály konstruktorán keresztül megkapja a beszállítandó alapanyagot és a mennyiséget. Az alapanyaghoz tartozó beszállító beszállít metódusát hívja meg a raktár referenciájával paraméterként, aminek bevetel() metódusa fog meghívódni.



