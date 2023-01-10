import contexts3.json.ToJSON
import contexts3.shapes.{Circle, Point, Rectangle, Shape, Triangle}

given ToJSON[Point] with
  extension (point: Point)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
         |${indent}"x": "${point.x}",
         |${indent}"y": "${point.y}"
         |$outdent}""".stripMargin

given circleToJSON: ToJSON[Circle] with
  def toJSON2(circle: Circle, name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
       |${indent}${circle.center.toJSON("center", level + 1)},
       |${indent}"radius": ${circle.radius}
       |$outdent}""".stripMargin
  extension (circle: Circle)
    def toJSON(name: String = "", level: Int = 0): String =
      toJSON2(circle, name, level)

given rectangleToJSON: ToJSON[Rectangle] with
  def toJSON2(rect: Rectangle, name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
       |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
       |${indent}"height":    ${rect.height}
       |${indent}"width":     ${rect.width}
       |$outdent}""".stripMargin
  extension (rect: Rectangle)
    def toJSON(name: String = "", level: Int = 0): String =
      toJSON2(rect, name, level)

given triangleToJSON: ToJSON[Triangle] with
  def toJSON2(tri: Triangle, name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
       |$indent${tri.point1.toJSON("point1", level + 1)},
       |$indent${tri.point2.toJSON("point2", level + 1)},
       |$indent${tri.point3.toJSON("point3", level + 1)}
       |$outdent}""".stripMargin
  extension (tri: Triangle)
    def toJSON(name: String = "", level: Int = 0): String =
      toJSON2(tri, name, level)

given ToJSON[Shape] with
  extension (shape: Shape)
    def toJSON(name: String = "", level: Int = 0): String =
      shape match
        case c: Circle => circleToJSON.toJSON2(c, name, level)
        case r: Rectangle => rectangleToJSON.toJSON2(r, name, level)
        case t: Triangle => triangleToJSON.toJSON2(t, name, level)

Seq(Circle(Point(1.0, 2.0), 1.0), Rectangle(Point(2.0, 3.0), 2, 5)).map(
  shape => shape.toJSON("shape", 0)
)
