package com.easycall.project.views;


import com.easycall.project.options.Options;
import com.easycall.project.options.OptionsService;
import com.easycall.project.data.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("CreateOptionsView")
@PageTitle("Create Options")
public class CreateOptionsView extends VerticalLayout {

    private final OptionsService optionsService;
    private Button createOptionsButton;

    public CreateOptionsView(OptionsService optionsService) {
        this.optionsService = optionsService;

        createOptionsButton = new Button("Create Options", event -> createOptions());

        add(new H1("Create Options for Current User, Esta Pagina Es Solo Para Poblar La Base De Datos"), createOptionsButton);
    }

    private void createOptions() {
        User currentUser = VaadinSession.getCurrent().getAttribute(User.class);

        if (currentUser != null) {
            Options options = new Options();

            optionsService.createOrUpdateOptions(currentUser, options);
        } else {

        }
    }
}
