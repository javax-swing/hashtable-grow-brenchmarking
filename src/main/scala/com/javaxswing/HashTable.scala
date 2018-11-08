package com.javaxswing

case class Node(hash: Int, var next: Node)

object HashTable {

  def apply(size: Int, hashes: Int*): Array[Node] = {
    val array = new Array[Node](size)
    hashes.foreach(addToArray(_, array))
    array
  }

  // for testing the grow
  private def addToArray(hash: Int, array: Array[Node]): Unit = {
    val bucket = hash & (array.length - 1)
    array(bucket) = Node(hash, array(bucket))
  }

  def sizeOf(array: Array[Node]): Int = {
    var size = 0
    array.foreach { node =>
      var n = node
      while (n ne null) {
        size += 1
        n = n.next
      }
    }
    size
  }
}
