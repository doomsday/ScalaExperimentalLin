case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String
  val INDENTATION = "  "
  def indentation(level: Int = 0): (String, String) =
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
  override def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
       |${indent}"street": "${address.street}",
       |${indent}"city":  "${address.city}"
       |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown")

println(a.toJSON())
