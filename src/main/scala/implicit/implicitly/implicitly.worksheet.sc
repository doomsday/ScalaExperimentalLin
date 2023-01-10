/**
 * Type classes represent how Scala (and Haskell, of course) implements the so-called ad-hoc polymorphism.
 * Basically, a type class represents a behavior or a characteristic that a class of a generic type T could have.
 * @tparam T Generic type
 */
trait Searchable[T]:
  def uri(obj: T): String

def searchWithImplicit[S](obj: S)(implicit searchable: Searchable[S]): String =
  searchable.uri(obj)

case class Customer(taxCode: String, name: String, surname: String)
case class Policy(policyId: String, description: String)

implicit val searchableCustomer: Searchable[Customer] = new Searchable[Customer]:
  override def uri(customer: Customer): String = s"/customers/${customer.taxCode}"

implicit val searchablePolicy: Searchable[Policy] = new Searchable[Policy]:
  override def uri(policy: Policy): String = s"/policies/${policy.policyId}"

val customer = Customer("taxcode", "name", "surname")
val policy = Policy("policyid", "description")

searchWithImplicit(customer)
searchWithImplicit(policy)

def searchWithContextBound[S: Searchable](obj: S): String =
  val searchable = summon[Searchable[S]]
  searchable.uri(obj)

searchWithContextBound(customer)
searchWithContextBound(policy)
