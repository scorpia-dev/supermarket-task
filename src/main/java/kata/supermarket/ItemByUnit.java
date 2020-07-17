package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final StandardProduct standardProduct;

    ItemByUnit(final StandardProduct standardProduct) {
        this.standardProduct = standardProduct;
    }

    public BigDecimal price() {
        return standardProduct.pricePerUnit();
    }

    @Override
    public ProductType productType() {
        return standardProduct.productType;
    }
}
