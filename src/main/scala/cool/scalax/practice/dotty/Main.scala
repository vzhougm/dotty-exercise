package cool.scalax.practice.dotty

@main def hello: Unit = {
    println("Hello world!")
    println(msg)
}

def msg = "I was compiled by Scala 3. :)"

trait Animal:
    def speak(): Unit

trait HasTail:
    def wagTail(): Unit

class Dog extends Animal, HasTail:
    def speak() = println("Woof")
    def wagTail() = println("⎞⎜⎛  ⎞⎜⎛")

def test: Unit = {
    val dog = new Dog
    println(dog.speak())
    println(dog.wagTail())
}


trait Tail:
    def wagTail: Unit
    def stopTail: Unit

enum Topping: 
    case Cheese, Pepperoni, Sausage, Mushrooms, Onions

// class Dog2 extends Animal, Tail, Legs, RubberyNose

case class Person(firstName: String, lastName: String, age: Int )