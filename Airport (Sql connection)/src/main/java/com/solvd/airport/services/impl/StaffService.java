package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.RoleDAO;
import com.solvd.airport.dao.interfaces.StaffDAO;
import com.solvd.airport.models.Staff;

public class StaffService {
    private StaffDAO staffDAO;
    private RoleDAO roleDAO;

    public StaffService(StaffDAO staffDAO, RoleDAO roleDAO) {
        this.staffDAO = staffDAO;
        this.roleDAO = roleDAO;
    }

    public void hireStaff(Staff staff) {
        staffDAO.add(staff);
    }
}
