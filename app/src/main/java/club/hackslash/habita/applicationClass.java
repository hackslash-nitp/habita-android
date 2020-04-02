package club.hackslash.habita;

import android.app.Application;

import java.util.ArrayList;

public class applicationClass extends Application {
     public static ArrayList<food_recipe> list;

    /*
    This class is made to add item in recycler view, just after the application is started.
    You can remove this....  and add your own list in recycler view


     */
    @Override
    public void onCreate() {
        super.onCreate();
    list = new ArrayList<food_recipe>();
    list.add(new food_recipe("Pizaa","5/10"));
        list.add(new food_recipe("Burger","8/10"));
        list.add(new food_recipe("Fry","6/10"));
        list.add(new food_recipe("Chicken Roll","2/10"));
        list.add(new food_recipe("Pasta","10/10"));
        list.add(new food_recipe("Chowmien","3/10"));
        list.add(new food_recipe("Dhosa","9/10"));
        list.add(new food_recipe("Petiz","8/10"));


    }
}
