package com.javaxswing

object HashTableGrowNew {

  /**
    * Grows a table to the desired size, the old array will be broken after this method is ran (because the nodes are mutable).
    * The size MUST be a power of 2
    *
    * To grow, we relink the Node structures and place them in new buckets based on the hash using a bitMask generated from the old and new size.
    *
    * For example (if we were using bytes)
    *
    * Size from 16 to 32
    * 16				00001000
    * 32				00010000
    * mask			00001000
    *
    * Size from 16 to 128
    *
    * 16				00001000
    * 128			01000000
    * mask			00111000
    *
    * This is actually just newLen - oldLen
    *
    * We perform a bitwise & on the hash with the mask above, this gets us a bucket delta
    * So if the hash were 31 and the bucket went from size 16 to 32
    *
    * Start Bucket = 31 & (16 - 1) = 15
    * Delta				= 31 & 16				= 16
    * End Bucket	  = 16 + 15 		  = 31
    */
  def grow(newLength : Int, table : Array[Node]) : Array[Node] = {
    val oldLength = table.length
    val newTable = new Array[Node](newLength)

    // for calculations
    val sizeMask = newLength - oldLength
    val growFactor = newLength / oldLength
    val lastNodesTable = new Array[Node](growFactor)

    // populate the new array
    var i = 0
    while(i < oldLength) {

      // split the nodes into the correct buckets
      var node = table(i)
      while(node ne null) {
        val delta = node.hash & sizeMask
        val bucket = i + delta
        val lastIndex = delta / oldLength

        if (newTable(bucket) eq null) {
          newTable(bucket) = node
          lastNodesTable(lastIndex) = node
        } else {
          lastNodesTable(lastIndex).next = node
          lastNodesTable(lastIndex) = node
        }
        node = node.next
      }
      i += 1

      // wipe lastNodesTable and trim the linked lists
      var j = 0
      while (j < growFactor) {
        if(lastNodesTable(j) ne null) {
          lastNodesTable(j).next = null
          lastNodesTable(j) = null
        }
        j += 1
      }
    }

    newTable
  }

}
