package com.yourri1012.chemistrymod.objectives;

import java.util.ArrayList;

public class ObjectivesList {
	public static ArrayList<Objective> list = new ArrayList<>(10);
	
	public ObjectivesList() {
		list.clear();
		for(int i = 0; i < 10; i++) {
			list.add(new Objective());
		}
		java.util.Collections.shuffle(list);
	}

	public ArrayList<Objective> getList(){
		return list;
	}
	
	public static void reset() {
		list = new ArrayList<>(10);
		list.clear();
		for(int i = 0; i < 10; i++) {
			list.add(new Objective());
		}
		java.util.Collections.shuffle(list);
	}
}
