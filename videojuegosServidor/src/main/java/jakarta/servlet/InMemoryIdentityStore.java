    package jakarta.servlet;

    import domain.servicios.CredencialServicio;
    import jakarta.inject.Inject;
    import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
    import jakarta.security.enterprise.credential.Credential;
    import jakarta.security.enterprise.credential.RememberMeCredential;
    import jakarta.security.enterprise.identitystore.CredentialValidationResult;
    import jakarta.security.enterprise.identitystore.IdentityStore;

    import java.util.Collections;
    import java.util.HashSet;

    import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
    import static jakarta.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;


    public class InMemoryIdentityStore implements IdentityStore {

        private final CredencialServicio credencialServicio;

        @Inject
        public InMemoryIdentityStore(CredencialServicio credencialServicio) {
            this.credencialServicio = credencialServicio;
        }

        @Override
        public int priority() {
            return 10;
        }

        @Override
        public CredentialValidationResult validate(Credential credential) {


            if (credential instanceof BasicAuthenticationCredential user) {

                HashSet<String> roles = new HashSet<>();
                roles.add("Admin");
                roles.add("User");

                user.getPassword().getValue();
                return switch (user.getCaller()) {
                    case "root" -> new CredentialValidationResult("root", Collections.singleton("Admin"));
                    case "usuarioSinActivar" -> NOT_VALIDATED_RESULT;
                    default -> INVALID_RESULT;
                };

            } else if (credential instanceof RememberMeCredential jwt) {
                jwt.getToken();

            } else {
                throw new IllegalStateException("Unexpected value: " + credential);
            }
            return INVALID_RESULT;

        }

    }
