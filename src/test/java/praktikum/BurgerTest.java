package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerTest {
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientChoice;
    private final float ingredientPrice;
    private final String ingredientName;
    private final float burgerPrice;

    public BurgerTest(String bunName, float bunPrice, IngredientType ingredientChoice, float ingredientPrice, String ingredientName, float burgerPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientChoice = ingredientChoice;
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
        this.burgerPrice = burgerPrice;
    }

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredientAnother;

    @Parameterized.Parameters(name = "{index}: checkBurgerPrice {5}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Булочка", 99.99F, IngredientType.SAUCE, 109.99F, "Соус", 309.97F},
                {"Хлебушек", 59.99F, IngredientType.FILLING, 59.99F, "Колбаска", 179.97F},
                {"Булка5000", 299.99F, IngredientType.FILLING, 599.99F, "5кг ингридиентов", 1199.97F}
        };
    }

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
    public void getPriceTest() {
        Burger burger = new Burger();
        Bun bun = new Bun(bunName, bunPrice);
        Ingredient ingredient = new Ingredient(ingredientChoice, ingredientName, ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float actual = burger.getPrice();
        Assert.assertEquals(burgerPrice, actual, 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = Mockito.spy(new Burger());
        burger.setBuns(bun);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredientAnother);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientAnother.getType()).thenReturn(IngredientType.FILLING);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        burger.getReceipt();

        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredient, Mockito.times(1)).getName();
        Mockito.verify(ingredientAnother, Mockito.times(1)).getName();
        Mockito.verify(ingredient, Mockito.times(1)).getType();
        Mockito.verify(ingredientAnother, Mockito.times(1)).getType();
        Mockito.verify(ingredientAnother, Mockito.times(1)).getType();
        Mockito.verify(burger, Mockito.times(1)).getPrice();
    }
}
