DefEscalasAcordes{
	var <>armsList;
	init{arg base=0, tipo="M";
		var def = DefAcordesJazz.new.init();
		armsList=Array.new(500);
		switch (tipo)
		{"M"}   {
			armsList.add
			(Armonia.new.init(base+0+def.str2Ac("M")));
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+4+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("M")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("M")));
			armsList.add
			(Armonia.new.init(base+9+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+11+def.str2Ac("o")));
			// Septimos
			armsList.add
			(Armonia.new.init(base+0+def.str2Ac("M7")));
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+4+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("M7")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("D7")));
			armsList.add
			(Armonia.new.init(base+9+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+11+def.str2Ac("o/7")));
			armsList.add
			(Armonia.new.init(base+4+def.str2Ac("m7")));
			// Mayor armonica
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac( "o")));
			// Mayor armonicos septimo
			armsList.add
			(Armonia.new.init(base+11+def.str2Ac( "o7")));
			//Napolitano
			//Subdominante armonico
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac( "m")));
			//Dominante noveno
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "9")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "DM9*5")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "Dm9*5")));
			// 9na de Cristian
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "7b9")));
			//Dominantes alterados
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "7b5")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "7#5")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac( "D7*5")));
		}
		{"m"}   {
			// Menor natural
			// {"m", "o", "M", "m", "m", "M", "M"};
			armsList.add
			(Armonia.new.init(base+0+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac("o")));
			armsList.add
			(Armonia.new.init(base+3+def.str2Ac("M")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("m")));
			armsList.add
			(Armonia.new.init(base+8+def.str2Ac("M")));
			armsList.add
			(Armonia.new.init(base+10+def.str2Ac("M")));
			//public static String[] Ac7EscMenNat = {"m7", "o/7", "M7", "m7", "m7", "M7", "D7"};
			armsList.add
			(Armonia.new.init(base+0+def.str2Ac("m7")));
			//    armsList.add(Armonia.new.init(base+2+def.str2Ac("o/7")));
			armsList.add
			(Armonia.new.init(base+3+def.str2Ac("M7")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+8+def.str2Ac("M7")));
			armsList.add
			(Armonia.new.init(base+10+def.str2Ac("D7")));
			//Menor armonico
			//    armsList.add(Armonia.new.init(base+3+def.str2Ac("+")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("M")));
			//    armsList.add(Armonia.new.init(base+11+def.str2Ac("o")));
			//Setpimos
			//    armsList.add(Armonia.new.init(base+0+def.str2Ac("I+")));
			//    armsList.add(Armonia.new.init(base+3+def.str2Ac("III+")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("D7")));
			armsList.add
			(Armonia.new.init(base+11+def.str2Ac("o7")));
			//Menor MelÃ³dico
			// Menor melodica
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac("m")));
			//    armsList.add(Armonia.new.init(base+3+def.str2Ac("+")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("M")));
			//    armsList.add(Armonia.new.init(base+9+def.str2Ac("o")));
			//    armsList.add(Armonia.new.init(base+11+def.str2Ac("o")));
			//Septimos
			armsList.add
			(Armonia.new.init(base+2+def.str2Ac("m7")));
			armsList.add
			(Armonia.new.init(base+5+def.str2Ac("D7")));
			//    armsList.add(Armonia.new.init(base+9+def.str2Ac("o/7")));
			//    armsList.add(Armonia.new.init(base+11+def.str2Ac("o/7")));
			//Napolitano
			//    armsList.add(Armonia.new.init(base+1+def.str2Ac("M")));
			//Dominante noveno
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("DM9*5")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("Dm9*5")));
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("D7*5")));
			// 9na de Cristian
			armsList.add
			(Armonia.new.init(base+7+def.str2Ac("7b9")));
		}
		{"l"}   { "no hay".postln; }
		{"n"}   { "no hay".postln; }
		{"a"}   { "no hay".postln; };
		^this;
	}
}