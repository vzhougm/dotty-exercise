package cool.scalax.practice

import zio.test.Assertion.equalTo
import zio.test.junit.JUnitRunnableSpec
import zio.test.{assert, suite, test}

/**
 * @author Rubin
 * @version v1 2021/1/26 14:05
 */
object MainSpec extends JUnitRunnableSpec {

  def spec = suite("Test environment")(
    test("expect call with input satisfying assertion") {
      assert(40 + 2)(equalTo(42))
    }
  )
}
