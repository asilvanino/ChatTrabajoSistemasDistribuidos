package demo;

import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<String>();
        /*lista.add("w1 - envio 10 - w2");
        lista.add("w1 - retiro 20 - w1");
        lista.add("w5 - envio 50 - w7");*/
        
        int b = (lista.get(1).getBytes().length)-1;
            
        System.out.println(b);
    }
    
}
