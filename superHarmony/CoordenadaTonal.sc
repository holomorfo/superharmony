CoordenadaTonal{
	var <>escala=0,<>grado=0, <>tipo="M", <>etq="-";

	init{arg esc=0, tp="M", gra=0;
		escala=esc;
		grado=gra;
		tipo=tp;
		^this;
	}

	setRegion{arg esc,tp,gra;
		escala =esc;
		grado=gra;
		tipo=tp;
	}


	imprimir{
		"Escala ".post;
		escala.post;
		" ".post;
		tipo.post;
		" grado: ".post;
		grado.post;
		" Etq".post;
		etq.post;
	}
}

