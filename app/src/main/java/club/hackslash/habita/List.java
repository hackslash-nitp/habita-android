package club.hackslash.habita;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class List extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter  myadapter;
    RecyclerView.LayoutManager layoutManager;
    View view;

    public List() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      recyclerView = view.findViewById(R.id.recycler_list);
      recyclerView.setHasFixedSize(true);
      layoutManager = new LinearLayoutManager(this.getActivity());
      recyclerView.setLayoutManager(layoutManager);

      myadapter = new recipe_adapter(this.getActivity(),applicationClass.list);
        recyclerView.setAdapter(myadapter);

    }
}
