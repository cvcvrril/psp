package cliente.ui.pantallas.login;


import cliente.domain.usecases.LoginUseCase;
import domain.errores.ApiError;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class LoginViewModel {

    private final LoginUseCase loginUseCase;
    private final ObjectProperty<LoginState> _state;

    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        _state = new SimpleObjectProperty<>(new LoginState(null, null));
    }

    public void login(String username, String password) {
        loginUseCase.login(username, password)
                .subscribe(res -> {
                            if (res.isLeft()) {
                                _state.setValue(new LoginState(null, new ApiError("Error al hacer login", LocalDateTime.now())));
                            } else {
                                _state.setValue(new LoginState(res.get(), null));
                            }
                        }
                );
    }


    public ReadOnlyObjectProperty<LoginState> getState() {
        return _state;
    }


}
