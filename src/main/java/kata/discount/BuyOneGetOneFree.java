package kata.discount;

import kata.supermarket.DiscountType;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class BuyOneGetOneFree extends Discount {
    public BuyOneGetOneFree(DiscountsChecker discountsChecker) {
        super(discountsChecker);
    }

    //find cheapest products in bogof list
    @Override
    public BigDecimal getDiscount(List<Item> discountItems) {
        List<Item> sortedDiscountItems = sortByPrice(discountItems);
        int chunkSize = sortedDiscountItems.size() / 2;
        List<Item> firstHalf = sortedDiscountItems.subList(0, chunkSize);

        return firstHalf.stream().map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    DiscountType getDiscountType() {
        return DiscountType.BOGOF;
    }
}
