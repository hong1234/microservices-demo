package com.hong.msv.controller;

import org.mapstruct.Mapper;
import com.hong.msv.model.Product;
import com.hong.msv.model.ProductOR;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product entityToApi(ProductOR entity);

    ProductOR apiToEntity(Product api);
}
