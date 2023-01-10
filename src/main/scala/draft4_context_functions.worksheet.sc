val f = {
  implicit x: Int => x + 10
}

given Int = 5
def d()(implicit x: Int) =
  x + 5
d()


type MagicNumber = Int ?=> Int
def g(): MagicNumber =
  val x = summon[Int]
  x + 5
g()
