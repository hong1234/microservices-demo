package com.hong.msv.model;

//import io.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{

	//@ApiModelProperty(position = 1)
	private String productId;
	
	//@ApiModelProperty(position = 2)
	private String name;
	
	//@ApiModelProperty(position = 3)
	private String code;;
	
	//@ApiModelProperty(position = 4)
	private String title;
	
	//@ApiModelProperty(position = 5)
	private Double price;

	//@ApiModelProperty(position = 6)
	private String category;
}
