package michallenge.iterator

import org.scalatest.FlatSpec

import scala.collection.Iterator

class StringIteratorTest extends FlatSpec {

  "An empty Set" should "have size 0" in {
    val iterator = Iterator(
      "1",
      Iterator("1.1", "1.2"),
      "2",
      Iterator(Iterator("2.1.1"), "2.2")
    )
    val result = StringIterator.flatten(iterator).toList
    assert(result == List("1", "1.1", "1.2", "2", "2.1.1", "2.2"))
  }
}
