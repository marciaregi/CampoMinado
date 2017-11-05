package campominado.marcia.com.br.campominado;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import campominado.marcia.com.br.campominado.util.Gerador;
import campominado.marcia.com.br.campominado.util.PrintConsole;
import campominado.marcia.com.br.campominado.views.grid.Quadrados;
/**
 * Created by marcia on 27/10/2017.
 */

public class JogoMain {
    private static JogoMain instance;

    public static final int NUMERO_BOMBA = 10;
    public static final int LARGURA = 10;
    public static final int ALTURA = 16;

    private Context context;

    private Quadrados[][] CampoMinadoGrid = new Quadrados[LARGURA][ALTURA];

    public static JogoMain getInstance() {
        if( instance == null ){
            instance = new JogoMain();
        }
        return instance;
    }

    private JogoMain(){ }

    public void createGrid(Context context){
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = Gerador.generate(NUMERO_BOMBA,LARGURA, ALTURA);
        PrintConsole.print(GeneratedGrid,LARGURA,ALTURA);
        setGrid(context,GeneratedGrid);
    }

    private void setGrid( final Context context, final int[][] grid ){
        for( int x = 0 ; x < LARGURA ; x++ ){
            for( int y = 0 ; y < ALTURA ; y++ ){
                if( CampoMinadoGrid[x][y] == null ){
                    CampoMinadoGrid[x][y] = new Quadrados( context , x,y);
                }
                CampoMinadoGrid[x][y].setValor(grid[x][y]);
                CampoMinadoGrid[x][y].invalidate();
            }
        }
    }

    public Quadrados getCellAt(int position) {
        int x = position % LARGURA;
        int y = position / LARGURA;

        return CampoMinadoGrid[x][y];
    }

    public Quadrados getCellAt(int x , int y ){
        return CampoMinadoGrid[x][y];
    }

    public void click( int x , int y ){
        if( x >= 0 && y >= 0 && x < LARGURA && y < ALTURA && !getCellAt(x,y).clicado() ){
            getCellAt(x,y).setClicado();

            if( getCellAt(x,y).getValor() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCellAt(x,y).bomba() ){
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd(){
        int bombNotFound = NUMERO_BOMBA;
        int notRevealed = LARGURA * ALTURA;
        for ( int x = 0 ; x < LARGURA ; x++ ){
            for( int y = 0 ; y < ALTURA ; y++ ){
                if( getCellAt(x,y).revelado() || getCellAt(x,y).marcado() ){
                    notRevealed--;
                }

                if( getCellAt(x,y).marcado() && getCellAt(x,y).bomba() ){
                    bombNotFound--;
                }
            }
        }

        if( bombNotFound == 0 && notRevealed == 0 ){
            Toast.makeText(context,"Você ganhou :D", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag( int x , int y ){
        boolean isFlagged = getCellAt(x,y).marcado();
        getCellAt(x,y).setMarcado(!isFlagged);
        getCellAt(x,y).invalidate();
    }

    private void onGameLost(){
        // handle lost game
        Toast.makeText(context,"Você perdeu :(", Toast.LENGTH_SHORT).show();

        for ( int x = 0 ; x < LARGURA ; x++ ) {
            for (int y = 0; y < ALTURA; y++) {
                getCellAt(x,y).setRevelado();
            }
        }
    }

}
