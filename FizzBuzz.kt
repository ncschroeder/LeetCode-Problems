/*
Iterate through ints in the range of 1 to 100, inclusive. For each int, if it's divisible by 3 and not by 5, print "Fizz".
If it's divisible by 5 and not by 3, print "Buzz". If it's divisible by both 3 and 5, print "FizzBuzz". If it isn't
divisible by either of those, print the int.

I got this challenge in an interview 1 time and I used Java to solve it. I think the solution looked something like this:

static void fizzBuzz() {
    for (int i = 1; i <= 100; i++) {
        boolean isDivisibleBy3 = i % 3 == 0;
        boolean isDivisibleBy5 = i % 5 == 0;
        boolean isDivisibleByNeither = !isDivisibleBy3 && !isDivisibleBy5;

        if (isDivisibleBy3) {
            System.out.print("Fizz");
        }

        if (isDivisibleBy5) {
            System.out.print("Buzz");
        }

        if (isDivisibleByNeither) {
            System.out.println(i);
        } else {
            System.out.println();
        }
    }
}


Here's a concise string concatenation based solution
*/
fun fizzBuzz() {
    for (i in 1..100) {
        var s = ""
        if (i % 3 == 0) {
            s = "Fizz"
        }
        if (i % 5 == 0) {
            s += "Buzz"
        }
        println(s.ifEmpty { i })
    }
}