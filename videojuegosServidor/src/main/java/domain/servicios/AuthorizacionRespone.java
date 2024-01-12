package domain.servicios;

import lombok.Data;

@Data
public class AuthorizacionRespone {

    private final String accessToken;
    private final String refreshToken;
}
