# superharmony
SuperCollider harmony helper utility.

## Description 
SuperCollider classes for creating chords and harmonies.

- You can create chords 
```
// Example 1
(
a=Harmony();
a=a.create(5,"D7" );
a.nameHarmonyString.postln;
a.notesHarmony.postln;
a.harmonyTypeStr.postln;
)
```
- You can create chord progressions with tonal information and modulation.
```
//Example 2
u=TonalUniverse();
~str=
"C,m:  I V7/VI VI V VI II II7 IV VII7 I
E,m: V7 I IV V7/V I
C,m: V7/V V7 I
";
~chords=u.getHarmonyList(~str);
~arrayNotes=~chords.collect({arg ar; ar.notesHarmony[0..3]});
```


## Installation
Add the main folde to the Super Collider extensions folder.



TITLE:: Harmony
summary:: Representation of a chord with its harmonic content.
categories:: Chords, Harmonies, Tonalities


DESCRIPTION::
Defines a chord as an array of numbers toghether with its harmonic content, including its chord type, base and inversion.

Part of link::Guides/superharmony::, a library with utilities for tonal analysis of chords.

CLASSMETHODS::

METHOD:: new
Creates a new Harmony instance, it takes an array of integer numbers. Can be called with the shorthand notation
code::
a = Harmony.new([0,4,7])
b = Harmony([0,4,7])
::

METHOD:: create
Creates a four voice harmony instance from the fundamental note and chord type from the list in link::Classes/ChordsDefinition:: where you can select triads, sevenths and ninth chords among others. For example
code::
// C minor chord [0,3,7,12]
a = Harmony.create(0,"m")

// A major chord [9,13,16,21]
b = Harmony.create(9,"M")

// G diminished seventh chord [7,10,13,16]
c = Harmony.create(7,"o7")

// D dominant seventh [2,6,9,12]
d = Harmony.create(2,"D7")

// B major ninth with fourth suspended [11,13,16,18,22]
e = Harmony.create(11,"M9sus4")
::

ARGUMENT::
Fundamental note of the chord as an integer number.

ARGUMENT::
Type of chord, see link::Classes/ChordsDefinition:: for available types.

INSTANCEMETHODS::

subsection:: Accessing


METHOD:: equivalent
Checks if two chords have the same chord and type, regardless of number of voices and order or octave.
code::
a= Harmony([4,7,12,0])
b= Harmony.create(12,"M")
c= Harmony([0,4,7])
d= Harmony([0,3,7])
a.equivalent(b) // true
a.equivalent(c) // true
a.equivalent(d) // false
::

METHOD:: fundamental
Fundamental interger value of a chord.
code::
a= Harmony([0,4,9]) // A minor chord
a.fundamental // 9, which is the A value of the A minor chord
::

METHOD:: fundamentalString
Fundamental string value of a chord.
code::
a= Harmony([0,4,9]) // A minor chord
a.fundamentalString // "A"
::

METHOD:: harmonyTypeStr
String value of the harmony type
code::
a= Harmony([0,4,9]) // A minor chord
a.harmonyTypeStr // "m", which is the kinf of chord of A minor
::

METHOD:: inversion
Numerical value of the inversion of the chord, CM in root value would be [0,4,7] would have value 0, first inversion [4,7,12] would have value 1, and so on.
code::
a= Harmony([0,4,7]) // A minor chord
a.inversion // "0", root position of C Major chord
a= Harmony([4,7,12]) // A minor chord
a.inversion // "1", first inversion oc C Major chord
a= Harmony([7,12,12+4]) // A minor chord
a.inversion // "2", first inversion oc C Major chord
::

METHOD:: inversionStr
String value of the inversion of the chord, CM in root value would be [0,4,7,11] would have value "7" as a seventh chord in root position, first inversion [4,7,12] would have value "6,5". Other inversions would have "6,4", "4,3", "2". This method works for triads and seventh chords.
code::
a= Harmony([0,4,7,11,12])
a.inversionStr // "7", root position of C M7 chord
b= Harmony([7,4,11,12,7+12])
b.inversionStr // "6,5", first inversion of C M7 chord
c= Harmony([7,11,12,12+4])
c.inversionStr // "4,3", second inversion of C M7 chord
::

METHOD:: notesHarmony
Array with the sorted notes in the harmony.
code::
b= Harmony([7,4,12,19,11,4])
b.notesHarmony // [ 4, 4, 7, 11, 12, 19 ]
::

METHOD:: notesSimplified
Array with simplified version of the array of notes. Notes are converted to pitch class module 12, duplicates are removed and elements are sorted ascending.
code::
b= Harmony([7,4,12,11,4,19])
b.notesSimplified // [ 0,4,7,11 ]
::

METHOD:: nameHarmonyNoInversionStr
String with fundamental letter value an type of chord string value
code::
b= Harmony([7,4,12,11,4,19])
b.nameHarmonyNoInversionStr // CM7
::

METHOD:: nameHarmonyString
String with fundamental letter value an type of chord string value with inversion string added.
code::
b= Harmony([7,4,12,11,4,19])
b.nameHarmonyString // CM7 6,5
::

METHOD:: notesInterval
Degree of each chord with respect to chord type, for example for C Major seventh chord [0,4,7,11], 0 would be the fundamental 1, 4 would be the and interval of third, 7 an interval of fifth and 11 the seventh interval.
code::
a= Harmony([0,4,7,11]) // CM7
a.notesInterval // [1,3,5,7]

b= Harmony([7,4,12,11,4,19]) // CM7
b.notesInterval // [ 3, 3, 5, 7, 1, 5 ]
::

METHOD:: print
Prints information about the harmony: Fundamental string, type, inversion, notes, simplified notes and interval types
code::
a= Harmony([0,4,7,11]) // CM7
a.print
// Fundamental: C M7  7
// Notes: [ 0, 4, 7, 11 ]
// Simplified Notes[ 0, 4, 7, 11 ]
// Interval types[ 1, 3, 5, 7 ]
::

METHOD:: printSimple
Prints simplifiedinformation about the harmony: Fundamental string, type and inversion.
code::
a= Harmony([0,4,7,11]) // CM7
a.printSimple // CM7 7
::



EXAMPLES::

Creating a chord
code::
a = Harmony([0,4,7,10])
a.print
// Fundamental: C D7  7
// Notes: [ 0, 4, 7, 10 ]
// Simplified Notes[ 0, 4, 7, 10 ]
// Interval types[ 1, 3, 5, 7 ]
::



section:: Authors
Cristian Banuelos, 2022.
