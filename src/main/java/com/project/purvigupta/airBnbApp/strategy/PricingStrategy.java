package com.project.purvigupta.airBnbApp.strategy;

import com.project.purvigupta.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
