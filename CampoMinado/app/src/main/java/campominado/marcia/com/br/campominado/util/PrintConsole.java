package campominado.marcia.com.br.campominado.util;

import android.util.Log;

/**
 * Created by marcia on 27/10/2017.
 */

public class PrintConsole {
    public static void print(final int grid[][], final int largura, final int altura){
        for (int i = 0; i < largura; i++){
            String printTest = " | " ;
            for (int j = 0; j < altura; j++){
                printTest += String.valueOf(grid[i][j]).replace("-1", "B") + " | ";
            }
            Log.e("",printTest);
        }
    }
}
