trait Witness
case object IntWitness extends Witness
case object StringWitness extends Witness

def useWitness(using Witness): String = summon[Witness].toString

useWitness

for given Witness <- Seq(IntWitness, StringWitness)
  do println(useWitness)

useWitness

class Test {
  def unit: String = ""
}

