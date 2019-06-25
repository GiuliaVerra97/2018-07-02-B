package it.polito.tdp.extflightdelays.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;


public class Model {
	
	Graph<Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao=new ExtFlightDelaysDAO();
	Map<Integer, Airport> mappaAereoporti;
	
	public void creaGrafo(int numVoli) {
		
		grafo=new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		mappaAereoporti=new HashMap<Integer, Airport>();
		List<Airport> aereoporti=dao.loadAllAirports();
		
		
	
		
		for(Airport aereoporto: aereoporti) {
			int a=aereoporto.getId();
			int num=dao.getAereoporti(a);
			if(num>=numVoli) {
				grafo.addVertex(aereoporto);
				mappaAereoporti.put(aereoporto.getId(), aereoporto);
			}
		}
		
		List<Rotta> rotte=dao.getRotte(mappaAereoporti);
		for(Rotta r: rotte) {
			if(mappaAereoporti.containsKey(r.getA1()) && mappaAereoporti.containsKey(r.getA2())) {
				DefaultWeightedEdge edge=grafo.getEdge(mappaAereoporti.get(r.getA1()), mappaAereoporti.get(r.getA2()));
				if(edge==null) {
					Graphs.addEdge(grafo,mappaAereoporti.get(r.getA1()), mappaAereoporti.get(r.getA2()), r.getDurata());
				}else {
					double peso=grafo.getEdgeWeight(edge);
					double nuovoPeso=peso+r.getDurata();
					grafo.setEdgeWeight(edge, nuovoPeso);
				}
			}else {
				System.out.println("NON c'è");
			}
		}
		
		
		System.out.println("Grafo creato con "+grafo.vertexSet().size()+" "+grafo.edgeSet().size());
		
	
	}

	
	
	
	public Collection<Airport> getAereoporti() {
		List<Airport> lista=new ArrayList<Airport>();
		for(Airport a: mappaAereoporti.values()) {
			lista.add(a);
		}
		
		Collections.sort(lista);
		return lista;
	}




	public List<Ordina> elencoConnessi(Airport a) {
		List<Ordina> lista=new ArrayList<Ordina>();
		List<Airport> vicini=Graphs.neighborListOf(grafo, a);
		
		for(Airport vicino: vicini) {
			DefaultWeightedEdge edge=grafo.getEdge(a, vicino);
			lista.add(new Ordina(vicino, (int)grafo.getEdgeWeight(edge)));
		}
		
		Collections.sort(lista);
		return lista;	
		
	}




	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}




	public void setGrafo(Graph<Airport, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	
	
	
}
