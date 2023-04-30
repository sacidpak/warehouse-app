package com.sacidpak.common.exception;


public class BusinessException extends RuntimeException {

    private BusinessValidation businessValidation;

    public BusinessException(BusinessValidation businessValidation) {
        super(businessValidation.getCode());
        this.businessValidation = businessValidation;
    }

    public BusinessValidation getValidation(){
        return businessValidation;
    }
}
