package com.hong.msv.model;

//import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

	//@ApiModelProperty(position = 1)
	private String id;

	//@ApiModelProperty(position = 2)
	private String name;

	//@ApiModelProperty(position = 3)
	private String title;

	//@ApiModelProperty(position = 4)
	private String description;

	//@ApiModelProperty(position = 5)
	private String imgUrl;
}