package com.example.armando.boton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button boton1, boton2, boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1=(Button)findViewById(R.id.Boton1);
        boton2=(Button)findViewById(R.id.Boton2);
        boton3=(Button)findViewById(R.id.Boton3);

    boton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Este es el boton 1", Toast.LENGTH_SHORT).show();
        }
    });

        boton2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Este es el boton 2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Este es el boton 3", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*
    public void pulsarBoton1(View vista) {
        Toast.makeText(this, "Este es el boton1", Toast.LENGTH_SHORT).show();
    }

    public void pulsarBoton2(View vista) {
        Toast.makeText(this, "Este es el boton2", Toast.LENGTH_SHORT).show();
    }

    public void pulsarBoton3(View vista) {
        Toast.makeText(this, "Este es el boton3", Toast.LENGTH_SHORT).show();
    }
*/
}
