package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.UserDao;
import com.nativapps.arpia.database.entity.Operation;
import com.nativapps.arpia.database.entity.Role;
import com.nativapps.arpia.database.entity.User;
import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.database.exception.IncorrectCredentialsException;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.TokenData;
import com.nativapps.arpia.model.dto.UserRequest;
import com.nativapps.arpia.model.dto.UserResponse;
import com.nativapps.arpia.rest.TokenUtil;
import com.nativapps.arpia.rest.exception.TokenException;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.1.1
 */
public class UserServiceImpl extends GenericService implements UserService,
        DtoConverter<User, UserRequest, UserResponse> {

    private static final Logger LOG = Logger
            .getLogger(UserServiceImpl.class.getName());

    private final UserDao controller = EntityControllerFactory
            .getUserController();

    /**
     * Get user information in the database from the given user ID.
     *
     * @param userId user ID.
     * @return user information.
     */
    private User getUserEntity(Long userId) {
        if (userId == null || userId <= 0)
            throw new BadRequestException(config.getString("user.id_required"));

        User user = controller.find(userId);
        if (user == null) {
            String m = String.format(config.getString("user.not_found"), userId);
            throw new NotFoundException(m);
        }

        return user;
    }

    @Override
    public List<UserResponse> getAllUsersPaginated(int start, int size) {
        if (size <= 0)
            throw new BadRequestException(config.getString("user.size"));
        List<UserResponse> users = new ArrayList<>();
        for (User user : controller.findAllPaginated(start, size))
            users.add(convertToDto(user));
        return users;
    }

    @Override
    public List<UserResponse> getAllUserByTypePaginated(UserType type, int start,
            int size) {
        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);

        if (type == null)
            emd.addMessage(config.getString("user.type"));
        if (size <= 0)
            emd.addMessage(config.getString("user.size"));

        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);

        List<UserResponse> users = new ArrayList<>();
        for (User user : controller.findAllUserByType(type, start, size))
            users.add(convertToDto(user));
        return users;
    }

    @Override
    public UserResponse getUser(Long userId) {
        return convertToDto(getUserEntity(userId));
    }

    @Override
    public UserResponse addNewUser(UserRequest user) {
        if (user == null)
            throw new BadRequestException(config.getString("user.is_null"));

        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);

        if (TextUtil.isEmpty(user.getEmail()))
            emd.addMessage(config.getString("user.email"));
        else if (!TextUtil.isEmail(user.getEmail()))
            emd.addMessage(config.getString("user.e_format"));
        else if (controller.findByEmail(user.getEmail()) != null)
            emd.addMessage(String.format(config.getString("user.e_exists"),
                    user.getEmail()));

        if (!TextUtil.isEmpty(user.getUsername())) {
            if (!TextUtil.isUsername(user.getUsername())) {
                emd.addMessage(config.getString("user.username"));
            } else if (controller.findByUsername(user.getUsername()) != null) {
                String msg = String.format(config.getString("user.u_exists"),
                        user.getUsername());
                emd.addMessage(msg);
            }
        }

        if (TextUtil.isEmpty(user.getPassword())
                || user.getPassword().length() < 6)
            emd.addMessage(config.getString("user.passw"));
        if (TextUtil.isEmpty(user.getDisplayName()))
            emd.addMessage(config.getString("user.dname"));

        if (user.getOperations() == null || user.getOperations().isEmpty())
            emd.addMessage(config.getString("user.operation"));

        if (user.getType() == null)
            emd.addMessage(config.getString("user.type"));
        else if (user.getType().equals(UserType.OPERATOR)
                && (user.getRoleId() == null || user.getRoleId() <= 0))
            emd.addMessage(config.getString("user.role"));
        else if (user.getType().equals(UserType.SUPER_USER))
            emd.addMessage(config.getString("user.nsuper"));

        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);

        user.setUpdated(Calendar.getInstance());

        return convertToDto(controller.save(convertToEntity(user)));
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest user) {
        if (userId == null || userId <= 0)
            throw new BadRequestException(config.getString("user.id_required"));

        if (user == null)
            throw new BadRequestException(config.getString("user.modify"));

        User currentUser = getUserEntity(userId);
        if (!TextUtil.isEmpty(user.getUsername())
                && !user.getUsername().equalsIgnoreCase(currentUser.getUsername())) {
            if (!TextUtil.isUsername(user.getUsername()))
                throw new BadRequestException(config.getString("user.username"));
            if (controller.findByUsername(user.getUsername()) != null) {
                String msg = String.format(config.getString("user.u_exists"),
                        user.getUsername());
                throw new BadRequestException(msg);
            }
            currentUser.setUsername(user.getUsername());
        }

        if (!TextUtil.isEmpty(user.getEmail())
                && !currentUser.getEmail().equalsIgnoreCase(user.getEmail())) {
            if (!TextUtil.isEmail(user.getEmail()))
                throw new BadRequestException(config.getString("user.e_format"));
            if (controller.findByEmail(user.getEmail()) != null)
                throw new BadRequestException(String.format(config.getString("user.e_exists"),
                        user.getEmail()));
            currentUser.setEmail(user.getEmail());
        }

        if (!TextUtil.isEmpty(user.getDisplayName())
                && !user.getDisplayName().equals(currentUser.getDisplayName()))
            currentUser.setDisplayName(user.getDisplayName());
        if (user.getRoleId() != null)
            currentUser.setRole(new Role(user.getRoleId()));
        if (user.getEnabled() != null
                && user.getEnabled().equals(currentUser.isEnabled()))
            currentUser.setEnabled(user.getEnabled());
        
        if (user.getOperations() != null) {
            List<Operation> operations = new ArrayList<>();
            for (Long operationId : user.getOperations()) {
                operations.add(new Operation(operationId));
            }
            currentUser.setOperations(operations);
        }

        currentUser.setUpdated(Calendar.getInstance());

        return convertToDto(controller.save(currentUser));
    }

    @Override
    public UserResponse deleteUser(Long userId) {
        UserResponse user = getUser(userId);
        if (user.getType() == UserType.ADMINISTRATOR)
            throw new BadRequestException(config.getString("user.delete"));
        controller.delete(userId);
        return user;
    }

    @Override
    public TokenData login(String userData, String password) {
        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);

        if (TextUtil.isEmpty(userData))
            emd.addMessage(config.getString("user.login.username"));
        if (TextUtil.isEmpty(password))
            emd.addMessage(config.getString("user.login.passw"));

        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);

        try {
            User userLogged = controller.login(userData, password);
            String token = TokenUtil.generateToken(convertToDto(userLogged));
            return new TokenData(token);
        } catch (IncorrectCredentialsException ex) {
            throw new WebApplicationException(ex.getMessage(),
                    Response.Status.UNAUTHORIZED);
        } catch (TokenException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex.getMessage(), ex);
        }
    }

    @Override
    public User convertToEntity(UserRequest d) {
        List<Operation> companies = new ArrayList<>();
        if (d.getOperations() != null) {
            for (Long company : d.getOperations())
                companies.add(new Operation(company == null ? 0 : company));
        }
        return new User(0, d.getUsername(), d.getEmail(), d.getPassword(),
                d.getDisplayName(), d.getRoleId() == null ? null : new Role(d.getRoleId()),
                d.getType(), companies, d.getEnabled(), d.getUpdated());
    }

    @Override
    public UserResponse convertToDto(User e) {
        List<Long> companies = new ArrayList<>();
        if (e.getOperations() != null) {
            for (Operation company : e.getOperations())
                companies.add(company.getId());
        }
        return new UserResponse(e.getId(), e.getUsername(), e.getEmail(),
                e.getDisplayName(), e.getRole() == null ? null : e.getRole().getId(),
                e.getType(), companies, e.isEnabled(), e.getCreated(), e.getUpdated());
    }
}
