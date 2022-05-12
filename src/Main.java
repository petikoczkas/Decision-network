import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static int n;
	private static ArrayList<Node> Nodes = new ArrayList<Node>();
	private static int ismertsz;
	private static int celidx;
	private static int dontessz;
	private static ArrayList<ArrayList<Double>> celertekek = new ArrayList<ArrayList<Double>>();
	
	
	public static void beolvas(){
		Scanner s=new Scanner(System.in);
		n=s.nextInt();
		for(int i=0; i<n; i++) {
			Node n=new Node();
			n.erteksz=s.nextInt();
			n.szulosz=s.nextInt();
			if(n.szulosz==0) {
				ArrayList<Double> a=new ArrayList<Double>();
				String str=s.next();
				String t[]=str.split(",");
				for(String string : t) {	
					a.add(Double.parseDouble(string));
				}
				
				n.ertekek.add(a);
				Nodes.add(n);
			}
			else {
				int db=1;
				for(int j=0; j<n.szulosz; j++) {
					n.szulo.add(Nodes.get(s.nextInt()));
					db*=n.szulo.get(j).erteksz;
				}
				
				for(int j=0; j<db; j++) {
					ArrayList<Double> a=new ArrayList<Double>();
					String str=s.next();
					String t[]=str.split(":");
					String t2[]=null;
					ArrayList<String> darabolt= new ArrayList<String>();
					for(String string : t) {
						t2=string.split(",");
						for(String string2 : t2) {
							darabolt.add(string2);
						}
						
					}
				
					for(String string : darabolt) {
						a.add(Double.parseDouble(string));
					}
					
					n.ertekek.add(a);
				}
				Nodes.add(n);
			}
	
		}
		ismertsz=s.nextInt();
		for(int i=0; i<ismertsz; i++) {
			int ism=s.nextInt();
			Nodes.get(ism).ismert=true;
			Nodes.get(ism).ismertertek=s.nextInt();
		}
		celidx=s.nextInt();
		dontessz=s.nextInt();
		
		s.nextLine();
		for(int i=0; i<dontessz*Nodes.get(celidx).erteksz; i++) {
			ArrayList<Double> a=new ArrayList<Double>();
			String str=s.nextLine();
			//System.out.println(str);
			String t[]=str.split("\t");
			for(String string : t) {
				a.add(Double.parseDouble(string));
				//System.out.println(string);
			}
			celertekek.add(a);
		}
		s.close();
	}
	
	public static ArrayList<Double> normalizal(ArrayList<Double> Q){
		double sum=0;
		ArrayList<Double> newQ = new ArrayList<Double>();
		for(Double d: Q) {
			sum+=d;
		}
		for(Double d: Q) {
			newQ.add(d/sum);
		}
		return newQ;
	}
	
	public static ArrayList<Double> kivalaszt(Node x) {
		ArrayList<Double> Q = new ArrayList<Double>();
		
		
		x.ismert=true;
		
		
		for(int i=0; i<x.erteksz; i++) {
			x.ismertertek=i;
			ArrayList<Node> e = new ArrayList<Node>();
			for(Node n: Nodes) {
				if(n.ismert) {
					e.add(n);
				}
			}
			
			Q.add(szamol(Nodes, e));
		}
		return normalizal(Q);
		
		
	}
	
	public static double szamol(ArrayList<Node> valtozok, ArrayList<Node> e) {
		if(valtozok.isEmpty()) return 1;
		
		Node Y=valtozok.get(0);
		double eredmeny = 0;
		ArrayList<Node> ujvaltozok = (ArrayList<Node>) valtozok.clone();
		ujvaltozok.remove(0);
		int cnt=0;
		
		if(e.contains(Y)) {
			
			/*for(int i=0; i<Y.ertekek.size(); i++) {
				eredmeny=Y.ertekek.get(i).get(Y.szulosz+Y.ismertertek) * szamol(valtozok, e);
			}*/
			for(int i=0; i<Y.ertekek.size(); i++) {
				for(int j=0; j<Y.szulosz; j++) {
					if(Y.ertekek.get(i).get(j)==Y.szulo.get(j).ismertertek) {
						cnt++;
					}
				}
				if(cnt==Y.szulosz) {
					
					eredmeny=Y.ertekek.get(i).get(Y.szulosz+Y.ismertertek) * szamol(ujvaltozok, e);
					break;
				}
				if(cnt==Y.szulosz) break;
				else cnt=0;
			}
			
			
		}
		else {
			int idx=0;
			for(int i=0; i<Y.ertekek.size(); i++) {
				for(int j=0; j<Y.szulosz; j++) {
					if(Y.ertekek.get(i).get(j)==Y.szulo.get(j).ismertertek) {
						cnt++;
					}
				}
				if(cnt==Y.szulosz) {
					
					idx=i;
					break;
				}
				if(cnt==Y.szulosz) break;
				else cnt=0;
			}
			for(int i=0; i<Y.erteksz; i++){
				Y.ismertertek=i;
				e.add(Y);
				/*for(int j=0; j<Y.erteksz; j++)*/ {
					eredmeny+=Y.ertekek.get(idx).get(Y.szulosz+Y.ismertertek) * szamol(ujvaltozok, e);
				}
				e.remove(Y);
			}
		}
		return eredmeny;
	}
	
	public static void hasznossag(ArrayList<Double> Q) {
		ArrayList<Double> eredmeny = new ArrayList<Double>();
		for(int i=0; i<dontessz; i++) {
			double seged = 0;
			for(int j=0; j<dontessz*Nodes.get(celidx).erteksz; j++) {
				if(celertekek.get(j).get(1)==i) {
					seged+=Q.get(celertekek.get(j).get(0).intValue()) * celertekek.get(j).get(2);
				}
			}
			eredmeny.add(seged);
		}
		kiir(Q, eredmeny.indexOf(Collections.max(eredmeny)));
	}
	
	public static void kiir(ArrayList<Double> Q, int varhasz) {
		for(Double ertek : Q)
			System.out.println(ertek);
		System.out.println(varhasz);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		beolvas();
		/*for(ArrayList<Double> d:Nodes.get(celidx).ertekek) {
			for(Double a:d) {
				System.out.print(a + "\t");
			}
			System.out.println();
		}*/
		hasznossag(kivalaszt(Nodes.get(celidx)));
		
	}

}
