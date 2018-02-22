UniversoTonal{
	var <>universoMayor;
	var <>universoMenor;
	var <>porcentajeMayor;
	var <>porcentajeMenor;
	var <>porcentajesVector;
	var <>escActFund;
	var <>escActTipo;
	var <>escActIndice=0;

	*new {
		^super.new.init();
	}

	init{
		escActFund = 0;
		escActTipo = "M";
		escActIndice = 0;
		universoMayor = Array.new(32);
		universoMenor = Array.new(32);
		for(0,12-1,{arg i;
			universoMayor.add(
				Tonalidad.new.init(i,"M"));
		});
		for(0,12-1,{arg i;
			universoMenor.add(
				Tonalidad.new.init(i,"m"));
		});
		^this;
	}

	setEscalaAct{arg fund, tip;
		escActFund =fund;
		escActTipo=tip;
	}

	getEscAct{
		var esc = nil;
		if(this.escActTipo=="M",{
			esc = universoMayor[this.escActFund];
			},{
				if(this.escActTipo=="m",{
					esc =
					universoMenor[this.escActFund];
				});
		});
		^esc;
	}

	getEscala{arg fund, tip;
		var esc;
		if(tip=="M",{
			esc=this.universoMayor[fund];
			},{
				if(tip =="m" ,{
					esc=this.universoMenor[fund];
				});
				if(tip =="o" ,{
					esc=this.universoMenor[fund];
				});
				if(tip =="+" ,{
					esc=this.universoMenor[fund];
				});
		});
		^esc
	}

	getEscalaString{arg string;
		var esc,fund, tip ;
		string= string.stripWhiteSpace;
		fund = this.nota2Num(string.split($,)[0]);
		//("fund "++fund).postln;
		tip =string.split($,)[1];
		if(tip=="M",{
			esc=this.universoMayor[fund];
			},{
				if(tip =="m" ,{
					esc=this.universoMenor[fund];
				});
				if(tip =="o" ,{
					esc=this.universoMenor[fund];
				});
				if(tip =="+" ,{
					esc=this.universoMenor[fund];
				});
		});
		^esc
	}

	getListaArmonias{arg stringLista;
		var seccionesStr, tonActStr, listaGradStr, gradActualStr;
		var seccionesList, tonActual, listaFinal= Array.new(1000);
		var acorde;
		// Separar por puntos las secciones de distintas tonalidades
		// Correr sobre la lista de secciones y para cada una:
		// Separar por : la def de tonalidad con la lista de grados
		// Get tonalidad actul con la definición de tonalidad
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
			tonActual= this.getEscalaString(tonActStr);
			listaGradStr =seccionAct[1].stripWhiteSpace.split($ );
			listaGradStr.do({arg grad;
				if(listaFinal.size>0,{
					var arm;
					grad = grad.stripWhiteSpace;
//					"Agregar los siguientes".postln;
					// "==== ".post;
					// ("Lista size "++listaFinal.size).postln;
					// listaFinal.do({arg v; (","++v.nombreArmStrSinInv).post});
					// (listaFinal[listaFinal.size-1].notasArmonia
					// ).postln;
					arm=tonActual.getArmoniaMasCercana(
						listaFinal[listaFinal.size-1],tonActual.getArmoniaGradoStr(grad));
					listaFinal.add(arm);
					//listaFinal.postln;
					},{
						grad = grad.stripWhiteSpace;
						//"Agregar el primero".postln;
						listaFinal.add(tonActual.getArmoniaGradoStr(grad));
				});
				listaFinal.collect({arg val; val.nombreArmStrSinInv});
			});
		});
		^listaFinal;
	}

	vectorPorcentajes{arg listaArm;
		porcentajesVector=Array.new(32);
		porcentajeMayor=Array.new(32);
		porcentajeMenor=Array.new(32);
		for(0,12-1,{arg i;
			var porc=
			universoMayor[i].porcentajeNotas(listaArm);
			porcentajesVector.add(porc);
			porcentajeMayor.add(porc);
		});
		for(0,12-1,{arg i;
			var porc=
			universoMenor[i].porcentajeNotas(listaArm);
			porcentajesVector.add(porc);
			porcentajeMayor.add(porc);
		});
		^porcentajesVector;
	}

	gradoSencillo{arg unArm, esc;
		// 2014 dic 14 aun no sirve
		var reg = 0;
		var cond =  false;
		var fund = (-1);
		for(0,esc.listArms.size-1,{arg i;
			if(esc.listArms[i].equivalente(unArm),{
				// Revisa si es o7
				if(unArm.tipoDeAcordeStr=="o7",{
					for(0,unArm.notasArmonia.size-1,{arg j;
						if(esc.gradoNota(unArm.notasArmonia[j])==7,{
							fund =
							unArm.notasArmonia[j]%12;
							cond=true;
							//Break
						});
					});
					},{
						fund=unArm.fundamental%12;
						cond =true;
				});// Fin IF i7
			});//FIN if equivalente
		});// FIN FOR i
		if(cond,{
			reg = esc.gradoNota(fund)
			},{
				reg=0;
		});
		^reg;
	}

	gradoSencilloActual{arg unArm;
		^this.gradoSencillo(unArm,this.getEscAct());
	}

	gradoSecundario{arg unArm, escala;
		var coor = CoordenadaTonal.new.init();
		var fund = escala.baseEsc;
		var tipo="M";
		var cond = false;
		var esc;
		if(this.gradoSencillo(unArm,escala)>0,{
			// Si es acorde de la tonalidad
			coor.setRegion(1,
				escala.tipoEsc,
				this.gradoSencillo(unArm,escala));
			},{
				//"Entré1".postln;
				// Si es dominante de otra escala
				for(0,escala.notasTonalidad.size-1,{arg i;
					var grad;
					//"Entré 1.1".postln;
					fund=escala.notasTonalidad[i];
					//Aqui los grados 0-6
					tipo=escala.getTipoTriada(i);
					esc = this.getEscala(fund,tipo);
					grad=this.gradoSencillo(unArm,esc);
					//"Entré3".postln;
					if(grad==5,{
						coor.setRegion(
							i+1,esc.tipoEsc,grad);
						// AQUI VOY
					});
					if(grad==7,{
						// Aqui revisa si es o7
						if(unArm.tipoDeAcordeStr=="o7",{
							for(0,unArm.notasArmonia.size-1,{arg j;
								if(esc.gradoNota(unArm.notasArmonia[j])==7,{
									coor.setRegion(i+1,esc.tipoEsc,grad);
									//break
								});
							});
						});
					});

				});
		})
		^coor;
	}

	gradoSecundActual{arg unArm;
		^this.gradoSecundario(unArm,this.getEscAct);
	}

	gradoSecundarioStr{arg unAcorde,escala;
		var cor =
		this.gradoSecundario(unAcorde,escala);
		var grado = cor.grado;
		var es=cor.escala;
		var gradStr ="", esS="",graS="",pertS="";
		if(grado>0,{
			esS = switch (es,
				1, { "I" },
				2, { "II" },
				3, { "III" },
				4, { "IV" },
				5, { "V" },
				6, { "VI" },
				7, { "VII" }
			);
			graS = switch (grado,
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
					if(unAcorde.tipoDeAcordeStr=="D7",{
						graS="DV";
						},{
							graS="V";
					});
				},
				6, { "VI" },
				7, {
					if(unAcorde.tipoDeAcordeStr=="o7",{
						graS="Dvii";
						},{
							graS="VII";
					})
				}
			);

			if(escala.perteneceListaNotas
				(unAcorde.notasArmonia).not,{
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
		});// IF grado
		^gradStr;
	}

	getAcordesFromString{arg strList;
		var listaArms=Armonia.new(100);


	}

	gradoSecundarioActStr{arg unArm;
		^this.gradoSecundarioStr
		(unArm,this.getEscAct);
	}


	nota2Num{arg notaString = "C";
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


	imprimirEscAct{
		this.getEscAct.baseEsc.post;
		this.getEscAct.tipoEsc.postln;

	}
}

