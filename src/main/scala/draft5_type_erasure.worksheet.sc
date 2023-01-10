import scala.annotation.targetName

object O:
  @targetName("m_seq_int") def m(is: Seq[Int]): Int = is.sum
  @targetName("m_seq_string") def m(ss: Seq[String]): Int = ss.length

println(O.m(Seq(1,2,3,4)))
println(O.m(Seq("a","b","c")))
