package com.solvd.models.market;

import java.util.HashSet;
import java.util.Set;

import com.solvd.exceptions.DuplicatePlayerRoleException;

abstract class AbstractMarket<T> {
    protected Set<T> items = new HashSet<>();

    public void addItem(T item) throws DuplicatePlayerRoleException {
        items.add(item);
    }

    public abstract T getItem(String name);
}
