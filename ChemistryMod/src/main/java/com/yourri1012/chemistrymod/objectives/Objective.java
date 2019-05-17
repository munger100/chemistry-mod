package com.yourri1012.chemistrymod.objectives;

public class Objective {
public static final String[] OBJ_POS = {"Make", "Neutralize"};
	public static final String[] MAKE_POS = {"an alloy", "molten ", "a strong acid", "a weak acid", "a strong base", "a weak base", "Sodium Hydride (NaH)", "Calcium Hydride (CaH2)", "salty water"};
	public static final String[] METALS = {"gold", "iron", "diamond", "emerald", "platinum", "copper"};
	public static final String[] NEUT_POS = {"Weak Acid", "Strong Acid", "Weak Base", "Strong Base"};
	public static final String[] SALTY_WATER_POS = {"Weak Acid", "Strong Acid", "Weak Base", "Strong Base", ""};
	
	public String objective;
	public String product;
	public String from ="";
	public String solution;
	public String molten;
	
	public Objective() {
		objective = OBJ_POS[(int)(Math.random() * 2)];
		
		if(objective.equals("Make")) {
			product = MAKE_POS[(int)(Math.random() * 9)];
			
			switch(product) {
			case "an alloy": molten = METALS[(int) (Math.random() * 6)]; alloy(molten); solution = "1xitem.alloy@0"; break;
			case "molten ": molten = METALS[(int)(Math.random() * 6)]; product += molten; 
				switch(molten) {
				case "gold" : solution = "1xitem.molten_gold@0"; break;
				case "iron" : solution = "1xitem.molten_iron@0"; break;
				case "diamond" : solution = "1xitem.molten_diamond@0"; break;
				case "emerald" : solution = "1xitem.molten_emerald@0"; break;
				case "platinum" : solution = "1xitem.molten_platinum@0"; break;
				case "copper" : solution = "1xitem.molten_copper@0"; break;
				} break;
			case "a strong acid" : solution = "1xitem.strong_acid@0"; break;
			case "a weak acid" : solution = "1xitem.weak_acid@0"; break;
			case "a strong base" : solution = "1xitem.strong_base@0"; break;
			case "a weak base" : solution = "1xitem.weak_base@0"; break;
			case "Sodium Hydride (NaH)" : solution = "1xitem.salt@0"; break;
			case "Calcium Hydride (CaH2)" : solution = "1xitem.salt@0"; break;
			case "salty water": from = SALTY_WATER_POS[(int)(Math.random() * 5)]; solution = "1xitem.salty_water@0"; break;
			}
		} else {
			product = NEUT_POS[(int)(Math.random() * 4)];
			solution = "1xitem.salty_water@0";
		}
	}
	
	@Override
	public String toString() {
		if(this.from == null ^ this.from.equals("")) return this.objective + " " + this.product;
		return this.objective + " " + this.product + " using " + this.from;
	}
	
	public boolean equals(Objective o) {
		return this.objective.toString().equals(o.toString());
	}
	
	public void alloy(String metal1) {
		String metal2;
		do {
			metal2 = METALS[(int) (Math.random() * 6)];
		} while (metal2.equals(metal1));
		from = metal1 + " and " + metal2;
	}
}
