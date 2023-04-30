package com.sacidpak.product.enums;

import com.sacidpak.common.exception.BusinessValidation;
import org.apache.commons.lang.StringUtils;

public enum ProductValidation implements BusinessValidation {
    PRODUCT_NOT_FOUND(""),
    STOCK_MUST_DEFINE_MAIN_DEPOT(""),
    TRANSFER_AMOUNT_CANNOT_BE_MORE_THAN_TOTAL_AMOUNT(""),
    INTERCITY_TRANSFER_MUST_BE_FROM_MAIN_DEPOT(""),
    INNERCITY_TRANSFER_MUST_BE_LESS_THAN_100_KM(""),
    DEPOT_NOT_FOUND(""),
    MAIN_DEPOT_NOT_FOUND("");

    private String code;
    private String description;

    ProductValidation(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return StringUtils.isEmpty(description) ? this.name() : description;
    }
}
