package main;

import java.util.Random;

public class Auto {

    /* ADATTAGOK */
    private static int objektumDb = 0; //osztály adattagja, lehet itt inicializálni
    private static int potkerekDb = 1;
    private static Random rnd = new Random();

    /* objektum adattagokat a konstruktor inicializál: */
    private boolean uzemanyag; //példány v. másnéven az objektum adattagja
    private boolean beinditva; //példány v. másnéven az objektum adattagja
    private boolean defekt;

    /* TAGFÜGGVÉNYEK */
 /* kstr hívási lánc: túlterhelt kstr hívja a másik kstr-t */
    public Auto() {
        /* semmi nem lehet a kstr. hívás előtt, mert nem fordul le! */
        //int i = 7;

        /* kstr csak kstr-ból hívunk, this kulcsszóval, nem a nevével */
        this(true, false);
    }

    public Auto(boolean beinditva) {
        this(false, beinditva);
    }

    public Auto(boolean uzemanyag, boolean beinditva) {
        Auto.objektumDb++;
        System.out.println("*********************************");
        System.out.printf("********** %d. bemutató **********\n".formatted(Auto.objektumDb));
        this.uzemanyag = uzemanyag;
        this.beinditva = beinditva;
        final String VAN = "✔ (van)";
        final String NINCS = "❌ (nincs)";
        String info = uzemanyag ? VAN : NINCS;
        System.out.println("üzemanyag: " + info);
        info = beinditva ? VAN : NINCS;
        System.out.println("beindítva: " + info);
        info = defekt ? VAN : NINCS;
        System.out.println("defekt: " + info);
        System.out.println("-----------------");
    }

    public void setBeinditva(boolean beinditva) {
        /* lehetne további ellenőrzés, pl.:
        csak akkor lehet beindítani, ha van üzemanyag
         */
        this.beinditva = beinditva;
        if (beinditva) {
            System.out.println("A motor be van indítva.");
        } else {
            System.out.println("A motor le van állítva.");
        }
    }

    //setUzemanyag(false)
    public void megy() {
        if (beinditva && uzemanyag && !defekt) {
            this.uzemanyag = false;
            System.out.println("Haladtunk, az autó megérkezett, de üres a tank.");
            int esely = rnd.nextInt(1, 6);
            if (esely == 1) {
                setDefekt(true);
            }
        } else {
            System.out.printf("Nem haladtunk: beindítva: %s, üzemanyag %s, defekt %s\n", beinditva, uzemanyag, defekt);
        }
    }

    //setUzemanyag(true)
    public void tankol() {
        if (!beinditva && !uzemanyag) {
            this.uzemanyag = true;
            System.out.println("Sikerült tankolni, tele a tank.");
        } else if (!beinditva) {
            System.out.println("A tank már tele van");
        } else {
            System.out.println("Sikertelen tankolás, mert be van indítva a motor.");
        }
    }

    public void setDefekt(boolean defekt) {
        this.defekt = defekt;
        if (defekt) {
            System.out.println("Defektet kaptunk.");
        }
    }

    public void gumicsere() {
        if (defekt && potkerekDb > 0) {
            this.potkerekDb -= 1;
            setDefekt(false);
            System.out.println("A gumicsere megtörtént, elfogyott a pótkerék.");
        } else if (defekt) {
            System.out.println("Nem sikerült gumit cserélni, elfogyott a pórkerék.");
        } else {
            System.out.println("Nem sikerült gumit cserélni, nem defektes az autó.");
        }
    }
}
