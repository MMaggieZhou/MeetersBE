package daos;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.PersistenceException;

import domain.UserEntity;
import requests.LoginRequest;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.transaction.TransactionTimeoutException;
import exceptions.*;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.PessimisticLockException;
import org.hibernate.QueryTimeoutException;

import play.Logger;
import play.db.jpa.JPA;

import exceptions.ConflictException;

public class UserDao
{

    public UserEntity verifyUserCredencialByAccount(LoginRequest loginRequest)
    {
        String loginAccount = loginRequest.getLoginAccount().trim();
        String password = loginRequest.getPassword().trim();
        UserEntity user = null;
        if (StringUtils.isNumeric(loginAccount))
        {
            user = getUserByPhone(loginAccount);
        }
        else
        {
            user = getUserByEmail(loginAccount);
        }

        if (user != null && password.equals(user.getPassword()))
        {
            return user;
        }

        return null;
    }

    public UserEntity createUser(UserEntity user)
    {

        if (getUserByEmail(user.getEmail()) != null)
        {
            throw new ConflictException("Email conflict");
        }

        if (!StringUtils.isBlank(user.getPhone()) && getUserByPhone(user.getPhone()) != null)// phone
                                                                                             // is
                                                                                             // //
                                                                                             // blank?
        {
            throw new ConflictException("Phnoe conflict");
        }

        try
        {

            JPA.em().persist(user);

        }
        catch (IllegalArgumentException iae)
        {
            Logger.error(iae.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
        catch (Exception e)
        {
            // Handle errors for Class.forName
            Logger.error(e.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }

        return getUserByEmail(user.getEmail());
    }

    public UserEntity getUserByEmail(String email)
    {
        // TODO validate email
        if (StringUtils.isBlank(email))
        {
            return null;
        }
        UserEntity user = new UserEntity();
        try
        {
            List<?> results = JPA.em()
                    .createQuery("select user from UserEntity as user where user.email" + "= '" + email + "'")
                    .getResultList();

            if (results == null || results.size() == 0)
            {
                return null;
            }
            user = (UserEntity) results.get(0);
        }
        catch (Exception e)
        {
            throw new DatabaseAccessException("Database access error!");
        }
        return user;
    }

    public UserEntity getUserByPhone(String phone)
    {
        // TODO validate email
        if (StringUtils.isBlank(phone))
        {
            return null;
        }
        UserEntity user = new UserEntity();
        try
        {
            List<?> results = JPA.em()
                    .createQuery("select user from UserEntity as user where user.phone" + "= '" + phone + "'")
                    .getResultList();
            
            if (results == null || results.size() == 0)
            {
                return null;
            }
            user = (UserEntity) results.get(0);
        }
        catch (Exception e)
        {
            throw new DatabaseAccessException("Database access error!");
        }
        return user;
    }

    public boolean updateUserById(int colId, int id, String colValue)
    {
        // TODO validate email
        if (StringUtils.isBlank(colValue))
        {
            return false;
        }
        UserEntity user = (UserEntity) JPA.em().find(UserEntity.class, new BigInteger(String.valueOf(id)));
        try
        {
            switch (colId)
            {
                case 2:
                    user.setNickname(colValue);
                    break;
                case 3:
                    user.setEmail(colValue);
                    break;
            }
        }
        catch (Exception e)
        {
            throw new DatabaseAccessException("Database access error!");
        }
        return true;
    }
}