
public class Transaccion {

    private double monto;
    private String dir1;
    private String dir2;

    public Transaccion(double monto, String dir1, String dir2) {
        this.monto = monto;
        this.dir1 = dir1;
        this.dir2 = dir2;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDir1() {
        return dir1;
    }

    public void setDir1(String dir1) {
        this.dir1 = dir1;
    }

    public String getDir2() {
        return dir2;
    }

    public void setDir2(String dir2) {
        this.dir2 = dir2;
    }

    Object getBytes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
