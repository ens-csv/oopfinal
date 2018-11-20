
package javaapplication17;

import java.util.Scanner;

public class JavaApplication17 {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        System.out.println("\t Welcome! ");
        System.out.println("\t Instructions: ");
        System.out.println("You play until you find all chests or you die. ");
        System.out.println("You get to choose a level: ");
        System.out.println("Level 1: Map:5x5, Life:50, +:8, !: 2, $: 1");
        System.out.println("Level 2: Map:8x8, Life:40, +:6, !: 4, $: 2");
        System.out.println("Level 3: Map:12x12, Life:30, +:8, !: 8, $: 4");
        System.out.println("+=food, !=enemy, $=chest");
        System.out.println("You land on +: +3 life, You land on !: -5 life.");
        System.out.println("\t Controls ");
        System.out.println("W(a/j) N(w/i) S(s/k) E(d/l)");
        System.out.println("key a -> Move West, key j-> See West");
        System.out.println("key w -> Move North, key i-> See North");
        System.out.println("key s -> Move South, key k-> See South");
        System.out.println("key d -> Move East, key l-> See East");
        System.out.println("Every Move: -2 life, Every See: -1 life.");
        System.out.println("\t Agree?(y/n): ");
        char agree = lector.next().charAt(0);
        if(agree=='y'){
            System.out.println("Choose Level(1/2/3): ");
            int dificultad = lector.nextInt();
            Juego partida = new Juego(dificultad);
        }
        else{
            System.out.println("Thank you! ");
        }
    }
    
}
