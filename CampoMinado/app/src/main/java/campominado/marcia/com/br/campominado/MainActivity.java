package campominado.marcia.com.br.campominado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button buttonJogar = (Button) findViewById(R.id.buttonJogar);

        buttonJogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View args) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        Button buttonSobre = (Button) findViewById(R.id.buttonSobre);

        buttonSobre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View args) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
}
