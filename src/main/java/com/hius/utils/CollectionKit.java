package com.hius.utils;

import com.hius.exception.ToolException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectionKit {
    private CollectionKit(){}

    public static <T, K> HashMap<T, K> newHashMap(){
        return new HashMap<T, K>(100);
    }

    public static <T, K> HashMap<T, K> newHashMap(int size) { return new HashMap<T, K>((int) (size/0.75)); }

    public static <T> T[] newArray(Class<?> componentType, int length){
        return (T[]) Array.newInstance(componentType, length);
    }

    public static <T> T[] resize(T[] array, int length, Class<?> componentType){
        T[] newArray = newArray(componentType, length);
        System.arraycopy(array, 0, newArray, 0, array.length >= length ? length : array.length);
        return newArray;
    }

    public static <T> T[] append(T[] array, T element){
        T[] newArray = resize(array, array.length + 1, element.getClass());
        newArray[array.length] = element;
        return newArray;
    }

    public static <T> boolean isEmpty(T[] array) { return  array == null || array.length == 0; }

    public static <T> boolean isNotEmpty(T[] array) { return  false == isEmpty(array); }

    public static boolean isEmpty(Collection<?> collection) { return collection == null || collection.isEmpty(); }

    public static boolean isNotEmpty(Collection<?> collection) { return false == isEmpty(collection); }

    public static boolean isEmpty(Map<?, ?> map) { return map == null || map.isEmpty(); }

    public static boolean isNotEmpty(Map<?, ?> map) { return false == isEmpty(map); }

    public static boolean isArray(Object obj) { return obj.getClass().isArray(); }

    public static String toString(Object obj){
        if(null == obj){
            return null;
        }
        if(isArray(obj)){
            try {
                Arrays.deepToString((Object[]) obj);
            } catch(Exception e){
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return Arrays.toString((long[]) obj);
                    case "int":
                        return Arrays.toString((long[]) obj);
                    case "short":
                        return Arrays.toString((long[]) obj);
                    case "char":
                        return Arrays.toString((long[]) obj);
                    case "byte":
                        return Arrays.toString((long[]) obj);
                    case "boolean":
                        return Arrays.toString((long[]) obj);
                    case "float":
                        return Arrays.toString((long[]) obj);
                    case "double":
                        return Arrays.toString((long[]) obj);
                    default:
                        throw new ToolException(e);
                }
            }
        }
        return obj.toString();
    }
}
