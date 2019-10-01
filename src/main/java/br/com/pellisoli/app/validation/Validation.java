package br.com.pellisoli.app.validation;

import java.util.ArrayList;

public abstract class Validation<T>{
		
	ArrayList<String> brokenRules = new ArrayList<String>();
	
	public boolean IsValid (T obj) {
		brokenRules = validate(obj);
		return brokenRules.size() <= 0;
	};
	
	protected abstract ArrayList<String> validate(T obj);

	public ArrayList<String> getBrokenRules() {
		return brokenRules;
	}
	
	
}
