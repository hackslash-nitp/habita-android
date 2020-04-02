package club.hackslash.habita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class camStart extends AppCompatActivity  {
    Camera camera;
    FrameLayout frameLayout;
    showCam showcam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_start);


        frameLayout = findViewById(R.id.camFrame);

        camera = Camera.open();
        showcam = new showCam(this, camera);
        frameLayout.addView(showcam);



    }

}
