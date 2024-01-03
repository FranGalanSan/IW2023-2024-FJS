package com.easycall.project.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView() {
        LoginOverlay overlay = getContent();
        overlay.setTitle("EasyCall");
        overlay.setDescription("");
        overlay.setOpened(true);

        overlay.addLoginListener(loginEvent -> {

        });
    }
}
