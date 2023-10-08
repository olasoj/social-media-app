package com.olasoj.socialapp.audit;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public class AuditUtils {

    private static final String ANONYMOUS = "SYSTEM";

    private AuditUtils() {
    }

    public static void onCreate(AuditObject auditObject) {
        String username = getUsername();
        auditObject.setCreatedBy(username);
        auditObject.setUpdatedBy(username);
    }

    public static void onUpdate(AuditObject auditObject) {
        auditObject.setUpdatedBy(getUsername());
    }

    private static String getUsername() {

        SecurityContext context = SecurityContextHolder.getContext();
        if (Objects.isNull(context)) return ANONYMOUS;

        Authentication authentication = context.getAuthentication();
        if (Objects.isNull(authentication)) return ANONYMOUS;

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }

        return ANONYMOUS;
    }

}
