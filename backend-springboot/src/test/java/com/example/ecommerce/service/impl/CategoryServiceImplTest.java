package com.example.ecommerce.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.DuplicateException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.StillRelationException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);

        categoryService = new CategoryServiceImpl(categoryRepository, productRepository);
    }

    @Test
    void testCreateCategoryWhenCategoryNameExistShouldThrowException() {
        String existCategoryName = "exist-category-name";
        Category category = Category.builder().categoryName(existCategoryName).build();
        Mockito.when(categoryRepository.findByCategoryNameIgnoreCase(existCategoryName))
                .thenReturn(Optional.of(Mockito.mock(Category.class)));

        DuplicateException duplicateException = Assertions.assertThrows(DuplicateException.class,
                () -> categoryService.createCategory(category));

        assertThat(duplicateException.getMessage()).isEqualTo(
                "Category with name exist-category-name already exists");
    }

    @Test
    void testCreateCategoryWhenCategoryNameValidShouldSaveSuccess() {
        String validCategoryName = "valid-category-name";
        Category category = Category.builder().categoryName(validCategoryName).build();
        Mockito.when(categoryRepository.findByCategoryNameIgnoreCase(validCategoryName))
                .thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    Category category1 = invocationOnMock.getArgument(0);
                    return category1;
                }
                );

        Category actual = categoryService.createCategory(category);

        assertThat(actual.getCategoryName()).isEqualTo("valid-category-name");
    }

    @Test
    void testFindAll() {
        int pageNumber = 1;
        int pageSize = 2;
        Page<Category> categoryPage = Mockito.mock(Page.class);
        List<Category> expectedCategories = List.of(Mockito.mock(Category.class));
        Mockito.when(categoryPage.getContent()).thenReturn(expectedCategories);
        Mockito.when(categoryRepository.findAll(PageRequest.of(
                        pageNumber, pageSize, Sort.by("categoryId"))))
                .thenReturn(categoryPage);

        List<Category> actual = categoryService.findAll(pageNumber, pageSize);

        assertThat(actual).isEqualTo(expectedCategories);
    }

    @Test
    void testFindByIdWhenNotFoundShouldThrowException() {
        Long notFoundCategoryId = 123L;

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class,
                () -> categoryService.findById(notFoundCategoryId));

        assertThat(notFoundException.getMessage()).isEqualTo("Category with id 123 is not found");
    }

    @Test
    void testFindByIdWhenFoundShouldReturnCategory() {
        Long foundCategoryId = 1L;
        Category expectedFoundCategory = Mockito.mock(Category.class);
        Mockito.when(categoryRepository.findById(foundCategoryId))
                .thenReturn(Optional.of(expectedFoundCategory));

        Category actual = categoryService.findById(foundCategoryId);

        assertThat(actual).isEqualTo(expectedFoundCategory);
    }

    @Test
    void testUpdateByIdWhenFoundCategoryShouldUpdateNewValue() {
        Long foundCategoryId = 1L;
        Category foundCategory = Category.builder().categoryName("old-name").build();
        Mockito.when(categoryRepository.findById(foundCategoryId))
                .thenReturn(Optional.of(foundCategory));
        Mockito.when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Category actual = categoryService.updateById(foundCategoryId,
                Category.builder().categoryName("new-name").build());

        assertThat(actual.getCategoryName()).isEqualTo("new-name");
    }

    @Test
    void testDeleteByIdWhenStillHaveReferentShouldThrowException() {
        Long foundCategoryId = 1L;
        Category foundCategory = Category.builder().categoryId(foundCategoryId).build();
        Mockito.when(categoryRepository.findById(foundCategoryId))
                .thenReturn(Optional.of(foundCategory));
        Mockito.when(productRepository.countProductByCategoryId(foundCategoryId))
                .thenReturn(2);

        StillRelationException relationException = Assertions.assertThrows(StillRelationException.class,
                () -> categoryService.deleteById(foundCategoryId));

        assertThat(relationException.getMessage()).isEqualTo(
                "Category with id 1 still referent to another Product");
    }

    @Test
    void testDeleteByIdWhenHaveNoReferentShouldDeleteSuccess() {
        Long foundCategoryId = 2L;
        Category foundCategory = Category.builder().categoryId(foundCategoryId).build();
        Mockito.when(categoryRepository.findById(foundCategoryId))
                .thenReturn(Optional.of(foundCategory));
        Mockito.when(productRepository.countProductByCategoryId(foundCategoryId))
                .thenReturn(0);

        categoryService.deleteById(foundCategoryId);

        Mockito.verify(categoryRepository).deleteById(2L);
    }
}
