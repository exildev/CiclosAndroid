package com.nativapps.arpia.database;

import com.nativapps.arpia.database.dao.AddressDao;
import com.nativapps.arpia.database.dao.AddressDaoController;
import com.nativapps.arpia.database.dao.AdministratorDao;
import com.nativapps.arpia.database.dao.AdministratorDaoController;
import com.nativapps.arpia.database.dao.BonusDao;
import com.nativapps.arpia.database.dao.BonusDaoController;
import com.nativapps.arpia.database.dao.CreditInfoDao;
import com.nativapps.arpia.database.dao.CreditInfoDaoController;
import com.nativapps.arpia.database.dao.CustomerBanHistoryDao;
import com.nativapps.arpia.database.dao.CustomerBanHistoryDaoController;
import com.nativapps.arpia.database.dao.CustomerDataDao;
import com.nativapps.arpia.database.dao.CustomerDataDaoController;
import com.nativapps.arpia.database.dao.OperationDaoController;
import com.nativapps.arpia.database.dao.CustomerParamDaoController;
import com.nativapps.arpia.database.dao.DiseaseDao;
import com.nativapps.arpia.database.dao.DiseaseDaoController;
import com.nativapps.arpia.database.dao.EmailDao;
import com.nativapps.arpia.database.dao.EmailDaoController;
import com.nativapps.arpia.database.dao.EstablishmentDao;
import com.nativapps.arpia.database.dao.EstablishmentDaoController;
import com.nativapps.arpia.database.dao.FaultDao;
import com.nativapps.arpia.database.dao.FaultDaoController;
import com.nativapps.arpia.database.dao.InventoryDao;
import com.nativapps.arpia.database.dao.InventoryDaoController;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.dao.MessengerDaoController;
import com.nativapps.arpia.database.dao.ParticularDao;
import com.nativapps.arpia.database.dao.ParticularDaoController;
import com.nativapps.arpia.database.dao.PermissionDao;
import com.nativapps.arpia.database.dao.PermissionDaoController;
import com.nativapps.arpia.database.dao.PersonDao;
import com.nativapps.arpia.database.dao.PersonDaoController;
import com.nativapps.arpia.database.dao.PhoneDao;
import com.nativapps.arpia.database.dao.PhoneDaoController;
import com.nativapps.arpia.database.dao.ReferenceDao;
import com.nativapps.arpia.database.dao.ReferenceDaoController;
import com.nativapps.arpia.database.dao.RoleDao;
import com.nativapps.arpia.database.dao.RoleDaoController;
import com.nativapps.arpia.database.dao.UserDao;
import com.nativapps.arpia.database.dao.UserDaoController;
import com.nativapps.arpia.database.dao.VehicleDao;
import com.nativapps.arpia.database.dao.VehicleDaoController;
import com.nativapps.arpia.database.dao.OperationDao;
import com.nativapps.arpia.database.dao.WorkshiftParamDaoController;
import com.nativapps.arpia.database.dao.CustomerParamDao;
import com.nativapps.arpia.database.dao.InventoryLogDao;
import com.nativapps.arpia.database.dao.InventoryLogDaoController;
import com.nativapps.arpia.database.dao.MessengerActionDaoController;
import com.nativapps.arpia.database.dao.MessengerActionDao;
import com.nativapps.arpia.database.dao.ReliabilityDao;
import com.nativapps.arpia.database.dao.ReliabilityDaoController;
import com.nativapps.arpia.database.dao.WorkshiftDao;
import com.nativapps.arpia.database.dao.WorkshiftDaoController;
import com.nativapps.arpia.database.dao.WorkshiftParamDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.dao.NeighborhoodDaoController;
import com.nativapps.arpia.database.dao.MobileNumberDaoController;
import com.nativapps.arpia.database.dao.MobileNumberDao;

/**
 * The <strong>EntityControllerFactory</strong> class provided methods to get
 * entities controllers.
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 *
 * @version 1.0-SNAPSHOT
 */
public class EntityControllerFactory {

    public static PersonDao getPersonController() {
        return new PersonDaoController();
    }

    public static EmailDao getEmailController() {
        return EmailDaoController.getInstance();
    }

    public static AddressDao getAddressController() {
        return AddressDaoController.getInstance();
    }

    public static PhoneDao getPhoneController() {
        return PhoneDaoController.getInstance();
    }

    public static UserDao getUserController() {
        return UserDaoController.getInstance();
    }

    public static RoleDao getRoleController() {
        return RoleDaoController.getInstance();
    }

    public static PermissionDao getPermissionController() {
        return PermissionDaoController.getInstance();
    }

    public static ParticularDao getParticularController() {
        return ParticularDaoController.getInstance();
    }

    public static EstablishmentDao getEstablishmentController() {
        return EstablishmentDaoController.getInstance();
    }

    public static AdministratorDao getAdministratorController() {
        return AdministratorDaoController.getInstance();
    }

    public static MessengerDao getMessengerController() {
        return MessengerDaoController.getInstance();
    }

    public static InventoryDao getInventoryController() {
        return InventoryDaoController.getInstance();
    }

    public static DiseaseDao getDiseaseController() {
        return DiseaseDaoController.getInstance();
    }

    public static FaultDao getFaultController() {
        return FaultDaoController.getInstance();
    }

    public static ReferenceDao getReferenceController() {
        return ReferenceDaoController.getInstance();
    }

    public static OperationDao getOperationController() {
        return OperationDaoController.getInstance();
    }

    public static VehicleDao getVehicleController() {
        return VehicleDaoController.getInstance();
    }

    public static CustomerParamDao getCustomerParamController() {
        return CustomerParamDaoController.getInstance();
    }

    public static WorkshiftParamDao getWorkshiftParamController() {
        return WorkshiftParamDaoController.getInstance();
    }

    public static BonusDao getBonusDaoController() {
        return BonusDaoController.getInstance();
    }

    public static CustomerDataDao getCustomerDataController() {
        return CustomerDataDaoController.getInstance();
    }

    public static WorkshiftDao getWorkshiftController() {
        return WorkshiftDaoController.getInstance();
    }

    public static ReliabilityDao getReliabilityController() {
        return ReliabilityDaoController.getInstance();
    }

    public static NeighborhoodDao getNeighborhoodController() {
        return NeighborhoodDaoController.getInstance();
    }

    public static CreditInfoDao getCreditInfoController() {
        return CreditInfoDaoController.getInstance();
    }

    public static MessengerActionDao getMessengerActionController() {
        return MessengerActionDaoController.getInstance();
    }

    public static CustomerBanHistoryDao getBanHistoryDao() {
        return CustomerBanHistoryDaoController.getInstance();
    }

    public static InventoryLogDao getInventoryLogController() {
        return InventoryLogDaoController.getInstance();
    }

    public static MobileNumberDao getNumberMobileDao() {
        return MobileNumberDaoController.getInstance();
    }
}
