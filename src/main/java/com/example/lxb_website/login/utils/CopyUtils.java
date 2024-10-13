package com.example.lxb_website.login.utils;

import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CopyUtils {
    /**
     * Creates a deep copy of an object using serialization.
     *
     * @param <T> the type of the object
     * @param object the object to copy
     * @return a deep copy of the object, or null if the object is not serializable
     */
    public static <T extends Serializable> T deepCopy(T object) {
        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOutStream);
            out.writeObject(object);

            ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteOutStream.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteInStream);
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a shallow copy of a list.
     *
     * @param <T> the type of the list elements
     * @param list the list to copy
     * @return a shallow copy of the list
     */
    public static <T> List<T> shallowCopy(List<T> list) {
        return new ArrayList<>(list);
    }

    /**
     * Copies an array.
     *
     * @param <T> the type of the array elements
     * @param array the array to copy
     * @return a copy of the array
     */
    public static <T> T[] copyArray(T[] array) {
        return array.clone();
    }

    /**
     * Copies a 2D array.
     *
     * @param <T> the type of the array elements
     * @param array the 2D array to copy
     * @return a copy of the 2D array
     */
    public static <T> T[][] copy2DArray(T[][] array) {
        T[][] copy = array.clone();
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }
}
