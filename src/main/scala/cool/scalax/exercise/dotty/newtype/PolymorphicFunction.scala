package cool.scalax.exercise.dotty.newtype

/**
 * 多态函数类型
 * 
 * @author Rubin
 * @version v1 2021/1/27 14:53
 */
class PolymorphicFunction {

  def foo[A](xs: List[A]): List[A] = xs.reverse

  //当要求方法的调用者提供必须是多态的函数时，多态函数类型特别有用，这意味着它应接受任意类型作为其输入的一部分。
  
  //例如，考虑以下情况：我们有一个数据类型以强类型的方式表示简单语言的表达式（仅由变量和函数应用程序组成）：
  enum Expr[A]:
    case Var(name: String)
    case Apply[A, B](fun: Expr[B => A], arg: Expr[B]) extends Expr[A]


  // TODO (((φ(◎ロ◎;)φ)))

//  case class Var(name: String)
//  case class Apply(var1: Var, var2: Var)


  // 我们想为用户提供一种在给定的所有立即子表达式上映射函数的方法Expr。
  // 这要求给定的函数是多态的，因为每个子表达式可能具有不同的类型。以下是使用多态函数类型实现此方法的方法：
//  def mapSubexpressions[A](e: Expr[A])(f: [B] => Expr[B] => Expr[B]): Expr[A] =
//    e match {
//      case Apply(fun, arg) => Apply(f(fun), f(arg))
//      case Var(n) => Var(n)
//    }
//      
  

//  val e0 = Apply(Var("f"), Var("a"))
//  val e1 = mapSubexpressions(e0)([B] => (se: Expr[B]) => Apply(Var[B => B]("wrap"), se))
//  println(e1) // Apply(Apply(Var(wrap),Var(f)),Apply(Var(wrap),Var(a)))
}
