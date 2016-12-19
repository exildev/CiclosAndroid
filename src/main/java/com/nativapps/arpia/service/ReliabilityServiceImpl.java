package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.ReliabilityDao;
import com.nativapps.arpia.database.entity.Reliability;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.ReliabilityRequest;
import com.nativapps.arpia.model.dto.ReliabilityResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.ReliabilityValidator;
import javax.ws.rs.core.Response;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class ReliabilityServiceImpl extends GenericService
        implements ReliabilityService,
        DtoConverter<Reliability, ReliabilityRequest, ReliabilityResponse> {

    private final ReliabilityDao controller = EntityControllerFactory
            .getReliabilityController();

    /**
     * Validate if reliability data have all attributes to save in database.
     *
     * @param reliability reliability data
     *
     * @throws BadRequestException if the reliability is null
     * @throws ServiceException if the reliability don't have any attribute
     * required
     */
    private void validateData(ReliabilityRequest reliability)
            throws BadRequestException, ServiceException {
        if (reliability == null)
            throw new BadRequestException(config.getString("reliability.required"));

        ErrorMessageData errors = new ErrorMessageData();
        ReliabilityValidator.evaluateReliability(reliability, errors, config);

        //verify if exist error messages
        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }
    }

    /**
     * Get reliability entity from the given messenger ID.
     *
     * @param messengerId the messenger ID
     * @return reliability entity
     *
     * @throws NotFoundException if reliability data is null
     * @throws BadRequestException if messenger ID is null or less than or equal
     * to 0
     */
    private Reliability getEntity(Long messengerId) throws NotFoundException,
            BadRequestException {
        if (messengerId == null || messengerId <= 0)
            throw new BadRequestException(config.getString("reliability.id"));

        Reliability reliability = controller.findByMessengerId(messengerId);
        if (reliability == null) {
            String msg = String.format(config.getString("reliability.not_found"),
                    messengerId);
            throw new NotFoundException(msg);
        }

        return reliability;
    }

    @Override
    public ReliabilityResponse get(Long messengerId) {
        return convertToDto(getEntity(messengerId));
    }

    @Override
    public ReliabilityResponse update(Long messengerId,
            ReliabilityRequest reliability) {
        validateData(reliability);

        Reliability currentReliability = getEntity(messengerId);

        currentReliability.setConfidence(reliability.getConfidence());
        currentReliability.setDailySaving(reliability.getDailySaving());
        currentReliability.setManagement(reliability.getManagement());
        currentReliability.setMinNumberBase(reliability.getMinNumberBase());
        currentReliability.setMinProduction(reliability.getMinProduction());
        currentReliability.setMinService(reliability.getMinService());
        currentReliability.setSpeed(reliability.getSpeed());

        return convertToDto(controller.save(currentReliability));
    }

    @Override
    public Reliability convertToEntity(ReliabilityRequest d) {
        return new Reliability(d.getConfidence(), d.getSpeed(), d.getManagement(),
                d.getMinNumberBase(), d.getMinProduction(), d.getMinService(),
                d.getDailySaving());
    }

    @Override
    public ReliabilityResponse convertToDto(Reliability e) {
        return new ReliabilityResponse(e.getId(), e.getConfidence(), e.getSpeed(),
                e.getManagement(), e.getMinNumberBase(), e.getMinProduction(),
                e.getMinService(), e.getDailySaving());
    }

}
