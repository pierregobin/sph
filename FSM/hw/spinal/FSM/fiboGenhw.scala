package FSM

import spinal.core._

object fiboHw {
  def main(args:Array[String]) {
    println("generating Hardware")
    SpinalConfig(targetDirectory="rtl").generateVerilog(Fibo(maxi = 127))
  }
}


// object fiboHw2 {
//   def main(args:Array[String]) {
//     println("generating Hardware")
//     SpinalConfig(targetDirectory="rtl").generateVerilog(testFibo2)
//   }
// }

