package com.hong.msv.model;

//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name ="productcategory")
public class ProductCategoryOR {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "categoryid")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "imgurl")
	private String imgUrl;
}