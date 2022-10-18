package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ListProductDTO;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.ProductPostDTO;
import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
    }


    public List<ProductDTO> convertToProductDTO(List<Product> products){
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice(),
                        null,
                        product.getCategory().getCategoryId())).toList();
    }
    @Override
    public Product createProduct(ProductPostDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Date currentDate = Date.valueOf(LocalDate.now());
        Set<Attribute> attributes = productDTO.getAttributeIds()
                .stream()
                .map(categoryId -> attributeRepository.findById(categoryId).orElseThrow(() ->
                        new NotFoundException(String.format("Attribute with id : %s is not found",categoryId))))
                .collect(Collectors.toSet());
        Category foundCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId())));
        return productRepository.save(new Product(
                productDTO.getProductName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                currentDate,
                null,
                foundCategory,
                attributes,
                null));
    }

    @Override
    public Page<Product> findAll(int pageNumber,int pageSize) {
        log.debug("Request to findAll Product (Paging)");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName"));
        return productRepository.findAll(pageable);
    }


    @Override
    public ListProductDTO findByConditions(int pageNumber, int pageSize, String productName, String categoryName) {
        log.debug("Request to find Product by conditions  : %s");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> foundProducts;
        if (productName.isEmpty() && categoryName.isEmpty()){
            foundProducts = productRepository.findAll(pageable).getContent();
        } else if (productName.isEmpty()){
            foundProducts = productRepository.findByCategoryName(pageable,categoryName).getContent();
        } else if (categoryName.isEmpty()) {
            foundProducts = productRepository.findByProductName(pageable,productName).getContent();
        } else {
            foundProducts = productRepository.findByConditions(pageable, categoryName, productName).getContent();
        }

        List<ProductDTO> listFoundProductDTOs = convertToProductDTO(foundProducts);

        return new ListProductDTO(pageNumber, pageSize, listFoundProductDTOs);
    }

    @Override
    public ProductDTO findById(Long productId) {
        log.debug(String.format("Request to find Product by id : %s", productId.toString()));
        Product foundProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id : %s is not found", productId)));
        return new ProductDTO(
                foundProduct.getProductId(),
                foundProduct.getProductName(),
                foundProduct.getDescription(),
                foundProduct.getPrice(),
                null,
                foundProduct.getCategory().getCategoryId());
    }

    @Override
    public Product updatedProductById(ProductDTO productDTO, Long productId) {
        log.debug("Request to update Product : {}", productDTO);
        Date currentDate = Date.valueOf(LocalDate.now());

        Product foundProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product with id : %s is not found", productId)));
        if(!productDTO.getProductName().trim().isEmpty()){
            foundProduct.setProductName(productDTO.getProductName().trim());
        }
        if(!productDTO.getDescription().trim().isEmpty()){
            foundProduct.setDescription(productDTO.getDescription());
        }
        foundProduct.setDescription(productDTO.getDescription());
        if(!productDTO.getAttributeIds().isEmpty()) {
            Set<Attribute> attributes = productDTO.getAttributeIds()
                            .stream()
                            .map(attributeId -> attributeRepository
                                    .findById(attributeId)
                                    .orElseThrow(() ->
                                            new NotFoundException(String.format("Attribute with id : %s is not found",attributeId))))
                                    .collect(Collectors.toSet());
            foundProduct.setAttributes(attributes);
        }
        foundProduct.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                () -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId()))));
        foundProduct.setPrice(productDTO.getPrice());
        foundProduct.setUpdatedDate(currentDate);
        return productRepository.save(foundProduct);

    }

    @Override
    public ListProductDTO findByCategoryId(int pageNumber, int pageSize, Long categoryId) {
        log.debug("Request to find by categoryId Product (Paging)");
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException(String.format("Category with id : %s is not found", categoryId)));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> foundProducts = productRepository.findByCategory(pageable, category).getContent();
        List<ProductDTO> listFoundProductDTO = convertToProductDTO(foundProducts);
        return new ListProductDTO(pageNumber, pageSize, listFoundProductDTO);
    }

    public void deleteProductById(Long id) {
        log.debug(String.format("Request to delete Product : %s", id.toString()));
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product with id : %s is not found", id)));
        productRepository.deleteById(foundProduct.getProductId());
    }
}
