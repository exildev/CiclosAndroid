package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.WorkshiftRequest;
import com.nativapps.arpia.model.dto.WorkshiftResponse;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.0.1
 */
public interface WorkshiftService extends Service {

    /**
     * Create new workshift from the given data.
     *
     * @param workshift workshift data
     * @return workshift saved
     */
    public WorkshiftResponse add(WorkshiftRequest workshift);

    /**
     * Get a workshift information from the given ID.
     *
     * @param id
     * @return
     */
    public WorkshiftResponse get(Long id);

    /**
     * Get all workshifts.
     *
     * @return list of workshift
     */
    public List<WorkshiftResponse> getAll();

    /**
     * Updates the corresponding workshift the messenger.
     *
     * @param id workshift ID
     * @param data workshift data
     * @return workshift updated.
     */
    public WorkshiftResponse update(Long id, WorkshiftRequest data);

    /**
     * Remove the workshift information provided by the ID.
     *
     * @param id workshift ID
     * @return workshift deleted
     */
    public WorkshiftResponse delete(Long id);
}
