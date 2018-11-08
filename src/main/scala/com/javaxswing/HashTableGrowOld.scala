package com.javaxswing

object HashTableGrowOld {

  def grow(newlen : Int, ot : Array[Node]) : Array[Node] = {
    var oldlen = ot.length
    val table = java.util.Arrays.copyOf(ot, newlen)
    val preLow: Node = Node(0, null)
    val preHigh: Node = Node(0, null)
    // Split buckets until the new length has been reached. This could be done more
    // efficiently when growing an already filled table to more than double the size.
    while(oldlen < newlen) {
      var i = 0
      while (i < oldlen) {
        val old = table(i)
        if(old ne null) {
          preLow.next = null
          preHigh.next = null
          var lastLow: Node = preLow
          var lastHigh: Node = preHigh
          var n = old
          while(n ne null) {
            val next = n.next
            if((n.hash & oldlen) == 0) { // keep low
              lastLow.next = n
              lastLow = n
            } else { // move to high
              lastHigh.next = n
              lastHigh = n
            }
            n = next
          }
          lastLow.next = null
          if(old ne preLow.next) table(i) = preLow.next
          if(preHigh.next ne null) {
            table(i + oldlen) = preHigh.next
            lastHigh.next = null
          }
        }
        i += 1
      }
      oldlen *= 2
    }

    table
  }
}
