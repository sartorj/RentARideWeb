//
// Class:       Create Reservation
//
// Type:        Servlet
//
// Team 9
//
//
//


package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;




// Servlet class Create Reservation
//
// doPost starts the execution of the Create Reservation Use Case
//
//   parameters:
//
//	RL_name   string 
//	Vehicle_type   string
//  pickup      string
//  duration    string
//
public class CreateReservation
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "CreateReservation-Result.ftl";

    private Configuration  cfg;

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }

    public void doPost( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template       resultTemplate = null;
        BufferedWriter toClient = null;
        LogicLayer     logicLayer = null;
        String	       RL_name = null;
        String         Vehicle_type = null;
        String         pickup = null;
        String         duration = null;
        int	           duration_int = 0;
        long           customerId = 0;
        HttpSession    httpSession;
        Session        session;
        String         ssid;

        httpSession = req.getSession();

        ssid = (String) httpSession.getAttribute( "ssid" );

        if( ssid == null ) {       // not logged in!
            RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
            RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }

        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch (IOException e) {
            throw new ServletException( 
                    "Can't load template in: " + templateDir + ": " + e.toString());
        }

        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        toClient = new BufferedWriter(
                new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() )
                );

        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        // Get the form parameters
        //
        RL_name = req.getParameter( "RL_name" );

        if( RL_name == null ) {
            RARError.error( cfg, toClient, "Unspecified Rental location" );
            return;
        }
        
        Vehicle_type = req.getParameter( "Vehicle_type" );

        if( Vehicle_type == null ) {
            RARError.error( cfg, toClient, "Unspecified Vehicle Type" );
            return;
        }
        
        pickup = req.getParameter( "pickup" );

        if( pickup == null ) {
            RARError.error( cfg, toClient, "Unspecified pickup" );
            return;
        }
        
        duration = req.getParameter( "duration" );

        if( duration == null ) {
            RARError.error( cfg, toClient, "Unspecified duration" );
            return;
        }

        try {
            duration_int = Integer.parseInt( duration );
        }
        catch( Exception e ) {
            RARError.error( cfg, toClient, "duration should be a number and is: " + duration );
            return;
        }

        if( duration_int <= 0 ) {
            RARError.error( cfg, toClient, "Non-positive duration: " + duration );
            return;
        }
        
        User user = session.getUser();
        String name = user.getUserName();
        customerId = user.getId();
        System.out.println(name);

        try {
            logicLayer.createReservation( pickup, duration_int, RL_name, name, Vehicle_type );
        } 
        catch( Exception e ) {
            RARError.error( cfg, toClient, e );
            return;
        }

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();

        // Build the data-model
        //
        root.put( "customerName", name );
        root.put( "RL_name", RL_name );
        root.put( "Vehicle_type",  Vehicle_type );
        root.put( "pickup", pickup );
        root.put( "duration",  duration );

        // Merge the data-model and the template
        //
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();

    }
}