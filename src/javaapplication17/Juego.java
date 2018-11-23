
package javaapplication17;

import java.util.Scanner;

public final class Juego {
    Personaje p1;
    Crater gucci;
    Tesoro tesoro;
    Comida comida;
    Enemigo enemigo;
    Espacio visto;

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
        visto = new Espacio(p1);
        boolean seSale=false;
        this.representacion();
        gucci.agregar(visto);
        while(!seSale){
            Scanner lector = new Scanner(System.in);
            System.out.println("W(a/j) N(w/i) S(s/k) E(d/l)");
            char opcion = lector.next().charAt(0);
                switch(opcion){
                    case 'a':                     
                        if(p1.posY-1<0){
                            seSale=true;
                            break;
                        }
                        p1.moverY(-1);
                        this.representacion();
                        gucci.agregar(visto);
                        this.encontrarItem();
                        p1.cambiarVida(-2);
                        break;
                    case 'w':                       
                        if(p1.posX-1<0){
                            seSale=true;
                            break;
                        }
                        p1.moverX(-1);
                        this.representacion();
                        gucci.agregar(visto);
                        this.encontrarItem();
                        p1.cambiarVida(-2);                        
                        break;
                    case 's':                        
                        if(p1.posX+1==gucci.dimension){
                            seSale=true;
                            break;
                        }
                        p1.moverX(1);
                        this.representacion();
                        gucci.agregar(visto);
                        this.encontrarItem();
                        p1.cambiarVida(-2);                       
                        break;
                    case 'd':                       
                        if(p1.posY+1==gucci.dimension){
                            seSale=true;
                            break;
                        }
                        p1.moverY(1); 
                        this.representacion();
                        gucci.agregar(visto);
                        this.encontrarItem();
                        p1.cambiarVida(-2);                     
                        break;  
                    case 'j':
                        if(visto.posY-1<0){
                            seSale=true;
                            break;
                        }
                        visto.moverY(-1);
                        this.representacion();
                        gucci.agregar(visto);
                        p1.cambiarVida(-1);                      
                        break;
                    case 'i':
                        if(visto.posX-1<0){
                            seSale=true;
                            break;
                        }
                        visto.moverX(-1);
                        this.representacion();
                        gucci.agregar(visto);
                        p1.cambiarVida(-1);                        
                        break;
                    case 'k':
                        if(visto.posX+1==gucci.dimension){
                            seSale=true;
                            break;
                        }
                        visto.moverX(1);
                        this.representacion();
                        gucci.agregar(visto);
                        p1.cambiarVida(-1);                       
                        break;
                    case 'l':
                        if(visto.posY+1==gucci.dimension){
                            seSale=true;
                            break;
                        }
                        visto.moverY(1);
                        this.representacion();
                        gucci.agregar(visto);
                        p1.cambiarVida(-1);                        
                        break;  
                    default:
                        seSale=true;
                        break;
                }
        }
    }
    
    public void representacion(){
        visto.representacion=' ';
        for(int i=0;i<comida.cantidad;i++){
            Comida food = (Comida) comida.matriz[i];
            if(visto.posX==food.posX&&visto.posY==food.posY){
                visto.representacion=food.representacion;
            }
        }
        for(int i=0;i<enemigo.cantidad;i++){
            Enemigo enemy = (Enemigo) enemigo.matriz[i];
            if(visto.posX==enemy.posX&&visto.posY==enemy.posY){
                visto.representacion=enemy.representacion;
            } 
        }
        for(int i=0;i<tesoro.cantidad;i++){
            Tesoro chest = (Tesoro) tesoro.matriz[i];
            if(visto.posX==chest.posX&&visto.posY==chest.posY){
                visto.representacion=chest.representacion;
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
            if(p1.posX==enemy.posX&&p1.posY==enemy.posY&&ItemYaEncontrado()){
                System.out.println("Encontraste Un Enemigo! Salud-5");
                enemigo.encontrados++;
                enemigo.yaSalio[enemigo.encontrados-1]=enemy;
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
    
}
