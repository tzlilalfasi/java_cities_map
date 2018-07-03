import java.util.Vector;


public class CitiesMap {

	private Graph<City> map;
	
	public CitiesMap(){
		map=new Graph<City>();
	}
	
	public boolean addCity(City c) { // The method adds a city to the map
		boolean addCity=map.addVertex(c);
		return addCity;
	}
	
	public boolean addWay(City c1, City c2){ 
		boolean addWay=map.addEdge(c1, c2);
		return addWay;
	}
	
	public Vector<City> getCities(){
		Vector<City> getCities=map.getVerteices();
		return getCities;
	}
	
	public Vector<City> getWays(City c){
		Vector<City> getWays=map.getEdges(c);
		return getWays;
	}
	
	public Vector<City> findPath(City c1, City c2) {
		Vector<City> findPath=map.bfs(c1, c2);
		return findPath;
	}
	
}
