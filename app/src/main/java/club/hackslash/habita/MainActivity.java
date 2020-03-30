package club.hackslash.habita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_SHORT;



public class MainActivity extends AppCompatActivity {
    ImageView imgprofile;
    TextView notyet;
    Button btnask,receipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_scanner);


        imgprofile = findViewById(R.id.imgprofile);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pubg);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imgprofile.setImageDrawable(roundedBitmapDrawable);

        notyet = findViewById(R.id.notyet);
        btnask  = findViewById(R.id.AskPer);
        receipe = findViewById(R.id.receipe);
        receipe.setVisibility(View.GONE);

        btnask.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}
                            , 2);

                }

                else {
                    Intent intent = new Intent(MainActivity.this,club.hackslash.habita.camStart.class);
                    startActivityForResult(intent,22);
                }
            }
        } );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 2){
            //&& grantResults[0] == ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "THANK YOU,PERMISSION GRANTED!!", LENGTH_SHORT).show();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 22);
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("It is important to granted the permission,So that we can scan the image.Please Permit It!")
                            .setTitle("IMPORTANT PERMISSION REQUIRED!");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                        }
                    });
                    dialog.setNegativeButton("Not Scan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Not Done", LENGTH_SHORT).show();
                        }
                    });
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        dialog.show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Never Show Again", LENGTH_SHORT).show();
                }

            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 22)  {
            if(resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this,"Scan Successfully", LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(MainActivity.this,"Not Scan", LENGTH_SHORT).show();
            }

        }
    }

}








