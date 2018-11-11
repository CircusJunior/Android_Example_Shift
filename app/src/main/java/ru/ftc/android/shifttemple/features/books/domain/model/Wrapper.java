package ru.ftc.android.shifttemple.features.books.domain.model;

/**
 * Created: samokryl
 * Date: 03.07.18
 * Time: 15:28
 */

public class Wrapper<T> {

    private String status;
    private String message;
    private T data;

    public Wrapper(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
