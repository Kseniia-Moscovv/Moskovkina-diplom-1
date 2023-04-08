package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTest {
    private IngredientType typeFilling = IngredientType.FILLING;
    private IngredientType typeSauce = IngredientType.SAUCE;
    private String name = "forКрабсбургер";
    private float price = 199.99F;

    @Test
    public void ingredientPriceTest() {
        Ingredient ingredient = new Ingredient(typeFilling, name, price);
        float actual = ingredient.getPrice();
        Assert.assertEquals(price, actual, 0);
    }

    @Test
    public void ingredientNameTest() {
        Ingredient ingredient = new Ingredient(typeSauce, name, price);
        String actual = ingredient.getName();
        Assert.assertEquals(name, actual);
    }

    @Test
    public void ingredientTypeFillingTest() {
        Ingredient ingredient = new Ingredient(typeFilling, name, price);
        IngredientType actual = ingredient.getType();
        Assert.assertEquals(typeFilling, actual);
    }

    @Test
    public void ingredientTypeSauceTest() {
        Ingredient ingredient = new Ingredient(typeSauce, name, price);
        IngredientType actual = ingredient.getType();
        Assert.assertEquals(typeSauce, actual);
    }
}
