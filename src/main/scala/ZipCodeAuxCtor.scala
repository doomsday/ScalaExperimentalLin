// src/script/scala/progscala3/basicoop/people/ZipCodeAuxConstructors.scala

case class ZipCodeAuxCtor(zip: Int, extension: Int = 0):
  override def toString: String =
    if extension != 0 then s"$zip-$extension" else zip.toString
  def this(zip: String, extension: String) =
    this(zip.toInt, if extension.isEmpty then 0 else extension.toInt)
  def this(zip: String) = this(zip, "")



object ZipCodeAuxCtor:
  def main(args: Array[String]): Unit =
    ZipCodeAuxCtor(12345)
    ZipCodeAuxCtor(12345, 6789)
    ZipCodeAuxCtor("12345")
    ZipCodeAuxCtor("12345", "6789")
