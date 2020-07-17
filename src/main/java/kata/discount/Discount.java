package kata.discount;

import kata.supermarket.DiscountType;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

abstract class Discount {

    DiscountsChecker discountsChecker;

    public Discount(DiscountsChecker discountsChecker) {
        this.discountsChecker = discountsChecker;
    }

    public List<Item> getItemsEligibleForDiscount(List<Item> sortedList) {
        return sortedList.stream()
                .filter(item -> discountsChecker.productIdOrTypeInDiscountList(item, getDiscountType()))
                .collect(Collectors.toList());
    }

    public List<Item> sortByPrice(List<Item> items) {
        return items.stream().sorted().sorted(Comparator.comparing(Item::price)).
                collect(Collectors.toList());
    }

    abstract BigDecimal getDiscount(List<Item> discountBasket);


    abstract DiscountType getDiscountType();


}
