package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entity.Role;
import entity.User;
import entity.UserFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import exceptions.AuthenticationException;
import exceptions.GenericExceptionMapper;
import java.util.ArrayList;

@Path("login")
public class LoginEndpoint {
Gson gson;
  public static final int TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30 min

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(String jsonString) throws AuthenticationException {

    JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
    String username = json.get("username").getAsString();
    String password = json.get("password").getAsString();

    //Todo refactor into facade
    try {
      User user = UserFacade.getInstance().getVeryfiedUser(username, password);
      String token = createToken(username, user.getRolesAsStrings());
      JsonObject responseJson = new JsonObject();
      responseJson.addProperty("username", username);
      responseJson.addProperty("token", token);
      return Response.ok(new Gson().toJson(responseJson)).build();

    } catch (Exception ex) {
      if (ex instanceof AuthenticationException) {
        throw (AuthenticationException) ex;
      }
      Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
    }
    throw new AuthenticationException("Invalid username or password! Please try again");
  }

  public static String createToken(String userName, List<String> roles) throws JOSEException {

    StringBuilder res = new StringBuilder();
    for (String string : roles) {
      res.append(string);
      res.append(",");
    }
    String rolesAsString = res.length() > 0 ? res.substring(0, res.length() - 1) : "";
    String issuer = "semesterdemo_security_course";

    JWSSigner signer = new MACSigner(SharedSecret.getSharedKey());
    Date date = new Date();
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(userName)
            .claim("username", userName)
            .claim("roles", rolesAsString)
            .claim("issuer", issuer)
            .issueTime(date)
            .expirationTime(new Date(date.getTime() + TOKEN_EXPIRE_TIME))
            .build();
    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
    signedJWT.sign(signer);
    return signedJWT.serialize();

  }
  
    public static void main(String[] args) throws JOSEException {
        LoginEndpoint l = new LoginEndpoint();
        Role role = new Role("user");
        List<String> roles = new ArrayList();
        roles.add("user");
        System.out.println(createToken("user", roles));
        
    }
    
}
