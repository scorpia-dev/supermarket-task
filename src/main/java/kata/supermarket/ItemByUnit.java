package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final StandardProduct standardProduct;

    public ItemByUnit(final StandardProduct standardProduct) {
        this.standardProduct = standardProduct;
    }

    public BigDecimal price() {
        return standardProduct.pricePerUnit();
    }

    @Override
    public ProductType productType() {
        return standardProduct.productType;
    }

    @Override
    public int compareTo(Item o) {
        return price().compareTo(o.price());
    }

}
