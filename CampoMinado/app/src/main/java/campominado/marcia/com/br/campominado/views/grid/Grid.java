package campominado.marcia.com.br.campominado.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import campominado.marcia.com.br.campominado.JogoMain;

/**
 * Created by marci on 27/10/2017.
 */

public class Grid extends GridView{

    public Grid(Context context , AttributeSet attrs){
        super(context,attrs);

        JogoMain.getInstance().createGrid(context);

        setNumColumns(JogoMain.LARGURA);
        setAdapter(new GridAdapter());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return JogoMain.getInstance().LARGURA * JogoMain.getInstance().ALTURA;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return  JogoMain.getInstance().getCellAt(position);
        }
    }
}