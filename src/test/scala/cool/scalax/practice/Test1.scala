package cool.scalax.practice

import cool.scalax.practice.dotty.msg
import org.junit.Assert._
import org.junit.Test

class Test1 {
  @Test def t1(): Unit = {
    assertEquals("I was compiled by Scala 3. :)", msg)
  }
}