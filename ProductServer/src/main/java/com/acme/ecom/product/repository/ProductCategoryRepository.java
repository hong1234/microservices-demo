package com.hong.msv.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hong.msv.model.ProductCategoryOR;

@RepositoryRestResource(collectionResourceRel = "productcategorydata", path = "productcategorydata")
public interface ProductCategoryRepository extends CrudRepository<ProductCategoryOR, Long> {

	public List<ProductCategoryOR> findByName(@Param("name") String  name);
}
