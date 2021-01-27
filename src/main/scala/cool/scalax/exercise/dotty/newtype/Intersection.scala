package cool.scalax.exercise.dotty.newtype

import cool.scalax.exercise.dotty.newtype

/**
 * & 交叉类型
 *
 * @author Rubin
 * @version v1 2021/1/27 13:39
 */
class Intersection {

  trait Resettable:
    def reset(): Unit

  trait Growable[T]:
    def add(t: T): Unit

  /**
   * 相交类型A & B的成员是的所有成员A和的所有成员B。例如Resettable & Growable[String]具有成员方法reset和add。
   */
  def f(x: Resettable & Growable[String]) =
    x.reset()
    x.add("first")

  ///////////////////////////////////////////
  
  //
  trait A:
    def children: List[A]

  trait B:
    def children: List[B]


  class C extends A with B {
    // childrenin的类型是in的类型与其in的类型A & B的交集，即。这可以进一步简化为因为是协变的。
    // childrenABList[A] & List[B]List[A & B]List
    def children: List[A & B] = new C :: List.empty[A & B]
  }

  // 写法2
  class C2 extends A, B :
    def children: List[A & B] = new C :: new C2 :: List.empty[A & B]


  val x: A & B = new C

  val ys: List[A & B] = x.children


}


// 子类型化规则
/**
 *
 * T <: A    T <: B
 * ----------------
 * T <: A & B
 *
 * A <: T
 * ----------------
 * A & B <: T
 *
 * B <: T
 * ----------------
 * A & B <: T
 */
// 从上面的规则，我们可以证明这&是可交换的：A & B <: B & A对于任何类型A和B。

/**
 *
 * B <: B           A <: A
 * ----------       -----------
 * A & B <: B       A & B <: A
 * ---------------------------
 * A & B  <:  B & A
 *
 */
// 换句话说，A & B与的类型相同B & A，因为这两种类型具有相同的值并且是彼此的子类型。
//
//如果C是类型构造函数，则C[A] & C[B]可以使用以下三个规则进行简化：
//
//如果C是协变的，C[A] & C[B] ~> C[A & B]
//如果C是对立的C[A] & C[B] ~> C[A | B]
//如果C为非变量，则发出编译错误
//当C为协变时，C[A & B] <: C[A] & C[B]可以得出：
/**
 *
 * A <: A                  B <: B
 * ----------               ---------
 * A & B <: A               A & B <: B
 * ---------------         -----------------
 * C[A & B] <: C[A]          C[A & B] <: C[B]
 * ------------------------------------------
 * C[A & B] <: C[A] & C[B]
 */
// 当C为逆时，C[A | B] <: C[A] & C[B]可以得出：
/**
 *
 * A <: A                        B <: B
 * ----------                     ---------
 * A <: A | B                     B <: A | B
 * -------------------           ----------------
 * C[A | B] <: C[A]              C[A | B] <: C[B]
 * --------------------------------------------------
 * C[A | B] <: C[A] & C[B]
 */

// 擦除

// 对于擦除类型S & T是擦除GLB的擦除型的（最大下界）S和T。下面以伪代码给出了删除相交类型的规则：

/**
 *
 * |S & T| = glb(|S|, |T|)
 *
 * glb(JArray(A), JArray(B)) = JArray(glb(A, B))
 * glb(JArray(T), _)         = JArray(T)
 * glb(_, JArray(T))         = JArray(T)
 * glb(A, B)                 = A                     if A extends B
 * glb(A, B)                 = B                     if B extends A
 * glb(A, _)                 = A                     if A is not a trait
 * glb(_, B)                 = B                     if B is not a trait
 * glb(A, _)                 = A                     // use first
 */
// 在上述中，|T|表示擦除型T，JArray指的Java阵列的类型。


// Relationship with Compound Type (with)
// 交集类型A & B替换A with B了Scala 2中的复合类型。
// 目前，该语法A with B仍被允许并将其解释为A & B，但将来将不建议使用该类型并将其用作类型（与new or extends子句相对）。

