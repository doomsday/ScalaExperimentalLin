//import scala.annotation.targetName
//
//object A:
//  @targetName("TIEFighter1") case class <+>[A,B](a: A, b: B)
//  infix case class tie[A,B](a: A, b: B)
//
//object Main1:
//  import A.{<+>, tie}
//
//  def main(args: Array[String]): Unit =
//    import A.{tie, <+>}
//    val ab1: Int <+> String = 1 <+> "one"
//    val ab2: Int <+> String = <+>(1, "one")
//
//
//    val ab3: Int tie String = 1 tie "one"
//    val ab4: Int tie String = tie(1, "one")
