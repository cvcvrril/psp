package jakarta.servlet;

import jakarta.servlet.annotation.WebFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@WebFilter("regex(/api/((?!login).)*)")
public class TokenFilter {
}
