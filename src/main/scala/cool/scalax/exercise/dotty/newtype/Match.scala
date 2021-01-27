package cool.scalax.exercise.dotty.newtype

/**
 * 匹配类型
 *
 * @author Rubin
 * @version v1 2021/1/27 14:29
 */
class Match {

  class M1:
    type Elem[X] = X match
    case String => Char
    case Array[t]
    => t
    case Iterable[t]
    => t


  // =:= 理解为，左手侧和右手侧彼此是彼此的子类型。
  //  Elem[String]       =:=  Char
  //  Elem[Array[Int]]   =:=  Int
  //  Elem[List[Float]]  =:=  Float
  //  Elem[Nil.type]     =:=  Nothing


  // TODO 还没搞明白！！

}
