package com.sonderben.sdbvideoapi.validator;

import com.sonderben.sdbvideoapi.dto.Dto;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;

import java.io.Serializable;

public interface SdbValidator {
    public void validateInsert(Dto e);
    public void validateUpdate(Dto e);
}
