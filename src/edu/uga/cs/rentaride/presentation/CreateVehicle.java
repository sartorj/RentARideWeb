//
// Class:       CreateVehicle
//
// Type:        Servlet
//
// Brad Fuster
//
//
//


package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


// Servlet class CreateVehicle
//
// doPost starts the execution of the Create Vehicle Use Case
//
//   parameters:
//
//	name    string
//	address string
//  capacity   int
//
public class CreateVehicle
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "CreateVehicle-Result.ftl";

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

    public void doPost( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template       resultTemplate = null;
        BufferedWriter toClient = null;
        String      registrationTag;
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date        lastService = null;
        int         mileage;
        String      model;
        String      make;
        String      rentalLocation_str;
        RentalLocation rentalLocation;
        String      vehicleStatus_str;
        VehicleStatus status;
        String      vehicleType_str;
        VehicleType vehicleType;
        int         vehicleYear;
        String      vehicleCondition_str;
        VehicleCondition vehicleCondition;
        long	    vehicle_id = 0;
        LogicLayer     logicLayer = null;
        HttpSession    httpSession;
        Session        session;
        String         ssid;

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

        // Get the form parameters
        //
        registrationTag = req.getParameter( "registrationTag" );
		//try {
		//	lastService     = sdf.parse(req.getParameter("lastService"));
		//} catch (ParseException e1) {
		//	e1.printStackTrace();
		//}
        
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
		try {
			date = format.parse(req.getParameter("lastService"));
			lastService = date;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        mileage         = Integer.parseInt(req.getParameter( "mileage" ));
        model           = req.getParameter( "model" );
        make            = req.getParameter( "make" );
        rentalLocation_str  = req.getParameter( "rentalLocation" );
        vehicleStatus_str          = req.getParameter( "status" );
        vehicleType_str     = req.getParameter( "vehicleType" );
        vehicleYear     = Integer.parseInt(req.getParameter( "vehicleYear" ));
        vehicleCondition_str = req.getParameter( "vehicleCondition" );


        if( make == null || model == null) {
            RARError.error( cfg, toClient, "Unspecified make or model" );
            return;
        }

        if( mileage < 0 ) {
            RARError.error( cfg, toClient, "Can't have negative mileage:"  + mileage);
            return;
        }


        if( vehicleYear <= 1980 ) {
            RARError.error( cfg, toClient, "Are we really renting cars this old: " + vehicleYear + "?" );
            return;
        }

        try {
            
            
            vehicle_id = logicLayer.createVehicle( registrationTag, lastService, make, mileage, model, rentalLocation_str, vehicleStatus_str, vehicleType_str, vehicleYear, vehicleCondition_str  );
        } 
        catch ( Exception e ) {
            RARError.error( cfg, toClient, e );
            return;
        }

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();

        // Build the data-model
        //
        root.put( "make", make );
        root.put( "model", model );
        root.put( "registrationTag", registrationTag                                                                             );
        root.put( "vehicle_id", new Long( vehicle_id ) );

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
