import contexts3.json.ToJSON
import contexts3.shapes.{Circle, Point, Rectangle, Shape, Triangle}

given ToJSON[Point] with
  extension (point: Point)
    override def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
         |$indent"x": "${point.x}",
         |$indent"y": "${point.y}",
         |$outdent}""".stripMargin

given ToJSON[Circle] with
  extension (circle: Circle)
    override def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
         |$indent${circle.center.toJSON("center", level + 1)},
         |$indent"radius": ${circle.radius}
         |$outdent}""".stripMargin

given ToJSON[Rectangle] with
  extension (rect: Rectangle)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
         |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
         |${indent}"height":    ${rect.height}
         |${indent}"width":     ${rect.width}
         |$outdent}""".stripMargin

given ToJSON[Shape] with
  extension (shape: Shape)
    override def toJSON(name: String = "", level: Int = 0): String =
      shape match
        case c: Circle => summon[ToJSON[Circle]].toJSON(c)(name, level)
        case r: Rectangle => summon[ToJSON[Rectangle]].toJSON(r)(name, level)

println(Point(1.0,2.0).toJSON())

println(Circle(Point(1.0,2.0), 1.0).toJSON("circle", 0))

Seq(Circle(Point(1.0,2.0), 1.0), Rectangle(Point(2.0,3.0), 2, 5)).map(
  shape => shape.toJSON("shape", 0)
)
