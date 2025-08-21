package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Staff;

public interface StaffDAO {
    void add(Staff staff);
    Staff getById(int id);
    List<Staff> getAll();
    void update(Staff staff);
    void delete(int id);
}

