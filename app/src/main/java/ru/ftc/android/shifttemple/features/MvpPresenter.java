package ru.ftc.android.shifttemple.features;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:07
 */

public class MvpPresenter<View extends MvpView> {

    protected View view;

    public void attachView(View view) {
        this.view = view;
        onViewReady();
    }

    public void detachView() {
        view = null;
    }

    protected void onViewReady() {
        // override if need
    }
}
