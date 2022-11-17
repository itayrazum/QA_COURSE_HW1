package sise.sqe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShoppingListTestYuval {
    private static ShoppingList shoppingList;

    private static Supermarket supermarket;

    @BeforeEach
    public void Init() throws NoSuchFieldException {
        supermarket = Mockito.mock(Supermarket.class);
        Supermarket mysuperspy = Mockito.spy(supermarket);
        shoppingList = Mockito.spy(new ShoppingList(supermarket));
        //Person person = new Person();
       // Field nameField = shoppingList.getClass().getDeclaredField("products");
      //  nameField.setAccessible(true);
        //List<Product> prodlist= nameField.get();


        //shoppingList = Mockito.mock(ShoppingList.class);
        //shoppingList = new ShoppingList(mysuper);
    }




    @Test

    public void getDiscount_Test_Fails_With_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingList.getDiscount(-0.1);
        });

        String expectedMessage = "Price cannot be negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void getDiscoun_Test_Success_Above_1000() {

        assertEquals(0.85, shoppingList.getDiscount(1000.1));
        Mockito.verify(shoppingList).getDiscount(1000.1);
    }
    @Test
    public void getDiscoun_Test_Success_Above_750_Below1000()
    {
        assertEquals(0.9, shoppingList.getDiscount(750.1));
        Mockito.verify(shoppingList).getDiscount(750.1);
    }
    @Test
    public void getDiscoun_Test_Success_1000() {
        assertEquals(0.9, shoppingList.getDiscount(1000));
        Mockito.verify(shoppingList).getDiscount(1000);

    }
    @Test
    public void getDiscoun_Test_Success_Above_500_Below_750()
    {
        assertEquals(0.95, shoppingList.getDiscount(500.01));
        Mockito.verify(shoppingList).getDiscount(500.01);
    }
    @Test
    public void getDiscoun_Test_Success_750()
    {
        assertEquals(0.95, shoppingList.getDiscount(750));
        Mockito.verify(shoppingList).getDiscount(750);
    }




    @Test
    public void priceWithDelivery_Test_Fails_With_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingList.priceWithDelivery(-1);
            Mockito.verify(shoppingList).priceWithDelivery(-1);

        });

        String expectedMessage = "Miles cannot be negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void priceWithDelivery_Test_Success_deliveryfee_0_Marketprice_0()
    {
        // total price = deliveryFee + price
        int miles=0;
        int numofproducts=0;
        when(supermarket.calcDeliveryFee(miles,numofproducts)).thenReturn((double) 0);
        when(shoppingList.getMarketPrice()).thenReturn((double) 0);
        double totalprice = shoppingList.priceWithDelivery(0);
        Mockito.verify(shoppingList).priceWithDelivery(0);
        assertEquals(0,shoppingList.priceWithDelivery(0));







    }
    @Test
    public void priceWithDelivery_Test_Success_deliveryfee_0_Marketprice_5()
    {
        // total price = deliveryFee + price
        int miles=0;
        int numofproducts=0;
        double totalprice=0;
        when(supermarket.calcDeliveryFee(miles,numofproducts)).thenReturn((double) 5);
        when(shoppingList.getMarketPrice()).thenReturn((double) 0);
        totalprice = shoppingList.priceWithDelivery(0);
        Mockito.verify(shoppingList).priceWithDelivery(0);
        assertEquals(5,shoppingList.priceWithDelivery(0));
    }

    @Test
    public void priceWithDelivery_Test_Success_deliveryfee_5_Marketprice_0()
    {
        // total price = deliveryFee + price
        int miles=0;
        int numofproducts=0;
        double totalprice=0;
        when(supermarket.calcDeliveryFee(miles,numofproducts)).thenReturn((double) 0);
        when(shoppingList.getMarketPrice()).thenReturn((double) 5);
        totalprice = shoppingList.priceWithDelivery(0);
        Mockito.verify(shoppingList).priceWithDelivery(0);
        assertEquals(5,shoppingList.priceWithDelivery(0));
    }

    @Test
    public void priceWithDelivery_Test_Success_deliveryfee_5_Marketprice_5()
    {
        // total price = deliveryFee + price
        int miles=0;
        int numofproducts=0;
        double totalprice=0;
        when(supermarket.calcDeliveryFee(miles,numofproducts)).thenReturn((double) 5);
        when(shoppingList.getMarketPrice()).thenReturn((double) 5);
        totalprice = shoppingList.priceWithDelivery(0);
        Mockito.verify(shoppingList).priceWithDelivery(0);
        assertEquals(5,shoppingList.priceWithDelivery(10));
    }
    }





