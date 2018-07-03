import java.util.Vector;


public class Graph <E> {

	private Vector<Vector<E>> vertices; 

	public Graph(){
		vertices=new Vector<Vector<E>>();
	}

	public boolean addVertex(E ver) { // the method adds an intersection
		for(Vector<E> s : vertices){ //for each vector in vertices it checks if "ver" is the first object
			if(s.get(0).equals(ver))
				return false;
		}
		Vector<E> vector1 = null ;
		vertices.add(vector1=new Vector<E>());
		vector1.add(ver);
		return true;
	}

	public boolean addEdge(E ver1, E ver2){ // the method adds a "bow" between two given variables
		boolean isExist2=false, isExist1=false ;
		for(Vector<E> s : vertices){ //checks if the two variables exist 
			if(s.get(0).equals(ver2))
				isExist2=true;
			if(s.get(0).equals(ver1))
				isExist1=true;
		}
		for(Vector<E> s : vertices){
			if(isExist1==true && isExist2==true ){
				if(s.get(0).equals(ver1)){
					s.add(ver2);
				}
				if(s.get(0).equals(ver2)){
					s.add(ver1);
				}
			}
			else
				return false;
		}
		return true;
	}

	public Vector<E> getEdges(E ver) { //the method returns a vector that has "connection" to an intersection
		Vector<E> vector1 = new Vector<E>();
		for(Vector<E> s : vertices){
			if(s.get(0).equals(ver) && s.size()> 1){
				for (int i = 1; i < s.size(); i++)
					vector1.add(s.get(i));
				return vector1;
			}
			if(s.size() == 1)
				return null;
		}
		return null;
	}

	public Vector<E> getVerteices() { //the method returns a vector of the intersections
		if(vertices.isEmpty())
			return null;
		Vector<E> vector1 = new Vector<E>();
		for(Vector<E> s : vertices){
			vector1.add(s.get(0));
		}
		return vector1;
	}

	public Vector<E> bfs(E source, E target) { // the method returns the route from source to target
		Vector<Vector<E>> routeVector = new Vector<Vector<E>>(); 
		Vector<E> sourceVector = new Vector<E>(); 
		sourceVector.add(source);
		routeVector.add(sourceVector);
		while(!routeVector.isEmpty()){
			Vector<E> firstElementVector=routeVector.get(0); 
			E last=firstElementVector.get(firstElementVector.size()-1);
			Vector<E> getEdges=getEdges(last);
			for (int i = 0; i < getEdges.size(); i++) { 
				Vector<E> route=new Vector<E>();
				route.addAll(firstElementVector); 
				if(!route.contains(getEdges.get(i))){
					route.add(getEdges.get(i));
					routeVector.add(route);}
				if(getEdges.get(i).equals(target))
					return route;
			}
			routeVector.remove(0);
			if(routeVector.isEmpty())
				return null;
		}
		return null;
	}
}
