import java.util.ArrayList;

public class Node {

	int erteksz;
	int szulosz;
	ArrayList<Node> szulo = new ArrayList<Node>();
	ArrayList<ArrayList<Double>> ertekek = new ArrayList<ArrayList<Double>>();
	boolean ismert;
	int ismertertek;
	
	public Node(){
		ismert=false;
		ismertertek=Integer.MAX_VALUE;
	}
}
