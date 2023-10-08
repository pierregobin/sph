
package counterproject
import spinal.core._

case class Counter(width: Int) extends Component {
  val io = new Bundle {
    val clean = in Bool()
    val value = out UInt(width bits)
    val full = out Bool()
  }
  val counter = Reg(UInt(width bits)) init(0)

  counter := counter + 1
  when(io.clean) {
    counter := 0
  }

  io.value := counter
  io.full := counter === (U"1" << width) -1 
}
case class CounterMem(width: Int) extends Component {
  val io = new Bundle {
    val clean = in Bool()
    val value = out UInt(width bits)
    val full = out Bool()
  }
  val memory = Mem(Bits(32 bits),1 << width)
  val counter = Reg(UInt(width bits)) init(0)

  memory(counter) := B(100-counter,32 bits)
  counter := (counter + 1) & ((U"1" << width) - 1).resize(width)
  when(io.clean) {
    counter := 0
  }

  io.value := counter
  report(L"counter = $counter")
  io.full := counter === (U"1" << width) -1 
}
