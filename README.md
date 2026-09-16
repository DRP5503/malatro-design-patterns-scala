# Malatro

This project aims to apply Object-Oriented Programming (OOP) concepts and design patterns to replicate the core mechanics of the card game Balatro.

To achieve this, the project is divided into two main packages: `model` and `logic`.
The `model` package contains all the code focused on modeling the different entities needed to play the game.
The `logic` package houses the code necessary to structure a game turn, identify a played hand, calculate its score, and manage the overall flow of a match through different game states.

## Recent Updates & Design Decisions

### Data Processors: `Calculator` and `Evaluator`
For this deliverable, the scoring implementation was finalized with the creation of `Calculator`, a Scala `object`.
Like `Evaluator`, it didn't need to be a class since its only job is to process data and return a result (a recognized hand in the case of `Evaluator`, and a score for `Calculator`). Using an `object` instead of a class seemed ideal to avoid unnecessary instantiations every time a calculation is needed.

### Refactoring and minor changes
I changed the rank classifications from standard objects to `case class`es. This change facilitates the implementation of double dispatch and avoids having to use `.type`.
Additionally, case classes automatically implement `equals`, which is very helpful for testing. They are also immutable by default, which makes sense for our domain since a rank classification shouldn't change. A `trait` wasn't the most accurate choice here either, as I'm not defining shared behavior, but rather using it to represent data (in this case, the suit/rank).

With this change, the pending implementation of the `applyScore` methods, which use double dispatch to calculate the final score, is now complete.

### Game State Management: `GameController` and `GameState`
I defined `GameState` as an abstract class. This allows me to define all the methods used by the `GameController` with a default behavior (throwing an exception since the action shouldn't be allowed by default), and then override them in the subclasses that represent the actual phases of the match. I chose this approach because I believe the State design pattern is the most effective way to keep a project like this maintainable and easy to modify. It clearly outlines the different phases of the game and simplifies the code flow.

I also modified the states from the previous deliverable, simplifying them down to just three: **`InitializingState`**, **`PlayerInTurnState`**, and **`FinalState`**. I felt it was better to keep things simple. Most of the logic happens inside `PlayerInTurnState`, as that is where the player can actually play or discard cards; the other states do not involve player actions. The score is updated after every play, and the game transitions to `FinalState` through a simple check at the end of `PlayerInTurnState` if the remaining plays hit zero.

Lastly, I decided that `GameController` should store all the relevant information during a match, such as the current hand, the score (stored as an `Int`, not a separate `Score()` object), etc. This way, all necessary game data is managed by the controller itself. By passing the controller instance to the different states, they can access and manipulate this information whenever needed, without relying on external storage.
