package campominado.marcia.com.br.campominado.util;

import java.util.Random;
import static java.lang.Thread.sleep;

/**
 * Created by marcia on 27/10/2017.
 */

public class Gerador {

    public static int[][] generate( int bombanumero , final int largura , final int altura){
        // Random ira gerar os numeros aleatorios
        Random r = new Random();

        int [][] grid = new int[largura][altura];
        for( int x = 0 ; x< largura ;x++ ){
            grid[x] = new int[altura];
        }
        int timer = 0;
        int splashTime = 1000;
        int contador=0;

        Thread th = new Thread() {

            @Override
            public void run() {
                try {
                    for (timer = 0; timer < 3; timer++) {
                        int waited = 0;
                        while (waited < splashTime) {
                            Thread.sleep(5000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                            Thread.sleep(5000);

                                        while( bombanumero > 0 ){
                                            int x = r.nextInt(largura);
                                            int y = r.nextInt(altura);

                                            // -1 e a bomba
                                            if( grid[x][y] != -1 ){
                                                grid[x][y] = -1;
                                                bombanumero--;
                                            }
                                        }
                                        grid = calcularVizinhos(grid,largura,altura);

                                            contador = 0;
                                        return grid;

                                    }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                }

            }
        };
        th.start();
    }


    private static int[][] calcularVizinhos( int[][] grid , final int largura , final int altura){
        for( int x = 0 ; x < largura ; x++){
            for( int y = 0 ; y < altura ; y++){
                grid[x][y] = getNumeroVizinho(grid,x,y,largura,altura);
            }
        }

        return grid;
    }

    private static int getNumeroVizinho( final int grid[][] , final int x , final int y , final int largura , final int altura){
        if( grid[x][y] == -1 ){
            return -1;
        }

        int count = 0;

        if( isMineAt(grid,x - 1 ,y + 1,largura,altura)) count++; // topo-esquerda
        if( isMineAt(grid,x     ,y + 1,largura,altura)) count++; // topo
        if( isMineAt(grid,x + 1 ,y + 1,largura,altura)) count++; // topo-direita
        if( isMineAt(grid,x - 1 ,y    ,largura,altura)) count++; // esquerda
        if( isMineAt(grid,x + 1 ,y    ,largura,altura)) count++; // direita
        if( isMineAt(grid,x - 1 ,y - 1,largura,altura)) count++; // inferior-esquerda
        if( isMineAt(grid,x     ,y - 1,largura,altura)) count++; // inferior
        if( isMineAt(grid,x + 1 ,y - 1,largura,altura)) count++; // inferior-direita

        return count;
    }

    private static boolean isMineAt( final int [][] grid, final int x , final int y , final int largura , final int altura){
        if( x >= 0 && y >= 0 && x < largura && y < altura ){
            if( grid[x][y] == -1 ){
                return true;
            }
        }
        return false;
    }

}
