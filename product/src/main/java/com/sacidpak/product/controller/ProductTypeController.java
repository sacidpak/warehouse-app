package com.sacidpak.product.controller;

import com.sacidpak.common.controller.BaseController;
import com.sacidpak.product.dto.ProductTypeDTO;
import com.sacidpak.product.service.IProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/product-type")
public class ProductTypeController extends BaseController<IProductTypeService, ProductTypeDTO> {
}
