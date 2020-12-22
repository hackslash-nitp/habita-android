package club.hackslash.habita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.nio.charset.StandardCharsets;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import java.util.ArrayList;


public class camStart extends AppCompatActivity  {
    Camera camera;
    FrameLayout frameLayout;
    showCam showcam;
    ImageView cam_cap;
    byte imageBytedata[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_start);

        cam_cap = findViewById(R.id.cam_capture);


        frameLayout = findViewById(R.id.camFrame);

        frameLayout = findViewById(R.id.camFrame);
        camera = Camera.open();
        showcam = new showCam(this, camera);
        frameLayout.addView(showcam);


    cam_cap.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(camera != null) {
                camera.takePicture(null, null, mPictureCallBack);

            }
            else
            {
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        }
    });
    }

    Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
           String  s = new String(data, StandardCharsets.UTF_8);
            imageBytedata = data;
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",imageBytedata);
            setResult(camStart.RESULT_OK,returnIntent);
            finish();
            Log.d("byte data",s);

      }
    };

}


