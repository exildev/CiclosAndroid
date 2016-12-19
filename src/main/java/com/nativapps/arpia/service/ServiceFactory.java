package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;

/**
 * The <strong>ServiceFactory</strong> class provided methods to get services
 * implementations.
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 *
 * @version 1.0-SNAPSHOT
 */
public class ServiceFactory {

    public static ParticularService getParticularService() {
        return new ParticularServiceImpl();
    }

    public static AddressService getAddressService() {
        return new AddressServiceImpl();
    }

    public static EmailService getEmailService() {
        return new EmailServiceImpl();
    }

    public static PhoneService getPhoneService() {
        return new PhoneServiceImpl();
    }

    public static EstablishmentService getEstablishmentService() {
        return new EstablishmentServiceImpl();
    }

    public static AdministratorService getAdministratorService() {
        return new AdministratorServiceImpl();
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl();
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static MessengerService getMessengerService() {
        return new MessengerServiceImpl();
    }

    public static InventoryService getInventoryService() {
        return new InventoryServiceImpl();
    }

    public static FaultService getFaultService() {
        return new FaultServiceImpl();
    }

    public static ReferenceService getReferenceService() {
        return new ReferenceServiceImpl();
    }

    public static OperationService getOperationService() {
        return new OperationServiceImpl();
    }

    public static VehicleService getVehicleService() {
        return new VehicleServiceImpl();
    }

    public static ParticularParamService getParticularParameterService() {
        return new ParticularParamServiceImpl();
    }

    public static EstablishmentParamService getEstablishmentParamService() {
        return new EstablishmentParamServiceImpl();
    }

    public static MessengerBlackListService getMessengerBlackListService() {
        return new MessengerBlackListServiceImpl();
    }

    public static WorkshiftParamService getWorkShiftParamService() {
        return new WorkshiftParamServiceImpl();
    }

    public static BonusService getBonusService() {
        return new BonusServiceImpl();
    }

    public static WorkshiftService getWorkshiftService() {
        return new WorkshiftServiceImpl();
    }

    public static ReliabilityService getReliabilityService() {
        return new ReliabilityServiceImpl();
    }

    public static NeighborhoodService getNeighborhoodService() {
        return new NeighborhoodServiceImpl(EntityControllerFactory
                .getNeighborhoodController());
    }

    public static CustomerDataService getCustomerDataService() {
        return new CustomerDataServiceImpl();
    }

    public static CreditInfoService getCreditInfoService() {
        return new CreditInforServiceImpl();
    }

    public static MessengerActionService getMessengerActionService() {
        return new MessengerActionServiceImpl();
    }

    public static CustomerBanService getCustomerBanService() {
        return new CustomerBanServiceImpl();
    }

    public static MobileNumberService getMobileNumberService() {
        return new MobileNumberServiceImpl();
    }
}
