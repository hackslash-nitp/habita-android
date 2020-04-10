package club.hackslash.habita;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MyBattery myBattery;



    RelativeLayout food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
 
        myBattery= new MyBattery();
    }
    @Override
    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter= new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);





        registerReceiver(myBattery,intentFilter);
        //to register broadcast receiver
 
        food=findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),foodActivity.class));
            }
        });


 
    }
    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(myBattery);

    }





}








