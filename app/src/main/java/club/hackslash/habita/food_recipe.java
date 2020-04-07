package club.hackslash.habita;

public class food_recipe {
    private  String recipe_name;
    private String aval_ing;

    public food_recipe(String recipe_name, String aval_ing) {
        this.recipe_name = recipe_name;
        this.aval_ing = aval_ing;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getAval_ing() {
        return aval_ing;
    }

    public void setAval_ing(String aval_ing) {
        this.aval_ing = aval_ing;
    }
}
