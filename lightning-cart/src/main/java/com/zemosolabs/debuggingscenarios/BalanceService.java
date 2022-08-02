package com.zemosolabs.debuggingscenarios;

import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceService implements IBalanceService {
  private final Map<UUID, Double> fCustomerBalance = new ConcurrentHashMap<>();

  public double getBalance(final UUID customerId) {
    Preconditions.checkNotNull(customerId, "CustomerId");
    if(fCustomerBalance.containsKey(customerId)){
      return fCustomerBalance.get(customerId);
    }
    return 0;
  }

  public void addBalance(final UUID customerId, final double amount) {
    Preconditions.checkNotNull(customerId, "CustomerId");
    Preconditions.checkState(
            amount > 0, "Amount should be greater than 0.");
    fCustomerBalance.merge(customerId, amount, Double::sum);
  }

  public void deductBalance(final UUID customerId, final double amount) {
    Preconditions.checkNotNull(customerId, "CustomerId");
//    synchronized (customerId){
      var balance = getBalance(customerId);
      Preconditions.checkState(
              amount > 0, "Amount should be greater than 0.");
      Preconditions.checkState(
              balance > 0 && balance - amount >= 0,
              "Customer does not have enough balance to deduct.");
      fCustomerBalance.put(customerId, balance - amount);
//    }
  }

  public void resetBalance(final UUID customerId){
    fCustomerBalance.put(customerId, 0.0D);
  }
}
