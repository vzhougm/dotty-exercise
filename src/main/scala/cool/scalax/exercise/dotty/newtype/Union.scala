package cool.scalax.exercise.dotty.newtype

import cool.scalax.exercise.dotty.newtype.Union.{Password, UserName}

/**
 * | 联合类型
 *
 * 一个联合类型 A | B 具有作为值类型的所有值A类型的所有值也B。
 *
 * @author Rubin
 * @version v1 2021/1/27 14:07
 */
class Union {
  // 仅当明确指定联合类型时，编译器才会为其指定联合类型。可以在以下REPL成绩单中看到：

  val password = Password(123)
  //val password: Password = Password(123)

  val name = UserName("Eve")
  //val name: UserName = UserName(Eve)

  if (true) then name else password
  //val res2: Object & Product = UserName(Eve)
  
  val either: Password | UserName = if true then name else password
  //val either: Password | UserName = UserName(Eve)

}

object Union {

  case class UserName(name: String)

  case class Password(hash: Int)

  def lookupPassword(hash: Any) = ???
  def lookupName(name: String) = ???

  def help(id: UserName | Password) =
    val user = id match
      case UserName(name) => lookupName(name)
      case Password(hash) => lookupPassword(hash)

  // 联合类型是交集类型的对偶。|是可交换的：A | B与相同的类型B | A。
  def help2(id: Password | UserName) =
    val user = id match
      case UserName(name) => lookupName(name)
      case Password(hash) => lookupPassword(hash)
  
}
