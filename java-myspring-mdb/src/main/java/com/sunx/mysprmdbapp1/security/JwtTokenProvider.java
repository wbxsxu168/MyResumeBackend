package com.sunx.mysprmdbapp1.security;

import com.sunx.mysprmdbapp1.errors.APIErrorType;
import com.sunx.mysprmdbapp1.errors.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${authapp.jwt_secret}")
    private String jwtSecret;

    @Value("${authapp.jwt_expiration_milliseconds}")
    private long jwtExpirationDate;
        
    //Generate JWT token
    public String generateToken(Authentication authentication){    	
        String username = authentication.getName();
        Date currentDate= new Date();
        Date expireDate	= new Date(currentDate.getTime()+jwtExpirationDate);

        String jwt_token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secretJwtKeyValue())  // add SignatureAlgorithm.HS256  & import io.jsonwebtoken.SignatureAlgorithm;
                .compact();
        return jwt_token;
    }
    
    private Key secretJwtKeyValue(){

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(this.jwtSecret)
        );  
    }

    //extract username from the parsed Jwt token
    public String getUsername(String jmt_tkn){
        Claims claims = Jwts.parser()
                .setSigningKey(secretJwtKeyValue())
                .parseClaimsJws(jmt_tkn)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    //Jwt token validation
    public boolean validateToken(String jwttoken )
    {
    	try{
            Jwts.parser()
                    .setSigningKey(secretJwtKeyValue())
                    .parse(jwttoken);    // using this? .parseClaimsJws(jwttoken)
            return true;
        } catch (MalformedJwtException ex) {
            throw new ApiException(APIErrorType.JWT_VALIDATION_ERRORS, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new ApiException(APIErrorType.JWT_EXPIRED_ERRORS, "Expired JWT token");            
        } catch (UnsupportedJwtException ex) {
            throw new ApiException(APIErrorType.JWT_UNSUPPORT_ERRORS, "This JWT token is not supported");
        } catch (IllegalArgumentException ex) {
        	 throw new ApiException(APIErrorType.JWT_CLAIMS_EMPTY_ERRORS, "The JWT claims string is empty!");
        }
    }
}

 