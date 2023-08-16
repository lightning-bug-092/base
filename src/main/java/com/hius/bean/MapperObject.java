package com.hius.bean;

import com.hius.utils.DateUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;

public abstract class MapperObject<T> {
    private static final Logger logger = LoggerFactory.getLogger(MapperObject.class);

    public abstract T createNewGenericInstance();

    private Map<String, String> mapper;

    public MapperObject(Map<String, String> mapper) {
        this.mapper = mapper;
    }

    public T mapper(Map input) {
        try {
            T output = createNewGenericInstance();
            return (T) cloneObject(output, mapper, input);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    private T cloneObject(T output, Map<String, String> mapper, Map<String, Object> input) {
        try {
            for (Field fieldOut : output.getClass().getDeclaredFields()) {
                fieldOut.setAccessible(true);
                if (Modifier.isFinal(fieldOut.getModifiers())) {
                    continue;
                }
                Object value = input.get(fieldOut.getName());
                if (value !=null){
                    if (fieldOut.getType().equals(Long.class)) {
                        fieldOut.set(output, Long.parseLong(value.toString()));
                    } else if (fieldOut.getType().equals(String.class)) {
                        fieldOut.set(output, value.toString());
                    } else if (fieldOut.getType().equals(Double.class)) {
                        fieldOut.set(output, Double.parseDouble(value.toString()));
                    } else if (fieldOut.getType().equals(Integer.class)) {
                        fieldOut.set(output, Integer.parseInt(value.toString()));
                    } else if (fieldOut.getType().equals(Date.class)) {
                        fieldOut.set(output, (Date) value);
                    } else {
                        try {
                            fieldOut.set(output, value);
                        } catch (IllegalArgumentException e) {
                            logger.error(ExceptionUtils.getStackTrace(e));
                        }
                    }
                }

            }
            for (String key : mapper.keySet()) {
                if (input.get(key) == null) continue;
                String value = mapper.get(key);
                for (Field fieldOut : output.getClass().getDeclaredFields()) {
                    fieldOut.setAccessible(true);
                    if (fieldOut.getName().equals(value)) {
                        if ( Modifier.isFinal(fieldOut.getModifiers())) {
                            continue;
                        }
                        if (fieldOut.getType().equals(Long.class)) {
                            fieldOut.set(output, Long.parseLong(input.get(key).toString()));
                        } else if (fieldOut.getType().equals(String.class)) {
                            fieldOut.set(output, input.get(key).toString());
                        } else if (fieldOut.getType().equals(Double.class)) {
                            fieldOut.set(output, Double.parseDouble(input.get(key).toString()));
                        } else if (fieldOut.getType().equals(Integer.class)) {
                            fieldOut.set(output, Integer.parseInt(input.get(key).toString()));
                        } else if (fieldOut.getType().equals(Date.class)) {
                            fieldOut.set(output, (Date) input.get(key));
                        } else {
                            try {
                                fieldOut.set(output, input.get(key));
                            } catch (IllegalArgumentException e) {
                                logger.error(ExceptionUtils.getStackTrace(e));
                            }
                        }
                        break;
                    }
                }

            }
            return output;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

}
