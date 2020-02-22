# Hearts Lite

Some **pair programming** and **induvidual work** for a [computer science class](https://lasacs.com/)

## Authors

Hollister Harper Ream [*Hollikill*](https://github.com/Hollikill)

Gabriel James Keller [*itsGJK*](https://github.com/ItsGJK)

## What Files Do What

Glossary for file types

### Output Files

These are .txt files which are simply the saved text output from runs of debug classes

### Debug Classes

Classes with 'Test' in the name , which are classes made only for the purposes of class assignemnts to check that the code is correct

### Base Classes

The *Deck*, *Card*, and *Player* classes, which are basic frameworks to support the actual design of the game

### Player Classes

The *CardPlayer* and more complicated *CardPlayerLevel1* classes, which build off of the *Player* class to provide a method to decide what card is best to play in the moment, based on what is in the player's hand at the moment, and what has been played in the round and game

### Tournament Classes

These classes pit the computer-controlled players, sometimes with human players, against each other to see which will come out victorious. Currently, this is *CardGame*, for testing only *CardPlayer* objects, and *CardGameImproved*, which takes an array of Player Classes larger than 4, and pits them off in rounds, switching out the order periodically to provide an equal footing for testing.

#### Use of Tournament Classes

To see the actual output of these classes, you must use the associated Debug Classes. In addition, for *CardGameImproved*, you must also configure what classes to actually test in the associated Debug Class.

### Local Player Classes

Person-controlled player, not yet implemented
