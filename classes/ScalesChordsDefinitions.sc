ScalesChordsDefinitions{
	var <>chordsList;
	init{arg base=0, type="M";
		var def = ChordsDefinitions.new.init();
		chordsList=Array.new(500);
		switch (type)
		{"M"}   {
			chordsList.add
			(Harmony.new.init(base+0+def.str2Ac("M")));
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+4+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("M")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("M")));
			chordsList.add
			(Harmony.new.init(base+9+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+11+def.str2Ac("o")));
			// Septimos
			chordsList.add
			(Harmony.new.init(base+0+def.str2Ac("M7")));
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+4+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("M7")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("D7")));
			chordsList.add
			(Harmony.new.init(base+9+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+11+def.str2Ac("o/7")));
			chordsList.add
			(Harmony.new.init(base+4+def.str2Ac("m7")));
			// major armonica
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac( "o")));
			// major armonicos septimo
			chordsList.add
			(Harmony.new.init(base+11+def.str2Ac( "o7")));
			//Napolitano
			//Subdominante armonico
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac( "m")));
			//Dominante noveno
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "9")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "DM9*5")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "Dm9*5")));
			// 9na de Cristian
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "7b9")));
			//Dominantes alterados
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "7b5")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "7#5")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac( "D7*5")));
		}
		{"m"}   {
			// Menor natural
			// {"m", "o", "M", "m", "m", "M", "M"};
			chordsList.add
			(Harmony.new.init(base+0+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac("o")));
			chordsList.add
			(Harmony.new.init(base+3+def.str2Ac("M")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("m")));
			chordsList.add
			(Harmony.new.init(base+8+def.str2Ac("M")));
			chordsList.add
			(Harmony.new.init(base+10+def.str2Ac("M")));
			//public static String[] Ac7EscMenNat = {"m7", "o/7", "M7", "m7", "m7", "M7", "D7"};
			chordsList.add
			(Harmony.new.init(base+0+def.str2Ac("m7")));
			//    chordsList.add(Harmony.new.init(base+2+def.str2Ac("o/7")));
			chordsList.add
			(Harmony.new.init(base+3+def.str2Ac("M7")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+8+def.str2Ac("M7")));
			chordsList.add
			(Harmony.new.init(base+10+def.str2Ac("D7")));
			//Menor armonico
			//    chordsList.add(Harmony.new.init(base+3+def.str2Ac("+")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("M")));
			//    chordsList.add(Harmony.new.init(base+11+def.str2Ac("o")));
			//Setpimos
			//    chordsList.add(Harmony.new.init(base+0+def.str2Ac("I+")));
			//    chordsList.add(Harmony.new.init(base+3+def.str2Ac("III+")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("D7")));
			chordsList.add
			(Harmony.new.init(base+11+def.str2Ac("o7")));
			//Menor MelÃ³dico
			// Menor melodica
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac("m")));
			//    chordsList.add(Harmony.new.init(base+3+def.str2Ac("+")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("M")));
			//    chordsList.add(Harmony.new.init(base+9+def.str2Ac("o")));
			//    chordsList.add(Harmony.new.init(base+11+def.str2Ac("o")));
			//Septimos
			chordsList.add
			(Harmony.new.init(base+2+def.str2Ac("m7")));
			chordsList.add
			(Harmony.new.init(base+5+def.str2Ac("D7")));
			//    chordsList.add(Harmony.new.init(base+9+def.str2Ac("o/7")));
			//    chordsList.add(Harmony.new.init(base+11+def.str2Ac("o/7")));
			//Napolitano
			//    chordsList.add(Harmony.new.init(base+1+def.str2Ac("M")));
			//Dominante noveno
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("DM9*5")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("Dm9*5")));
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("D7*5")));
			// 9na de Cristian
			chordsList.add
			(Harmony.new.init(base+7+def.str2Ac("7b9")));
		}
		{"l"}   { "no hay".postln; }
		{"n"}   { "no hay".postln; }
		{"a"}   { "no hay".postln; };
		^this;
	}
}