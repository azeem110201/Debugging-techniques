package com.zemosolabs.debuggingscenarios;

import com.google.common.base.Preconditions;

public class Item {
    private final String fName;
    private final double fCost;
    private final int fQuantity;

    public Item (final String name, final double cost, final int quantity){
        fName = Preconditions.checkNotNull(name, "ItemName");
        Preconditions.checkState(
                cost > 0, "ItemCost should be greater than 0.");
        Preconditions.checkState(
                quantity > 0, "ItemQuantity should be greater than 0.");
        fCost = cost;
        fQuantity = quantity;
    }

    public Item (final String name, final double cost){
        this(name, cost, 1);
    }

    public String getName() {
        return fName;
    }

    public double getCost() {
        return fCost;
    }

    public int getQuantity() {
        return fQuantity;
    }
}
