package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.dao.IntProductDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private IntProductDao productDaoMock;
    @InjectMocks
    private ProductService productService;

}