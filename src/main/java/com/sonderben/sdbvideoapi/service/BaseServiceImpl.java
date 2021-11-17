package com.sonderben.sdbvideoapi.service;


import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import com.sonderben.sdbvideoapi.exception.BadRequestException;
import com.sonderben.sdbvideoapi.exception.NoDataFoundException;
import com.sonderben.sdbvideoapi.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;



@Validated
public abstract class BaseServiceImpl<ENTITY extends BaseEntity,ID extends Serializable> implements BaseService<ENTITY,ID>{
    @Autowired
    BaseRepository<ENTITY,ID> repository;

    @Override
    public List<ENTITY> findAll(){

        return repository.findAll();
    }

    @Override
    public ENTITY findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ENTITY save( ENTITY e) {
        return repository.save(e);
    }

    @Override
    public ENTITY delete(ID id)  {
        ENTITY entity=repository.findById(id).orElse(null);
        if(entity!=null)
            repository.delete(entity);
        return entity;
    }

    @Override
    public ENTITY update(ENTITY entity,ID id)  {
        ENTITY entityFind=repository.findById(id).orElseThrow(()->new BadRequestException("Id don t exist"));
        ENTITY update=repository.save( entityFind);

            repository.save(entity);
            return  update;

    }
}
