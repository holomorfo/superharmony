# Superharmony

Superharmony is a [SuperCollider](https://supercollider.github.io/) library of utilities for exploring tonality. It containes classes for defining a Harmony, Tonality, TonalUniverse as a set of notes and functions between them.

## Components

###Harmony

You can create and manipulate harmonies as a set of notes and functions. For example:
```
a = Harmony.new([0,4,7])
b = Harmony([0,4,7])
// C minor chord [0,3,7,12]
a = Harmony.create(0,"m")
​
// A major chord [9,13,16,21]
b = Harmony.create(9,"M")
​
a= Harmony([0,4,7,11]) // CM7
a.print
// Fundamental: C M7  7
// Notes: [ 0, 4, 7, 11 ]
// Simplified Notes[ 0, 4, 7, 11 ]
// Interval types[ 1, 3, 5, 7 ]
```

### Tonality
We can create a tonality object that will give information about harmonies in it and their tonal functions. For example:

```
a = Tonality.new(0,"M")
b = Tonality(0,"m")
​
Tonality.degreeRoman2Number("I") // 0
Tonality.degreeRoman2Number("iii") // 2
Tonality.degreeRoman2Number("VII") // 6
​
​
// Get Closest chord
a= Harmony([0,4,7,12])
b= Harmony.create(9,"D7")
b.print
​
c=Tonality.getClosestChord(a,b)
c.print
// [9,13,16,19]
​
// Get notes percentages that belong to a tonality.
​
a = Tonality(0,"M") // [0,2,4,5,7,9,11]
a.notesPercentage([0,2,4]) // 1.0
a.notesPercentage([1,3,6]) // 0.0
a.notesPercentage([4,6,7]) // 0.666...
a.notesPercentage([0,1]) // 0.5
​
// Get Harmony from degree
a = Tonality(0,"M") // [0,2,4,5,7,9,11]
h = a.getHarmonyfromDegree("I") // Harmony
h.printSimple // CM
a.getHarmonyfromDegree("i").printSimple // CM since its case insensitive
a.getHarmonyfromDegree("II").printSimple // Dm
a.getHarmonyfromDegree("III").printSimple // Em
```


### Tonal Universe
With tonal universe you can get information about what chord belongs to what tonality and create sequences of chords from a string of roman degree numbers:

In this string, each line represents a tonality and FUNDAMENTAL,MAJOR/MINOR, separeted by a colon. After this a sequence of roman numerals are added, and you can add secondary functions like the fifht degree of the sixt V7/VI. When passed to the "getHarmonyList" function, this is parsed and we return an array of chords that we can pass a view bean

```
(
u=TonalUniverse();
~str=
"C,m:  I V7/VI VI V VI II II7 IV VII7 I
E,m: V7 I IV V7/V I
C,m: V7/V V7 I
";
// This function takes a while and logs the progress in the console.
~chords=u.getHarmonyList(~str);
~arrayNotes=~chords.collect({arg ar; ar.notesHarmony[0..3]});
)
// -> [ [ 0, 3, 7, 12 ], [ -2, 3, 7, 13 ], [ -4, 3, 8, 12 ], [ -5, 2, 7, 11 ], [ -4, 3, 8, 12 ], [ -4, 5, 8, 14 ], [ -7, 2, 8, 12 ], [ -7, 0, 8, 12 ], [ -7, -1, 8, 14 ], [ -9, 0, 7, 15 ], [ -9, -1, 9, 18 ], [ -8, -1, 7, 19 ], [ -8, 0, 9, 21 ], [ -8, 1, 6, 22 ], [ -8, -1, 7, 23 ], [ -10, -3, 6, 24 ], [ -10, -5, 5, 23 ], [ -9, -5, 3, 24 ] ]
(
Pbind(
    \note, Pseq(~arrayNotes.flat,1),
    \dur, 0.2
).play
)
~arrayNotes.flat.plot("Sequence", discrete:true)

```
![Piano roll](./graph.png)



## Installation
- Add the main folder to the Super Collider extensions folder. 
- This libarary can also be installed as a quark by following the instructions in this link: [QUARKS TUTORIAL](https://doc.sccode.org/Guides/UsingQuarks.html)


### Author
Cristian Banuelos, dr_holomorfo 2023
[wwww.holomorfo.com](https://www.holomorfo.com)
