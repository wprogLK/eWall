package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
@With(Secure.class)
public class Application extends Controller {

    public static void index() 
    {
    	String username = Secure.Security.connected();
    	User user = User.find("byUsername", username).first();
        render(user);
    }

}