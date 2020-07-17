package kata.supermarket;

abstract class Product {

    ProductType productType;

    Product(ProductType productType) {
        this.productType = productType;
    }

}
