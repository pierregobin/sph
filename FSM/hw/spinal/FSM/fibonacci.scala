package FSM

import spinal.core._
import spinal.lib.fsm._

case class Fibo(maxi: Int) extends Component {
  val io = new Bundle {
    val start = in Bool()
    val initDone = out Bool()
    val computeDone = out Bool()
    val data = out Bits(32 bits)
    val address=out Bits(log2Up(maxi) bits)
  }
  val fsm = new StateMachine {
    val counter=Reg(UInt(log2Up(maxi) bits)) init(0)
    val fibo0 = Reg(UInt(32 bits)) init(0)
    val fibo1 = Reg(UInt(32 bits)) init(1)

    val initFSM : State = new State with EntryPoint {
    io.initDone := False
      when(io.start) {
        goto(initMem)
      }
    }
    val initMem : State = new State {
      onEntry(counter := 0)
      whenIsActive {
        io.data := 0
        io.address := B(counter)
        counter := counter + 1
        when(counter === maxi) {
          goto(computeMem)
        }
      }
      onExit(io.initDone := True)
    }
    val computeMem : State = new State {
      onEntry(counter := 0)
      whenIsActive {
        io.data := B(fibo1)
        io.address := B(counter)
        fibo0 := fibo1
        fibo1 := fibo0 + fibo1
        counter := counter+1
        when(counter === maxi) {
          goto(initFSM)
        }
      }
      onExit(io.computeDone := True)
    }

  }
}

