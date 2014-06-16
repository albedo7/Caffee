package com.caffee.services;

import com.caffee.dao.DAOEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DAOService <E extends DAOEntity> {
    public E getById(long id);
    public boolean saveBean(E bean);
    public boolean deleteBean(long id);
    public List<E> getAllBeans();
}
