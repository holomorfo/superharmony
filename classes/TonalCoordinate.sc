TonalCoordinate{
	var <>scale=0,<>degree=0, <>type="M", <>label="-";

	init{arg esc=0, tp="M", gra=0;
		scale=esc;
		degree=gra;
		type=tp;
		^this;
	}

	setRegion{arg esc,tp,gra;
		scale =esc;
		degree=gra;
		type=tp;
	}

	print{
		"scale ".post;
		scale.post;
		" ".post;
		type.post;
		" degree: ".post;
		degree.post;
		" label".post;
		label.post;
	}
}
