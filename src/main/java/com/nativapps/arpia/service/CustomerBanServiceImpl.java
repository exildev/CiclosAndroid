package com.nativapps.arpia.service;

import com.nativapps.arpia.database.dao.CustomerBanHistoryDao;
import com.nativapps.arpia.database.entity.CustomerBanHistory;
import com.nativapps.arpia.model.dto.CustomerBanHistoryRequest;
import com.nativapps.arpia.model.dto.CustomerBanHistoryResponse;
import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.CustomerDataDao;
import com.nativapps.arpia.database.entity.CustomerData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CustomerBanServiceImpl extends GenericService
        implements CustomerBanService, DtoConverter<CustomerBanHistory, 
        CustomerBanHistoryRequest, CustomerBanHistoryResponse> {

    private final CustomerBanHistoryDao customerBanHistoryDao
            = EntityControllerFactory.getBanHistoryDao();
    
    private final CustomerDataDao customerDataDao = EntityControllerFactory
            .getCustomerDataController();

    @Override
    public List<CustomerBanHistoryResponse> getAll(int start, int size, Long customerId) {
        if (start < 0)
            throw new BadRequestException(config.getString("pagination.start"));
        if (size <= 0)
            throw new BadRequestException(config.getString("pagination.size"));
        getCustomerEntity(customerId);
        List<CustomerBanHistoryResponse> response = new ArrayList<>();
        for (CustomerBanHistory customerBanHistory : customerBanHistoryDao
                .findAllByCustomerId(start, size, customerId)) {
            response.add(convertToDto(customerBanHistory));
        }

        return response;
    }

    @Override
    public CustomerBanHistoryResponse getLastBan(Long customerId) {
        getCustomerEntity(customerId);
        return convertToDto(customerBanHistoryDao
                .findLastBanByCustomerId(customerId));
    }

    @Override
    public CustomerBanHistoryResponse ban(Long customerId,
            CustomerBanHistoryRequest data) {
        CustomerData customer = getCustomerEntity(customerId);
        if (data == null)
            throw new BadRequestException(config
                    .getString("customerBan.is_null"));
        else if (data.getReason() == null)
            throw new BadRequestException(config
                    .getString("customerBan.reason_null"));
        
        if (customer.isBanned())
            throw new BadRequestException(config
                    .getString("customerBan.is_banned"));
        
        customer.setBanned(true);
        customerDataDao.save(customer);
        
        CustomerBanHistory history = new CustomerBanHistory(customer, 
                data.getReason(), Calendar.getInstance(TimeZone
                        .getTimeZone("GMT")));
        
        return convertToDto(customerBanHistoryDao.save(history));
    }

    @Override
    public CustomerBanHistoryResponse cancelBan(Long customerId) {
        CustomerData customer = getCustomerEntity(customerId);
        if (!customer.isBanned())
            throw new BadRequestException(config
                    .getString("customerBan.is_not_banned"));
        
        CustomerBanHistory lastBan = customerBanHistoryDao
                .findLastBanByCustomerId(customerId);
        
        customer.setBanned(false);
        lastBan.setEnd(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
        
        customerDataDao.save(customer);
        return convertToDto(customerBanHistoryDao.save(lastBan));
    }

    @Override
    public CustomerBanHistory convertToEntity(CustomerBanHistoryRequest data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CustomerBanHistoryResponse convertToDto(CustomerBanHistory entity) {
        CustomerBanHistoryResponse response = new CustomerBanHistoryResponse();
        response.setCustomerId(entity.getId());
        response.setId(entity.getId());
        response.setReason(entity.getReason());
        response.setStart(entity.getStart());
        response.setEnd(entity.getEnd());
        return response;
    }
    
    public CustomerData getCustomerEntity(Long customerId) {
        if (customerId == null || customerId < 0)
            throw new BadRequestException(config
                    .getString("customer.id_required"));
        
        CustomerData customer = customerDataDao.find(customerId);
        
        if (customer == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), customerId));
        
        return customer;
    }
}
