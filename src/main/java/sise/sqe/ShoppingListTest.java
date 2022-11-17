package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShoppingListTest {
    private static ShoppingList shoppingList;
    private static Supermarket mysuper;

    @BeforeAll
    public static void Init()
    {
        mysuper = Mockito.mock(Supermarket.class);
        Supermarket mysuperspy = Mockito.spy(mysuper);

        shoppingList = Mockito.mock(ShoppingList.class);
        shoppingList = new ShoppingList(mysuper);
    }

    @Test
    public void addProduct_succses() {

        Product product1 = new Product("1", "Bamba1", 1);
        double Bambaprice = 10.0;
        when (mysuper.getPrice("1")).thenReturn(10.0);
        //Mockito.stub(mysuper.getPrice("1")).toReturn(10.0);
        assertEquals(Bambaprice,mysuper.getPrice("1"));



        shoppingList.addProduct(product1);
    }






}