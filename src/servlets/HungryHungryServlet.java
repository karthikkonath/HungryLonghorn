package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import services.SqlConn;

public class HungryHungryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		URL url = new URL("http://www.cs.utexas.edu/calendar");

		File datafile = new File("dataFile.txt");
		// jsoup test
		try {
			Document doc = Jsoup.connect(url.toString()).get();
			Elements links = doc.select(".view-item-calendar");
			ListIterator<Element> iter = links.listIterator();

			FileOutputStream f = new FileOutputStream(datafile);
			PrintWriter pw = new PrintWriter(f);
			int i = 0;
			SqlConn conn = new SqlConn();
			Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
			while (iter.hasNext()) {
				Element e = (Element) iter.next();
				System.out.println("///////Start//////");
				ArrayList<String> arr = new ArrayList<String>();
				String title = "" + e.select("#node-title").html();
				if (title.contains("Food") || title.contains("Dine")
						|| title.contains("Lunch") || title.contains("Freeze")
						|| title.contains("Tea") || title.contains("TGIF")
						|| title.contains("Tailgate")) {
					arr.add(title);
					System.out.println(title);
					arr.add(""
							+ e.select(
									".view-data-node-data-field-cal-location-field-cal-location-value")
									.html());
					arr.add("" + e.select(".date-display-start").html() + " - "
							+ e.select(".date-display-end").html());
					map.put(i, arr);
					i++;
				}
				System.out.println("///////End////////");
				pw.print(e);
			}
			conn.addEvent(map);

			System.out.println("len I = " + i);
			pw.flush();
			pw.close();
			// FileInputStream iStream = new FileInputStream(datafile);
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// iStream));
			// String line;
			// // //response.getWriter().write("<h2>" + "Links " + "</h2>");
			// // while ((line = br.readLine()) != null) {
			// // if(line.contains("free") || line.contains("TGIF") ||
			// line.contains("food")||line.contains("Pizza") ||
			// line.contains("Dine"))
			// // response.getWriter().write("<h3>" + line + "</h3>");
			// // }
			// br.close();

		} catch (Exception exp) {
			response.getWriter().println(
					"<h3>" + "Link not valid. Please enter a valid URL."
							+ "</h3>");
			exp.printStackTrace();
		}

	}
	protected void doPostFullUni(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		URL url = new URL("http://calendar.utexas.edu/calendar/week");

		File datafile = new File("dataFile.txt");
		// jsoup test
		try {
			Document doc = Jsoup.connect(url.toString()).get();
			Elements links = doc.select("div.date_divider:nth-of-type(1) h2, span.summary a, h4.description, a.event_item_venue");
			ListIterator<Element> iter = links.listIterator();

			FileOutputStream f = new FileOutputStream(datafile);
			PrintWriter pw = new PrintWriter(f);
			int i = 0;
			SqlConn conn = new SqlConn();
			Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
			while (iter.hasNext()) {
				Element e = (Element) iter.next();
				System.out.println("///////Start//////");
				ArrayList<String> arr = new ArrayList<String>();
				String title = "" + e.select("#description").html();
				if (title.contains("Food") || title.contains("Dine")
						|| title.contains("Lunch") || title.contains("Freeze")
						|| title.contains("Tea") || title.contains("TGIF")
						|| title.contains("Tailgate")) {
					arr.add(title);
					System.out.println(title);
					arr.add(""
							+ e.select(
									".view-data-node-data-field-cal-location-field-cal-location-value")
									.html());
					arr.add("" + e.select(".date-display-start").html() + " - "
							+ e.select(".date-display-end").html());
					map.put(i, arr);
					i++;
				}
				System.out.println("///////End////////");
				pw.print(e);
			}
			conn.addEvent(map);

			System.out.println("len I = " + i);
			pw.flush();
			pw.close();
			// FileInputStream iStream = new FileInputStream(datafile);
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// iStream));
			// String line;
			// // //response.getWriter().write("<h2>" + "Links " + "</h2>");
			// // while ((line = br.readLine()) != null) {
			// // if(line.contains("free") || line.contains("TGIF") ||
			// line.contains("food")||line.contains("Pizza") ||
			// line.contains("Dine"))
			// // response.getWriter().write("<h3>" + line + "</h3>");
			// // }
			// br.close();

		} catch (Exception exp) {
			response.getWriter().println(
					"<h3>" + "Link not valid. Please enter a valid URL."
							+ "</h3>");
			exp.printStackTrace();
		}

	}
}
