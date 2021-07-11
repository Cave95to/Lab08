package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo(500);

		System.out.println(model.getIdMap());
	
		//System.out.println(model.getDao().getRotte(model.getIdMap()));
	}

}
