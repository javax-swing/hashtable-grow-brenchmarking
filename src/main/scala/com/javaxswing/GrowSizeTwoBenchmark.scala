package com.javaxswing

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

@BenchmarkMode(Array(Mode.AverageTime))
@Fork(2)
@Threads(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
class GrowSizeTwoBenchmark {

  @Param(Array("0", "16", "64", "128", "512", "1024"))
  var size: Int = _
  var hashes: List[Int] = _

  @Setup(Level.Trial) def initHashes(): Unit = {
    hashes = (1 to size).toList
  }

  @Benchmark 
  def oldGrowTable16To32(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowOld.grow(32, HashTable(16, hashes: _*)))
  }

  @Benchmark 
  def newGrowTable16To32(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNew.grow(32, HashTable(16, hashes: _*)))
  }

  @Benchmark 
  def preGrowTable16To32(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNewPre.grow(32, HashTable(16, hashes: _*)))
  }

  @Benchmark
  def oldGrowTable32To64(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowOld.grow(64, HashTable(32, hashes: _*)))
  }

  @Benchmark
  def newGrowTable32To64(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNew.grow(64, HashTable(32, hashes: _*)))
  }

  @Benchmark
  def preGrowTable32To64(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNewPre.grow(64, HashTable(32, hashes: _*)))
  }

  @Benchmark
  def oldGrowTable64To128(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowOld.grow(128, HashTable(64, hashes: _*)))
  }

  @Benchmark
  def newGrowTable64To128(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNew.grow(128, HashTable(64, hashes: _*)))
  }

  @Benchmark
  def preGrowTable64To128(bh: Blackhole): Unit = {
    bh.consume(HashTableGrowNewPre.grow(128, HashTable(64, hashes: _*)))
  }


}
