package com.solvd.airport.services.impl;

import com.solvd.airport.dao.interfaces.IRoleDAO;
import com.solvd.airport.dao.interfaces.IStaffDAO;
import com.solvd.airport.models.Staff;

public class StaffService {
    private IStaffDAO staffDAO;
    private IRoleDAO roleDAO;

    public StaffService(IStaffDAO staffDAO, IRoleDAO roleDAO) {
        this.staffDAO = staffDAO;
        this.roleDAO = roleDAO;
    }

    public void hireStaff(Staff staff) {
        staffDAO.add(staff);
    }
}
