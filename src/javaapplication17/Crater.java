
package javaapplication17;

public class Crater {
    int dimension;
    char[][] matriz;

    public Crater(int dimension) {
        this.dimension = dimension;
        this.matriz = new char[this.dimension][this.dimension];
        for(int i=0;i<this.dimension;i++){
            for(int j=0;j<this.dimension;j++){
                this.matriz[i][j]='?';
            } 
        }        
    }

    public void imprimirMapa() {
        for(int i=0;i<this.dimension;i++){
            for(int j=0;j<this.dimension;j++){
                System.out.print(this.matriz[i][j]+" ");
            } 
        System.out.println(" ");
        }
    }
    
    public void agregar(Elemento o) {
        this.matriz[o.posX][o.posY]=o.representacion;
    }
    
}
