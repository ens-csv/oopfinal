
package javaapplication17;

import java.util.Scanner;

public final class Juego {
    Personaje p1;
    Crater gucci;
    Tesoro tesoro;
    Comida comida;
    Enemigo enemigo;
    Espacio visto;
    int turno=0;

    public Juego(int dificultad) {
        switch(dificultad){
            case 1: 
                gucci = new Crater(5);
                p1 = new Personaje(50,5);
                comida = new Comida(8);
                tesoro = new Tesoro(1);
                enemigo = new Enemigo(2);
                break;  
            case 2: 
                gucci = new Crater(8);
                p1 = new Personaje(40,8);
                comida = new Comida(6);
                tesoro = new Tesoro(2);
                enemigo = new Enemigo(4);
                break;  
            case 3: 
                gucci = new Crater(12);
                p1 = new Personaje(30,12);
                comida = new Comida(4);
                tesoro = new Tesoro(4);
                enemigo = new Enemigo(8);
                break;  
        }
        visto = new Espacio();
        this.encontrarItem();
        while(p1.vida>0&&tesoro.encontrados<tesoro.cantidad){
            this.jugar();
        }
        gucci.imprimirMapa();
        if(p1.vida>0){
            System.out.println("CONGRATS");
        }else{
            System.out.println("GAME OVER");
        }
        System.out.println(p1.toString());
        System.out.println(this.toString());
    }

    public void jugar() { 
        gucci.agregar(p1);  
        gucci.imprimirMapa();
        System.out.println(p1.toString());
        System.out.println(this.toString());
        this.opciones();
        visto = new Espacio(p1);
    }
    
    public void opciones() {
        visto.agregar(p1);
        this.turno++;
        boolean result=false;
        boolean seSale=false;
        this.recorrido();
        gucci.agregar(visto);
        while(!result&&!seSale){
            Scanner lector = new Scanner(System.in);
            System.out.println("W(a/j) N(w/i) S(s/k) E(d/l)");
            char opcion = lector.next().charAt(0);               
                switch(opcion){
                    case 'a':
                        p1.moverY(-1);
                        if(p1.posY<0||p1.posY<gucci.dimension){
                            seSale=true;
                        }
                        visto.agregar(p1);
                        this.encontrarItem();
                        result=true;
                        p1.cambiarVida(-2);
                        break;
                    case 'w':
                        p1.moverX(-1);
                        if(p1.posY<0||p1.posY<gucci.dimension){
                            seSale=true;
                        }
                        visto.agregar(p1);
                        this.encontrarItem();
                        result=true;
                        p1.cambiarVida(-2);                        
                        break;
                    case 's':
                        p1.moverX(1);
                        visto.agregar(p1);
                        this.encontrarItem();
                        result=true;
                        p1.cambiarVida(-2);                       
                        break;
                    case 'd':
                        p1.moverY(1); 
                        visto.agregar(p1);
                        this.encontrarItem();
                        result=true;
                        p1.cambiarVida(-2);                     
                        break;  
                    case 'j':
                        visto.moverY(-1);
                        visto.agregar(visto);
                        this.recorrido();
                        result=true;
                        p1.cambiarVida(-1);                      
                        break;
                    case 'i':
                        visto.moverX(-1);
                        visto.agregar(visto);
                        result=true;
                        p1.cambiarVida(-1);                        
                        break;
                    case 'k':
                        visto.moverX(1);
                        visto.agregar(visto);
                        result=true;
                        p1.cambiarVida(-1);                       
                        break;
                    case 'l':
                        visto.moverY(1);
                        visto.agregar(visto);
                        result=true;
                        p1.cambiarVida(-1);                        
                        break;  
                    default:
                        result=false;
                        break;
                }
        }
        this.turno++;
        this.recorrido();
    }
    
    public void representacion(Espacio o){
        o.representacion=' ';
        for(int i=0;i<comida.cantidad;i++){
            Comida food = (Comida) comida.matriz[i];
            if(o.posX==food.posX&&o.posY==food.posY){
                o.representacion=food.representacion;
            }
        }
        for(int i=0;i<enemigo.cantidad;i++){
            Enemigo enemy = (Enemigo) enemigo.matriz[i];
            if(o.posX==enemy.posX&&o.posY==enemy.posY){
                o.representacion=enemy.representacion;
            } 
        }
        for(int i=0;i<tesoro.cantidad;i++){
            Tesoro chest = (Tesoro) tesoro.matriz[i];
            if(o.posX==chest.posX&&o.posY==chest.posY){
                o.representacion=chest.representacion;
            }
        }
    }
    
    private void encontrarItem() {
        int i;
        for(i=0;i<tesoro.cantidad;i++){
            Tesoro chest = (Tesoro) tesoro.matriz[i];
            if(p1.posX==chest.posX&&p1.posY==chest.posY&&ItemYaEncontrado()){
                System.out.println("Encontraste un Tesoro!");
                tesoro.encontrados++;
                tesoro.yaSalio[tesoro.encontrados-1]=chest;
                gucci.agregar(chest);
            }
        }
        for(i=0;i<comida.cantidad;i++){
            Comida food = (Comida) comida.matriz[i];
            if(p1.posX==food.posX&&p1.posY==food.posY&&ItemYaEncontrado()){
                System.out.println("Encontraste Comida! Salud+3");
                comida.encontrados++;
                comida.yaSalio[comida.encontrados-1]=food; 
                p1.cambiarVida(food.afecta);
                gucci.agregar(comida);
            }
        }
        for(i=0;i<enemigo.cantidad;i++){
            Enemigo enemy = (Enemigo) enemigo.matriz[i];
            if(p1.posX==enemy.posX&&p1.posY==enemy.posY){
                System.out.println("Encontraste Un Enemigo! Salud-5");
                p1.cambiarVida(enemy.afecta);
                gucci.agregar(enemy);
            }
        }
    }
    
    private boolean ItemYaEncontrado(){
        boolean ya = true;
        int i;
        for(i=0;i<tesoro.encontrados;i++){
            Tesoro chest = (Tesoro) tesoro.yaSalio[i];
            if(p1.posX==chest.posX&&p1.posY==chest.posY){
                ya = false;
            }
        }
        for(i=0;i<comida.encontrados;i++){
            Comida food = (Comida) comida.yaSalio[i];
            if(p1.posX==food.posX&&p1.posY==food.posY){
                ya = false;
            }
        }
        for(i=0;i<enemigo.encontrados;i++){
            Enemigo enemy = (Enemigo) enemigo.yaSalio[i];
            if(p1.posX==enemy.posX&&p1.posY==enemy.posY){
                ya = false;
            }
        }
        return ya;
    }
    
    @Override
    public String toString() {
        return "$:" + tesoro.encontrados + "/"+tesoro.cantidad +", !:"+enemigo.encontrados + "/"+enemigo.cantidad+", +:"+comida.encontrados + "/"+comida.cantidad;
    }

    private void recorrido() {
        for(int i=0;i<this.turno;i++){
            Espacio walked = (Espacio) visto.matriz[i];
            this.representacion(walked);
            gucci.agregar(walked);
        }
    }
    
}
