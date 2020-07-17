package kata.supermarket;

import java.math.BigDecimal;

public interface Item extends Comparable<Item> {
    BigDecimal price();

    ProductType productType();
}
