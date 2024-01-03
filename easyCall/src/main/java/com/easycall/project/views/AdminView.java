package com.easycall.project.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


import javax.annotation.security.RolesAllowed;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView() {
        add(
                new H1("Admin view")
        );
    }
}