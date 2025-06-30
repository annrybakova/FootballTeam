package com.solvd.models;

import java.util.ArrayList;

import com.solvd.exceptions.DuplicatePlayerRoleException;

abstract class AbstractMarket<T> {
    protected ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) throws DuplicatePlayerRoleException {
        items.add(item);
    }

    public abstract T getItem(String name);
}
