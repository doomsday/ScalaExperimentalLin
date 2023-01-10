// tag::definitions[]

import scala.annotation.targetName

trait Semigroup[T]:
  extension (t: T)
    infix def combine(other: T): T                                   // <1>
    @targetName("plus") def <+>(other: T): T = t.combine(other)

trait Monoid[T] extends Semigroup[T]:
  def unit: T                                                        // <2>

given StringMonoid: Monoid[String] with                              // <3>
  println("Initializing StringMonoid")
  def unit: String = ""
  extension (s: String) infix def combine(other: String): String = s + other

//given IntMonoid: Monoid[Int] with
//  def unit: Int = 0
//  extension (i: Int) infix def combine(other: Int): Int = i + other

given NumericMonoid[T: Numeric]: Monoid[T] with
  println("Initializing NumericMonoid")
  def unit: T = summon[Numeric[T]].zero
  extension (t: T) infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)

//given NumericMonoidU[T](using num: Numeric[T]): Monoid[T] with
//  def unit: T = num.zero
//  extension (t: T) infix def combine(other: T): T = num.plus(t, other)

//given [T: Numeric]: Monoid[T] with
//  def unit: T = summon[Numeric[T]].zero
//  extension (t: T) infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)
// or
//given [T](using num: Numeric[T]): Monoid[T] with
//  def unit: T = num.zero
//  extension (t: T) infix def combine(other: T) = num.plus(t, other)

// end::definitions[]

given ByteMonoid: Monoid[Byte] with
  def unit: Byte = 0
  extension (b: Byte) infix def combine(onther: Byte): Byte = (b + onther).toByte

println("2" <+> ("3" <+> "4"))
println(("2" <+> "3") <+> "4")

println(2 <+> (3 <+> 4))
println((2 <+> 3) <+> 4)
println((2 combine 3) combine 4)

println(2.2 <+> (3.3 <+> 4.4))
println((2.2 <+> 3.3) <+> 4.4)
println((2.2 combine 3.3) combine 4.4)

BigDecimal(3.14) <+> NumericMonoid.unit
NumericMonoid[BigDecimal].unit <+> BigDecimal(3.14)
NumericMonoid[BigDecimal].unit combine BigDecimal(3.14)
