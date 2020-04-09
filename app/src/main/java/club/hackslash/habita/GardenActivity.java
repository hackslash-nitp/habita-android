package club.hackslash.habita;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GardenActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView emptyView;
    private Button waterPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        ArrayList<GardenItem> exampleList= new ArrayList<>();
        exampleList.add(new GardenItem(R.drawable.rose,"ROSE","Last watered at 3:40 PM"));


        mRecyclerView= findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter= new GardenAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        emptyView = (TextView)findViewById(R.id.empty_view);
        waterPlant= findViewById(R.id.water_plant);

        if (mAdapter.getItemCount()==0) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            waterPlant.setVisibility(View.GONE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            waterPlant.setVisibility(View.VISIBLE);
        }
    }
}

