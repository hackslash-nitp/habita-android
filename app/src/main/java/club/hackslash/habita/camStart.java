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

public class camStart extends AppCompatActivity {
    Camera camera;
    FrameLayout frameLayout;
    showCam showcam;
    View imgflash,btnclose,done;
    ListView lvingredient;
    ArrayList<food_ing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_start);
        imgflash = findViewById(R.id.flash);
        frameLayout = findViewById(R.id.camFrame);
        btnclose= findViewById(R.id.close);
        done =findViewById(R.id.done);

        camera = Camera.open();
        showcam = new showCam(this,camera);
        frameLayout.addView(showcam);

        imgflash.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Drawable d =  imgflash.getBackground();
               if(d.getConstantState() == getResources().getDrawable(R.drawable.flash).getConstantState())
               {
                   imgflash.setBackgroundResource(R.drawable.flashoff);
               }
               else{
                   imgflash.setBackgroundResource(R.drawable.flash);
               }

           }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.release();
                camera = null;
                Intent intent1 = new Intent();
                setResult(RESULT_CANCELED,intent1);
                camStart.this.finish();

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.release();
                camera = null;
                Intent intent1 = new Intent();
                setResult(RESULT_OK,intent1);
                camStart.this.finish();
            }
        });


        list = new ArrayList<food_ing>();
        lvingredient = findViewById(R.id.lvingredient);
        food_ing food1 = new food_ing("Apple");
        food_ing food2 = new food_ing("banana");
        food_ing food3 = new food_ing("mustard oil");
        list.add(food1);
        list.add(food2);
        list.add(food3);
        list.add(food1);
        list.add(food2);
        list.add(food3);

        food_adapter myadaptor = new food_adapter(camStart.this, list);
        lvingredient.setAdapter(myadaptor);

    }

}
