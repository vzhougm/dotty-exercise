package cool.scalax.exercise.tools.zio

import zio.console.{Console, getStrLn, putStrLn}
import zio.{App, ExitCode, URIO, ZEnv, ZIO}

import java.io.IOException

/**
 * @author Rubin
 * @version v1 2021/1/26 14:04
 */
object Main extends App :

  val app: ZIO[Console, IOException, Unit] =
    for
    _ <- putStrLn("What is your name?")
    name <- getStrLn
    out <- putStrLn(s"Hello $name!")
      yield out

  def run(args: List[String]): URIO[ZEnv, ExitCode] =
    app.exitCode
