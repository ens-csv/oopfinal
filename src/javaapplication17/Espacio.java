
package javaapplication17;

public class Espacio extends Elemento{
    
    public Espacio(int x, int y, char r) {
        this.posX=x;
        this.posY=y;
        this.representacion=r;
    }
    
    public Espacio(Personaje o) {
        this.posX=o.posX;
        this.posY=o.posY;
    }

    public void moverX(int i) {
        this.posX+=i;
    }
    
    public void moverY(int i) {
        this.posY+=i;
    }
    
}
