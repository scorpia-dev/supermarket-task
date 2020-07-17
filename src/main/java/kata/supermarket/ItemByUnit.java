package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final StandardProduct product;

    ItemByUnit(final StandardProduct product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }
}
