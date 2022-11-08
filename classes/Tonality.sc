Tonality{
	var <>notesTonality;
	var defScalesChords;
	var <>listChords; 
	var <>scaleType; //Char M:major, m: minor, a: armonica, n: natural, l:melodica
	var <>scaleBase; 
	var sharp; // boolean if notation has sharps or not
	var major;
	var harmonicMinor;

	*new { arg base=0, type="M";
		^super.new.init(base, type);
	}

	init{arg base=0, type="M";
		major = [0,2,4,5,7,9,11];
		harmonicMinor = [0, 2, 3, 5, 7, 8, 11];
		notesTonality=Array.new(12);
		scaleBase = base;
		switch (type)
		{"M"}   {
			for(0,major.size-1,{arg i;
				notesTonality.add(
					mod(scaleBase +major.at(i),12)
				);
			});
		}
		{"l"}   { "c".postln; }
		{"m"}   {
			for(0,harmonicMinor.size-1,{arg i;
				notesTonality.add(
					mod(scaleBase +harmonicMinor.at(i),12)
				);
			});
		}
		{"n"}   { "c".postln; }
		{"a"}   { "c".postln; };
		scaleType = type;
		defScalesChords = ScalesChordsDefinitions.new.init(scaleBase,type);
		listChords = defScalesChords.chordsList;
		^this;
	}

	notesPercentage{arg notesList;
		var numNotas=0;
		notesList.do({arg notaAc;
			if(this.noteBelongs(notaAc),{
				numNotas = numNotas+1;
			})
		});
		^(numNotas/notesList.size);
	}

	noteBelongs{arg notaAc;
		var cosa;
		var cond = false;
		cosa = notesTonality.indexOf(notaAc%12);
		if((cosa == nil).not,{
			cond = true;
		});
		^cond;
	}

	listNotesBelongs{arg notasLista;
		var cond = false;
		if(this.notesPercentage(notasLista)==1,{
			cond = true;
		});
		^cond;
	}

	noteDegree{arg notaAc;
		var reg=(-1);
		var grad =
		this.notesTonality.indexOf(notaAc%12);
		if((grad==nil).not,{
			reg = grad+1;
		});
		^reg;
	}

	getTriadType{arg degree;
		var str ="";
		var major = ["M", "m", "m", "M", "M", "m", "o"];
		var harmonicMinor =["m", "o", "M", "m", "M", "M", "o"];
		if(this.scaleType =="M",{
			str=major[degree];
			},{
				if(this.scaleType =="m",{
					str=harmonicMinor[degree];
				});
		});
		^str;
	}

	getSeventhType{arg degree;
		var str ="";
		var major = ["M7", "m7", "m7", "M7", "D7", "m7", "o/7" ];
		var harmonicMinor =[ "m7", "o/7", "M7", "m7", "D7", "M7", "o7"];
		if(this.scaleType =="M",{
			str=major[degree];
			},{
				if(this.scaleType =="m",{
					str=harmonicMinor[degree];
				});
		});
		^str;
	}

	possibleChords{arg unArm;
		var lista = Array.new(this.listChords.size);
		var indices = Array.new(this.listChords.size);
		var swap=true;
		var temp;

		this.listChords.do({arg ac;
			lista.add(
				[unArm.metricMod12(ac),
					ac.nameHarmonyString,]);
		});
		// Ordenarlos
		while({swap},{
			swap=false;
			temp;
			for(0, lista.size-2,{arg i;
				if(lista[i][0]>lista[i+1][0],{
					temp = lista[i];
					lista[i]=lista[i+1];
					lista[i+1]=temp;
					swap=true;
				});
			});

		});
		^lista;
	}


	getHarmonyTriad{arg degree=0;
		var arm ="";
		arm = Harmony().create(
			notesTonality[degree],
			this.getTriadType(degree));
		^arm;
	}

	getHarmonySeventh{arg degree=0;
		var arm ="";
		arm = Harmony().create(
			notesTonality[degree],
			this.getSeventhType(degree));
		^arm;
	}

	getClosestChord{arg ar1, ar2;
		var armTemp=ar1, armReg, distFinal=(100), dist;
		var nots, r=2;
		 "_______________".postln;
		// ("Harmony 1 "++ar1.nameHarmonyNoInversionStr).postln;
		// ("Harmony 2 "++ar2.nameHarmonyNoInversionStr).postln;
		armReg = Harmony(ar2.notesHarmony);
		nots = ar1.notesHarmony;
		if(nots.size==4,{
				for(nots[0]-r,nots[0]+r,{arg v1;
					for(nots[1]-r,nots[1]+r,{arg v2;
						for(nots[2]-r,nots[2]+r,{arg v3;
							for(nots[3]-r,nots[3]+r,{arg v4;
								//(""++v1++","++v2++","++v3++","++v4).postln;
								armTemp = Harmony([v1,v2,v3,v4]);
								dist = ar1.metricTaxi(armTemp);
								if(armTemp.equivalent(ar2),{
									if(dist<distFinal,{
										armReg= Harmony([v1,v2,v3,v4]);
										//("type lista "++
										//	armReg.nameHarmonyNoInversionStr).postln;
										distFinal=dist;
									});
								});
							});
						});
					});
				("%"++v1).postln;
				});

		});
//		("Dist y acorde a salir "++distFinal).postln;
//		(armReg.nameHarmonyNoInversionStr).postln;
		^armReg;
	}

	getChordDegreeString{arg grad = "I7 ";
		var arr, domSec, tonTemporal, regArm;
		var romNum,gradNum, gradTipRom,gradTipNum;
		domSec = grad.split($/);
		if(domSec.size==1,{
			arr= this.separateStringDegree(grad);
			romNum = arr[0];
			gradTipRom = arr[1];
			gradTipNum = "3";
			gradNum=this.degreeRoman2Number(romNum);
			switch (gradTipRom)
			{"7" }   { gradTipNum = "7";};

			regArm =this.getHarmony(gradNum,gradTipNum);
			},{
				// Aqui si es dominante secundario
				var gradObjNum = this.degreeRoman2Number(domSec[1]);
				var tipoObj =this.getTriadType(gradObjNum);
				var gradEscalado = this.notesTonality[gradObjNum];
				if((""++tipoObj) == "o",{"entro".postln; tipoObj = "m";});
				"Dominante secundario".postln;
				("Valores "++gradEscalado++" "++tipoObj).postln;
				tonTemporal = Tonality(gradEscalado,tipoObj);
				regArm = tonTemporal.getChordDegreeString(domSec[0]);
		});
		^(regArm);
		//		^(""++(gradNum)++" "++gradTip);
	}

	degreeRoman2Number{arg romNum;
		var gradNum=1;
		switch (romNum)
		{"I" }   { gradNum = 0;}
		{"II"}   { gradNum = 1;}
		{"III"}   { gradNum = 2;}
		{"IV"}   { gradNum = 3;}
		{"V"}   { gradNum = 4;}
		{"VI"}   { gradNum = 5;}
		{"VII"}   { gradNum = 6;}
		{"i" }   { gradNum = 0;}
		{"ii"}   { gradNum = 1;}
		{"iii"}   { gradNum = 2;}
		{"iv"}   { gradNum = 3;}
		{"v"}   { gradNum = 4;}
		{"vi"}   { gradNum = 5;}
		{"vii"}   { gradNum = 6;};
		^gradNum;
	}

	separateStringDegree{arg strDgr;
		var degreeRom="", typeRom ="";
		var reg= Array.new(2);
		strDgr= strDgr.stripWhiteSpace;
		strDgr.do({arg val;
			var valo =""++val;
			if((valo == "I")|| (valo == "i")
				||(valo == "V") ||(valo == "v"),{
					degreeRom=degreeRom++val;
				},{
					// Si no romano
					typeRom = typeRom++val;
			});
		});
		reg.add(degreeRom);
		reg.add(typeRom);
		//		("degree "++degreeRom).postln;
		//		("type "++typeRom).postln;
		^reg;
	}


	getHarmony{arg degree=0, type="3";
		var arm ="";
		arm = Harmony().create(
			notesTonality[degree],
			this.getTriadType(degree));
		if(type == "7",{
			arm = Harmony().create(
				notesTonality[degree],
				this.getSeventhType(degree));
			},{
				if(type == "DD",{
					arm = Harmony().create(
						notesTonality[degree]+7,
						"D7");
				});
		});
		^arm;
	}
}
