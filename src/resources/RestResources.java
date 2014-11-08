package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.StreamingOutput;

import services.SqlConn;

@ApplicationPath("/resources")
public class RestResources extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public RestResources() {
		singletons.add(new InnerResource(this));
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
	@Path("/sqlData")
	public class InnerResource {
		RestResources myResource;
		SqlConn connService;
		public InnerResource(RestResources resource){
			myResource = resource;
		}
		

		@GET
		@Path("/projects")
		@Produces("application/json")
		public String getEvents() throws SQLException {


			final HashMap<Integer, ArrayList<String>> dataMap = connService.getEvents();
			StringBuilder json = new StringBuilder();
			json.append("\"events\": [");
			Set<Integer> keySet = dataMap.keySet();
			for (int i : keySet){
				ArrayList<String> arr = dataMap.get(i);
				EventListing eL = new EventListing(arr.get(0), arr.get(1), arr.get(2));
				json.append("{" + eL + "}");
			}
			json.append("]");
			

			return json.toString();
		}
		
//		protected void outputUnion(OutputStream os, HashMap<Integer, ArrayList<String>> dataMap)
//				throws IOException {
//			PrintStream writer = new PrintStream(os);
//			//rework to fit datapoints
//			Set<Integer> keySet = dataMap.keySet();
//			writer.println("<events>");
//			for (int i : keySet){
//				writer.println("<event>");
//				for (String s : dataMap.get(i)) {
//					writer.println("<datapoint>" + s + "</datapoint>");
//				}
//	
//				writer.println("</event>");
//			}
//			writer.println("</events>");
//			writer.flush();
//			writer.close();
//		}
		
		
	}
}
