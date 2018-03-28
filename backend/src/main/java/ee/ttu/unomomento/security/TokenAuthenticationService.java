package ee.ttu.unomomento.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    private static final String SECRET = "te7QNhTSQau4BDjG2zqUmPaDtNxvdmwI";
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24;  // 1 day
    static final long REFRESH_TIME = 1000 * 60 * 60 * 24 * 14;  // 14 days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String JWT = createToken(username);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        res.getWriter().write("{\"token\":\"" + JWT + "\"}");
    }

    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;

        // parse the token.
        String user = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .getBody()
            .getSubject();

        return user != null ?
            new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
            null;
    }

    public static void refreshToken(HttpServletRequest req, HttpServletResponse res, String username) throws IOException {
        String token = req.getHeader(HEADER_STRING);
        if (token == null) return;

        Date expiration = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .getBody()
            .getExpiration();

        // If token is not expired
        if (new Date(System.currentTimeMillis()).before(expiration)) return;
        // If token is expired and from last request there was interval bigger than REFRESH_TIME
        if (expiration.after(new Date(System.currentTimeMillis() + REFRESH_TIME))) return;
        // Refresh token if above conditions not passed
        addAuthentication(res, username);
    }
}