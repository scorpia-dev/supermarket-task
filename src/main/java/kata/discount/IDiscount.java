package kata.discount;

import kata.supermarket.DiscountType;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface IDiscount {

    List<Item> getItemsEligibleForDiscount(List<Item> sortedList, DiscountsChecker discountChecker);

    List<Item> sortByPrice(List<Item> items);

    BigDecimal getDiscount(List<Item> discountBasket);

    DiscountType getDiscountType();
}
