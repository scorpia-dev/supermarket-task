package kata.discount;

import kata.supermarket.DiscountType;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Bogof implements IDiscount {


    @Override
    public List<Item> getItemsEligibleForDiscount(List<Item> items, DiscountsChecker discountsChecker) {
        return items.stream()
                .filter(item -> discountsChecker.productIdOrTypeInDiscountList(item, getDiscountType()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> sortByPrice(List<Item> items) {
        return items.stream().sorted().sorted(Comparator.comparing(Item::price)).
                collect(Collectors.toList());
    }

    @Override
    public BigDecimal getDiscount(List<Item> discountItems) {
        List<Item> sortedDiscountItems = sortByPrice(discountItems);
        int chunkSize = sortedDiscountItems.size() / 2;
        List<Item> firstHalf = sortedDiscountItems.subList(0, chunkSize);

        return firstHalf.stream().map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.BOGOF;
    }
}
