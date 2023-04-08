package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTest {
    private String name = "Булочка";
    private float price = 99.99F;

    @Test
    public void nameBunTest() {
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        Assert.assertEquals(name, actual);
    }

    @Test
    public void priceBunTest() {
        Bun bun = new Bun(name, price);
        float actual = bun.getPrice();
        Assert.assertEquals(price, actual, 0);
    }
}
