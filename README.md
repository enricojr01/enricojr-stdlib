# ABOUT
I recently watched the lecture videos for MIT's 6.006 Introduction to Algorithms course (available 
on Youtube & MIT's OpenCourseWare), and created this repository to practice what I've learned. This 
README doubles as both the documentation for the project as well as my notes, all carefully  
gathered here in the hopes they'll be useful to other learners like myself.

The class itself used Python, but I just happened to be working with Java at the time, so I've 
implemented all data structures using Java.

# CONCEPTS
## Set / Sequence
Both `set` and `sequence` refer to ordered collections of items, with the main difference between 
them being where the "order" arises from. 

The order of items in a sequence corresponds to the order in which they appear in the sequence, 
i.e. it is extrinsic to the item.

The order in a set is derived from some property of the item, called its `key`, i.e. order is 
intrinsic to the item. Sometimes the item itself IS the key. 

## Interface vs Data Structure
An interface describes a set of operations on data that should be performed, whereas a data 
structure describes how the data should be stored and how those interface operations are to be 
carried out.

Different data structures may implement the same interface with different performance.

## Measuring Performance
The performance of an algorithm is not measured in terms of time, the speed of a CPU + other 
factors can cause the same program to have different runtimes across different computers.

Algorithms are instead measured by the number of operations they perform, and its performance 
described using asymptotic notation that describes how the runtime of an algorithms scales with the 
size of its input, usually denoted by the lowercase letter `n`.

`Big-O Notation`, written simlar to "O(n)", is used to describe the worst-case runtime of an 
algorithm.

`Big Omega Notation` (TODO: find out how to write that symbol), is used to describe the the 
best-case runtime of an algorithm.

`Big Theta Notation`, is used to describe the average-case run time of an algorithm

These notations are often used as shorthands to describe classes of algorithmic complexity, listed 
here fastest runtime to slowest runtime:

Theta(1) - "Constant Time", i.e. an algorithm completes in a fixed amount of time regardless of the 
size of the input data.

Theta(log n) - "Logarithmic", i.e. the runtime of an algorithm grows equal to `log n` where n is 
the size of the input data.

Theta(n) - "Linear", i.e. the runtime of an algorithm increases in proportion to the size of 
the input data.

Theta(n^2) - "Quadratic", i.e. the runtime of an algorithm increases exponentially with the size of 
the data.

Theta(n^c) - "Polynomial", i.e the runtime of an algorithm increases by n ^ c yada yada.

# INTERFACES
## SequenceInterface
A Java interface representing the set of operations that a Sequence is meant to be able to perform.

```java
public interface SequenceInterface<T> {
    // returns the number of items in the sequence.
    int len();

    // returns the item at position x in the sequence.
    T getAt(int x);

    // places the item of type T at position x in the sequence, _overwriting its contents_
    void setAt(int x, T item);

    // convenience method - wraps a call to getAt(0)
    T getFirst();

    // convenience method - wraps a call to getAt(sequence.length - 1)
    T getLast();

    // convenience method - wraps a call to setAt(0)
    void setFirst();

    // convenience method - wraps a call to setAt(sequence.length - 1)
    void setLast();
}
```

The Java array already provides the functionality we are looking for, but the concrete class 
`StaticArray` wraps the Java array anyways to provide an interface that conforms to the 
specification here.

The class defined a `build()` function on all of the interfaces described, but Java's constructors 
stand in for that already, so `build()` has been excluded from the interface definition.

`iterable()` will be provided by implementing Java's Iterator interface, and custom Iterators where 
appropriate.

The methods are annotated below for documentation purposes.


## DynamicArrayInterface
An companion to the SequenceInterface that describes behavior required for Dynamic Arrays (ones 
that resize in response to inserts / deletes).

```java
public interface DynamicArrayInterface<T> {
    void insertAt(int idx, T item);
    void deleteAt(int idx);
    void insertFirst(T item);
    void insertLast(T item);
    void deleteFirst();
    void deleteLast();
}
```

The difference between setAt() on StaticArray and insertAt() here is that setAt(x, item) will 
_overwrite_ `sequence[x]` with item, whereas insertAt(x, item) will move items from sequence[x] 
onwards one position forward before placing item at sequence[x].

In a similar fashion, deleteAt(x) / deleteFirst(x) / deleteLast(x) will shift items one position 
backwards, thereby erasing the item at `sequence[x]`.

All insert and delete methods will trigger a resize of the array when necessary, according to the 
following criteria:

- The class declares a `chunkSize` constant of 8.
- After a delete, if the itemCount is less than or equal to 25% of the length of the StaticArray, 
then a smaller StaticArray will be created and the items copied over.
- Before an insert, if `itemCount + 1` is greater than `sequence.length`, then a new StaticArray
is created, with a size equal to the next highest mutltiple of 8 (based on itemCount), and the 
items copied over.

Although the DynamicArray concrete class implements both SequenceInterface and 
DynamicArrayInterface, calling the `setAt()` method will throw an UnsupportedOperationException 
because I've deemed it safer to use the `insertAt()` method instead, rather than duplicating the
resize logic in both functions, or cook up a way for one to call the other and vice versa. So the 
`setAt()` method is implemented simply to conform to the interface but is otherwise nonfunctional.
