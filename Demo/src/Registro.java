public class Registro {
    
    private String dir;
    private double dinero;

    public Registro(String dir, double dinero) {
        this.dir = dir;
        this.dinero = dinero;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    
    public boolean genereteRegistry(double m, String dir){
        //generar registro a la BD
        
        return true;
    }
    
}
