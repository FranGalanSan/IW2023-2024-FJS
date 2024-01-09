package com.easycall.project.views;

import com.easycall.project.options.Options;
import com.easycall.project.options.OptionsService;
import com.easycall.project.data.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("OptionsView")
@PageTitle("User Options")
public class OptionsView extends VerticalLayout {

    private final OptionsService optionsService;
    private Options userOptions;
    private User currentUser;

    private Button roamingOnButton;
    private Button roamingOffButton;
    private Span roamingStatus;
    private Button block905OnButton;
    private Button block905OffButton;
    private Span block905Status;
    private TextField addNumberField;
    private Button addButton;
    private ListBox<String> blockedNumbersList;
    private Button removeButton;

    public OptionsView(OptionsService optionsService) {
        this.optionsService = optionsService;
        currentUser = VaadinSession.getCurrent().getAttribute(User.class);
        userOptions = currentUser != null ? currentUser.getOptions() : new Options();

        setupUI();
        bindDataToFields();
    }

    private void setupUI() {
        add(new H1("Options for Current User"));

        roamingOnButton = new Button("Turn On Roaming", e -> setRoaming(true));
        roamingOffButton = new Button("Turn Off Roaming", e -> setRoaming(false));
        roamingStatus = new Span();

        block905OnButton = new Button("Block 905 Numbers", e -> setBlock905(true));
        block905OffButton = new Button("Unblock 905 Numbers", e -> setBlock905(false));
        block905Status = new Span();

        addNumberField = new TextField("Block Number");
        addNumberField.setPlaceholder("Enter phone number");
        addNumberField.setPattern("[0-9]*");  // Allows only numbers
        addNumberField.addBlurListener(e -> validatePhoneNumber());

        addButton = new Button("Add Number", e -> addNumber());
        blockedNumbersList = new ListBox<>();
        removeButton = new Button("Remove Selected", e -> removeNumber());

        HorizontalLayout roamingLayout = new HorizontalLayout(roamingOnButton, roamingOffButton, roamingStatus);
        HorizontalLayout block905Layout = new HorizontalLayout(block905OnButton, block905OffButton, block905Status);
        HorizontalLayout blockNumberLayout = new HorizontalLayout(addNumberField, addButton, blockedNumbersList, removeButton);

        add(roamingLayout, block905Layout, blockNumberLayout);
    }

    private void bindDataToFields() {
        updateRoamingStatus();
        updateBlock905Status();
        blockedNumbersList.setItems(userOptions.getNumerosBloqueados());
    }

    private void setRoaming(boolean status) {
        userOptions.setRoaming(status);
        optionsService.createOrUpdateOptions(currentUser, userOptions);
        updateRoamingStatus();
    }

    private void setBlock905(boolean status) {
        userOptions.setBloquearNumeros905(status);
        optionsService.createOrUpdateOptions(currentUser, userOptions);
        updateBlock905Status();
    }

    private void updateRoamingStatus() {
        roamingStatus.setText("Roaming is " + (userOptions.isRoaming() ? "ON" : "OFF"));
    }

    private void updateBlock905Status() {
        block905Status.setText("905 Blocking is " + (userOptions.isBloquearNumeros905() ? "ON" : "OFF"));
    }

    private void addNumber() {
        if (validatePhoneNumber()) {
            userOptions.getNumerosBloqueados().add(addNumberField.getValue());
            optionsService.createOrUpdateOptions(currentUser, userOptions);
            blockedNumbersList.setItems(userOptions.getNumerosBloqueados());
        }
    }

    private void removeNumber() {
        String selected = blockedNumbersList.getValue();
        if (selected != null) {
            userOptions.getNumerosBloqueados().remove(selected);
            optionsService.createOrUpdateOptions(currentUser, userOptions);
            blockedNumbersList.setItems(userOptions.getNumerosBloqueados());
        }
    }

    private boolean validatePhoneNumber() {
        if (!addNumberField.getValue().matches("\\d{7,15}")) { // Ejemplo de patrón para números de teléfono
            addNumberField.setInvalid(true);
            addNumberField.setErrorMessage("Invalid phone number");
            return false;
        } else {
            addNumberField.setInvalid(false);
            return true;
        }
    }
}
