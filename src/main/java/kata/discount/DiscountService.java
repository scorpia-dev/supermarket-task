package kata.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class DiscountService {

    private final DiscountsChecker discountChecker;
    private final IDiscount discount;

    public DiscountService(DiscountsChecker discountChecker, IDiscount discount) {
        this.discount = discount;
        this.discountChecker = discountChecker;
    }

    public BigDecimal getDiscount(List<Item> items) {

        BigDecimal sum = new BigDecimal(0);
        List<Item> discountItems = discount.getItemsEligibleForDiscount(items, discountChecker);
        sum = sum.add(discount.getDiscount(discountItems));

        return sum;
    }

}
