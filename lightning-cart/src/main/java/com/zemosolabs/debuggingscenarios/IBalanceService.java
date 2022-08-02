package com.zemosolabs.debuggingscenarios;

import java.util.UUID;
import java.util.concurrent.Future;

public interface IBalanceService {
    double getBalance(UUID customerId);

    void addBalance(UUID customerId, double amount);

    void resetBalance(UUID customerId);

    void deductBalance(UUID customerId, double amount);
}
