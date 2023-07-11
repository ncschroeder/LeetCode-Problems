/*
Write a function that has a string param and then returns that string reversed.

I got this in 2 interviews. The 1st time, I used Java and the solution looked something like this:

static void String reverseString(String s) {
    String reverseString = "";
    for (int i = s.length() - 1; i >= 0; i--) {
        reverseString += s.charAt(i);
    }
    return reverseString;
}

For the 2nd interview, I decided to use JavaScript and was aware of a way to reverse a string and I used it.
The solution was something like this:

function reverseString(aString) {
    return Array.from(aString).reverse().join('');
}

After giving that as the solution, I was told by the interviewer to pretend that there wasn't a reverse method
so I did the JavaScript equivalent of the Java solution above.

Below is what I would do with Kotlin.
*/
fun reverseString(s: String): String =
    buildString(capacity = s.length) {
        for (i in s.lastIndex downTo 0) {
            append(s[i])
        }
    }
