# reactive-programming

## What is reactive Programming ?

Reactive programming is an asynchronous programming paradigm that revolves around
data streams and the propagation of change. In simpler words, those programs which
propagate all the changes that affected its data/data streams to all the interested parties
(such as end users, components and sub-parts, and other programs that are somehow
related) are called reactive programs.

## Why ?

 + Get rid of the callback hell
 + Standard mechanism for error handling
 + It's a lot simpler than regular threading
 + Straightforward way for async operations
 + One for everything, the same API for every operations
 + The functional way
 + Maintainable and testable code:
 
## Reactive Manifesto 

http://www.reactivemanifesto.org

 + Responsive: The system responds in a timely manner. Responsive systems focus on providing
rapid and consistent response times, so they deliver a consistent quality of
service.
 + Resilient: In case the system faces any failure, it stays responsive. Resilience is achieved by
replication, containment, isolation, and delegation. Failures are contained within
each component, isolating components from each other, so when failure has
occurred in a component, it will not affect the other components or the system as
a whole.
 + Elastic: Reactive systems can react to changes and stay responsive under varying
workload. They achieve elasticity in a cost effective way on commodity hardware
and software platforms.
 + Message driven: In order to establish the resilient principle, reactive systems need to establish a
boundary between components by relying on asynchronous message passing.


### Observable Creation

```java
Observable.create<String> {
 it.onNext("Emit 1")
 it.onNext("Emit 2")
 it.onNext("Emit 3")
 it.onNext("Emit 4")
 it.onError(Exception("My Custom Exception"))
}
```

```java
Observable.fromArray()
Observable.fromIterator()
Observable.fromCallable()
Observable.fromPublisher()
```

```java
Observable.just("A String").subscribe(observer)
```

```java
Observable.range(1,10).subscribe(observer)
```

```java
Observable.interval(100,TimeUnit.MILLISECONDS)
```

### Subscribers

The subscribe operator serves the purpose of the media by connecting an Observable to
Observer . We can pass one to three methods ( onNext , onComplete , onError ) to the
subscribe operator, or we can pass an instance of the Observer interface to the
subscribe operator to get the Observable connected with an Observer .

### Cold Observable

if you subscribe to the same Observable multiple times, you will get the emissions from the beginning for all the
subscriptions

### Hot Observable

Cold Observables are passive, they don't emit anything until subscribe is called. Hot Observables are contrary to Cold Observables ; it doesn't need subscriptions to start emission. While you can compare Cold Observables to CD/DVD recordings, Hot Observables are like TV channels—they continue broadcasting (emitting) their content, irrespective of whether anyone is watching (Observing) it or not.
Hot Observables resemble events more than data. The events may carry data with them, but there is a time-sensitive component where Observers that subscribed lately can miss out previously emitted data. They are specifically useful for UI events while working with Android/JavaFX/Swing. They are also very useful in resembling server requests.

### Subjects

Another great way to implement Hot Observables is Subject . Basically, it is acombination of Observable and Observer , as it has many common behaviors to bothObservables and Observers . Like Hot Observables , it maintains an internal Observer list and relays a single push to every Observer subscribed to it at the time of emission.

 + AsyncSubject
 + PublishSubject
 + BehaviorSubject
 + ReplaySubject
 
 ```java
 val subject = BehaviorSubject.create<Int>()
```
### Flowables (backpressure)

The only problem with Observable is when an Observer cannot cope with the pace of an Observable.
Backpressure is not supported in Observables and Observers , the solution could be using Flowables and Subscribers instead.
Flowable hosts the default buffer size of 128 elements for operators, so, when the consumer is taking time, the emitted items may wait in the buffer.

Flowable, instead of emitting all the items, emitted few items in a chunk, waited for the consumer to coup up then again continued, and completed in an interleaved manner. This reduces a lot of problems itself.

**When to use Flowables?**
 + Flowables and backpressure are meant to help deal with larger amounts of data. So, use flowable if your source may emit 10,000+ items. Especially when the source is asynchronous so that the consumer chain may ask the producer to limit/regulate emissions when required.
 + If you are reading from/parsing a file or database.
 + When you want to emit from network IO operations/Streaming APIs that support
 + blocking while returning results, which is how many IO sources work.

**When to use Observables?**

 + When you are dealing with a smaller amount of data (less than 10,000 emissions)
 + When you are performing strictly synchronous operations or operations with limited concurrency
 + When you are emitting UI events (while working with Android, JavaFX, or Swing)

Also, keep in mind that Flowables are slower in comparison to Observables.

See **BackpressureStrategy.java**

### schedulers

 + **Schedulers.io() - I/O bound scheduler** : By I/O operations, we mean interactions with file systems, databases, services, or I/O devices. We should be cautious about using this scheduler as it can create an infinite number of
threads (until the memory lasts) and can cause OutOfMemory errors.
 + **Schedulers.computation() - CPU bound schedulers** : the most useful scheduler for programmers. It provides us with a bounded thread-pool, which can contain a number of threads equal to the number of available CPU cores. As the name suggests, this scheduler is meant for CPU intense works. 
 + **Schedulers.newThread()** : 
 + **Schedulers.single()** : 
 + **Schedulers.trampoline()** :
 + **Schedulers.from()** :
