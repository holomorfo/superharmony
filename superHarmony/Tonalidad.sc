Tonalidad{
	var <>notasTonalidad;
	var defEscAcordes;
	var <>listArms; // Lista de Armonias en la escala
	var <>tipoEsc; //Char M:mayor, m: menor, a: armonica, n: natural, l:melodica
	var <>baseEsc; // float base de la escala numerp
	var sostenido; // boolean, si va con sostenidos o no
	var mayor;
	var menArm;

	*new { arg base=0, tipo="M";
		^super.new.init(base, tipo);
	}

	init{arg base=0, tipo="M";
		mayor = [0,2,4,5,7,9,11];
		menArm = [0, 2, 3, 5, 7, 8, 11];
		notasTonalidad=Array.new(12);
		baseEsc = base;
		switch (tipo)
		{"M"}   {
			for(0,mayor.size-1,{arg i;
				notasTonalidad.add(
					mod(baseEsc +mayor.at(i),12)
				);
			});
		}
		{"l"}   { "c".postln; }
		{"m"}   {
			for(0,menArm.size-1,{arg i;
				notasTonalidad.add(
					mod(baseEsc +menArm.at(i),12)
				);
			});
		}
		{"n"}   { "c".postln; }
		{"a"}   { "c".postln; };
		tipoEsc = tipo;
		defEscAcordes =
		DefEscalasAcordes.new.init(baseEsc,tipo);
		listArms = defEscAcordes.armsList;
		^this;
	}

	porcentajeNotas{arg listaNotas;
		var numNotas=0;
		listaNotas.do({arg notaAc;
			if(this.perteneceNota(notaAc),{
				numNotas = numNotas+1;
			})
		});
		^(numNotas/listaNotas.size);
	}

	perteneceNota{arg notaAc;
		var cosa;
		var cond = false;
		cosa = notasTonalidad.indexOf(notaAc%12);
		if((cosa == nil).not,{
			cond = true;
		});
		^cond;
	}

	perteneceListaNotas{arg notasLista;
		var cond = false;
		if(this.porcentajeNotas(notasLista)==1,{
			cond = true;
		});
		^cond;
	}

	gradoNota{arg notaAc;
		var reg=(-1);
		var grad =
		this.notasTonalidad.indexOf(notaAc%12);
		if((grad==nil).not,{
			reg = grad+1;
		});
		^reg;
	}

	getTipoTriada{arg grado;
		var str ="";
		var mayor = ["M", "m", "m", "M", "M", "m", "o"];
		var menArm =["m", "o", "M", "m", "M", "M", "o"];
		if(this.tipoEsc =="M",{
			str=mayor[grado];
			},{
				if(this.tipoEsc =="m",{
					str=menArm[grado];
				});
		});
		^str;
	}

	getTipoSeptimo{arg grado;
		var str ="";
		var mayor = ["M7", "m7", "m7", "M7", "D7", "m7", "o/7" ];
		var menArm =[ "m7", "o/7", "M7", "m7", "D7", "M7", "o7"];
		if(this.tipoEsc =="M",{
			str=mayor[grado];
			},{
				if(this.tipoEsc =="m",{
					str=menArm[grado];
				});
		});
		^str;
	}

	acordesPosibles{arg unArm;
		var lista = Array.new(this.listArms.size);
		var indices = Array.new(this.listArms.size);
		var swap=true;
		var temp;

		this.listArms.do({arg ac;
			lista.add(
				[unArm.distanciaAcordes12(ac),
					ac.nombreArmStr,]);
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


	getArmonia3{arg grado=0;
		var arm ="";
		arm = Armonia().create(
			notasTonalidad[grado],
			this.getTipoTriada(grado));
		^arm;
	}

	getArmonia7{arg grado=0;
		var arm ="";
		arm = Armonia().create(
			notasTonalidad[grado],
			this.getTipoSeptimo(grado));
		^arm;
	}

	getArmoniaMasCercana{arg ar1, ar2;
		var armTemp=ar1, armReg, distFinal=(100), dist;
		var nots, r=2;
		 "_______________".postln;
		// ("Armonia 1 "++ar1.nombreArmStrSinInv).postln;
		// ("Armonia 2 "++ar2.nombreArmStrSinInv).postln;
		armReg = Armonia(ar2.notasArmonia);
		nots = ar1.notasArmonia;
		if(nots.size==4,{
				for(nots[0]-r,nots[0]+r,{arg v1;
					for(nots[1]-r,nots[1]+r,{arg v2;
						for(nots[2]-r,nots[2]+r,{arg v3;
							for(nots[3]-r,nots[3]+r,{arg v4;
								//(""++v1++","++v2++","++v3++","++v4).postln;
								armTemp = Armonia([v1,v2,v3,v4]);
								dist = ar1.distanciaTaxi(armTemp);
								if(armTemp.equivalente(ar2),{
									if(dist<distFinal,{
										armReg= Armonia([v1,v2,v3,v4]);
										//("Tipo lista "++
										//	armReg.nombreArmStrSinInv).postln;
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
//		(armReg.nombreArmStrSinInv).postln;
		^armReg;
	}

	getArmoniaGradoStr{arg grad = "I7 ";
		var arr, domSec, tonTemporal, regArm;
		var romNum,gradNum, gradTipRom,gradTipNum;
		domSec = grad.split($/);
		if(domSec.size==1,{
			arr= this.separateStringDegree(grad);
			romNum = arr[0];
			gradTipRom = arr[1];
			gradTipNum = "3";
			gradNum=this.gradRom2Num(romNum);
			switch (gradTipRom)
			{"7" }   { gradTipNum = "7";};

			regArm =this.getArmonia(gradNum,gradTipNum);
			},{
				// Aqui si es dominante secundario
				var gradObjNum = this.gradRom2Num(domSec[1]);
				var tipoObj =this.getTipoTriada(gradObjNum);
				var gradEscalado = this.notasTonalidad[gradObjNum];
				if((""++tipoObj) == "o",{"entro".postln; tipoObj = "m";});
				"Dominante secundario".postln;
				("Valores "++gradEscalado++" "++tipoObj).postln;
				tonTemporal = Tonalidad(gradEscalado,tipoObj);
				regArm = tonTemporal.getArmoniaGradoStr(domSec[0]);
		});
		^(regArm);
		//		^(""++(gradNum)++" "++gradTip);
	}

	gradRom2Num{arg romNum;
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
		//		("Grado "++degreeRom).postln;
		//		("Tipo "++typeRom).postln;
		^reg;
	}


	getArmonia{arg grado=0, tipo="3";
		var arm ="";
		arm = Armonia().create(
			notasTonalidad[grado],
			this.getTipoTriada(grado));
		if(tipo == "7",{
			arm = Armonia().create(
				notasTonalidad[grado],
				this.getTipoSeptimo(grado));
			},{
				if(tipo == "DD",{
					arm = Armonia().create(
						notasTonalidad[grado]+7,
						"D7");
				});
		});
		^arm;
	}

	//
	//
	// // setSostenido{}
	// // nota2num12{}
	// public Armonia getArmonia3(int grado)
	// {
	// 	Armonia arm = new Armonia(notasTonalidad[grado], getTipoTriada(grado));
	// 	return arm;
	// }
	//
	// public Armonia getArmonia7(int grado)
	// {
	// 	Armonia arm = new Armonia(notasTonalidad[grado], getTipoSeptimo(grado));
	// 	return arm;
	// }


}