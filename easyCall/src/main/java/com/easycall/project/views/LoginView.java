package com.easycall.project.views;

import com.easycall.project.data.user.login.AuthUserService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {

    private final AuthUserService authUserService;

    public LoginView(AuthUserService authUserService) {
        this.authUserService = authUserService;

        LoginOverlay overlay = getContent();
        overlay.setTitle("EasyCall");
        overlay.setDescription("");
        overlay.setOpened(true);

        overlay.addLoginListener(loginEvent -> {
            try {
                authUserService.authenticate(loginEvent.getUsername(), loginEvent.getPassword());
                // Redirect to the main view or handle authentication success
            } catch (AuthUserService.AuthException e) {
                // Handle authentication failure (e.g., show an error message)
                overlay.setError(true);
            }
        });
    }
}
