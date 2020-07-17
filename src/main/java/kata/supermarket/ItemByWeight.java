package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct weighedProduct;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct weighedProduct, final BigDecimal weightInKilos) {
        this.weighedProduct = weighedProduct;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return weighedProduct.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public ProductType productType() {
        return weighedProduct.productType;
    }

    @Override
    public int compareTo(Item o) {
        return price().compareTo(o.price());
    }

}
