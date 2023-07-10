package com.evi.teamfindernotifications.utils;


import com.evi.teamfindernotifications.exception.UserNotFoundException;
import com.evi.teamfindernotifications.security.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsHelper {

    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        throw new UserNotFoundException("Cant load security context");
    }


}
