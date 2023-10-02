package counterproject

import spinal.core._
import spinal.core.sim._
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Tools {

  def readmemh(path: String): Array[BigInt] = {
    val buffer = new ArrayBuffer[BigInt]
    for (line <- Source.fromFile(path).getLines) {
      val tokens: Array[String] = line.split("(//)").map(_.trim)
      if (tokens.length > 0 && tokens(0) != "") {
        val i = Integer.parseInt(tokens(0), 16)
        buffer.append(i)
      }
    }
    buffer.toArray
  }
}

object TestCounter extends App {
  SimConfig.withWave.compile(new Counter(4)).doSim {
    dut =>
    dut.clockDomain.forkStimulus(32)
      for (a <- 0 to 31) {
        dut.clockDomain.waitSampling()
        val z = dut.io.value.toInt
        assert(z == a, s"Got $z, expected $a")

      }
  }
}

object TestCounterMem extends App {
  SimConfig.withWave.compile(new CounterMem(4)).doSim {
    dut =>
    dut.clockDomain.forkStimulus(32)
      for (a <- 0 to 31) {
        dut.clockDomain.waitSampling()
        val z = dut.io.value.toInt
        assert(z == a, s"Got $z, expected $a")

      }
  }
}
