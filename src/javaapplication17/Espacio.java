
package javaapplication17;

public class Espacio extends Elemento{
    int turno = 0 ;
    int cantidad = 49;
    Elemento[] matriz = new Elemento[this.cantidad];
    public Espacio() {
    }
    
    public Espacio(Personaje o) {
        this.posX=o.posX;
        this.posY=o.posY;
    }
    public Espacio(int x, int y, char r) {
        this.posX=x;
        this.posY=y;
        this.representacion=r;
    }

    public void moverX(int i) {
        this.posX+=i;
    }
    
    public void moverY(int i) {
        this.posY+=i;
    }
    
    public void agregar(Elemento o){
        this.matriz[this.turno]=new Espacio(o.posX,o.posY,o.representacion);
        this.turno++;
    }
}
