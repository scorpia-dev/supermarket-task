package kata.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class DiscountService {

    private final DiscountsChecker discountChecker;

    public DiscountService(DiscountsChecker discountChecker) {
        this.discountChecker = discountChecker;
    }

    public BigDecimal getDiscount(List<Item> items) {
        Discount buyOneGetOneFree = new BuyOneGetOneFree(discountChecker);

        BigDecimal sum = new BigDecimal(0);

        List<Item> discountItems = buyOneGetOneFree.getItemsEligibleForDiscount(items);
        sum = sum.add(buyOneGetOneFree.getDiscount(discountItems));

        return sum;
    }

}
