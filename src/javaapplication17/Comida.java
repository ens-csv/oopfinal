
package javaapplication17;

public class Comida extends Afectadores{
    public Comida(int c) {
        this.cantidad=c;
        this.matriz = new Elemento[this.cantidad]; 
        this.yaSalio = new Elemento[this.cantidad]; 
        int n = 0;
        switch(c){
            case 8:
                n=5;
                break;
            case 6:
                n=8;
                break;
            case 4:
                n=12;
                break;
            default:
                break;                
        }
        for(int i=0;i<this.cantidad;i++){
                Azar x = new Azar(n);
                Azar y = new Azar(n);
                this.matriz[i]=new Comida(x.numero,y.numero);
        }
    }
    
    public Comida(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.afecta = 3;
        this.nombre = "comida ";
        this.representacion = '+';
    }
}
