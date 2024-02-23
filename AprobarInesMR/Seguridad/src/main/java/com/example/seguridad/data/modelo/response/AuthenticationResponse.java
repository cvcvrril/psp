package com.example.seguridad.data.modelo.response;

import com.example.seguridad.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty(Constantes.ACCESS_TOKEN)
  private String accessToken;
  @JsonProperty(Constantes.REFRESH_TOKEN)
  private String refreshToken;
}
