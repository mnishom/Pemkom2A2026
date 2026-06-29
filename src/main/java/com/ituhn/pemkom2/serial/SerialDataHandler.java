package com.ituhn.pemkom2.serial;

/**
 *
 * @author mnish
 * @param <T>
 */
public interface SerialDataHandler<T> {
    void onDataReceived(T data);
}
