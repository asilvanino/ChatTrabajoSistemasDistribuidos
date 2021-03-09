
import java.util.ArrayList;

public class Bloque {

    private int nonce;
    private ArrayList<Transaccion> datos = new ArrayList<Transaccion>();
    private String hast;
    private String hastCabeza;
    private int numBloque;
    private Bloque block = new Bloque(0,"0","0000000000000000000000000000000000000000000000000000000000000000", 1);
    private ArrayList<Bloque> bloques = new ArrayList<Bloque>();
    private int getNumBloque;

    public Bloque(int nonce, String hast, String hastCabeza, int numBloque) {
        this.nonce = nonce;
        this.hast = hast;
        this.hastCabeza = hastCabeza;
        this.numBloque = numBloque;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public ArrayList<Transaccion> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Transaccion> datos) {
        this.datos = datos;
    }

    public String getHast() {
        return hast;
    }

    public void setHast(String hast) {
        this.hast = hast;
    }

    public String getHastCabeza() {
        return hastCabeza;
    }

    public void setHastCabeza(String hastCabeza) {
        this.hastCabeza = hastCabeza;
    }

    public int getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(int numBloque) {
        this.numBloque = numBloque;
    }

    public boolean regitryDate(double m, String dir1, String dir2) {
        Transaccion t = new Transaccion(m, dir1, dir2);
        boolean rest = false;
        //boolean rest1 = Bloque.consultarDato(dir1);
        //boolean rest2 = Bloque.consultarDato(dir1);
        /*if(rest1 == true && rest2 == true){
            datos.add(t);
            rest = true;
        }*/
        Registro r = null;
        String data = "";
        int b = 0;
        for (int i = 0; i < datos.size(); i++) {
            Transaccion f = datos.get(i);
            String h = String.valueOf(f.getMonto());
            b = b + h.getBytes().length + f.getDir1().getBytes().length + f.getDir2().getBytes().length;
        }
        if (b == 500) {
            for (int i = 0; i < datos.size(); i++) {
                Transaccion f = datos.get(i);
                String h = String.valueOf(f.getMonto());
                data = data + h + f.getDir1() + f.getDir2();
            }
            //OpenCloser oc = openCloser(#bloque, data, hashCabeza);
            //block.getNonce() = oc.getNonce();
            //block.getHast() = oc.getHash();
            //bloques.add(block);
            //createBlock(((block.getNumBloque)+1), (block.getHast));
            
        }
        return rest;
    }

    public void createBlock(int nb, String hc) {
        block = new Bloque(0, "0", hc, nb);
    }
}
