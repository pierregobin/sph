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
    val done = Reg(Bool) init(False)
    val computeDone = Reg(Bool) init(False)
    io.data := B(fibo1)
    io.address := B(counter)
    io.initDone := done
    io.computeDone := computeDone

    val initFSM : State = new State with EntryPoint {
      onEntry(done := False)
      whenIsActive {
        when(io.start) {
          goto(initMem)
        }
      }
    }
    val initMem : State = new State {
      onEntry(counter := 0)
      onEntry(computeDone := False)
      whenIsActive {
        counter := counter + 1
        when(counter === maxi) {
          goto(computeMem)
        }
      }
      onExit(done := True)
    }
    val computeMem : State = new State {
      onEntry(counter := 0)
      whenIsActive {
        fibo0 := fibo1
        fibo1 := fibo0 + fibo1
        counter := counter+1
        when(counter === maxi) {
          goto(initFSM)
        }
      }
      onExit(computeDone := True)
    }

  }
}

