package com.thaiduong.myapplication.login_register.presenter;

import com.thaiduong.myapplication.login_register.model.AppAccount;

public class RegisterPresenter {
    private final IRegisterPresenter iRegisterPresenter;

    public RegisterPresenter(IRegisterPresenter iRegisterPresenter) {
        this.iRegisterPresenter = iRegisterPresenter;
    }
    public void registerCheck(AppAccount account) {
        for (Boolean b :
                account.getValidate()) {
            if (!b) {
                iRegisterPresenter.failedRegister();
                return;
            }
        }
        iRegisterPresenter.successRegister();
    }
}
