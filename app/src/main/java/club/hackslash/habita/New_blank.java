package club.hackslash.habita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import club.hackslash.habita.fragments.Home;
import club.hackslash.habita.fragments.profile;
import club.hackslash.habita.fragments.settings;

public class New_blank extends AppCompatActivity {

    private static final String TAG = New_blank.class.getSimpleName();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_blank);

        ChipNavigationBar navbar =findViewById(R.id.nav_bar);
        if(savedInstanceState==null){
            navbar.setItemSelected(R.id.home,true);
            fragmentManager=getSupportFragmentManager();
            Home home=new Home();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,home).commit();
        }
        navbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                switch (i){
                    case R.id.home:
                        fragment=new Home();
                        break;
                    case R.id.profile:
                        fragment=new profile();
                        break;
                    case R.id.setting:
                        fragment=new settings();
                        break;
                }
                if(fragment!=null){
                    fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }
                else {
                    Log.e(TAG,"ERROR");
                }
            }
        });
    }
}
