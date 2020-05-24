package michallenge.iterator

import scala.annotation.tailrec
import scala.collection.Iterator

/*
Task:
Есть итератор, каждый элемент которого либо строка, либо такой же итератор.
Нужно написать обертку в виде чистого итератора со строками.
class FlatIterator(i: Iterator[Any]) extends Iterator[String] {}
или
def flatten(i: Iterator[Any]): Iterator[String]
Iterator("1", Iterator("1.1", "1.2"), "2", Iterator(Iterator("2.1.1"), "2.2"))
Ожидаемый вывод: 1, 1.1, 1.2, 2, 2.1.1, 2.2
 */

object StringIterator {
  def flatten(iterator: Iterator[Any]): Iterator[String] = {
    @tailrec
    def loop(acc: Iterator[String] = Iterator()): Iterator[String] = {
      if (iterator.hasNext) {
        iterator.next() match {
          case str: String => loop(acc ++ Iterator(str))
          case innerIterator: Iterator[Any] =>
            loop(acc ++ flatten(innerIterator))
        }
      } else acc
    }
    loop()
  }
}
