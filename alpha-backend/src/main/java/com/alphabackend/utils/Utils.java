package com.alphabackend.utils;

import com.alphabackend.model.enum_model.NameException;

public interface Utils {

    /**
     * Concaténation d'une liste d'object type "[...]" vers un type "Object ..."
     * @param others List d'objet type "[...]"
     * @param nameException Nom de l'exception
     * @return La liste d'objet type 'Object ..."
     */
    static Object[] concatArgs(Object[] others, NameException nameException) {
        Object[] result = new Object[others.length + 1];
        result[0] = nameException.getException();
        System.arraycopy(others, 0, result, 1, others.length);
        return result;
    }
}
