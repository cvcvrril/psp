package cliente.ui.pantallas.login;


import cliente.domain.usecases.LoginUseCase;
import cliente.domain.usecases.RegistroUseCase;
import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class LoginViewModel {

    private final LoginUseCase loginUseCase;
    private final RegistroUseCase registroUseCase;
    private final ObjectProperty<LoginState> _state;

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase, RegistroUseCase registroUseCase) {
        this.loginUseCase = loginUseCase;
        this.registroUseCase = registroUseCase;
        _state = new SimpleObjectProperty<>(new LoginState(null, null, false));
    }

    public void login(String username, String password) {
        loginUseCase.login(username, password)
                .observeOn(Schedulers.single())
                .subscribe(res -> {
                            if (res.isLeft()) {
                                _state.setValue(new LoginState(null, new ApiError("Error al hacer login", LocalDateTime.now()),false));
                            } else {
                                _state.setValue(new LoginState(res.get(), null, false));
                            }
                        }
                );
    }

    public void registro(Usuario usuario) {
        registroUseCase.add(usuario)
                .subscribe(res ->{
                    if (res.isLeft()){
                        _state.setValue(new LoginState(null, new ApiError("Error al crear el usuario", LocalDateTime.now()),false));
                    }else {
                        _state.setValue(new LoginState(usuario, null, true));
                    }
                });

    }


    public ReadOnlyObjectProperty<LoginState> getState() {
        return _state;
    }

    public void reset() {
        _state.setValue(new LoginState(null, null, false));
    }
}
