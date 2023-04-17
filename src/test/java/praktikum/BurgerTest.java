package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

public class BurgerTest {
    @Spy
    Bun bun = new Bun("Булочка", 99.99F);

    @Spy
    Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Соус", 109.99F);

    @Spy
    Ingredient ingredientAnother = new Ingredient(IngredientType.FILLING, "Колбаска", 59.99F);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        Assert.assertEquals(ingredients, burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        burger.removeIngredient(1);

        Assert.assertEquals(ingredients.size() - 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(ingredient);
        ingredients.add(ingredientAnother);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        burger.moveIngredient(1, 0);

        Assert.assertEquals(ingredientAnother, burger.ingredients.get(0));
        Assert.assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptTest() {
        Burger burger = Mockito.spy(new Burger());
        burger.setBuns(bun);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredientAnother);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        burger.getReceipt();

        Mockito.verify(bun, Mockito.times(2)).getName();
        Assert.assertEquals("Булочка", bun.getName());
        Mockito.verify(ingredient, Mockito.times(1)).getName();
        Assert.assertEquals("Соус", ingredient.getName());
        Mockito.verify(ingredientAnother, Mockito.times(1)).getName();
        Assert.assertEquals("Колбаска", ingredientAnother.getName());
        Mockito.verify(ingredient, Mockito.times(1)).getType();
        Assert.assertEquals(IngredientType.SAUCE, ingredient.getType());
        Mockito.verify(ingredientAnother, Mockito.times(1)).getType();
        Assert.assertEquals(IngredientType.FILLING, ingredientAnother.getType());
        Mockito.verify(burger, Mockito.times(1)).getPrice();
        Assert.assertEquals(369.96F, burger.getPrice(), 0);
    }
}
