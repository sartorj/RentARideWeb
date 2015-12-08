// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	FindAllVehicles
//
// Type:	Servlet
//
// Brad Fuster
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

import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


// Boundary class FindAllVehicles (servlet)
//
// doGet starts the execution of the List All Vehicles Use Case
//
//   parameters:
//
//	none
//
public class FindAllVehicles

extends HttpServlet 

{
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindAllVehicles-Result.ftl";

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
        List<Vehicle>           vv = null;
        List<List<Object>>      vehicles = null;
        List<Object>            vehicle = null;
        Vehicle   	            v  = null;
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
            vv = logicLayer.FindAllVehicles();

            // Build the data-model
            //
            vehicles = new LinkedList<List<Object>>();
            root.put( "vehicles", vehicles );

            for( int i = 0; i < vv.size(); i++ ) {
                v = (Vehicle) vv.get( i );
                vehicle = new LinkedList<Object>();
                vehicle.add( v.getMake() );
                vehicle.add( v.getModel() );
                vehicle.add( v.getYear() );
                vehicle.add( v.getRegistrationTag() );
                vehicle.add( v.getMileage() );
                vehicle.add( v.getLastServiced() );
                vehicle.add( v.getStatus() );
                vehicle.add( v.getCondition() );
                vehicle.add( v.getVehicleType().getType() );
                vehicle.add( v.getRentalLocation().getName() );
                vehicles.add( vehicle );
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