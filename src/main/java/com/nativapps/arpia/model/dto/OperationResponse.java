package com.nativapps.arpia.model.dto;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0.1
 */
public class OperationResponse extends OperationData {

    private Long id;

    private Long adminUserId;

    public OperationResponse() {
    }

    public OperationResponse(Long id, Long adminUserId, String name,
            String alias, String description, String imageUrl) {
        super(name, alias, description, imageUrl);
        this.id = id;
        this.adminUserId = adminUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

}
