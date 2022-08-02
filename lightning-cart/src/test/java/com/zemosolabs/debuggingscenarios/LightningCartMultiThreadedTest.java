package com.zemosolabs.debuggingscenarios;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.UUID;

public class LightningCartMultiThreadedTest {
  private ICartService fCartService;
  private IBalanceService fBalanceService;
  private IItemCatalogue fItemCatalogue;
  private ICheckoutService fCheckoutService;
  private static final UUID CUSTOMER_1 = UUID.randomUUID();

  @BeforeSuite
  public void init(){
    fCartService = new CartService();
    fBalanceService = new BalanceService();
    fItemCatalogue = new ItemCatalogue();
    fCheckoutService = new CheckoutService(fCartService, fBalanceService, fItemCatalogue);
    //Create items
    var item1 = createItem("Book 1", 30);
    var item2 = createItem("Book 2", 30);

    fItemCatalogue.addItemToCatalogue(item1);
    fItemCatalogue.addItemToCatalogue(item2);

    //Add Balance to customer
    fBalanceService.addBalance(CUSTOMER_1, 100);

    //Add items to customer cart
    fCartService.addItemToCart(CUSTOMER_1, fItemCatalogue.getItem("Book 1").get());
    fCartService.addItemToCart(CUSTOMER_1, fItemCatalogue.getItem("Book 2").get());
    fCartService.addItemToCart(CUSTOMER_1, fItemCatalogue.getItem("Book 2").get());
  }

  @Test(threadPoolSize = 2, invocationCount = 2)
  public void testMultiThreadedCheckout() {
    fCheckoutService.checkout(CUSTOMER_1);
    Assertions.assertEquals(10, fBalanceService.getBalance(CUSTOMER_1));
  }

  private static Item createItem(
          final String name, final double cost){
    return new Item(name, cost);
  }
}
