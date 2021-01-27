package cool.scalax.exercise.dotty.newtype

/**
 * Lambdas类型
 *
 * @author Rubin
 * @version v1 2021/1/27 14:20
 */
class Lambda {

  //[X, Y] =>> Map[Y, X]

  //[X >: L <: U] =>> F[X]

  trait L1

  trait U1

  trait L2

  trait U2

  trait R1

  trait R2

  type TL1 =[X >: L1 <: U1] =>> R1
  type TL2 =[X >: L2 <: U2] =>> R2
}
