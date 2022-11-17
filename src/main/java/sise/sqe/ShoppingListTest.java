package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private static ShoppingList shoppingList;

    @BeforeAll
    public static void Init()
    {
        shoppingList = Mockito.mock(ShoppingList.class);
    }

    @Test
    public void addProduct_succses() {
        Product product1 = new Product("1", "Bamba1", 1);
        double Bambaprice = shoppingList.getMarketPrice();

        shoppingList.addProduct(product1);
    }




}