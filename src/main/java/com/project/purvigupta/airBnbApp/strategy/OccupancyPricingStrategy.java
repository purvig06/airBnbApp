package com.project.purvigupta.airBnbApp.strategy;

import com.project.purvigupta.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;



@Service
@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice( inventory);
        double occupancyRate = (double)inventory.getBookedCount()/ inventory.getTotalCount();
        if(occupancyRate>0.8){
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;

    }
}
