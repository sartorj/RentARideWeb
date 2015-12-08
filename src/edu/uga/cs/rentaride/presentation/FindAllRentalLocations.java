// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	FindAllRentalLocations
//
// Type:	Servlet
//
// Lauren Clapper
//
//
//

package edu.uga.cs.rentaride.presentation;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


// Boundary class FindAllRentalLocations (servlet)
//
// doGet starts the execution of the List All Rental Locations Use Case
//
//   parameters:
//
//	none
//
public class FindAllRentalLocations

extends HttpServlet 

{
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindAllRentalLocations-Result.ftl";

    private Configuration     cfg;

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }

    public void doGet( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template                resultTemplate = null;
        BufferedWriter          toClient = null;
        LogicLayer              logicLayer = null;
        List<RentalLocation>    rv = null;
        List<List<Object>>      rentalLocations = null;
        List<Object>            rentalLocation = null;
        RentalLocation   	    c  = null;
        HttpSession             httpSession;
        Session                 session;
        String                  ssid;

        
        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch( IOException e ) {
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
        
        httpSession = req.getSession();
        if( httpSession == null ) {       // assume not logged in!
            RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
        
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
        
        // Get the servlet parameters
        //
        // No parameters here

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();
        
        try {
            rv = logicLayer.findAllRentalLocations();

            // Build the data-model
            //
            rentalLocations = new LinkedList<List<Object>>();
            root.put( "rentalLocations", rentalLocations );

            System.out.println("!!!!!! RV SIZE:" + rv.size());
            
            for( int i = 0; i < rv.size(); i++ ) {
            	
                c = (RentalLocation) rv.get( i );
                rentalLocation = new LinkedList<Object>();
                rentalLocation.add( c.getName() );
                rentalLocation.add( c.getAddress() );
                rentalLocation.add( c.getCapacity() );
                rentalLocations.add( rentalLocation );
            }
        } 
        catch( Exception e) {
            RARError.error( cfg, toClient, e );
            return;
        }

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
