
package javaapplication17;

public class Tesoro extends Encontrado{
    public Tesoro(int c) {
        this.cantidad=c;
        this.matriz = new Elemento[this.cantidad];
        this.yaSalio = new Elemento[this.cantidad];
        int n = 0;
        switch(c){
            case 1:
                n=5;
                break;
            case 2:
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
                this.matriz[i]=new Tesoro(x.numero,y.numero);
        }
    }
    
    public Tesoro(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.nombre = "un tesoro ";
        this.representacion = '$';
    } 
}
