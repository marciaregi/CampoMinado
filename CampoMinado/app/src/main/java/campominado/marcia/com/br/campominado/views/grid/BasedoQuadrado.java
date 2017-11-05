package campominado.marcia.com.br.campominado.views.grid;

import android.content.Context;
import  android.view.View;

import campominado.marcia.com.br.campominado.JogoMain;

/**
 * Created by marci on 27/10/2017.
 */


public abstract class BasedoQuadrado extends View {

    private int valor;
    private boolean bomba;
    private boolean revelado;
    private boolean clicado;
    private boolean marcado;

    private int x , y;
    private int posicao;

    public BasedoQuadrado(Context context ){
        super(context);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        bomba = false;
        revelado = false;
        clicado = false;
        marcado = false;

        if( valor == -1 ){
            bomba = true;
        }

        this.valor = valor;
    }

    public boolean bomba() {
        return bomba;
    }

    public void setBomba(boolean bomba) {
        bomba = bomba;
    }

    public boolean revelado() {
        return revelado;
    }

    public void setRevelado() {
        revelado = true;
        invalidate();
    }

    public boolean clicado() {
        return clicado;
    }

    public void setClicado() {
        this.clicado = true;
        this.revelado = true;

        invalidate();
    }

    public boolean marcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        marcado = marcado;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao( int x , int y ){
        this.x = x;
        this.y = y;

        this.posicao = y * JogoMain.LARGURA + x;

        invalidate();
    }

}
