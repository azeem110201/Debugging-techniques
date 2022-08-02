package com.zemosolabs.debuggingscenarios;

import java.util.Optional;

public interface IItemCatalogue {
  void addItemToCatalogue(Item item);

  void removeItemFromCatalogue(Item item);

  Optional<Item> getItem(String itemName);
}
