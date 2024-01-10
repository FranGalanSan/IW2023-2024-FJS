package com.easycall.project.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "UserMainView")
@PageTitle("UserMainViewW")
public class UserMainView extends AppLayout {
    public UserMainView() {

        H1 header = new H1("Donde Quieres Ir");


        Button button2 = new Button("Desea Cerrar Sesion", event -> UI.getCurrent().navigate("UserLogoutView"));
        Button button4 = new Button("Datos de Usuario", event -> UI.getCurrent().navigate("UserDatosView"));
        Button button6 = new Button("Quejas", event -> UI.getCurrent().navigate("UserCreaComplaint"));
        Button button7 = new Button("Contrata Servicios", event -> UI.getCurrent().navigate("UserContrataServiceView"));
        Button button5 = new Button("Dar Baja Servicios", event -> UI.getCurrent().navigate("UserDarseDeBajaView"));
        Button button3 = new Button("Facturas", event -> UI.getCurrent().navigate("UserFacturaView"));
        Button button0 = new Button("Opciones", event -> UI.getCurrent().navigate("OptionsView"));


        VerticalLayout layout = new VerticalLayout();
        layout.add(header, button2, button4, button6, button7, button5, button3, button0);


        setContent(layout);
    }
}
