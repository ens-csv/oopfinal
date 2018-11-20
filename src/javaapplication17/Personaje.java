
package javaapplication17;

public class Personaje extends Elemento{
    int vida;

    public Personaje(int vida, int n) {
        this.vida = vida;
        this.representacion='L';
        Azar x = new Azar(n);
        Azar y = new Azar(n);
        this.posX = x.numero;
        this.posY = y.numero;
    }

    public String toString() {
        return "Vida: " + vida;
    }
    
    public void moverX(int i) {
        this.posX+=i;
    }
    
    public void moverY(int i) {
        this.posY+=i;
    }
    
    public void cambiarVida(int n){
        this.vida+=n;
    }
}
