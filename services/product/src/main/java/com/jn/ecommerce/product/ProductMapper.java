package com.jn.ecommerce.product;

import com.jn.ecommerce.category.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
     return Product.builder()
             .name(request.name())
             .description(request.description())
             .price(request.price())
             .availableQuantity(request.availableQuantity())
             .category(Category.builder()
                     .id(request.categoryId())
                     .build())
             .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,  Integer quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
