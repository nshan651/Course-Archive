object Main {
  def main(args: Array[String]) {
    val incoming = Array(1,2,3,4,5)
    val suma = incoming.map(s => s*3)
    //val suma = incoming.map(s => s.length).sum
    print(suma)
  }
}
