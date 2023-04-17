package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientChoice;
    private final float ingredientPrice;
    private final String ingredientName;
    private final float burgerPrice;

    public BurgerParametrizedTest(String bunName, float bunPrice, IngredientType ingredientChoice, float ingredientPrice, String ingredientName, float burgerPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientChoice = ingredientChoice;
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
        this.burgerPrice = burgerPrice;
    }

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
    public void getPriceTest() {
        Burger burger = new Burger();
        Bun bun = new Bun(bunName, bunPrice);
        Ingredient ingredient = new Ingredient(ingredientChoice, ingredientName, ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float actual = burger.getPrice();
        Assert.assertEquals(burgerPrice, actual, 0);
    }
}
