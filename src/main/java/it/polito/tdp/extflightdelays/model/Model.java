package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
		
	private ExtFlightDelaysDAO dao;
	
	private Map<Integer, Airport> idMap;
	
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	
	public Model() {
		
		this.dao = new ExtFlightDelaysDAO();
		this.idMap = new HashMap<>();
		this.dao.loadAllAirports(this.idMap);
	}
	
	public void creaGrafo(int x) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.idMap.values());
		
		
		// creiamo archi 
		
		List<Rotta> rotte = this.dao.getRotte(idMap);
		
		for(Rotta r : rotte) {
			DefaultWeightedEdge e = this.grafo.getEdge(r.getA1(), r.getA2());
			if(e==null) {
				Graphs.addEdge(this.grafo, r.getA1(), r.getA2(), r.getDist());
			}else {
				double peso = this.grafo.getEdgeWeight(e);
				double pesoNuovo = (peso + r.getDist())/2;
				this.grafo.setEdgeWeight(e, pesoNuovo);
			}
		}
		
		
		List<DefaultWeightedEdge> archiDaRimuovere = new ArrayList<>();
		for (DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)<= x)
				archiDaRimuovere.add(e);
		}
	
		this.grafo.removeAllEdges(archiDaRimuovere);
		
		
	}

	public int getNVertici() {
		if(this.grafo!=null)
			return this.grafo.vertexSet().size();
		return 0;
	}
	
	public int getNArchi() {
		if(this.grafo!=null)
			return this.grafo.edgeSet().size();
		return 0;
	}

	public Map<Integer, Airport> getIdMap() {
		return idMap;
	}

	public void setIdMap(Map<Integer, Airport> idMap) {
		this.idMap = idMap;
	}

	public ExtFlightDelaysDAO getDao() {
		return dao;
	}

	public void setDao(ExtFlightDelaysDAO dao) {
		this.dao = dao;
	}

	public List<Rotta> getRotte() {
		if(this.grafo!=null) {
			List<Rotta> rotte = new ArrayList<>();
			for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
				Rotta r = new Rotta(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), this.grafo.getEdgeWeight(e));
				rotte.add(r);
			}
			return rotte;
		}
			
		return null;
	}
}
