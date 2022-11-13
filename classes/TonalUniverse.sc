TonalUniverse{
	var <>majorUniverse;
	var <>minorUniverse;
	var <>majorPercentage;
	var <>minorPercentage;
	var <>percentagesVector;
	var <>currentScaleFundamental;
	var <>currentScaleType;
	var <>currentScaleIndex=0;

	*new {
		^super.new.init();
	}

	init{
		currentScaleFundamental = 0;
		currentScaleType = "M";
		currentScaleIndex = 0;
		majorUniverse = Array.new(32);
		minorUniverse = Array.new(32);
		for(0,12-1,{arg i;
			majorUniverse.add(
				Tonality.new.init(i,"M"));
		});
		for(0,12-1,{arg i;
			minorUniverse.add(
				Tonality.new.init(i,"m"));
		});
		^this;
	}

	setCurrentScale{arg fund, tip;
		currentScaleFundamental =fund;
		currentScaleType=tip;
	}

	getCurrentScale{
		var esc = nil;
		if(this.currentScaleType=="M",{
			esc = majorUniverse[this.currentScaleFundamental];
			},{
				if(this.currentScaleType=="m",{
					esc =
					minorUniverse[this.currentScaleFundamental];
				});
		});
		^esc;
	}

	getScale{arg fund, tip;
		var esc;
		if(tip=="M",{
			esc=this.majorUniverse[fund];
			},{
				if(tip =="m" ,{
					esc=this.minorUniverse[fund];
				});
				if(tip =="o" ,{
					esc=this.minorUniverse[fund];
				});
				if(tip =="+" ,{
					esc=this.minorUniverse[fund];
				});
		});
		^esc
	}

	getScaleString{arg string;
		var esc,fund, tip ;
		string= string.stripWhiteSpace;
		fund = this.note2Num(string.split($,)[0]);
		tip =string.split($,)[1];
		if(tip=="M",{
			esc=this.majorUniverse[fund];
			},{
				if(tip =="m" ,{
					esc=this.minorUniverse[fund];
				});
				if(tip =="o" ,{
					esc=this.minorUniverse[fund];
				});
				if(tip =="+" ,{
					esc=this.minorUniverse[fund];
				});
		});
		^esc
	}

	getHarmonyList{arg stringLista;
		var seccionesStr, tonActStr, listaGradStr, gradActualStr;
		var seccionesList, tonActual, listaFinal= Array.new(1000);
		var acorde;
		// Separar por puntos las secciones de distintas tonalidades
		// Correr sobre la lista de secciones y para cada una:
		// Separar por : la def de Tonality con la lista de grados
		// Get Tonality actul con la definición de Tonality
		// Correr sobre lista de grados y agregar cada acorde
		// Regresar la lista de armonías
		stringLista= stringLista.stripWhiteSpace;
		("Entrada: "++stringLista).postln;
		seccionesList=stringLista.split($\n);
		"Secciones lista ".post;
		seccionesList.postln;
		seccionesList.do({arg seccion;
			var seccionAct;
			"Seccion ".post;
			seccion.postln;
			seccion=seccion;
			seccionAct = seccion.split($:);
			tonActStr=seccionAct[0];
			tonActual= this.getScaleString(tonActStr);
			listaGradStr =seccionAct[1].stripWhiteSpace.split($ );
			listaGradStr.do({arg degree;
				if(listaFinal.size>0,{
					var arm;
					degree = degree.stripWhiteSpace;
//					"Agregar los siguientes".postln;
					// "==== ".post;
					// ("Lista size "++listaFinal.size).postln;
					// listaFinal.do({arg v; (","++v.nameHarmonyNoInversionStr).post});
					// (listaFinal[listaFinal.size-1].notesHarmony
					// ).postln;
					arm=Tonality.getClosestChord(
						listaFinal[listaFinal.size-1],tonActual.getHarmonyfromDegree(degree));
					listaFinal.add(arm);
					//listaFinal.postln;
					},{
						degree = degree.stripWhiteSpace;
						//"Agregar el primero".postln;
						listaFinal.add(tonActual.getHarmonyfromDegree(degree));
				});
				listaFinal.collect({arg val; val.nameHarmonyNoInversionStr});
			});
		});
		^listaFinal;
	}

	getVectorPercentages{arg listaArm;
		percentagesVector=Array.new(32);
		majorPercentage=Array.new(32);
		minorPercentage=Array.new(32);
		for(0,12-1,{arg i;
			var porc=
			majorUniverse[i].notesPercentage(listaArm);
			percentagesVector.add(porc);
			majorPercentage.add(porc);
		});
		for(0,12-1,{arg i;
			var porc=
			minorUniverse[i].notesPercentage(listaArm);
			percentagesVector.add(porc);
			majorPercentage.add(porc);
		});
		^percentagesVector;
	}

	degreeSimple{arg unArm, esc;
		// 2014 dic 14 aun no sirve
		var reg = 0;
		var cond =  false;
		var fund = (-1);
		for(0,esc.listChords.size-1,{arg i;
			if(esc.listChords[i].equivalent(unArm),{
				// Revisa si es o7
				if(unArm.harmonyTypeStr=="o7",{
					for(0,unArm.notesHarmony.size-1,{arg j;
						if(esc.noteDegree(unArm.notesHarmony[j])==7,{
							fund =
							unArm.notesHarmony[j]%12;
							cond=true;
							//Break
						});
					});
					},{
						fund=unArm.fundamental%12;
						cond =true;
				});// Fin IF i7
			});//FIN if equivalent
		});// FIN FOR i
		if(cond,{
			reg = esc.noteDegree(fund)
			},{
				reg=0;
		});
		^reg;
	}

	degreeSimpleCurrent{arg unArm;
		^this.degreeSimple(unArm,this.getCurrentScale());
	}

	degreeSecondary{arg unArm, scale;
		var coor = TonalCoordinate.new.init();
		var fund = scale.scaleBase;
		var type="M";
		var cond = false;
		var esc;
		if(this.degreeSimple(unArm,scale)>0,{
			// Si es acorde de la Tonality
			coor.setRegion(1,
				scale.scaleType,
				this.degreeSimple(unArm,scale));
			},{
				//"Entré1".postln;
				// Si es dominante de otra scale
				for(0,scale.notesTonality.size-1,{arg i;
					var degree;
					//"Entré 1.1".postln;
					fund=scale.notesTonality[i];
					//Aqui los grados 0-6
					type=scale.getTriadType(i);
					esc = this.getScale(fund,type);
					degree=this.degreeSimple(unArm,esc);
					//"Entré3".postln;
					if(degree==5,{
						coor.setRegion(
							i+1,esc.scaleType,degree);
						// AQUI VOY
					});
					if(degree==7,{
						// Aqui revisa si es o7
						if(unArm.harmonyTypeStr=="o7",{
							for(0,unArm.notesHarmony.size-1,{arg j;
								if(esc.noteDegree(unArm.notesHarmony[j])==7,{
									coor.setRegion(i+1,esc.scaleType,degree);
									//break
								});
							});
						});
					});

				});
		})
		^coor;
	}

	degreeSecondaryCurrent{arg unArm;
		^this.degreeSecondary(unArm,this.getCurrentScale);
	}

	degreeSecondaryStr{arg unAcorde,scale;
		var cor =
		this.degreeSecondary(unAcorde,scale);
		var degree = cor.degree;
		var es=cor.scale;
		var gradStr ="", esS="",graS="",pertS="";
		if(degree>0,{
			esS = switch (es,
				1, { "I" },
				2, { "II" },
				3, { "III" },
				4, { "IV" },
				5, { "V" },
				6, { "VI" },
				7, { "VII" }
			);
			graS = switch (degree,
				1, {
					if(unAcorde.inversion==2,{
						graS="K";
						},{
							graS="I";
					});
				},
				2, { graS="II";},//NAPOLITANO falta
				3, { "III" },
				4, { "IV" },
				5, {
					if(unAcorde.harmonyTypeStr=="D7",{
						graS="DV";
						},{
							graS="V";
					});
				},
				6, { "VI" },
				7, {
					if(unAcorde.harmonyTypeStr=="o7",{
						graS="Dvii";
						},{
							graS="VII";
					})
				}
			);

			if(scale.listNotesBelongs
				(unAcorde.notesHarmony).not,{
					pertS="*";
			});

			if((es==1).not,{
				gradStr =
				pertS ++ graS ++ ""
				++ unAcorde.inversionStr()
				++ "/"++ esS;
				},{
					gradStr = pertS ++ "" ++ graS + ""
					++ unAcorde.inversionStr();
			});

			},{
				gradStr="x";
		});// IF degree
		^gradStr;
	}

	getChordsFromString{arg strList;
		var listaArms=Harmony.new(100);
	}

	getSecondaryChordStr{arg unArm;
		^this.degreeSecondaryStr
		(unArm,this.getCurrentScale);
	}

	note2Num{arg notaString = "C";
		var gradNum;
		switch (notaString)
		{"C" }   { gradNum = 0;}
		{"C#" }   { gradNum = 1;}
		{"Db"}   { gradNum = 1;}
		{"D"}   { gradNum = 2;}
		{"D#"}   { gradNum = 3;}
		{"Eb"}   { gradNum = 3;}
		{"E"}   { gradNum = 4;}
		{"F"}   { gradNum = 5;}
		{"F#"}   { gradNum = 6;}
		{"Gb"}   { gradNum = 6;}
		{"G"}   { gradNum = 7;}
		{"G#"}   { gradNum = 8;}
		{"Ab"}   { gradNum = 8;}
		{"A"}   { gradNum = 9;}
		{"A#"}   { gradNum = 10;}
		{"Bb"}   { gradNum = 10;}
		{"B"}   { gradNum = 11;};
		^gradNum;
	}

	printCurrentString{
		this.getCurrentScale.scaleBase.post;
		this.getCurrentScale.scaleType.postln;
	}
}

