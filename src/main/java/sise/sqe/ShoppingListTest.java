package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private ShoppingList shoppingList;

    @BeforeAll
    public void Init(){
        shoppingList = Mockito.mock(ShoppingList.class);
    }


}