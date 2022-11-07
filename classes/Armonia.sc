Harmony{
	var <>fundamental = 0;
	var <>notesHarmony;
	var <>notesSimplified;
	var <>notesInterval;
	var <>harmonyTypeStr="*";
	var <>inversion;
	var <>inversionStr="";
	var <>defAc;
	var isSeventh=false;
	var substract;
	var <>idxAcorde;
	*new { arg notesList=[0];
		^super.new.init(notesList);
	}

	init{arg notesList;
		notesList=notesList.sort;
		if(notesList.size>0,{
			defAc= DefAcordesJazz.new.init;
			notesHarmony = notesList;
			notesInterval = Array.fill(notesHarmony.size,0);
			this.setSimplify();
			this.assignListIndex();
			this.setNotesIntervals();
			this.setInversion();
			this.setInversionString();
			},{
				notesHarmony = [0];
				notesInterval = [0];
		});
	}

	create{arg  fund, tipo;
		var notesList;
		defAc= DefAcordesJazz.new.init;
		// Correr sobre todos los tipos
		defAc.arms.collect({arg elem, num;
			if(elem.name == tipo,{
				notesList = elem.notesDefinition;
			});
		});
		if(notesList.size==3,{notesList.add(notesList[0]+12)});
		notesHarmony = notesList;
		^Harmony.new.init(notesList+fund);
	}



	assignListIndex{
		// Revisar si es igual a alguno de la librería
		var cond = true;
		var i=0;
		for(0,defAc.arms.size-1,{arg i;
			if(this.belongsInList(defAc.arms[i].notesDefinition),{
				idxAcorde = i;
				harmonyTypeStr = defAc.arms[i].name;
			});
		});

		// ENLACES
		// setDisposicion();
		// setPosMel();
		// setDuplicacion();
	}

	belongsInList{arg chordNotes;
		var cond=false;
		var tempA = Array.new();
		if(notesSimplified.size==chordNotes.size,{
			tempA = Array.fill(notesSimplified.size,0);
			for(0,notesSimplified.size-1,{arg r;
				substract = notesSimplified[r];
				for(0,tempA.size-1,{arg i;
					// El modulo debe ser el de grupos no con negativos
					tempA.put(i,(notesSimplified[i]-substract)%12);
				});
				tempA.sort;
				if(tempA == chordNotes,{
					fundamental = substract%12;
					cond=true;
				});
			});
			},{
				cond =false;
		});
		^cond;
	}

	setSimplify{
		var notesTemp = this.notesHarmony;
		var different = Array.new(notesHarmony.size);
		notesTemp  = notesTemp %12;
		notesTemp  = notesTemp .sort;
		// Quitar notas repetidas
		different.add(notesTemp[0]);
		for(0, notesTemp.size-1,{arg i;
			var condicion = 0;
			var act=0;
			for(0, i-1,{arg j;
				act =j;
				if(notesTemp[i]==notesTemp[j],{
					condicion=1;
				});
			});
			if(condicion == 0,{
				different.add(notesTemp[i]);
			});
		});
		notesSimplified = different;
	}

	setNotesIntervals{
		var dist;
		notesInterval = Array.new(notesHarmony.size);
		for(0,notesHarmony.size-1,{arg i;
			var resta = (fundamental-notesHarmony[i]);
			dist = abs((fundamental-notesHarmony[i]))%12;
			notesInterval.add(this.diatonicInterval(dist));
			if(this.diatonicInterval(dist)==7,{
				isSeventh=true;
			});
		});
	}

	diatonicInterval{arg dist;
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
		inversion = switch (notesInterval[0],
			1, { 0 },
			3, { 1 },
			5, { 2 },
			7, { 3 }
		);
	}

	setInversionString{
		var invS="";
		var inv = inversion;
		if(isSeventh.not,{
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

	equivalent{arg unArm;
		var cond = false;
		if((this.fundamental%12) == (unArm.fundamental%12),{
			if(this.harmonyTypeStr==unArm.harmonyTypeStr,{
				cond =true;
			});
		})
		^cond
	}

	fundamentalString{
		var note="";
		var acS = "";
		note = switch (fundamental%12,
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
		^note;
	}

	metricTaxi{arg unArm;
		var sum =0;
		if(this.notesHarmony.size == unArm.notesHarmony.size,{
			4.do({arg i;
				sum=sum+abs(this.notesHarmony[i]-unArm.notesHarmony[i]);
			});
		});
		^sum
	}

	metricMod12{arg unArm;
		^this.distance(
			this.notesHarmony%12,
			unArm.notesHarmony%12);
	}

	distance{arg s0, s1;
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

	print{
		"Fundamental: ".post;
		this.fundamentalString.post;
		" ".post;
		harmonyTypeStr.post;
		" ".post;
		inversionStr.postln;
		"Notes: ".post;
		notesHarmony.postln;
		"Simplified Notes".post;
		notesSimplified.postln;
		"Interval types".post;
		notesInterval.postln;
	}

	printSimple{
		this.fundamentalString.post;
		harmonyTypeStr.post;
		inversionStr.postln;
	}

	nameHarmonyString{
		^this.fundamentalString++
		harmonyTypeStr++
		inversionStr;
	}

	nameHarmonyNoInversionStr{
		^this.fundamentalString++
		harmonyTypeStr;
	}

}