package com.zemosolabs.debuggingscenarios;

import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ItemCatalogue implements IItemCatalogue {

    private final Map<String, Item> fCatalogue = new ConcurrentHashMap<>();

    @Override
    public void addItemToCatalogue(final Item item) {
        Preconditions.checkNotNull(item, "Item");
        synchronized (item.getName()){
            var catalogueItem = getItem(item.getName());
            if(catalogueItem.isPresent()){
                throw new IllegalArgumentException("Item already present.");
            }
            fCatalogue.put(item.getName(), item);
        }
    }

    @Override
    public void removeItemFromCatalogue(final Item item) {
        Preconditions.checkNotNull(item, "Item");
        synchronized (item.getName()){
            var cart = getItem(item.getName());
            Preconditions.checkState(
                    cart.isPresent(),
                    "Catalogue doesn't contain the supplied item.");
            fCatalogue.remove(item.getName());
        }
    }

    @Override
    public Optional<Item> getItem(final String itemName) {
        Preconditions.checkNotNull(itemName, "ItemName");
        synchronized (itemName){
            var item = fCatalogue.get(itemName);
            return item != null ? Optional.of(item) : Optional.empty();
        }
    }
}
