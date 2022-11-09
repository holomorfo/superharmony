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
