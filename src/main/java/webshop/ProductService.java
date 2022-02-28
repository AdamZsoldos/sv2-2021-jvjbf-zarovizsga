package webshop;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amountSold) {
        Product product = productRepository.findProductById(id);
        validateStock(product, amountSold);
        productRepository.updateProductStock(id, amountSold);
    }

    private void validateStock(Product product, int amountSold) {
        if (product.getStock() < amountSold) {
            throw new IllegalArgumentException("Insufficient stock");
        }
    }
}
