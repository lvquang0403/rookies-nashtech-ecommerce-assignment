package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Attribute;
import com.example.ecommerce.entity.AttributeProduct;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.AttributeProductRepository;
import com.example.ecommerce.repository.AttributeRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AttributeProductService;
import com.example.ecommerce.service.AttributeService;
import com.example.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;
    private final AttributeProductService attributeProductService;
    private final AttributeProductRepository attributeProductRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository, AttributeService attributeService, AttributeProductService attributeProductService, AttributeProductRepository attributeProductRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.attributeService = attributeService;
        this.attributeProductService = attributeProductService;
        this.attributeProductRepository = attributeProductRepository;
    }


    public List<ProductDTO> convertToProductDTO(List<Product> products) {
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
    @Transactional
    public ResponseProductDTO createProduct(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        String productName = productDTO.getProductName();
        if (productRepository.findByProductNameIgnoreCase(productName).isPresent()) {
            throw new DuplicateException(String.format("Product with name : %s already exists", productName));
        }
        Date currentDate = Date.valueOf(LocalDate.now());

        Category foundCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId())));

        Product newProduct = new Product(
                productDTO.getProductName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                currentDate,
                null,
                foundCategory,
                null,
                null
        );
        Product saveProduct = productRepository.save(newProduct);
        productDTO.getAttributeDTOs()
                .forEach(attributeDTO -> {
                    Optional<Attribute> foundAttribute = attributeRepository.findByAttributeNameIgnoreCase(attributeDTO.getAttributeName());
                    Long attributeId;
                    if (foundAttribute.isEmpty()) {
                        attributeId = attributeService.createAttribute(attributeDTO).getAttributeId();
                    }
                    else {
                        attributeId = foundAttribute.get().getAttributeId();
                    }
                    attributeProductService.createAttributeProduct(
                            new AttributeProductDTO(
                                    attributeDTO.getValue(),
                                    saveProduct.getProductId(),
                                    attributeId
                            )
                        );
                    }
                );
        return Product.convertToResponseProductDTO(saveProduct);
    }

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        log.debug("Request to findAll Product (Paging)");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName"));
        return productRepository.findAll(pageable).getContent();
    }


    @Override
    public ListProductDTO findByConditions(int pageNumber, int pageSize, String productName, String categoryName) {
        log.debug("Request to find Product by conditions  : %s");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> foundProducts;
        if (productName.isEmpty() && categoryName.isEmpty()) {
            foundProducts = productRepository.findAll(pageable).getContent();
        } else if (productName.isEmpty()) {
            foundProducts = productRepository.findByCategoryName(pageable, categoryName).getContent();
        } else if (categoryName.isEmpty()) {
            foundProducts = productRepository.filterByProductName(pageable, productName).getContent();
        } else {
            foundProducts = productRepository.filterByConditions(pageable, categoryName, productName).getContent();
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
    public ResponseProductDTO updatedProductById(ProductDTO productDTO, Long productId) {
        log.debug("Request to update Product : {}", productDTO);
        Date currentDate = Date.valueOf(LocalDate.now());

        Product foundProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product with id : %s is not found", productId)));
        if (!productDTO.getProductName().trim().isEmpty()) {
            foundProduct.setProductName(productDTO.getProductName().trim());
        }
        if (!productDTO.getDescription().trim().isEmpty()) {
            foundProduct.setDescription(productDTO.getDescription());
        }
        foundProduct.setDescription(productDTO.getDescription());
        if (!productDTO.getAttributeDTOs().isEmpty()) {
            Set<AttributeProduct> attributeProducts = productDTO.getAttributeDTOs()
                    .stream()
                    .map(attributeDTO -> {
                                if(attributeRepository.findById(attributeDTO.getAttributeId()).isEmpty()){
                                    throw new NotFoundException(String.format("Attribute with id %s is not found", attributeDTO.getAttributeId()));
                                }
                                AttributeProduct attributeProduct = attributeProductRepository
                                        .findByProductIdAndAttributeId(productId, attributeDTO.getAttributeId())
                                        .orElseThrow(
                                                () -> new BadRequestException(String.format(
                                                        "Attribute with id %s don't belong to Product with id %s", attributeDTO.getAttributeId(), productId)
                                                )
                                        );
                                if(!attributeDTO.getValue().isEmpty()){
                                    attributeProduct.setValue(attributeDTO.getValue());
                                }
                                return attributeProductRepository.save(attributeProduct);
                            }
                    )
                    .collect(Collectors.toSet());
            foundProduct.setAttributeProducts(attributeProducts);
        }
        foundProduct.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                () -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId()))));
        foundProduct.setPrice(productDTO.getPrice());
        foundProduct.setUpdatedDate(currentDate);
        try {
            return Product.convertToResponseProductDTO(productRepository.save(foundProduct));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
            return null;
        }
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
