package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserService;
import com.easycall.project.usoDatos.UsoDatosService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("UserDatosView")
public class UserDatosView extends VerticalLayout {

    private final UserService userService;
    private final UsoDatosService usoDatosService;
    private User currentUser;

    private TextField consumoDatosHoyField;
    private TextField consumoTotalMesField;
    private Binder<User> binder;

    public UserDatosView(UserService userService, UsoDatosService usoDatosService) {
        this.userService = userService;
        this.usoDatosService = usoDatosService;

        currentUser = getCurrentUser();
        binder = new Binder<>(User.class);
        if (currentUser != null) {
            initializeView();
        } else {
            add(new Span("Usuario no encontrado o sesión no iniciada."));
        }
    }

    private void initializeView() {
        Button buttonx = new Button("Volver a Home", event -> UI.getCurrent().navigate("UserMainView"));
        Button buttonx1=new Button("SMS", event -> UI.getCurrent().navigate("UserSmsView"));
        Button buttonx2=new Button("Llamadas", event -> UI.getCurrent().navigate("UserCallsDesglose"));

        add(new H1("Bienvenido! Estos Son Tus Datos De Usuario"),buttonx,buttonx1,buttonx2);
        displayUserData();
        setupEditForm();
        setupUsoDatosFields();
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void displayUserData() {
        add(new Span("Nombre: " + currentUser.getFirstName() + " " + currentUser.getLastName()));
        add(new Span("Nombre de usuario: " + currentUser.getUsername()));
        add(new Span("Correo electrónico: " + currentUser.getEmail()));
        add(new Span("Dirección: " + currentUser.getAddress()));
        add(new Span("Teléfono: " + currentUser.getPhone()));
        add(new Span("Fecha de nacimiento: " + currentUser.getDateOfBirth().toString()));
    }

    private void setupEditForm() {
        TextField firstNameField = new TextField("Nombre", currentUser.getFirstName());
        TextField lastNameField = new TextField("Apellido", currentUser.getLastName());
        TextField addressField = new TextField("Dirección", currentUser.getAddress());
        TextField phoneField = new TextField("Teléfono", currentUser.getPhone());

        binder.forField(phoneField)
                .withValidator(new RegexpValidator("El número de teléfono no es válido", "\\d{3,15}"))
                .bind(User::getPhone, User::setPhone);

        Button updateButton = new Button("Actualizar Datos");
        updateButton.setEnabled(false);

        binder.addStatusChangeListener(event -> updateButton.setEnabled(binder.isValid()));

        updateButton.addClickListener(event -> {
            if (binder.writeBeanIfValid(currentUser)) {
                boolean updated = userService.updateUserData(
                        currentUser,
                        firstNameField.getValue(),
                        lastNameField.getValue(),
                        addressField.getValue(),
                        phoneField.getValue()
                );
                if (updated) {
                    Notification.show("Datos actualizados con éxito");
                    removeAll();
                    initializeView(); // Recargar la vista completa
                } else {
                    Notification.show("Error: El correo electrónico o el teléfono ya están en uso", 3000, Notification.Position.MIDDLE);
                }
            }
        });

        add(firstNameField, lastNameField, addressField, phoneField, updateButton);
    }

    private void setupUsoDatosFields() {
        consumoDatosHoyField = new TextField("Consumo de Datos Hoy (MB)");
        consumoTotalMesField = new TextField("Consumo Total del Mes (MB)");

        actualizarConsumoDatos();

        consumoDatosHoyField.setReadOnly(true);
        consumoTotalMesField.setReadOnly(true);

        add(consumoDatosHoyField, consumoTotalMesField);
    }

    private void actualizarConsumoDatos() {
        int consumoHoy = usoDatosService.obtenerConsumoDatosHoy(currentUser);
        consumoDatosHoyField.setValue(consumoHoy + " MB");

        int consumoMes = usoDatosService.obtenerConsumoDatosMes(currentUser);
        consumoTotalMesField.setValue(consumoMes + " MB");
    }
}
