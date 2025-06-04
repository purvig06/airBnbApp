package com.project.purvigupta.airBnbApp.strategy;

import com.project.purvigupta.airBnbApp.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
   public BigDecimal calculateDynamicPricing(Inventory inventory){
       PricingStrategy pricingStrategy = new BasePricingStrategy();
       pricingStrategy = new SurgePriceStrategy(pricingStrategy);
       pricingStrategy = new OccupancyPricingStrategy(pricingStrategy);
       pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
       pricingStrategy = new HolidayPricingStrategy(pricingStrategy);
       return pricingStrategy.calculatePrice(inventory);

   }
}
