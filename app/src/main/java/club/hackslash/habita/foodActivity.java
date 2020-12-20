package club.hackslash.habita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import java.util.Random;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static android.widget.Toast.LENGTH_SHORT;

public class foodActivity extends AppCompatActivity {

    ImageView imgprofile,foodImage;
    TextView notyet;
    Button btnask;
    private StorageReference foodImageRef;
    private  int CAMERACODE = 7777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_scanner);

        foodImage = findViewById(R.id.foodimage);
        foodImage.setVisibility(View.GONE);
        imgprofile = findViewById(R.id.imgprofile);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.persontab);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imgprofile.setImageDrawable(roundedBitmapDrawable);

        notyet = findViewById(R.id.notyet);
        btnask  = findViewById(R.id.AskPer);




        btnask.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(club.hackslash.habita.foodActivity.this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(club.hackslash.habita.foodActivity.this, new String[]{Manifest.permission.CAMERA}
                            , 2);

                }

                else {
                    Intent intent = new Intent(club.hackslash.habita.foodActivity.this,club.hackslash.habita.camStart.class);
                    startActivityForResult(intent,CAMERACODE);
                }
            }
        } );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 2){
            //&& grantResults[0] == ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(club.hackslash.habita.foodActivity.this, "THANK YOU,PERMISSION GRANTED!!", LENGTH_SHORT).show();
                Intent intent = new Intent(club.hackslash.habita.foodActivity.this,club.hackslash.habita.camStart.class);
                startActivityForResult(intent,CAMERACODE);
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(club.hackslash.habita.foodActivity.this, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("It is important to granted the permission,So that we can scan the image.Please Permit It!")
                            .setTitle("IMPORTANT PERMISSION REQUIRED!");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(club.hackslash.habita.foodActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                        }
                    });
                    dialog.setNegativeButton("Not Scan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(club.hackslash.habita.foodActivity.this, "Not Done", LENGTH_SHORT).show();
                        }
                    });
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        dialog.show();
                    }
                } else {
                    Toast.makeText(club.hackslash.habita.foodActivity.this, "Never Show Again", LENGTH_SHORT).show();
                }

            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERACODE && resultCode == RESULT_OK){
            onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        /* Bitmap image = (Bitmap) data.getExtras().get("result");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,bytes);
        byte[] bb  = bytes.toByteArray();
         */
        uploadToFirebase((byte[]) data.getExtras().get("result"));

    }

    private void uploadToFirebase(byte[] bb) {
         Random rand = new Random();
         int randomNumber = rand.nextInt(1000000);
         final StorageReference storePath = FirebaseStorage.getInstance().getReference().child("foodImages/"+randomNumber+".jpg");

        storePath.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(foodActivity.this,"food Image is uploaded", LENGTH_SHORT);
                storePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final String download_url = uri.toString();
                       Log.i("download url","Url is downloaded here ");
                        Picasso.get().load(download_url).into(foodImage);
                        foodImage.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(foodActivity.this,"food Image is failed ", LENGTH_SHORT);

            }
        });
    }

}

