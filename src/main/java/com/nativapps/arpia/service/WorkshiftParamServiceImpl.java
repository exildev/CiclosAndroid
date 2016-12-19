package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.WorkshiftParamDao;
import com.nativapps.arpia.database.entity.Workshift;
import com.nativapps.arpia.database.entity.WorkshiftParam;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.WorkshiftParamRequest;
import com.nativapps.arpia.model.dto.WorkshiftParamResponse;
import com.nativapps.arpia.model.dto.WorkshiftResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class WorkshiftParamServiceImpl extends GenericService implements WorkshiftParamService,
        DtoConverter<WorkshiftParam, WorkshiftParamRequest, WorkshiftParamResponse> {

    private final WorkshiftParamDao controller
            = EntityControllerFactory.getWorkshiftParamController();

    /**
     * Get workshift param entity from the given messenger ID.
     *
     * @param messengerId messenger ID
     * @return workshift param
     *
     * @throws BadRequestException if messenger ID is null or less than or equal
     * to 0.
     * @throws NotFoundException if workshift param was not found
     */
    private WorkshiftParam getEntity(Long messengerId)
            throws BadRequestException, NotFoundException {
        if (messengerId == null || messengerId <= 0)
            throw new BadRequestException(config.getString("workshift_param.id"));

        WorkshiftParam workshiftParam = controller.findByMessengerId(messengerId);
        if (workshiftParam == null) {
            String msg = String.format(config.getString("workshift_param.not_found"), messengerId);
            throw new NotFoundException(msg);
        }

        return workshiftParam;
    }

    @Override
    public WorkshiftParamResponse get(Long messengerId) {
        return convertToDto(getEntity(messengerId));
    }

    @Override
    public WorkshiftParamResponse update(Long messengerId,
            WorkshiftParamRequest workshiftParam) {
        if (workshiftParam == null)
            throw new BadRequestException(config.getString("workshift_param.is_null"));

        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);

        if (workshiftParam.getWorkDay() == null || workshiftParam.getWorkDay() <= 0)
            emd.addMessage(config.getString("workshift_param.workDay"));
        if (workshiftParam.getFestiveDay() == null || workshiftParam.getFestiveDay() <= 0)
            emd.addMessage(config.getString("workshift_param.festiveDay"));
        if (workshiftParam.getBreakDay() == null || workshiftParam.getBreakDay() <= 0)
            emd.addMessage(config.getString("workshift_param.breakDay"));

        WorkshiftParam currentWorkshiftParam = getEntity(messengerId);
        currentWorkshiftParam.setWorkDay(new Workshift(workshiftParam.getWorkDay()));
        currentWorkshiftParam.setFestiveDay(new Workshift(workshiftParam.getFestiveDay()));
        currentWorkshiftParam.setBreakDay(workshiftParam.getBreakDay());

        return convertToDto(controller.save(currentWorkshiftParam));
    }

    @Override
    public WorkshiftParam convertToEntity(WorkshiftParamRequest d) {
        return new WorkshiftParam(new Workshift(d.getFestiveDay()),
                new Workshift(d.getWorkDay()), d.getBreakDay());
    }

    @Override
    public WorkshiftParamResponse convertToDto(WorkshiftParam e) {
        Workshift workDay = e.getWorkDay();
        WorkshiftResponse workDayResponse = new WorkshiftResponse(workDay.getId(),
                workDay.getEnterTime(), workDay.getExitTime());

        Workshift festiveDay = e.getFestiveDay();
        WorkshiftResponse festiveDayResponse = new WorkshiftResponse(festiveDay.getId(),
                festiveDay.getEnterTime(), festiveDay.getExitTime());

        return new WorkshiftParamResponse(e.getId(), workDayResponse,
                festiveDayResponse, e.getBreakDay());
    }
}
