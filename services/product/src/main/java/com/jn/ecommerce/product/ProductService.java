package com.jn.ecommerce.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct(ProductRequest request) {

        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();

    }


    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProduct = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProduct.size()) {
            throw new ProductPurchaseException("One or more products not found");
        }
        var storedRequest = request.stream().
                sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProduct.size(); i++) {
            var product = storedProduct.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Not enough quantity available for product id: " + product.getId());
            }
            var newAvailableQuantity = (product.getAvailableQuantity() - productRequest.quantity());
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));


        }

        return purchaseProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productMapper.toProductResponse(productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product not found with id: " + productId)
        ));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
}
