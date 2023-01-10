import scala.util.control.TailCalls.{TailRec, done, tailcall}

def isEven(xs: Seq[Int]): TailRec[Boolean] =
  if xs.isEmpty then done(true) else tailcall(isOdd(xs.tail))

def isOdd(xs: Seq[Int]): TailRec[Boolean] =
  if xs.isEmpty then done(false) else tailcall(isEven(xs.tail))


object Trampoline:
  def main(args: Array[String]): Unit =
    val eo = (1 to 5).map(i => (i, isEven(1 to i).result))
    println(eo)
