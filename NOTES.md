# ABOUT
I'm keeping all my various notes on the development work here rather than cluttering up the source 
code with them.

```java
/**
 * NOTE: Casting Object[] to T[] is unsafe because there's no way for compiler to guarantee
 * that the Object[] actually contains T[]. The correct way to do this is to create an array
 * of the correct type via reflection:
 * 
 *     Array.newInstance(String.class, size)
 * 
 * Where String.class is replaced with the appropriate class.
 * 
 * T[] is erased at runtime by the compiler and becomes Object[], thus casting Object[] to T[]
 * will succeed regardless of whatever is in the Object[] array, and errors will only appear
 * at runtime when elements are accessed. There is no way for the compiler to guarantee that
 * an Object[] actually contains anything of type T.
 */

/**
 * NOTE: the Java primitives int, char, float, boolean are not "Objects" in the Java sense
 * of the word, they're "raw" i.e. have no identity / hashcode / members / methods. They represent
 * near-literally the values that a CPU would work with. Most of the time, especially with respect
 * to Java's type system and with this particular project, we want to be working with the boxed 
 * versions of the primitive type i.e. Integer, Float, Boolean, etc.
 * 
 * There is a performance penalty to using boxed over primitive, but that can be dealt with later
 * and is arguably worth paying when you consider the type safety you get in return.
 */
```

