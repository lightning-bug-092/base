package com.hius.dao.db;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ParameterMode;

@Getter
@Setter
@Builder
public class SPParameter {

    private String key;

    private Object value;

    private ParameterMode mode;

}
