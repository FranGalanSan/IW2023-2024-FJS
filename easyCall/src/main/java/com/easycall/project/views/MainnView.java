package com.easycall.project.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("EmployeeMainViewW")
public class MainnView extends AppLayout {

    public MainnView() {
        // Crear el layout y el encabezado
        HorizontalLayout layout = new HorizontalLayout();
        H1 header = new H1("Hola Mundo FUNCIONO bien");

        // Añadir el encabezado al layout
        layout.add(header);

        // Añadir el layout al contenido del AppLayout
        setContent(layout);
    }
}
