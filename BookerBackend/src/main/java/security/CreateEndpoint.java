/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import entity.User;
import entity.UserFacade;
import exceptions.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kristian
 */
@Path("create")
public class CreateEndpoint
{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String json) throws AuthenticationException, JOSEException
    {
        JsonObject get_json = new JsonParser().parse(json).getAsJsonObject();
        String username = get_json.get("username").getAsString();
        String password = get_json.get("password").getAsString();

        System.out.println(json);
        UserFacade uf = new UserFacade();
        uf.CreateUser(new User(username, password));
        User user = UserFacade.getInstance().getVeryfiedUser(username, password);
        String token = LoginEndpoint.createToken(username, user.getRolesAsStrings());
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("username", username);
        responseJson.addProperty("token", token);
        return Response.ok(new Gson().toJson(responseJson)).build();
    }
}
