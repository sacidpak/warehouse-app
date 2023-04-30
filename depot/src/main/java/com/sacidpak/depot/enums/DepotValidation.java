package com.sacidpak.depot.enums;

import com.sacidpak.common.exception.BusinessValidation;
import org.apache.commons.lang.StringUtils;

public enum DepotValidation implements BusinessValidation {
    DEPOT_NOT_FOUND(""),
    MAIN_DEPOT_SHOULD_BE_ONLY_ONE(""),
    MAIN_DEPOT_CANNOT_CLOSE(""),
    DEPOT_STATUS_UPDATE_NOT_SUCCESS("");

    private String code;
    private String description;

    DepotValidation(String description) {
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
