package com.easycall.project.employee.login;

import com.easycall.project.employee.login.AuthEmployeeService;
import com.easycall.project.views.MainView;
import com.easycall.project.views.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import jdk.jfr.Label;

@Route(value = "loginEmployee")
@PageTitle("LoginEmployee")

public class LoginView extends Div {

    public LoginView(AuthEmployeeService authEmployeeService) {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        add(
                new H1("Welcome"),
                username,
                password,
                new Button("Login", event -> {
                    try {
                        authEmployeeService.authenticate(username.getValue(), password.getValue());
                        UI.getCurrent().navigate("EmployeeMainView");
                    } catch (AuthEmployeeService.AuthException e) {
                        Notification.show("Wrong credentials.");
                    }
                })
                //,new RouterLink("Register", RegisterView.class)
        );
    }

}