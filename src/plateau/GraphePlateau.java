package plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GraphePlateau {
	int h;
	int l;
	Case[][] cases;
	private Map<Case, List<Case>> graphe;
	
	public Map<Case, List<Case>> getGraphe() {
		return graphe;
	}

	public void setGraphe(Map<Case, List<Case>> graphe) {
		this.graphe = graphe;
	}

	public GraphePlateau(int hauteur,int largeur,Case[][] cases) {
		h = hauteur;
		l = largeur;
		this.cases = cases;
		graphe = new TreeMap<>();
		remplirGraphe();
	}
	
	public void remplirGraphe() {
		Case c;
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<l;j++) {
				c = cases[i][j];
				graphe.put(c,trouverVoisins(i,j));
			}
		}
	}
	
	public List<Case> trouverVoisins(int i,int j){
		Case c = cases[i][j];
		Case vois;
		List<Case> listVois = new ArrayList<>();
		if(j>0) {
			vois = cases[i][j-1];
			if(c.getTypeImage().contains("G") && vois.getTypeImage().contains("D"))
				listVois.add(vois);
		}
		if(i>0) {
			vois = cases[i-1][j];
			if(c.getTypeImage().contains("H") && vois.getTypeImage().contains("B"))
				listVois.add(vois);
		}
		if(j+1 < l) {
			vois = cases[i][j+1];
			if(c.getTypeImage().contains("D") && vois.getTypeImage().contains("G"))
				listVois.add(vois);
		}
		if(i+1 < h) {
			vois = cases[i+1][j];
			if(c.getTypeImage().contains("B") && vois.getTypeImage().contains("H"))
				listVois.add(vois);
		}
		return listVois;
	}
	
	public String toString() {
		String ret = "";
		for (Map.Entry<Case,List<Case>> entry : graphe.entrySet()) {
			ret += entry.getKey().toString() + " " + entry.getValue().toString()+"\n";
		}
		return ret;
	}
	
}
