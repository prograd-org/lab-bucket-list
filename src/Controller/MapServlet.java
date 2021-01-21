package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.TouristPlace;
import service.MapOperations;


@WebServlet(urlPatterns= {"/map"})
public class MapServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	MapOperations mapop=new MapOperations();
	HashMap<String,TouristPlace> map=new HashMap<String,TouristPlace>();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String destination = request.getParameter("travel");
		String rank = request.getParameter("rank");
		String add = request.getParameter("add");
		
		String sortRandom = request.getParameter("sortrandomly");
		String sortInEntryOrder = request.getParameter("sortinentryorder");
		String sortAlphabetically = request.getParameter("sortalphabetically");
		String remove = request.getParameter("delete");
		String reset = request.getParameter("reset");
		
		TouristPlace tp=new TouristPlace(name,destination,rank);
		
			
		if(add!=null) {
			// call the add method and store the return value in a map variable
			map=mapop.add(tp);
			
			request.setAttribute("bucketList", map);
			request.setAttribute("message", "user added successfully");
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}

		if(remove!=null) {
			// call the remove method and store the return value in a map variable
			map=mapop.remove(tp);
			
			request.setAttribute("bucketList", map);
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}

		
		if(sortRandom!=null) {
			// call the sortRandomly method and store the return value in a map variable
			map=mapop.sortRandomly(map);
			
			request.setAttribute("bucketList",map);
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}

		if(sortInEntryOrder!=null) {
			// call the sortInEntryOrder and store the return value in a map variable
			map=mapop.sortInEntryOrder(map);
			
			request.setAttribute("bucketList",map);
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}
		if(sortAlphabetically!=null) {
			// call the sort Alphabetically and store the return value in a map variable
			TreeMap<String,TouristPlace> map1=mapop.sortAlphabetically(map);
			
			request.setAttribute("bucketList",map1);
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}

		if(reset!=null) {	
			// call the reset method and store the return value in a map variable
			map=mapop.reset(map);
			
			request.setAttribute("bucketList", map);
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/map.jsp");
			rd.forward(request, response);
		}

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

