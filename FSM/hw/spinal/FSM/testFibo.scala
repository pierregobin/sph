package FSM

import spinal.core._
import spinal.core.sim._
// import scala.collection.mutable.ArrayBuffer
// import scala.io.Source
//

object testFibo extends App {
  SimConfig.withWave.compile(new Fibo(maxi=15)).doSim {
    dut =>
      dut.clockDomain.forkStimulus(64) 
      dut.io.start #= true
      dut.clockDomain.waitSampling()
      dut.io.start #= false
      dut.clockDomain.waitSampling()
      for(a <- 1 to 64) {
        dut.clockDomain.waitSampling()
        
      }
  }
}


object testFibo2 extends App {
  SimConfig.withWave.compile(new Fibo(maxi=15)).doSim {
    dut =>
      dut.clockDomain.forkStimulus(76) 
      dut.io.start #= false
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.start #= true
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.start #= false
      dut.clockDomain.waitSampling()
      for(a <- 1 to 32) {
        dut.clockDomain.waitSampling()
      }
      dut.io.start #= true
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.start #= false
      dut.clockDomain.waitSampling()
      for(a <- 1 to 32) {
        dut.clockDomain.waitSampling()
      }
  }
}

