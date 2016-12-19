package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.WorkshiftDao;
import com.nativapps.arpia.database.entity.Workshift;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.WorkshiftRequest;
import com.nativapps.arpia.model.dto.WorkshiftResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.WorkshiftValidator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class WorkshiftServiceImpl extends GenericService implements WorkshiftService,
        DtoConverter<Workshift, WorkshiftRequest, WorkshiftResponse> {

    private final WorkshiftDao controller = EntityControllerFactory
            .getWorkshiftController();

    /**
     * Validate if workshift have all attributes to save in the database.
     *
     * @param workshift workshift data
     *
     * @throws BadRequestException if the workshift data is null
     * @throws ServiceException if the workshift data don't have any attribute
     * required
     */
    private void validateWorkshift(WorkshiftRequest workshift)
            throws BadRequestException, ServiceException {
        if (workshift == null)
            throw new BadRequestException(config.getString("workshift.required"));

        ErrorMessageData emd = new ErrorMessageData();
        WorkshiftValidator.evaluateWorkshift(workshift, emd, config);

        //verify if exist error messages
        if (!emd.getMessages().isEmpty()) {
            emd.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(emd);
        }
    }

    /**
     * Get workshift entity from the given ID.
     *
     * @param id workshift ID
     * @return workshift entity
     *
     * @throws BadRequestException if workshift ID is null or less than or equal
     * to 0
     * @throws NotFoundException if the workshift obtained is null
     */
    private Workshift getEntity(Long id) throws BadRequestException,
            NotFoundException {
        if (id == null || id <= 0)
            throw new BadRequestException(config.getString("workshift.id"));

        Workshift workshift = controller.find(id);
        if (workshift == null) {
            String msg = String.format(config.getString("workshift.not_found"), id);
            throw new NotFoundException(msg);
        }

        return workshift;
    }

    @Override
    public WorkshiftResponse add(WorkshiftRequest data) {
        validateWorkshift(data);
        return convertToDto(controller.save(convertToEntity(data)));
    }

    @Override
    public WorkshiftResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    @Override
    public List<WorkshiftResponse> getAll() {
        List<WorkshiftResponse> workshifts = new ArrayList<>();
        for (Workshift workshift : controller.findAll()) {
            workshifts.add(convertToDto(workshift));
        }
        return workshifts;
    }

    @Override
    public WorkshiftResponse update(Long id, WorkshiftRequest data) {
        validateWorkshift(data);

        Workshift workshift = getEntity(id);
        workshift.setEnterTime(data.getEnterTime());
        workshift.setExitTime(data.getExitTime());

        return convertToDto(controller.save(workshift));
    }

    @Override
    public WorkshiftResponse delete(Long id) {
        WorkshiftResponse workshift = get(id);
        controller.delete(id);
        return workshift;
    }

    @Override
    public Workshift convertToEntity(WorkshiftRequest d) {
        return new Workshift(d.getEnterTime(), d.getExitTime());
    }

    @Override
    public WorkshiftResponse convertToDto(Workshift e) {
        return new WorkshiftResponse(e.getId(), e.getEnterTime(), e.getExitTime());
    }

}
