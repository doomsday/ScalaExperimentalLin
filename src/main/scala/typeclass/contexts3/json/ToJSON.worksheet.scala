package typeclass.contexts3.json

trait ToJSON[T]:
  extension (t: T) def toJSON(name: String = "", level: Int = 0): String
  protected val indent = "  "

  protected def indentation(level: Int): (String, String) =
    (indent * level, indent * (level + 1))

  protected def handleName(name: String): String =
    if name.nonEmpty then s""""$name": """ else ""
