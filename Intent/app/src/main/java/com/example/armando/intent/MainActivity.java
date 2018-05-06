package com.example.armando.intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btn;
    ImageView img;
    Intent i;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iniciar();
    }

    public void Iniciar () {
        btn=(Button)findViewById(R.id.btnCamara);
        btn.setOnClickListener(this);
        img=(ImageView)findViewById(R.id.imagen);

    }

    @Override
    public void onClick (View view) {
        switch(view.getId()) {
            case R.id.btnCamara:
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode== Activity.RESULT_OK)&&requestCode==1) {
            Bundle ext=data.getExtras();
            bmp=(Bitmap)ext.get("data");
            img.setImageBitmap(bmp);
        }
    }
}
