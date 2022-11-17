package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private static ShoppingList shoppingList;
    private static Supermarket supermarket;

    @BeforeAll
    public static void Init()
    {
        supermarket = Mockito.mock(Supermarket.class);
        shoppingList = new ShoppingList(supermarket);
    }

    @Test
    public void getMarketPrice_succses() {
        Product product1 = new Product("1", "Bamba1", 1);
        shoppingList.addProduct(product1);
        when((supermarket.getPrice("1"))).thenReturn(10.0);
        assertEquals(10,shoppingList.getMarketPrice());
    }

    @Test
    public void getMarketPrice_NoProducts_succses() {
        int result = 0;
        assertEquals(result,shoppingList.getMarketPrice(),"Getting correct price of empty list failed");
    }


    @Test
    public void getMarketPrice_5Products_NoDiscount_succses(){
        Product prod1 = new Product("1","1",1);
        Product prod2 = new Product("2","2",1);
        Product prod3 = new Product("3","3",1);
        Product prod4 = new Product("4","4",1);
        Product prod5 = new Product("5","5",1);
        shoppingList.addProduct(prod1);
        shoppingList.addProduct(prod2);
        shoppingList.addProduct(prod3);
        shoppingList.addProduct(prod4);
        shoppingList.addProduct(prod5);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        when((supermarket.getPrice("2"))).thenReturn(1.0);
        when((supermarket.getPrice("3"))).thenReturn(1.0);
        when((supermarket.getPrice("4"))).thenReturn(1.0);
        when((supermarket.getPrice("5"))).thenReturn(1.0);
        assertEquals(5,shoppingList.getMarketPrice());


    }




}