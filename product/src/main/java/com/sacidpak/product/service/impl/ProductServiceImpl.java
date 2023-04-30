package com.sacidpak.product.service.impl;

import com.sacidpak.common.service.BaseServiceImpl;
import com.sacidpak.product.domain.Product;
import com.sacidpak.product.dto.ProductDTO;
import com.sacidpak.product.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductDTO,Long> implements IProductService {
}
