package com.m2i.filrougebo.service;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private IntCategoryDao categoryDaoMock;
    @InjectMocks
    private CategoryService categoryService;
    @Test
    void ShouldReturnAllCategories(){

        List<Category> expected = new ArrayList<>();
        expected.add(new Category(1,"categ1"));
        expected.add(new Category(2,"categ1"));

        when(categoryDaoMock.findAll()).thenReturn(expected);

        List<Category> result = categoryService.findAll();

        assertEquals(expected,result);

    }
    @Test
    void ShouldCreateAndReturnCategoryGivenCategory(){

        String name = "category name";
        int id = 1;
        Category expected = new Category(id,name);

        when(categoryDaoMock.create(any(Category.class))).thenReturn(expected);

        Category result = categoryService.create(expected);

        assertEquals(expected, result);
    }
    @Test
    void ShouldCreateAndReturnCategoryGivenName(){

        String name = "category name";
        int id = 1;

        when(categoryDaoMock.create(any(Category.class))).thenReturn(new Category(id,name));

        Category createdCategory = categoryService.create(name);

        assertEquals(name, createdCategory.getName());
        assertEquals(id, createdCategory.getIdCategory());
    }
    @Test
    void ShouldFindByIdCategoryGivenInt(){

        int id = 1;
        Category expected = new Category(id,"name");

        when(categoryDaoMock.findById(any(int.class))).thenReturn(expected);

        Category result = categoryService.findById(id);

        assertEquals(expected, result);
    }
    @Test
    void ShouldReturnTrueGivenCategoryToUpdate(){

        int id = 1;
        when(categoryDaoMock.update(any(Category.class))).thenReturn(true);

        boolean isModified = categoryService.update(new Category(id,"updated name"));

        assertEquals(true, isModified);

    }
    @Test
    void ShouldReturnTrueGivenCategoryToDelete(){

        int id = 1;
        when(categoryDaoMock.delete(any(Category.class))).thenReturn(true);

        boolean isDeleted = categoryService.delete(new Category(id,"updated name"));

        assertEquals(true, isDeleted);
    }
    @Test
    void ShouldReturnListOfCategoriesExceptGivenProductCategory(){

        Category cat1 = new Category(1,"categ1");
        Category cat2 = new Category(2,"categ2");
        Product p = new Product();
        p.setCategory(new Category(1,"categ1"));

        List<Category> categories = List.of(cat1, cat2);
        List<Category> expected = List.of(cat2);

        when(categoryDaoMock.findAll()).thenReturn(categories);
        List<Category> result = categoryService.findAllCategoriesExceptProductCategory(p);

        assertEquals(expected.get(0),result.get(0));
    }
    @Test
    void ShouldReturnListOfCategoriesGivenAKeywordSearchByName(){

        String search = "cat";
        Category cat1 = new Category(1,"categ1");
        Category cat2 = new Category(2,"categ2");
        Category cat3 = new Category(3,"else");
        List<Category> expected = List.of(cat1,cat2);

        when(categoryDaoMock.searchByName(any(String.class))).thenReturn(expected);
        List<Category> result = categoryService.searchByName(search);

        assertEquals(expected.get(0),result.get(0));
        assertEquals(expected.get(1),result.get(1));
    }

}