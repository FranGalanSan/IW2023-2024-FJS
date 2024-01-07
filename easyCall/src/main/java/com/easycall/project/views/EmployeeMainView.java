package com.easycall.project.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("employee-main-view")
@PageTitle("EmployeeMainViewW")
public class EmployeeMainView extends VerticalLayout {

    public EmployeeMainView() {
        Button buttonToComplaints = new Button("Ver Quejas", event -> {
            UI.getCurrent().navigate(EmployeeComplaintsView.class);
        });

        add(buttonToComplaints);
    }


}
