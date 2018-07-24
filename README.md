# Ten Ten Solver

A framework for developing algorithms to perform efficiently and productively in a game of 10!10!.

---
**Authors:** Charles (@c650), Cassandra (@cassdelacruzmunoz), Arturo (@arturogalan99), and Michael.

---
# Change Logs:

### Changes Jul 23, 2018:

* Fixed bug involving `ImprovedAdjacencySol`, `Board`, and passing objects by reference.
* Refactored library paths.
* Added support for getting advice from one of the algorithms.

### Changes Jul 22, 2018:

* Some minor changes to `Board` to improve run speed.
* Added javadoc comments and generated Javadoc html.
* Modified tester to run a comparison test of the three different algorithms.


### Changes Jan 09, 2017:

* Added `abstract class Solution` to designate how solutions are to be implemented so that
`Game` can work with any subclass of `Solution` without actually caring about its specific type.
* Cleaned up `Game` to be just the core of what a `Game` should be. The `Game`, other than asking your `Solution` implementation,
has no role in picking which `Piece` to play or where to put it. **The `Game` will also make sure that you can't keep playing after the game is over.**

---
# Development

As of right now, most of the core gameplay logic is solid. What needs to be developed are the actual algorithms to use to pick where to place the next piece.

### Building a Solution:

To build a solution, simple **extend** the `abstract class` [`Solution`](/src/Solution.java) with the necessary methods (as well as any extra functionality you deem useful/critical to efficient gameplay/puzzle-solving.).

An example implementation is available [here](/src/solutions/MySolution.java). It's _reaaaallly_ bad though.

---
### Compile with:

	javac ./src/*.java ./src/solutions/*.java -d ./bin

***Note:*** _if you don't have a bin directory, make one. If you're running Windows, use backslashes instead._
