package kata.discount;

import kata.supermarket.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOneGetOneFreeDiscountTest {
    DiscountsChecker discountsChecker = new DiscountsChecker();

    Item item1 = new ItemByUnit(new StandardProduct(new BigDecimal("1.00"), ProductType.DAIRY));
    Item item2 = new ItemByUnit(new StandardProduct(new BigDecimal("3.50"), ProductType.DAIRY));
    Item item3 = new ItemByUnit(new StandardProduct(new BigDecimal("3.50"), ProductType.FISH));
    Item item4 = new WeighedProduct(new BigDecimal("1.99"), ProductType.MEAT).weighing(new BigDecimal("4.00"));
    Item item5 = new WeighedProduct(new BigDecimal("6.99"), ProductType.MEAT).weighing(new BigDecimal("1.00"));

    List<Item> basket = Arrays.asList(item1, item2, item3, item4, item5);
    IDiscount buyOneGetOneFree = new Bogof();

    @Test
    void buyOneGetOneFreeStandardProductTest() {
        discountsChecker.addDiscountToProductType(ProductType.MEAT, DiscountType.BOGOF);
        discountsChecker.addDiscountToIndividualProduct(item1, DiscountType.BOGOF);
        discountsChecker.addDiscountToIndividualProduct(item2, DiscountType.TWO_FOR_ONE_POUND);

        List<Item> itemsInBogofOffer = buyOneGetOneFree.getItemsEligibleForDiscount(basket, discountsChecker);
        assertEquals(3, itemsInBogofOffer.size());

        BigDecimal discount = buyOneGetOneFree.getDiscount(itemsInBogofOffer);
        //we get item1 for free, so discount = 1.00
        assertEquals(discount, new BigDecimal("1.00"));
    }

    @Test
    void buyOneGetOneFreeWeighedProductsTest() {
        discountsChecker.addDiscountToProductType(ProductType.MEAT, DiscountType.BOGOF);
        List<Item> itemsInBogofOffer = buyOneGetOneFree.getItemsEligibleForDiscount(basket, discountsChecker);
        assertEquals(2, itemsInBogofOffer.size());

        BigDecimal discount = buyOneGetOneFree.getDiscount(itemsInBogofOffer);
        //we get item5 for free, so discount = 6.99
        assertEquals(discount, new BigDecimal("6.99"));

    }
}
