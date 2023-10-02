package counterproject

import spinal.core._

object CounterMain {
  def main(args: Array[String]) {
    println("generating hardware")
    println("################# VHDL")
    SpinalConfig(targetDirectory = "rtl").generateVhdl(Counter (width = 4))
    println("################# Verilog")
    SpinalConfig(targetDirectory = "rtl").generateVerilog(Counter (width = 4))
  }
}

