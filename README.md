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
