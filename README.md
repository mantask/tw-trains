Trains
======

This package contains a solution to the coding assignment "Trains" 
by ThoughtWorks. The solution has been developed as part of the
hiring process. 


Running the application
-----------------------

First, build a JAR package:

    $ mvn package
  
Next, execute the JAR. No need to specify the main class, as it is
already specified in the manifest.

	$ java -jar target/trains-FINAL.jar
	
The application will read a single line from standard input and 
will print the results to the standard output. To read and write 
from files, use input redirection. Make sure the input data is
terminated with the end-of-line symbol.

	$ java -jar target/trains-FINAL.jar < trains.in > trains.out


Design of the application
-------------------------
	
I was trying to balance between overengineering the solution and
building enough abstractions to manage complexity and code 
duplication. Thus I did not encapsulate edges, vertexes and paths 
into separate data structures and kept it lightweight (chars and 
strings).

Certain trade-offs in terms of performance were made to keep the 
design clean, e.g. instead of using a well-known Dijkstra's algorithm 
for shortest path search, the same depth-first search graph walking 
style is used.

The main classes are:

* `App`. It handles input / output and passes-on the calls to other
classes to calculate the needed graph properties.
* `Railway`. It represents a network of tracks. This class knows of
towns and distances between them. 
* `RailwayFactory`.  It knows how to parse string representation of
railway network and convert it into a `Railway` object.
* `RailwayWalker`. It can walk through a railway network from one town 
to the other in a "depth-first search" style.
* `WalkingStrategy`. It abstracts away specific walking rules from 
basic graph traversal.

More detailed descriptions of the design decisions can be found 
inside the source code.


Assumptions
-----------

* JVM v1.6+ is required.
* Input data shall be provided in a single line and shall terminated 
by end-of-line symbol.
* The distances between towns exists, i.e. >0.
* Town names are in range A-Z. (Although, the task description says,
that it's in range A..D, samples include E as well.)
* Upper/lower case is ignored.
* Each distance between two towns shall fit into 32-bit integer. As
well as each route's distance. The failure to use that will result in
overflows. Otherwise Long or BigInteger data types needed to be used.
* No business-specific exceptions are generated. Yet, in more advanced
applications, there might be a need to distinguish between different 
error types.  
