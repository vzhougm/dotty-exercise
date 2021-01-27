package cool.scalax.exercise.dotty.newtype

/**
 * 相关函数类型
 * 
 * @author Rubin
 * @version v1 2021/1/27 14:41
 */
object DependentFunction {

  trait Entry { type Key; val key: Key }

  def extractKey(e: Entry): e.Key = e.key

  trait C { type M; val m: M }

  type DF = (x: C) => x.M

  type IDF = (x: C) ?=> x.M

  //@main
  def test =
    val c = new C { type M = Int; val m = 3 }

    val depfun: DF = (x: C) => x.m
    val t = depfun(c)
    println(s"t=$t")   // prints "t=3"

    
    // TODO 没理解
    val idepfun: IDF = summon[C].m
    val u = idepfun(using c)
    println(s"u=$u")   // prints "u=3"

    /////////////////////////////////////////////////

}


// TODO 有点晕，还没去看
trait Effect

// Type X => Y
abstract class Fun[-X, +Y] :
  type Eff <: Effect
  def apply(x: X): Eff ?=> Y

class CanThrow extends Effect
class CanIO extends Effect

given ct: CanThrow = new CanThrow
given ci: CanIO = new CanIO

class I2S extends Fun[Int, String] :
  type Eff = CanThrow
  def apply(x: Int) = x.toString


class S2I extends Fun[String, Int] :
  type Eff = CanIO
  def apply(x: String) = x.length

// def map(f: A => B)(xs: List[A]): List[B]
def map[A, B](f: Fun[A, B])(xs: List[A]): f.Eff ?=> List[B] =
  xs.map(f.apply)

// def mapFn[A, B]: (A => B) -> List[A] -> List[B]
def mapFn[A, B]: (f: Fun[A, B]) => List[A] => f.Eff ?=> List[B] =
  f => xs => map(f)(xs)

// def compose(f: A => B)(g: B => C)(x: A): C
def compose[A, B, C](f: Fun[A, B])(g: Fun[B, C])(x: A):
  f.Eff ?=> g.Eff ?=> C =
    g(f(x))

// def composeFn: (A => B) -> (B => C) -> A -> C
def composeFn[A, B, C]:
(f: Fun[A, B]) => (g: Fun[B, C]) => A => f.Eff ?=> g.Eff ?=> C =
  f => g => x => compose(f)(g)(x)

@main
def test =
  val i2s = new I2S
  val s2i = new S2I

  assert(mapFn(i2s)(List(1, 2, 3)).mkString == "123")
  assert(composeFn(i2s)(s2i)(22) == 2)


