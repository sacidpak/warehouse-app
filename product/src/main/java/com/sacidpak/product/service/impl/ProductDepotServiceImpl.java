package com.sacidpak.product.service.impl;

import com.sacidpak.clients.depot.DepotClient;
import com.sacidpak.clients.depot.DepotDistanceResponse;
import com.sacidpak.clients.depot.DepotResponse;
import com.sacidpak.clients.product.DepotCloseResponse;
import com.sacidpak.common.exception.BusinessException;
import com.sacidpak.common.service.BaseServiceImpl;
import com.sacidpak.product.domain.Product;
import com.sacidpak.product.domain.ProductDepot;
import com.sacidpak.product.dto.ProductDepotDTO;
import com.sacidpak.product.dto.ProductTransferDTO;
import com.sacidpak.product.repository.IProductDepotRepository;
import com.sacidpak.product.repository.IProductRepository;
import com.sacidpak.product.service.IProductDepotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sacidpak.product.enums.ProductValidation.*;

@Service
@AllArgsConstructor
public class ProductDepotServiceImpl extends BaseServiceImpl<ProductDepot, ProductDepotDTO,Long> implements IProductDepotService {

    private final IProductDepotRepository productDepotRepository;

    private final IProductRepository productRepository;
    private final DepotClient depotClient;

    private final String MAIN_DEPOT = "MAIN_DEPOT";

    @Override
    protected void validateSave(ProductDepotDTO productDepotDTO) {
        Product product = productRepository.findOne(productDepotDTO.getProduct().getId());

        if(product == null){
            throw new BusinessException(PRODUCT_NOT_FOUND);
        }

        DepotResponse depot = depotClient.getById(productDepotDTO.getDepotId()).getBody();

        if (depot == null) {
            throw new BusinessException(DEPOT_NOT_FOUND);
        }else if (!depot.type().equals(MAIN_DEPOT)) {
            throw new BusinessException(STOCK_MUST_DEFINE_MAIN_DEPOT);
        }
    }

    @Override
    public void stock(ProductDepotDTO productDepotDTO) {
        ProductDepot productDepot =
                productDepotRepository.findProductDepot(productDepotDTO.getDepotId(), productDepotDTO.getProduct().getId());
        if(productDepot != null){
            productDepot.setAmount(productDepotDTO.getAmount());
            productDepotRepository.save(productDepot);
        }else{
            save(productDepotDTO);
        }
    }

    @Override
    public void transfer(ProductTransferDTO productTransferDTO) {
        Product product = productRepository.findOne(productTransferDTO.getProductId());

        if(product == null){
            throw new BusinessException(PRODUCT_NOT_FOUND);
        }

        DepotResponse toDepot = depotClient.getById(productTransferDTO.getToDepotId()).getBody();
        DepotResponse fromDepot = depotClient.getById(productTransferDTO.getFromDepotId()).getBody();

        if (toDepot == null || fromDepot == null) {
            throw new BusinessException(DEPOT_NOT_FOUND);
        }

        if(!toDepot.city().equals(fromDepot.city())){
            if(!fromDepot.type().equals(MAIN_DEPOT))
                throw new BusinessException(INTERCITY_TRANSFER_MUST_BE_FROM_MAIN_DEPOT);
        }else{
            DepotDistanceResponse response = depotClient.getDistance(fromDepot.id(), toDepot.id()).getBody();
            if(response != null && response.distance() > 100.0)
                throw new BusinessException(INNERCITY_TRANSFER_MUST_BE_LESS_THAN_100_KM);
        }

        ProductDepot fromStock =
                productDepotRepository.findProductDepot(productTransferDTO.getFromDepotId(), productTransferDTO.getProductId());

        if(fromStock == null || fromStock.getAmount() < productTransferDTO.getTransferAmount()){
            throw new BusinessException(TRANSFER_AMOUNT_CANNOT_BE_MORE_THAN_TOTAL_AMOUNT);
        }

        ProductDepot toStock = productDepotRepository.findProductDepot(productTransferDTO.getToDepotId(), productTransferDTO.getProductId());

        if(toStock != null){
            toStock.setAmount(toStock.getAmount() + productTransferDTO.getTransferAmount());
        }else{
            toStock = new ProductDepot();
            toStock.setProduct(product);
            toStock.setDepotId(toDepot.id());
            toStock.setAmount(productTransferDTO.getTransferAmount());
        }

        productDepotRepository.save(toStock);

        if(fromStock.getAmount().equals(productTransferDTO.getTransferAmount())){
            productDepotRepository.delete(fromStock);
        }else{
            fromStock.setAmount(fromStock.getAmount() - productTransferDTO.getTransferAmount());
            productDepotRepository.save(fromStock);
        }


    }

    @Override
    @Transactional
    public DepotCloseResponse transferFordepotClose(Long depotId) {
        List<ProductDepot> closingDepotProductList = productDepotRepository.findProductDepotByDepotId(depotId);

        DepotResponse mainDepot = depotClient.getMainDepot().getBody();

        if (mainDepot == null) {
            throw new BusinessException(MAIN_DEPOT_NOT_FOUND);
        }

        List<ProductDepot> mainDepotProductList = productDepotRepository.findProductDepotByDepotId(mainDepot.id());

        for (ProductDepot transferProduct : closingDepotProductList){
            ProductDepot productInMain = mainDepotProductList.stream()
                                                             .filter(f -> f.getProduct().getId().equals(transferProduct.getProduct().getId()))
                                                             .findFirst()
                                                             .orElse(null);

            if (productInMain != null) {
                productInMain.setAmount(productInMain.getAmount() + transferProduct.getAmount());
                productDepotRepository.save(productInMain);

                productDepotRepository.delete(transferProduct);
            } else {
                transferProduct.setDepotId(mainDepot.id());
                productDepotRepository.save(transferProduct);
            }

        }

        return new DepotCloseResponse(Boolean.TRUE);
    }
}
