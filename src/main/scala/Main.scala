case class SortableSeq[A](seq: Seq[A]):
  def sortBy2[B: Ordering](transform: A => B): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(summon[Ordering[B]]))

object Main:
  def main(args: Array[String]): Unit =
    implicit val oddEven: Ordering[Int] = new Ordering[Int] :
      def compare(i: Int, j: Int): Int = i % 2 compare j % 2 match
        case 0 => i compare j
        case c => c

    val seq = SortableSeq(Seq(1, 3, 5, 2, 4))
    val expected = SortableSeq(Seq(5, 3, 1, 4, 2))
    val result = seq.sortBy2(i => -i)(using oddEven)
    assert(result == expected)

    given evenOdd: Ordering[Int] with
      def compare(i: Int, j: Int): Int = i % 2 compare j % 2 match
        case 0 => i compare j
        case c => -c

    val seq_g = SortableSeq(Seq(1,3,5,2,4))
    val expected_g = SortableSeq(Seq(4, 2, 5, 3, 1))
    val result_g = seq_g.sortBy2(i => -i)(using evenOdd)
    assert(result_g == expected_g)
