`MemorySupport` / `MemoryAssist` &amp; `ObjectPool`s &rarr; for allocating and reusing memory (ie FlyWeight pattern). Save in `ObjectPool`s (might generate uuid per pool so that
separate consumers can request separate pools for objects of the same type, if they don't want to reuse an existing pool - just an option)
Do by type:
- allocate(Class or TypeReference, number of objects (optional - overload method));
- ‎request(type) / request(type, number);
- ‎release(obj) / release(collection of obj)
- also could do checkIn(T), checkOut(): T instead / in addition (?), aka borrow(): T and return(T)

It's up to consumers to initialize and reset / clear objects (OR MAYBE NOT -- typical object pool pattern places that responsibility on the ObjectPool itself!).
Might send a supplier/function to say what to do to initialize / reset object upon creating the pool. `Initializer` & `Resetter` (?). Becomes members of the ObjectPool
(ie dependencies)
- ‎expiration on objects? Why or why not?
- ‎poolSize vs both minPoolSize AND maxPoolSize
-‎ object validation?