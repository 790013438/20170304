Call‐by‐Value Arguments.
In Java, all method arguments are call‐by‐value. This means that if the argument is a primitive
type, its value (not its address) is passed to the method, so the method can’t modify the
argument value and have the modification remain after return from the method. Some other
programming languages provide a call‐by‐reference or call‐by‐address mechanism so that a
method can modify a primitive‐type argument.
If the argument is of a class type, the value that is passed to the method is the value of the
reference variable, not the value of the object itself (see Section A.2). The reference variable
value points to the object, allowing the method to access the object itself using the methods
of the object’s own class. Any modification to the object will remain after the return from the
method. This will be discussed in Section A.7.
