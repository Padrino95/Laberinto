

import java.util.Random;
import java.util.Scanner;

public class Laberinto {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random generador = new Random();

        final char jugador = 'P';
        final char bomba = '❗';
        final char llave = '¥';
        final char salida = '✔';
        final int num_bombas = 10;

        boolean fin = false;
        boolean tiene_llave = false;
        boolean llave_colocada = false;
        boolean jugador_colocado = false;
        boolean salida_colocada = false;
        boolean bomba_colocada = false;

        //POSICION DEL JUGADOR
        int jugador_i, jugador_j;
        //OPCION QUE ELIGE PARA MOVERSE
        int opcion;
        //VARIABLES PARA LAS POSICIONES ALEATORIAS DE LOS OBJETOS A COLOCAR INICIALMENTE
        int ale_i, ale_j;
        int sig_i, sig_j;

        String[] direccion = {"ARRIBA", "ABAJO", "IZQUIERDA", "DERECHA"};
        int[] dir_i = {-1, 1, 0, 0};
        int[] dir_j = {0, 0, -1, 1};
        boolean[] posibles = new boolean[direccion.length];

        //DIBUJAR EL LABERINTO
        char[][] laberinto = {{'|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
        {'|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|'},
        {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'},
        {'|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|'}};

        //COLOCAMOS LA LLAVE
        do {
            ale_i = generador.nextInt(laberinto.length);
            ale_j = generador.nextInt(laberinto[0].length);
            if (laberinto[ale_i][ale_j] == ' ') {
                laberinto[ale_i][ale_j] = llave;
                llave_colocada = true;
            }
        } while (!llave_colocada);
        //COLOCAMOS LA SALIDA
        do {
            ale_i = generador.nextInt(laberinto.length);
            ale_j = generador.nextInt(laberinto[0].length);
            if (laberinto[ale_i][ale_j] == ' ') {
                laberinto[ale_i][ale_j] = salida;
                salida_colocada = true;
            }
        } while (!salida_colocada);
        //COLOCAMOS AL JUGADOR
        do {
            ale_i = generador.nextInt(laberinto.length);
            ale_j = generador.nextInt(laberinto[0].length);
            if (laberinto[ale_i][ale_j] == ' ') {
                laberinto[ale_i][ale_j] = jugador;
                jugador_colocado = true;
            }
        } while (!jugador_colocado);
        jugador_i = ale_i;
        jugador_j = ale_j;
        //GENERAMOS LAS BOMBAS
        for (int i = 0; i < num_bombas; i++) {
            do {
                ale_i = generador.nextInt(laberinto.length);
                ale_j = generador.nextInt(laberinto[0].length);
                if (laberinto[ale_i][ale_j] == ' ') {
                    laberinto[ale_i][ale_j] = bomba;
                    bomba_colocada = true;
                }
            } while (!bomba_colocada);
            bomba_colocada = false;
        }

        //BUCLE DEL JUEGO
        System.out.println("Intenta salir del laberinto");
        do {
            //DIBUJAMOS EL TABLERO
            for (int i = 0; i < laberinto.length; i++) {
                for (int j = 0; j < laberinto[0].length; j++) {
                    System.out.print(laberinto[i][j]);

                }
                System.out.println();
            }
            //CALCULAR QUE MOVIMIENTOS PUEDE HACER EL JUGADOR
            boolean[] visitables = new boolean[dir_i.length];
            for (int k = 0; k < dir_i.length; k++) {
                if (jugador_i + dir_i[k] < laberinto.length
                        && jugador_j + dir_j[k] < laberinto[0].length
                        && jugador_i + dir_i[k] >= 0
                        && jugador_j + dir_j[k] >= 0) {
                    if (laberinto[jugador_i + dir_i[k]][jugador_j + dir_j[k]] != '|') {
                        visitables[k] = true;
                    }
                }
            }

            //ELEGIR MOVIMIENTO POSIBLE
            for (int k = 0; k < direccion.length; k++) {
                sig_i = jugador_i + dir_i[k];
                sig_j = jugador_j + dir_j[k];
                if (laberinto[sig_i][sig_j] != '|') {
                    posibles[k] = true;
                } else {
                    posibles[k] = false;
                }

            }
            do {
                for (int i = 0; i < direccion.length; i++) {
                    if (posibles[i]) {
                        System.out.println(i + "-" + direccion[i] + ":");
                    }

                }
                System.out.println("Que camino quieres seguir?");
                opcion = teclado.nextInt();
            } while (!(opcion >= 0 && opcion < direccion.length && posibles[opcion]));

            //SI ES NECESARIO VACIAR LA POSICION ACTUAL Y ACTUALIZAR LA POSICION  
            //ADEMAS QUITANDO Y PONIENDO CARACTERES PARA VER LA EVOLUCION DEL JUEGO A TRAVES DE LA MATRIZ
            //EN FUNCION DEL CONTENIDO DE LA NUEVA POSICION 
            sig_i = jugador_i + dir_i[opcion];
            sig_j = jugador_j + dir_j[opcion];

            if (laberinto[sig_i][sig_j] == bomba) {
                System.out.println("has perdido");
                fin = true;
                laberinto[jugador_i][jugador_j] = ' ';
                //tengo que desaparecer el jugador
            } else if (laberinto[sig_i][sig_j] == salida) {
                if (tiene_llave) {
                    System.out.println("has ganado");
                    fin = true;
                    laberinto[jugador_i][jugador_j] = ' ';
                    //desaparecer el jugador de su posicion anterior
                } else {
                    System.out.println("no puedes salir, necesitas la llave");
                }
            } else if (laberinto[sig_i][sig_j] == llave) {
                tiene_llave = true;
                laberinto[jugador_i][jugador_j] = ' ';
                laberinto[sig_i][sig_j] = jugador;
                jugador_i = sig_i;
                jugador_j = sig_j;
            }else{
                laberinto[jugador_i][jugador_j] = ' ';
                laberinto[sig_i][sig_j] = jugador;
                jugador_i = sig_i;
                jugador_j = sig_j;
            }
            //TOCA UNA BOMBA--> SE ACABA EL JUEGO Y MENSAJE FAIL Y DESAPARECE EL JUGADOR
            //TOCA LA LLAVE--> CAMBIAR EL ESTADO DE LA LLAVE Y LA LLAVE NO APARECE
            //TOCA LA SALIDA---> SI TIENE LLAVE SE ACABA EL JUEGO Y MENSAJE OK Y DESAPARECE EL JUGADOR
        } while (!fin);
        //MOSTRAR COMO QUEDA EL LABERINTO AL TERMINAR EL JUEGO
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                System.out.print(laberinto[i][j]);

            }
            System.out.println();
        }
    }

}