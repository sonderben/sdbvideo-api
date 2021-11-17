package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity,T extends Serializable> extends JpaRepository<E,T> {
}
