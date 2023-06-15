/*
https://leetcode.com/problems/destination-city/

You are given the array paths, where paths[i] = [cityA<sub>i</sub>, cityB<sub>i</sub>] means there exists a direct path going from cityA<sub>i</sub> to cityB<sub>i</sub>. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

Example:
Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo" 
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
*/
fun destCity(paths: List<List<String>>): String {
    val pathsSequence: Sequence<List<String>> = paths.asSequence()

    val startCities: Set<String> =
        pathsSequence
        .map { ( start, _ ) -> start }
        .toCollection(HashSet(initialCapacity = paths.size))

    // Return the 1st end city that isn't one of the start cities
    return pathsSequence
        .map { ( _, end ) -> end }
        .first { it !in startCities }
}