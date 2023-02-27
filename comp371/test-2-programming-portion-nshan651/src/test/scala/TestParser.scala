package nanol

import org.scalatest.funsuite.AnyFunSuite

class TestParser extends AnyFunSuite:
  // only assert successful or isEmpty (failure)

  val prefix = "parser works for "

  /** A. (hello (world what ()) (up)) */
  test(prefix + NanoSources.a) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.a)
    assert(result.successful)
  }

  /** B. (hello world what up) */
  test(prefix + NanoSources.b) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.b)
    assert(result.successful)
  }

  /** C. (hello) */
  test(prefix + NanoSources.c) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.c)
    assert(result.successful)
  }

  /** D. the empty string */
  test(prefix + NanoSources.d) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.d)
    assert(result.isEmpty)
  }

  /** E. ( ( ( ) ) ( ) ) */
  test(prefix + NanoSources.e) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.e)
    assert(result.successful)
  }

  /** F. ("hello" "world" "what" "up") */
  test(prefix + NanoSources.f) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.f)
    assert(result.isEmpty)
  }

  /** G. ( ( ) ) ( ) ) */
  test(prefix + NanoSources.g) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.g)
    assert(result.isEmpty)
  }

  /** H. hello */
  test(prefix + NanoSources.h) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.h)
    assert(result.successful) // example of an invalid source string
  }

  /** I. ( ( hello world ) ( what up ) ) */
  test(prefix + NanoSources.i) {
    val result = NanoParser.parseAll(NanoParser.elem, NanoSources.i)
    assert(result.successful)
    assert(result.get == NanoASTs.elem) // omit this second assertion from the remaining tests below
  }


end TestParser
