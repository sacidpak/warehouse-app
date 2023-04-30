package com.sacidpak.product.service.impl;

import com.sacidpak.common.service.BaseServiceImpl;
import com.sacidpak.product.domain.ProductType;
import com.sacidpak.product.dto.ProductTypeDTO;
import com.sacidpak.product.service.IProductTypeService;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeDTO,Long> implements IProductTypeService {
}
