package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTestItay {
    private static ShoppingList shoppingList;
    private static Supermarket supermarket;

    @BeforeEach
    public void Init()
    {
        supermarket = Mockito.mock(Supermarket.class);
        shoppingList = new ShoppingList(supermarket);
    }


    @Test
    public void getMarketPrice_NoProducts_succses() {
        int result = 0;
        assertEquals(result,shoppingList.getMarketPrice(),"Getting correct price of empty list failed");
    }

    @Test
    public void getMarketPrice_NoProducts_failed() {
        int result = 0;
        assertFalse(shoppingList.getMarketPrice()>0,"Getting correct price of empty list failed");
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
    @Test
    public void getMarketPrice_5Products_Discount_succses(){
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
        when((supermarket.getPrice("1"))).thenReturn(1000.0);
        when((supermarket.getPrice("2"))).thenReturn(10.0);
        when((supermarket.getPrice("3"))).thenReturn(1.0);
        when((supermarket.getPrice("4"))).thenReturn(1.0);
        when((supermarket.getPrice("5"))).thenReturn(1.0);
        double discount = shoppingList.getDiscount(1000.0+10.0+1.0+1.0+1.0);
        assertEquals((1000.0+10.0+1.0+1.0+1.0) * discount,shoppingList.getMarketPrice());
    }

    @Test
    public void getMarketPrice_1Prodcut_50Quantity_No_Discount(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        assertEquals(50.0,shoppingList.getMarketPrice());
    }

    @Test
    public void getMarketPrice_1Prodcut_50Quantity_Discount(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        when((supermarket.getPrice("1"))).thenReturn(1000.0);
        double discount = shoppingList.getDiscount(prod1.getQuantity()*supermarket.getPrice("1"));
        assertEquals(prod1.getQuantity()*supermarket.getPrice("1")*discount,shoppingList.getMarketPrice());

    }

    //****************----changeQuantity----*************************
    @Test
    public void changeQuantity_NegativeQuantity_Input_Exeption_Catch_Succses(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {shoppingList.changeQuantity(-5,"1");});
        String expectedMessage = "Quantity cannot be negative";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void changeQuantity_Quantity0_Product_Remove_Succses(){
        Product prod1 = new Product("1","1",50);
        Product prod2 = new Product("2","2",50);
        shoppingList.addProduct(prod1);
        shoppingList.addProduct(prod2);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        when((supermarket.getPrice("2"))).thenReturn(1.0);
        assertEquals(100,shoppingList.getMarketPrice());
        shoppingList.changeQuantity(0,"2");
        assertEquals(50,shoppingList.getMarketPrice());
    }








}