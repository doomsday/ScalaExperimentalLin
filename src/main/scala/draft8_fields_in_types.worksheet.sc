class Name(s: String):
  private var _value: String = s
  def value: String = _value
  def value_=(newValue: String): Unit = _value = newValue

val n = Name("Dean")
n.value
n.value = "Buck"
n.value
