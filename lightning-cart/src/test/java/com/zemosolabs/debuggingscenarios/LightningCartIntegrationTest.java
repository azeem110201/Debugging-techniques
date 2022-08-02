package com.zemosolabs.debuggingscenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class LightningCartIntegrationTest {
  private ICartService fCartService;
  private IBalanceService fBalanceService;
  private ICheckoutService fCheckoutService;
  private IItemCatalogue fItemCatalogue;
  private static final UUID CUSTOMER_1 = UUID.randomUUID();

  @BeforeEach
  public void initEach(){
    fCartService = new CartService();
    fBalanceService = new BalanceService();
    fItemCatalogue = new ItemCatalogue();
    fCheckoutService = new CheckoutService(fCartService, fBalanceService, fItemCatalogue);
  }

  @Test
  public void testSmoothCheckout(){
    //Add Balance to customer
    fBalanceService.addBalance(CUSTOMER_1, 100);

    //Create items
    var item1 = createItem("Book 1", 30, 1);
    var item2 = createItem("Book 2", 30, 2);

    //Add items to customer cart
    fCartService.addItemToCart(CUSTOMER_1, item1);
    fCartService.addItemToCart(CUSTOMER_1, item2);

    //Checkout
    Assertions.assertDoesNotThrow(() -> fCheckoutService.checkout(CUSTOMER_1));
  }

  @Test
  public void testLowBalanceCheckout(){
    //Add Balance to customer
    fBalanceService.addBalance(CUSTOMER_1, 100);

    //Create items
    var item1 = createItem("Book 1", 30, 2);
    var item2 = createItem("Book 2", 30, 2);

    //Add items to customer cart
    fCartService.addItemToCart(CUSTOMER_1, item1);
    fCartService.addItemToCart(CUSTOMER_1, item2);

    //Checkout
    Assertions.assertThrows(
            IllegalStateException.class, () -> fCheckoutService.checkout(CUSTOMER_1));
  }

  private static Item createItem(
          final String name, final double cost, final int quantity){
    return new Item(name, cost, quantity);
  }
}
