package nanol

import org.scalatest.funsuite.AnyFunSuite

import size.*

class TestSize extends AnyFunSuite:

  test("size works for atom") {
    assert(size(NanoASTs.atom) == 1)
  }

  test("size works for simple list") {
    assert(size(NanoASTs.li) == 2)
  }

  test("size works for complex list") {
    assert(size(NanoASTs.elem) == 4)
  }

end TestSize
