package com.zemosolabs.debuggingscenarios;

import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CheckoutService implements ICheckoutService{
  private final ICartService fCartService;
  private final IBalanceService fBalanceService;
  private final IItemCatalogue fItemCatalogue;

  public CheckoutService(
          final ICartService cartService,
          final IBalanceService balanceService,
          final IItemCatalogue itemCatalogue){
    fCartService = Preconditions.checkNotNull(cartService, "CartService");
    fBalanceService = Preconditions.checkNotNull(balanceService, "BalanceService");
    fItemCatalogue = Preconditions.checkNotNull(itemCatalogue, "ItemCatalogue");
  }

  @Override
  public void checkout(final UUID customerId) {
    Preconditions.checkNotNull(customerId, "CustomerId");
    var cart = fCartService.getCart(customerId);
    Preconditions.checkState(
            cart != null && !cart.isEmpty(),
            "Customer doesn't have items in his cart.");
    var amount = cart.keySet().stream()
            .filter(itemKey -> fItemCatalogue.getItem(itemKey).isPresent())
            .map(itemKey -> fItemCatalogue.getItem(itemKey).get().getCost() * cart.get(itemKey))
            .reduce(0.0D, Double::sum);
    fBalanceService.deductBalance(customerId, amount);
    fCartService.clearCart(customerId);
  }
}
