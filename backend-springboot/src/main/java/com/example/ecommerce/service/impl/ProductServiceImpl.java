package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.dto.request.AttributeDTO;
import com.example.ecommerce.dto.request.AttributePostDTO;
import com.example.ecommerce.dto.request.ProductPostDTO;
import com.example.ecommerce.dto.request.ProductPutDTO;
import com.example.ecommerce.dto.response.*;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.AttributeProductService;
import com.example.ecommerce.service.AttributeService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;
    private final AttributeProductService attributeProductService;
    private final AttributeProductRepository attributeProductRepository;
    private final ImageRepository imageRepository;
    private final RatingRepository ratingRepository;
    private final RatingService ratingService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository, AttributeService attributeService, AttributeProductService attributeProductService, AttributeProductRepository attributeProductRepository, ImageRepository imageRepository, RatingRepository ratingRepository, RatingService ratingService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.attributeService = attributeService;
        this.attributeProductService = attributeProductService;
        this.attributeProductRepository = attributeProductRepository;
        this.imageRepository = imageRepository;
        this.ratingRepository = ratingRepository;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public ResponseProductDTO createProduct(ProductPostDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Date currentDate = Date.valueOf(LocalDate.now());

        Category foundCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId())));

        Product newProduct = Product.builder()
                .productName(productDTO.getProductName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .productDetails(productDTO.getProductDetails())
                .createdDate(currentDate)
                .category(foundCategory)
                .build();
        Product saveProduct = productRepository.save(newProduct);
//        productDTO.getImages()
//                .forEach(imageDTO -> {
//                    Optional<Image> foundImage = imageRepository.findByColorIgnoreCaseAndProductProductId(imageDTO.getColor(), saveProduct.getProductId());
//                    Image image;
//                    if (foundImage.isEmpty()) {
//                        image = imageRepository.save(Image.builder().color(imageDTO.getColor()).url(imageDTO.getUrl()).build());
//                    } else {
//                        image = foundImage.get();
//                    }
//                    image.setProduct(saveProduct);
//                    imageRepository.save(image);
//                });
        for (ImageDTO imageDTO : productDTO.getImages()) {
            Optional<Image> foundImage = imageRepository.findByColorIgnoreCaseAndProductProductId(imageDTO.getColor(), saveProduct.getProductId());
            Image image;
            if (foundImage.isEmpty()) {
                image = imageRepository.save(Image.builder().color(imageDTO.getColor()).url(imageDTO.getUrl()).build());
            } else {
                image = foundImage.get();
            }
            image.setProduct(saveProduct);
            imageRepository.save(image);
        }
        for (AttributeDTO attributeDTO : productDTO.getAttributes()) {
            Optional<Attribute> foundAttribute = attributeRepository.findByAttributeNameIgnoreCase(attributeDTO.getAttributeName());
            Long attributeId;
            if (foundAttribute.isEmpty()) {
                attributeId = attributeService.createAttribute(
                        new AttributePostDTO(attributeDTO.getAttributeName())
                ).getAttributeId();
            } else {
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

        return Product.convertToResponseProductDTO(saveProduct);
    }

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        log.debug("Request to findAll Product (Paging)");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productName"));
        return productRepository.findAll(pageable).getContent();
    }


    @Override
    public ListProductViewDTO searchProductByProductName(int pageNumber, int pageSize, String productName) {
        log.debug("Request to find Product by conditions  : %s");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage;
        if (productName.isEmpty()) {
            productPage = productRepository.findAll(pageable);
        } else {
            productPage = productRepository.filterByConditions(pageable, productName);
        }
        List<Product> foundProducts = productPage.getContent();
        List<ProductViewHomeDTO> listProducts = foundProducts.stream()
                .map(product -> {
                    Double number = ratingRepository.avgRatingByProductId(product.getProductId());
                    int numberRating = 0;
                    if (number != null) {
                        numberRating = number.intValue();
                    }

                    return new ProductViewHomeDTO(
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getImages().stream().map(ImageDTO::fromImage).collect(toList()),
                            product.getProductDetails(),
                            product.getCategory().getCategoryId(),
                            numberRating
                    );
                }).collect(toList());
        PageResponse pageResponse = new PageResponse(pageSize, pageNumber, productPage.getTotalPages());
        return new ListProductViewDTO(pageResponse ,listProducts);
    }

    @Override
    public DetailProductDTO findById(Long productId) {
        log.debug(String.format("Request to find Product by id : %s", productId.toString()));
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(String.format("Product with id : %s is not found", productId)));
        List<Image> images = imageRepository.findAllByProductProductId(productId);
        List<AttributeProduct> attributes = attributeProductRepository.findInfoAttributeWithoutImageByProductId(productId);
        List<AttributeDTO> listAttribute = attributes.stream()
                .map(AttributeDTO::fromAttributeProduct)
                .collect(toList());
        Double avgRating = ratingRepository.avgRatingByProductId(foundProduct.getProductId());
        int numberRating = 0;
        if(avgRating != null){
            numberRating = avgRating.intValue();
        }
        return new DetailProductDTO(
                productId,
                foundProduct.getProductName(),
                images.stream().map(ImageDTO::fromImage).collect(toList()),
                listAttribute,
                foundProduct.getPrice(),
                numberRating,
                ratingService.findAllByProductId(0,30,productId)
        );
    }

    @Override
    @Transactional
    public void updatedProductById(ProductPutDTO productDTO, Long productId) {
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
        if (!productDTO.getAttributes().isEmpty()) {
            Set<AttributeProduct> attributeProducts = new HashSet<>();
            List<AttributeDTO> attributeDTOS = productDTO.getAttributes();
            for (AttributeDTO attributeDTO : attributeDTOS) {
                Optional<Attribute> foundAttribute = attributeRepository.findByAttributeNameIgnoreCase(attributeDTO.getAttributeName());
                if (foundAttribute.isPresent()) {
                    attributeProducts.add(AttributeProduct.builder()
                            .attribute(foundAttribute.get())
                            .product(foundProduct)
                            .value(attributeDTO.getValue())
                            .build());
                } else {
                    Attribute newAttribute = Attribute.builder()
                            .attributeName(attributeDTO.getAttributeName())
                            .build();
                    attributeProducts.add(AttributeProduct.builder()
                            .attribute(attributeRepository.save(newAttribute))
                            .product(foundProduct)
                            .value(attributeDTO.getValue())
                            .build());
                }

                foundProduct.setAttributeProducts(attributeProducts);
            }
        }
        foundProduct.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                () -> new NotFoundException(String.format("Category with id : %s is not found", productDTO.getCategoryId()))));
        foundProduct.setPrice(productDTO.getPrice());
        foundProduct.setUpdatedDate(currentDate);
        productRepository.save(foundProduct);
    }

    @Override
    public ListProductViewDTO findByCategoryId(int pageNumber, int pageSize, Long categoryId) {
        log.debug("Request to find by categoryId Product (Paging)");
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new NotFoundException(String.format("Category with id : %s is not found", categoryId));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findByCategoryCategoryId(categoryId, pageable);
        List<Product> foundProducts = productPage.getContent();
        List<ProductViewHomeDTO> listFoundProductDTO = foundProducts.stream()
                .map(product -> {
                    Double number = ratingRepository.avgRatingByProductId(product.getProductId());
                    int numberRating = 0;
                    if (number != null) {
                        numberRating = number.intValue();
                    }
                    return new ProductViewHomeDTO(
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getImages().stream().map(ImageDTO::fromImage).collect(toList()),
                            product.getProductDetails(),
                            product.getCategory().getCategoryId(),
                            numberRating
                    );
                }).collect(toList());
        PageResponse pageResponse = new PageResponse(pageSize, pageNumber, productPage.getTotalPages());
        return new ListProductViewDTO(pageResponse,listFoundProductDTO);
    }

    public void deleteProductById(Long id) {
        log.debug(String.format("Request to delete Product : %s", id.toString()));
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product with id : %s is not found", id)));
        productRepository.deleteById(foundProduct.getProductId());
    }
}