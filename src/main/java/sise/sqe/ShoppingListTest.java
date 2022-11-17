package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private ShoppingList shoppingList;

    @BeforeAll
    public void Init()
    {
        shoppingList = Mockito.mock(ShoppingList.class);
    }

    @Test
    public void addProduct_succses() {
        Product product1 = new Product("1", "Bamba1", 1);
        shoppingList.addProduct(product1);
    }




}