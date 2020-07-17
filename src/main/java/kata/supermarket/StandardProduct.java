package kata.supermarket;

import java.math.BigDecimal;

public class StandardProduct extends Product {

    private final BigDecimal pricePerUnit;

    public StandardProduct(final BigDecimal pricePerUnit, final ProductType productType) {
        super(productType);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
