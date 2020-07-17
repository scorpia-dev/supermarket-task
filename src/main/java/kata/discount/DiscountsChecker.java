package kata.discount;

import kata.supermarket.DiscountType;
import kata.supermarket.Item;
import kata.supermarket.ProductType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DiscountsChecker {

    private final Map<Item, DiscountType> discountOnIndividualProducts;
    private final Map<ProductType, DiscountType> discountsOnProductType;

    public DiscountsChecker() {
        this.discountOnIndividualProducts = new HashMap<>();
        this.discountsOnProductType = new HashMap<>();
    }

    public boolean productIdOrTypeInDiscountList(Item item, DiscountType discountType) {
        return getDiscountOnIndividualProducts().get(item) == discountType
                || getDiscountsOnProductType().get(item.productType()) == discountType;
    }

    public void addDiscountToIndividualProduct(Item item, DiscountType discountType) {
        discountOnIndividualProducts.put(item, discountType);
    }

    public void addDiscountToProductType(ProductType productType, DiscountType discountType) {
        discountsOnProductType.put(productType, discountType);
    }

    private Map<Item, DiscountType> getDiscountOnIndividualProducts() {
        return discountOnIndividualProducts;
    }

    private Map<ProductType, DiscountType> getDiscountsOnProductType() {
        return discountsOnProductType;
    }


}
