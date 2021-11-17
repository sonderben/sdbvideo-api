package com.sonderben.sdbvideoapi.service;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService <ENTITY extends BaseEntity,ID extends Serializable>{
    public List<ENTITY> findAll() ;
    public ENTITY findById(ID id) ;
    public ENTITY save(ENTITY e) ;
    public ENTITY delete (ID id) ;
    public ENTITY update (ENTITY entity,ID id) ;
}
