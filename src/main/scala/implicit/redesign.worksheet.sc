object NumericExtension {
  extension [T: Numeric](v1: T)
    def add(v2: T): T = summon[Numeric[T]].plus(v1, v2)
}
