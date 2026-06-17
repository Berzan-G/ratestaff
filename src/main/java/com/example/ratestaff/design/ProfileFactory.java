package com.example.ratestaff.design;

import com.example.ratestaff.model.RoleType;

public class ProfileFactory {

    public static StaffMemberProfile createProfile(RoleType roleType) {
        if (roleType == RoleType.TA) {
            return new TaProfile();
        }

        if (roleType == RoleType.PROF) {
            return new ProfProfile();
        }

        return new GenericStaffProfile();
    }
}