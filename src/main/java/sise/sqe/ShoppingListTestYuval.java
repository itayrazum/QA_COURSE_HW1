package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShoppingListTestYuval {
    private static ShoppingList shoppingList;
    private static Supermarket mysuper;

    @BeforeAll
    public static void Init() {
        mysuper = Mockito.mock(Supermarket.class);
        Supermarket mysuperspy = Mockito.spy(mysuper);

        shoppingList = Mockito.mock(ShoppingList.class);
        shoppingList = new ShoppingList(mysuper);
    }

    @Test
    public void addProduct_succses() {

        Product product1 = new Product("1", "Bamba1", 1);
        double Bambaprice = 10.0;
        when(mysuper.getPrice("1")).thenReturn(10.0);
        //Mockito.stub(mysuper.getPrice("1")).toReturn(10.0);
        assertEquals(Bambaprice, mysuper.getPrice("1"));


        shoppingList.addProduct(product1);
    }


    @Test

    public void getDiscoun_Test_Fails_With_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingList.getDiscount(-0.1);
        });

        String expectedMessage = "Price cannot be negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void getDiscoun_Test_Success() {

        assertEquals(0.85, shoppingList.getDiscount(1000.1));
        assertEquals(0.9, shoppingList.getDiscount(750.1));
        assertEquals(0.9, shoppingList.getDiscount(1000));
        assertEquals(0.95, shoppingList.getDiscount(500.01));
        assertEquals(0.95, shoppingList.getDiscount(750));


    }
}



