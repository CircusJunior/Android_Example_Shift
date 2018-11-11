package ru.ftc.android.shifttemple.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ftc.android.shifttemple.exception.EmptyBodyException;
import ru.ftc.android.shifttemple.features.books.domain.model.Wrapper;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 23:55
 */


public final class DefaultCallback<T> implements Callback<Wrapper<T>> {

    private final Carry<T> carry;

    public DefaultCallback(final Carry<T> carry) {
        this.carry = carry;
    }

    @Override
    public void onResponse(Call<Wrapper<T>> call, Response<Wrapper<T>> response) {
        Wrapper<T> wrapper = response.body();
        if (wrapper != null) {
            carry.onSuccess(wrapper.getData());
        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void onFailure(Call<Wrapper<T>> call, Throwable t) {
        carry.onFailure(t);
    }

}