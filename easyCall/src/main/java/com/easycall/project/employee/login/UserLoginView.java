package com.easycall.project.employee.login;

import com.easycall.project.data.user.UserService;
import com.easycall.project.employee.login.AuthUserService;
//import com.easycall.project.views.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route(value = "UserLoginView")
@PageTitle("UserLoginView")
public class UserLoginView extends Div{

    public UserLoginView(AuthUserService authUserService) {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        add(
                new H1("Welcome"),
                username,
                password,
                new Button("Login", event -> {
                    try {
                        authUserService.authenticate(username.getValue(), password.getValue());
                        UI.getCurrent().navigate("UserFacturaView");
                    } catch (AuthUserService.AuthException e) {
                        Notification.show("Wrong credentials.");
                    }
                })
                //new RouterLink("Register", RegisterView.class)
        );
    }



}
