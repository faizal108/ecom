package com.faizal.ecom.dao;

import com.faizal.ecom.dao.repos.PasswordRepo;
import com.faizal.ecom.entity.Password;
import com.faizal.ecom.entity.Status;
import com.faizal.ecom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PasswordDao {
    @Autowired
    PasswordRepo passwordRepo;

    public String getActivePasswordOfUser (String userId){
        return passwordRepo.findByUser_uidAndStatus(userId, Status.ACTIVE).get(0).getUserPassword();
    }

    public void addPassword(Password password){
        managePasswordHistory(password.getUser());
        passwordRepo.save(password);
    }

    public boolean isPasswordExist (String userId, String password){
        return passwordRepo.countByUser_uidAndUserPassword(userId, password) != 0;
    }

//    --------------------------- Private Function Area ----------------------------------------
    private void managePasswordHistory (User user) {
        List<Password> passwords = passwordRepo.findByUser_uidOrderByCreatedDateAsc(user.getUid());
        passwords.stream().filter(p -> p.getStatus() == Status.ACTIVE).forEach(p -> {p.setStatus(Status.DEACTIVE); passwordRepo.save(p);});
        if(passwords.size() == 3){
//          Delete the oldest password of user
            passwordRepo.deleteById(passwords.get(0).getPsw_id());
        }
    }

}
