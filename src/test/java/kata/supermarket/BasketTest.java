package kata.supermarket;

import kata.discount.Bogof;
import kata.discount.DiscountsChecker;
import kata.discount.IDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    static DiscountsChecker noDiscounts = new DiscountsChecker();
    IDiscount discount = new Bogof();

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                individualItemsInBuyOneGetOneFree(),
                productTypeInBuyOneGetOneFree()
        );
    }

    private static Arguments individualItemsInBuyOneGetOneFree() {
        Item aKiloOfPickAndMix = aKiloOfPickAndMix().weighing(new BigDecimal("1.00"));
        Item packOfDigestives = aPackOfDigestives();

        //2.99 + 1.55 = 4.45
        DiscountsChecker discountsChecker = new DiscountsChecker();
        discountsChecker.addDiscountToIndividualProduct(packOfDigestives, DiscountType.BOGOF);
        discountsChecker.addDiscountToIndividualProduct(aKiloOfPickAndMix, DiscountType.BOGOF);

        //get cheapest product for free, so packOfDigestives = free (1.55)
        return Arguments.of("buy one get one free by individual products", "2.99",
                Arrays.asList(aKiloOfPickAndMix, packOfDigestives), discountsChecker, "1.55");
    }

    private static Arguments productTypeInBuyOneGetOneFree() {
        DiscountsChecker twoForOnePound = new DiscountsChecker();
        twoForOnePound.addDiscountToProductType(ProductType.MEAT, DiscountType.BOGOF);

        //get cheapest product for free, so packOfDigestives = free (6.99)
        return Arguments.of("buy one get one free by product type", "7.96",
                Arrays.asList(
                        new WeighedProduct(new BigDecimal("1.99"), ProductType.MEAT).weighing(new BigDecimal("4.00")),
                        new WeighedProduct(new BigDecimal("6.99"), ProductType.MEAT).weighing(new BigDecimal("1.00"))
                ), twoForOnePound, "6.99");
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()), noDiscounts, "0.00");
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix()), noDiscounts, "0.00");
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()), noDiscounts, "0.00");
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()), noDiscounts, "0.00");
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList(), noDiscounts, "0.00");
    }

    private static Item aPintOfMilk() {
        return new StandardProduct(new BigDecimal("0.49"), ProductType.DAIRY).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new StandardProduct(new BigDecimal("1.55"), ProductType.SWEETS).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), ProductType.SWEETS);
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), ProductType.SWEETS);
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items, DiscountsChecker discountsChecker
            , String expectedDiscount) {
        final Basket basket = new Basket(discountsChecker, discount);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
}