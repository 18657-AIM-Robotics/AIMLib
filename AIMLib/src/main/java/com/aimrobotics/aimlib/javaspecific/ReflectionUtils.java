package com.aimrobotics.aimlib.javaspecific;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ReflectionUtils is compilation of utilities written with
 * the built-in java reflect methods.
 *
 * @author Nate Schmelkin/GPT-4
 */

public class ReflectionUtils {

    /**
     *
     * @param obj object whose fields are being checked
     * @param type desired type of field
     * @return array containing all of certain field
     * @throws IllegalAccessException if access is unavailivble (shouldn't throw b/c field.setAcessible(true))
     */

    @SuppressWarnings("unchecked")
    public static <T> T[] getVariablesOfType(Object obj, Class<T> type) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<T> variablesOfType = new ArrayList<>();

        for (Field field : fields) {
            if (field.getType().equals(type)) {
                field.setAccessible(true);
                variablesOfType.add((T) field.get(obj));
            }
        }
        return variablesOfType.toArray((T[]) Array.newInstance(type, variablesOfType.size()));
    }

    /**
     *
     * @param obj main object
     * @param childType child objects to extract
     * @param returnType type of variable to extract from child
     * @param <T> type of the variable being extracted
     * @return array with objects
     * @throws IllegalAccessException if access is unavailivble (shouldn't throw b/c field.setAcessible(true))
     */

    public static <T> T[] getVariablesOfTypeInChildClass(Object obj, Class<?> childType, Class<T> returnType) throws IllegalAccessException {
        List<Object> childVariables = new ArrayList<>();
        Object[] childObjects = getVariablesOfType(obj, childType);

        for (Object childObj : childObjects) {
            Field[] fields = childObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType().equals(returnType)) {
                    field.setAccessible(true);
                    childVariables.add(field.get(childObj));
                }
            }
        }

        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(returnType, childVariables.size());
        return result;
    }
}