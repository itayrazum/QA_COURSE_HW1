package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTestItay {
    private static ShoppingList shoppingList;
    private static Supermarket supermarket;

    @BeforeEach
    public void Init()
    {
        supermarket = Mockito.mock(Supermarket.class);
        shoppingList = Mockito.spy(new ShoppingList(supermarket));

    }

   /* @Test
    public void AddProduct_succses() throws NoSuchFieldException, IllegalAccessException {
        Product prod1 = new Product("1","1",1);
        shoppingList.addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod1);
        Field products =  shoppingList.getClass().getDeclaredField("products");
        products.setAccessible(true);
        List<Product> prods =(List<Product>)products.get(shoppingList);
        int flag = 0;
        if(prods.contains(prod1))
           flag = 1;
        assertTrue(flag == 1);
    }*/

    @Test
    public void getMarketPrice_NoProducts_succses() {
        int result = 0;
        double price = shoppingList.getMarketPrice();
        Mockito.verify(shoppingList).getMarketPrice();
        assertEquals(result,price,"Getting correct price of empty list failed");
    }

    @Test
    public void getMarketPrice_NoProducts_failed() {
        int result = 0;
        double price = shoppingList.getMarketPrice();
        Mockito.verify(shoppingList).getMarketPrice();
        assertFalse(price>0,"Getting correct price of empty list failed");
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
        Mockito.verify(shoppingList).addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod2);
        Mockito.verify(shoppingList).addProduct(prod3);
        Mockito.verify(shoppingList).addProduct(prod4);
        Mockito.verify(shoppingList).addProduct(prod5);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        when((supermarket.getPrice("2"))).thenReturn(1.0);
        when((supermarket.getPrice("3"))).thenReturn(1.0);
        when((supermarket.getPrice("4"))).thenReturn(1.0);
        when((supermarket.getPrice("5"))).thenReturn(1.0);
        double price = shoppingList.getMarketPrice();
        Mockito.verify(shoppingList).getMarketPrice();
        assertEquals(5,price);

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
        Mockito.verify(shoppingList).addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod2);
        Mockito.verify(shoppingList).addProduct(prod3);
        Mockito.verify(shoppingList).addProduct(prod4);
        Mockito.verify(shoppingList).addProduct(prod5);
        when((supermarket.getPrice("1"))).thenReturn(1000.0);
        when((supermarket.getPrice("2"))).thenReturn(10.0);
        when((supermarket.getPrice("3"))).thenReturn(1.0);
        when((supermarket.getPrice("4"))).thenReturn(1.0);
        when((supermarket.getPrice("5"))).thenReturn(1.0);
        double discount = shoppingList.getDiscount(1000.0+10.0+1.0+1.0+1.0);
        Mockito.verify(shoppingList).getDiscount(1000.0+10.0+1.0+1.0+1.0);
        double price = shoppingList.getMarketPrice();
        Mockito.verify(shoppingList).getMarketPrice();
        assertEquals((1000.0+10.0+1.0+1.0+1.0) * discount,price);
    }

    @Test
    public void getMarketPrice_1Prodcut_50Quantity_No_Discount(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod1);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        double price = shoppingList.getMarketPrice();
        Mockito.verify(shoppingList).getMarketPrice();
        assertEquals(50.0,price);
    }

    @Test
    public void getMarketPrice_1Prodcut_50Quantity_Discount(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod1);
        when((supermarket.getPrice("1"))).thenReturn(1000.0);
        when((shoppingList.getDiscount(prod1.getQuantity()*supermarket.getPrice("1")))).thenReturn(0.85);
        double discount = shoppingList.getDiscount(prod1.getQuantity()*supermarket.getPrice("1"));
        double price = shoppingList.getMarketPrice();
        assertEquals(prod1.getQuantity()*supermarket.getPrice("1")*discount,price);

    }

    //****************----changeQuantity----*************************
    @Test
    public void changeQuantity_NegativeQuantity_Input_Exeption_Catch_Succses(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {shoppingList.changeQuantity(-5,"1");});
        Mockito.verify(shoppingList).changeQuantity(-5,"1");
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
        Mockito.verify(shoppingList).addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod2);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        when((supermarket.getPrice("2"))).thenReturn(1.0);
        double price = shoppingList.getMarketPrice();
        assertEquals(100,price);
        shoppingList.changeQuantity(0,"2");
        Mockito.verify(shoppingList).changeQuantity(0,"2");
        price = shoppingList.getMarketPrice();
        assertEquals(50,price);
    }

    @Test
    public void changeQuantity_change_the_correct_product_only_succses(){
        Product prod1 = new Product("1","1",50);
        Product prod2 = new Product("2","2",50);
        shoppingList.addProduct(prod1);
        shoppingList.addProduct(prod2);
        Mockito.verify(shoppingList).addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod2);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        when((supermarket.getPrice("2"))).thenReturn(1.0);
        shoppingList.changeQuantity(100,"2");
        Mockito.verify(shoppingList).changeQuantity(100,"2");
        boolean bool = true;
        if(prod1.getQuantity() == 100){
            bool = false;
        }
        assertTrue(bool);
    }
    @Test
    public void changeQuantity_change_correct_succses(){
        Product prod1 = new Product("1","1",50);
        shoppingList.addProduct(prod1);
        Mockito.verify(shoppingList).addProduct(prod1);
        when((supermarket.getPrice("1"))).thenReturn(1.0);
        shoppingList.changeQuantity(555,"1");
        Mockito.verify(shoppingList).changeQuantity(555,"1");
        boolean bool = false;
        if (prod1.getQuantity()==555){
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void changeQuantity_no_product(){
        Product prod1 = new Product("1","1",1);
        shoppingList.changeQuantity(50,"1");
        Mockito.verify(shoppingList).changeQuantity(50,"1");
        boolean bool = true;
        if(prod1.getQuantity() != 1){
            bool = false;
        }
        assertTrue(bool);
    }








}