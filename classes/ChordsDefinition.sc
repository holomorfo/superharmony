ChordsDefinitions{
	var <>arms;
	init{
		arms=Array.new(500);
		arms.add(HarmonyDefinition.new.init("M",[0, 4, 7]));
		arms.add(HarmonyDefinition.new.init("m",[0, 3, 7]));
		arms.add(HarmonyDefinition.new.init("o",[0, 3, 6]));
		arms.add(HarmonyDefinition.new.init("+",[0, 4, 8]));
		arms.add(HarmonyDefinition.new.init("M7",[0, 4, 7, 11]));
		arms.add(HarmonyDefinition.new.init("D7",[0, 4, 7, 10]));
		arms.add(HarmonyDefinition.new.init("m7",[0, 3, 7, 10]));
		arms.add(HarmonyDefinition.new.init("o/7",[0, 3, 6, 10]));
		arms.add(HarmonyDefinition.new.init("o7",[0, 3, 6, 9]));
		arms.add(HarmonyDefinition.new.init("mM7",[0, 3, 7, 11]));
		arms.add(HarmonyDefinition.new.init("D7*5",[0, 4, 10]));
		arms.add(HarmonyDefinition.new.init("DM9*5",[0, 2, 4, 10]));
		arms.add(HarmonyDefinition.new.init("Dm9*5",[0, 1, 4, 10]));


		 arms.add(HarmonyDefinition.new.init("5",[0,7]));
		 arms.add(HarmonyDefinition.new.init("Sus4",[0,5,7]));
		 arms.add(HarmonyDefinition.new.init("Sus2",[0,2,7]));
		 arms.add(HarmonyDefinition.new.init("6",[0,4,7,9])); //Se confunde con A7 en [CM]
		 arms.add(HarmonyDefinition.new.init("m6",[0,3,7,9]));
		 arms.add(HarmonyDefinition.new.init("9",[0,2,4,7,10]));
		 arms.add(HarmonyDefinition.new.init("m9",[0,2,3,7,10]));
		 arms.add(HarmonyDefinition.new.init("M9",[0,2,4,7,11]));
		 arms.add(HarmonyDefinition.new.init("mM9",[0,2,3,7,11]));
		 arms.add(HarmonyDefinition.new.init("11",[0,2,4,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("m11",[0,2,3,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("M11",[0,2,4,5,7,11]));
		 arms.add(HarmonyDefinition.new.init("mM11",[0,2,3,5,7,11]));
		 arms.add(HarmonyDefinition.new.init("13",[0,2,4,7,9,10]));
		 arms.add(HarmonyDefinition.new.init("m13",[0,2,3,7,9,10]));
		 arms.add(HarmonyDefinition.new.init("M13",[0,2,4,7,9,11]));
		 arms.add(HarmonyDefinition.new.init("mM13",[0,2,3,7,9,11]));
		 arms.add(HarmonyDefinition.new.init("add9",[0,2,4,7]));
		 arms.add(HarmonyDefinition.new.init("Madd9",[0,2,3,7]));
		 arms.add(HarmonyDefinition.new.init("6add9",[0,2,4,7,9]));
		 arms.add(HarmonyDefinition.new.init("m6add9",[0,2,3,7,9]));
		 arms.add(HarmonyDefinition.new.init("D7add11",[0,4,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("M7add11",[0,4,5,7,11]));
		 arms.add(HarmonyDefinition.new.init("m7add11",[0,3,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("mM7add11",[0,3,5,7,11]));
		 arms.add(HarmonyDefinition.new.init("D7add13",[0,4,7,9,11]));
		 arms.add(HarmonyDefinition.new.init("M7add13",[0,4,7,9,11]));
		 arms.add(HarmonyDefinition.new.init("m7add13",[0,3,7,9,10]));
		 arms.add(HarmonyDefinition.new.init("mM7add13",[0,3,7,9,11]));
		 arms.add(HarmonyDefinition.new.init("7b5",[0,4,6,10]));
		 arms.add(HarmonyDefinition.new.init("7#5",[0,4,8,10]));
		 arms.add(HarmonyDefinition.new.init("7b9",[0,1,4,7,10]));
		 arms.add(HarmonyDefinition.new.init("7#9",[0,3,4,7,10]));
		 arms.add(HarmonyDefinition.new.init("7#5b9",[0,1,4,8,10]));
		 arms.add(HarmonyDefinition.new.init("m7#5",[0,3,8,10]));
		 arms.add(HarmonyDefinition.new.init("m7b9",[0,1,3,7,10]));
		 arms.add(HarmonyDefinition.new.init("9#11",[0,2,4,6,7,11]));
		 arms.add(HarmonyDefinition.new.init("9b13",[0,2,4,7,8,11]));
		 arms.add(HarmonyDefinition.new.init("6sus4",[0,5,7,9]));

		 arms.add(HarmonyDefinition.new.init("7sus4",[0,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("M7sus4",[0,5,7,11]));
		 arms.add(HarmonyDefinition.new.init("9sus4",[0,2,5,7,10]));
		 arms.add(HarmonyDefinition.new.init("M9sus4",[0,2,5,7,11]));

		 arms.add(HarmonyDefinition.new.init("2m",[0,1]));
		 arms.add(HarmonyDefinition.new.init("2M",[0,2]));
		 arms.add(HarmonyDefinition.new.init("3m",[0,3]));
		 arms.add(HarmonyDefinition.new.init("3M",[0,4]));
		 arms.add(HarmonyDefinition.new.init("4P",[0,5]));
		 arms.add(HarmonyDefinition.new.init("6+",[0,6]));
		 arms.add(HarmonyDefinition.new.init("5P",[0,7]));
		 arms.add(HarmonyDefinition.new.init("6m",[0,8]));
		 arms.add(HarmonyDefinition.new.init("6M",[0,9]));
		 arms.add(HarmonyDefinition.new.init("7m",[0,10]));
		 arms.add(HarmonyDefinition.new.init("7M",[0,11]));
		^this;
	}

	str2Ac{arg tip;
		var acord = Array.new(30);
		for(0,arms.size-1,{arg i;
			if(tip==arms[i].name,{
				acord = arms[i].notesDefinition;
			});
		});
		^acord;
	}
}
