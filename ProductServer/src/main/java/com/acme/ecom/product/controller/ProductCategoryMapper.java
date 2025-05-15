package com.hong.msv.controller;

import org.mapstruct.Mapper;
import com.hong.msv.model.ProductCategory;
import com.hong.msv.model.ProductCategoryOR;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategory entityToApi(ProductCategoryOR entity);

    ProductCategoryOR apiToEntity(ProductCategory api);
}
