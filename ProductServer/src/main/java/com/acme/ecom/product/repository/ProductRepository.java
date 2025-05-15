package com.hong.msv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hong.msv.model.ProductOR;

@RepositoryRestResource(collectionResourceRel = "productdata", path = "productdata")
public interface ProductRepository extends CrudRepository<ProductOR, Long> {

	public List<ProductOR> findByCode(@Param("code") String  code);

	public List<ProductOR> findByCategory(@Param("category") String  category);
}
