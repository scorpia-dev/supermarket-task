package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends Product {

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo, ProductType productType) {
        super(productType);
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
