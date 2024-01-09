package com.easycall.project.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("UserMainViewW")
public class UserMainView extends AppLayout{
    public UserMainView() {
        // Crear el layout y el encabezado
        HorizontalLayout layout = new HorizontalLayout();
        H1 header = new H1("aqui ponemos 2 links uno a login y otro a register");

        // Añadir el encabezado al layout
        layout.add(header);

        // Añadir el layout al contenido del AppLayout
        setContent(layout);
    }



}
