package club.hackslash.habita;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

public class recipe_adapter extends RecyclerView.Adapter<recipe_adapter.ViewHolder> {
    private ArrayList<food_recipe> recipelist;
    Context maincontext;

    public recipe_adapter(Context context, ArrayList<food_recipe> recipelist) {
            maincontext = context;
        this.recipelist = recipelist;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgrecipe;
        int index;
        TextView recipeName,missIng,ava_ing,miss_item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgrecipe =itemView.findViewById(R.id.imgRecipe);
            missIng = itemView.findViewById(R.id.missIng);
            recipeName = itemView.findViewById(R.id.recipeName);
            ava_ing = itemView.findViewById(R.id.ava_Ing);
            miss_item = itemView.findViewById(R.id.miss_item);
            miss_item.setVisibility(View.GONE);

           missIng.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   index = (recipelist.indexOf((food_recipe) v.getTag()));
                   if(miss_item.getVisibility() == View.GONE) {
                       miss_item.setVisibility(View.VISIBLE);
                       missIng.setText("^ Missing Ingredient");
                   }
                   else {
                       miss_item.setVisibility(View.GONE);
                       missIng.setText("v Missing Ingredient");
                   }
               }

           });
        }
    }

    @NonNull
    @Override
    public recipe_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recipe,parent,false);
        return new ViewHolder(view);
    }

  @Override
    public void onBindViewHolder(@NonNull recipe_adapter.ViewHolder holder, int position) {
        holder.missIng.setTag(recipelist.get(position));
        holder.recipeName.setText(recipelist.get(position).getRecipe_name());
        holder.ava_ing.setText("Available Ingredient: "+recipelist.get(position).getAval_ing());



        holder.miss_item.setText("Garlic\nBlack Pepper\nTomato");

       Bitmap bitmap = BitmapFactory.decodeResource(maincontext.getResources(),R.drawable.food);
      RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(maincontext.getResources(),bitmap);
      roundedBitmapDrawable.setCircular(true);
      holder.imgrecipe.setImageDrawable(roundedBitmapDrawable);


    }

    @Override
    public int getItemCount() {
        return recipelist.size();
    }
}
