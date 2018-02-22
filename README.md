# superharmony
SuperCollider harmony helper

## Description 
SuperCollider classes for creating chords and harmonies.

- You can create chords 
```
// Example 1
(
a=Armonia();
a=a.create(5,"D7" );
a.nombreArmStr.postln;
a.notasArmonia.postln;
a.tipoDeAcordeStr.postln;
)
```
- You can create chord progressions with tonal information and modulation.
```
//Example 2
u=UniversoTonal();
~str=
"C,m:  I V7/VI VI V VI II II7 IV VII7 I
E,m: V7 I IV V7/V I
C,m: V7/V V7 I
";
~chords=u.getListaArmonias(~str);
~arrayNotes=~chords.collect({arg ar; ar.notasArmonia[0..3]});
```


## Installation
Add the main folde to the Super Collider extensions folder.
