package security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenProvider {
    private static final byte[] keyBytes = Decoders.BASE64.decode("a29hcGRza2dvcGtlcmdma3BvZXJha2Zwa2Fld29ma29yZWt0YmpncHJlZnBva2FlcG9ma2V3cnBhZnNk");
    public static final Key key = Keys.hmacShaKeyFor(keyBytes);
}
