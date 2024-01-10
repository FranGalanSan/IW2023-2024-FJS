package com.easycall.project.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="")
@PageTitle("EmployeeMainViewW")
public class MainnView extends AppLayout {

    public MainnView() {

        H1 header = new H1("Bienvenidos a IWeasycall, llamar nunca ha sido tan fácil");
        addToNavbar(header);


        VerticalLayout layoutLeft = new VerticalLayout();
        VerticalLayout layoutRight = new VerticalLayout();


        Span message1 = new Span("Si ya tienes una cuenta con nosotros no dudes en");
        Span message2 = new Span("Si te lo estas pensando, deja de pensar y ");
        Span message3 = new Span("Si eres un empleado");
        layoutLeft.add(message1,message2,message3);


        Button button1 = new Button("Iniciar Sesion", event -> UI.getCurrent().navigate("UserLoginView"));
        Button button2 = new Button("Registrate", event -> UI.getCurrent().navigate("UserRegisterView"));
        Button button3 = new Button("Comienza el trabajo", event -> UI.getCurrent().navigate("loginEmployee"));

        layoutRight.add(button1, button2, button3);


        HorizontalLayout mainLayout = new HorizontalLayout(layoutLeft, layoutRight);


        setContent(mainLayout);
    }
}
