package kata.discount;

import kata.supermarket.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountCheckerTest {
    Item item1 = new ItemByUnit(new StandardProduct(new BigDecimal("1.00"), ProductType.DAIRY));
    Item item2 = new ItemByUnit(new StandardProduct(new BigDecimal("3.50"), ProductType.DAIRY));

    Item item3 = new WeighedProduct(new BigDecimal("3.50"), ProductType.FISH).weighing(BigDecimal.valueOf(1.00));
    Item item4 = new ItemByUnit(new StandardProduct(new BigDecimal("1.73"), ProductType.MEAT));
    Item item5 = new ItemByUnit(new StandardProduct(new BigDecimal("5.73"), ProductType.MEAT));

    DiscountsChecker discountsChecker = new DiscountsChecker();


    @Test
    void discountOnSingleProductTest() {
        discountsChecker.addDiscountToIndividualProduct(item1, DiscountType.TWO_FOR_ONE_POUND);

        assertTrue(discountsChecker.productIdOrTypeInDiscountList(item1, DiscountType.TWO_FOR_ONE_POUND));
        assertFalse(discountsChecker.productIdOrTypeInDiscountList(item2, DiscountType.TWO_FOR_ONE_POUND));
    }

    @Test
    void discountOnProductTypeTest() {
        discountsChecker.addDiscountToProductType(ProductType.MEAT, DiscountType.BOGOF);

        assertTrue(discountsChecker.productIdOrTypeInDiscountList(item4, DiscountType.BOGOF));
        assertTrue(discountsChecker.productIdOrTypeInDiscountList(item5, DiscountType.BOGOF));
        assertFalse(discountsChecker.productIdOrTypeInDiscountList(item3, DiscountType.BOGOF));

    }
}
