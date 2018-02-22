Armonia{
	var <>fundamental = 0;
	var <>notasArmonia;
	var <>notasSimplificadas;
	var <>notasTipo;
	var <>tipoDeAcordeStr="*";
	var <>inversion;
	var <>inversionStr="";
	var <>defAc;
	var isSeptimo=false;
	var restar;
	var <>idxAcorde;
	*new { arg notasL=[0];
		^super.new.init(notasL);
	}

	init{arg notasL;
		notasL=notasL.sort;
		if(notasL.size>0,{
			defAc= DefAcordesJazz.new.init;
			notasArmonia = notasL;
			notasTipo = Array.fill(notasArmonia.size,0);
			this.setSimplificado();
			this.asignarIndiceLista();
			this.setTipoVoces();
			this.setInversion();
			this.setInversionString();
			},{
				notasArmonia = [0];
				notasTipo = [0];
		});
	}

	create{arg  fund, tipo;
		var notasL;
		defAc= DefAcordesJazz.new.init;
		// Correr sobre todos los tipos
		defAc.arms.collect({arg elem, num;
			if(elem.nombre == tipo,{
				notasL = elem.notasDef;
			});
		});
		if(notasL.size==3,{notasL.add(notasL[0]+12)});
		notasArmonia = notasL;
		^Armonia.new.init(notasL+fund);
	}



	asignarIndiceLista{
		// Revisar si es igual a alguno de la librería
		var cond = true;
		var i=0;
		for(0,defAc.arms.size-1,{arg i;
			if(this.perteneceEnLista(defAc.arms[i].notasDef),{
				idxAcorde = i;
				tipoDeAcordeStr = defAc.arms[i].nombre;
			});
		});

		// ENLACES
		// setDisposicion();
		// setPosMel();
		// setDuplicacion();
	}

	perteneceEnLista{arg acordeNotas;
		var cond=false;
		var tempA = Array.new();
		if(notasSimplificadas.size==acordeNotas.size,{
			tempA = Array.fill(notasSimplificadas.size,0);
			for(0,notasSimplificadas.size-1,{arg r;
				restar = notasSimplificadas[r];
				for(0,tempA.size-1,{arg i;
					// El modulo debe ser el de grupos
					// no con negativos
					tempA.put(i,(notasSimplificadas[i]-restar)%12);
				});
				tempA.sort;
				if(tempA == acordeNotas,{
					fundamental = restar%12;
					cond=true;
					// Quiza usar un while, para
				});
			});
			},{
				cond =false;
		});
		^cond;
	}

	setSimplificado{
		var notasTemp = this.notasArmonia;
		var diferentes = Array.new(notasArmonia.size);
		notasTemp  = notasTemp %12;
		notasTemp  = notasTemp .sort;
		// Quitar notas repetidas
		diferentes.add(notasTemp[0]);
		for(0, notasTemp.size-1,{arg i;
			var condicion = 0;
			var act=0;
			for(0, i-1,{arg j;
				act =j;
				if(notasTemp[i]==notasTemp[j],{
					condicion=1;
				});
			});
			if(condicion ==0,{
				diferentes.add(notasTemp[i]);
			});
		});
		notasSimplificadas = diferentes;
	}

	setTipoVoces{
		var dist;
		notasTipo = Array.new(notasArmonia.size);
		for(0,notasArmonia.size-1,{arg i;
			var resta = (fundamental-notasArmonia[i]);
			dist = abs((fundamental-notasArmonia[i]))%12;
			notasTipo.add(this.intervaloDiatonico(dist));
			if(this.intervaloDiatonico(dist)==7,{
				isSeptimo=true;
			});
		});
	}

	intervaloDiatonico{arg dist;
		var regresar=(-1);
		regresar = switch (dist,
			0,   { 1 },
			1, { 2},
			2, { 2},
			3, { 3},
			4,   { 3},
			5,   { 5},
			6,   { 5},
			7,   { 5},
			8,   { 3},
			9,   { 3},
			10,   { 7},
			11,   { 7 }
		);
		^regresar;
	}

	setInversion{
		inversion = switch (notasTipo[0],
			1, { 0 },
			3, { 1 },
			5, { 2 },
			7, { 3 }
		);
	}

	setInversionString{
		var invS="";
		var inv = inversion;
		if(isSeptimo.not,{
			if(inv ==1,{
				invS =invS+"6";
			});
			if(inv ==2,{
				invS =invS+"6,4";
			});
			},{
				if(inv ==0,{
					invS =invS+"7";
				});
				if(inv ==1,{
					invS =invS+"6,5";
				});
				if(inv ==2,{
					invS =invS+"4,3";
				});
				if(inv ==3,{
					invS =invS+"2";
				});
		});
		inversionStr =invS;
	}

	equivalente{arg unArm;
		var cond = false;
		if((this.fundamental%12) == (unArm.fundamental%12),{
			if(this.tipoDeAcordeStr==unArm.tipoDeAcordeStr,{
				cond =true;
			});
		})
		^cond
	}

	fundamentalString{
		var nota="";
		var acS = "";
		nota = switch (fundamental%12,
			0, { "C" },
			1, { "C#" },
			2, { "D" },
			3, { "D#" },
			4, { "E" },
			5, { "F" },
			6, { "F#" },
			7, { "G" },
			8, { "G#" },
			9, { "A" },
			10, { "A#" },
			11, { "B" }
		);
		^nota;
	}

	distanciaTaxi{arg unArm;
		var sum =0;
		if(this.notasArmonia.size == unArm.notasArmonia.size,{
			4.do({arg i;
				sum=sum+abs(this.notasArmonia[i]-unArm.notasArmonia[i]);
			});
		});
		^sum
	}

	distanciaAcordes12{arg unArm;
		^this.distancia(
			this.notasArmonia%12,
			unArm.notasArmonia%12);
	}

	distancia{arg s0, s1;
		var len0 = s0.size+1;
		var len1 = s1.size+1;
		// the array of distances
		var cost = Array.fill(len0,0);
		var newcost = Array.fill(len0,0);
		var swap;
		// initial cost of skipping prefix in String s0
		//"1".postln;
		for(0,len0-1,{arg i;cost[i]=i;});
		//"2".postln;

		// dynamicaly computing the array of distances
		// transformation cost for each letter in s1
		for(1,len1-1,{arg j;
			//"3".postln;

			newcost[0]=j;
			for(1,len0-1,{arg i;
				// matching current letters in both str
				var match;
				// computing cost for each trans
				var cost_replace;
				var cose_insert;
				var cost_delete;
				//"4".postln;
				if(s0[i-1]==s1[j-1],
					{match=0},{match=1});
				//"Match ".post;
				//match.postln;
				// computing cost for each trans
				//"5".postln;
				//"cost".postln;
				//cost[i-1].postln;
				cost_replace=cost[i-1]+match;
				//"6".postln;
				cose_insert=cost[i]+1;
				//"7".postln;
				cost_delete=newcost[i-1]+1;

				// Keep minimum cost
				newcost[i]=min(
					min(cose_insert,
						cost_delete),
					cost_replace);
			});
			swap = cost;
			cost = newcost;
			newcost = swap;
		});
		^cost[len0-1];
	}

	imprimir{
		"Fundamental: ".post;
		this.fundamentalString.post;
		" ".post;
		tipoDeAcordeStr.post;
		" ".post;
		inversionStr.postln;
		"Notas: ".post;
		notasArmonia.postln;
		"Notas Simplificadas: ".post;
		notasSimplificadas.postln;
		"Tipo de voces: ".post;
		notasTipo.postln;
	}

	imprimirSencillo{
		this.fundamentalString.post;
		tipoDeAcordeStr.post;
		inversionStr.postln;
	}

	nombreArmStr{
		^this.fundamentalString++
		tipoDeAcordeStr++
		inversionStr;
	}

	nombreArmStrSinInv{
		^this.fundamentalString++
		tipoDeAcordeStr;
	}

}