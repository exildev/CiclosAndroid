package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.CustomerDataDao;
import com.nativapps.arpia.database.entity.CustomerData;
import com.nativapps.arpia.model.dto.CustomerDataDto;
import com.nativapps.arpia.model.dto.CustomerParamData;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CustomerDataServiceImpl extends GenericService implements CustomerDataService,
        DtoConverter<CustomerData, CustomerDataDto, CustomerDataDto> {

    private final CustomerDataDao customerDataDao = EntityControllerFactory
            .getCustomerDataController();

    @Override
    public List<CustomerDataDto> getAll(int start, int size) {
        if (start < 0)
            throw new BadRequestException(config.getString("pagination.start"));
        if (size <= 0)
            throw new BadRequestException(config.getString("pagination.size"));
        List<CustomerDataDto> response = new ArrayList<>();
        for (CustomerData customerData : customerDataDao
                .findAllPaginated(start, size)) {
            response.add(convertToDto(customerData));
        }
        return response;
    }

    @Override
    public CustomerDataDto get(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("customer.id_required"));

        CustomerData customer = customerDataDao.find(id);

        if (customer == null)
            throw new NotFoundException(config.getString("customer.not_found"));
        return convertToDto(customer);
    }

    @Override
    public CustomerData convertToEntity(CustomerDataDto data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CustomerDataDto convertToDto(CustomerData entity) {
        CustomerDataDto dto = new CustomerDataDto();

        dto.setId(entity.getId());
        dto.setObservations(entity.getObservations());
        dto.setBanned(entity.isBanned());
        dto.setParam(new CustomerParamData(entity.getParam()));

        return dto;
    }

}
