# What it does

1.  The code starts by creating the `graph` variable, which is a `HashMap` representing a **string** mapped to a **string and integer** 
    
2.  The code loops and asks the user to enter cities and their connections in the format "source, distance, destination" until the user enters "stop". The inputs are parsed and added to  `graph` accordingly.
    
3.  The code uses **3 main maps** for Dijkstra's algorithm, such as the map `distances` (to store the shortest distance from the source to each node), `previousNodes` (to store the previous nodes in the shortest path), and `visited` (to keep track of the visited nodes).
    
4.  Dijkstra's algorithm is executed. This works by iteratively calculating a distance for each node in the graph, starting from the start node, and continuing until we reach the end node. 
    
5.  After the algorithm finishes, the code checks if the existing path is not the java integer limit. If there is a path, it prints the shortest distance, the path itself separated by arrows, and the time it takes to travel the path. `(assuming a speed of 17 km/h)`.
    
6.  If there isn't a path it'll print that to the console as well.
    
7.  The `getPath` method is an additional method used to reconstruct the path from the `previousNodes` that were tracked during the algorithm execution. *This is for the printout to the console.*

