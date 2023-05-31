#include <iostream>
#include <CL/sycl.hpp>

using namespace sycl;

/*
  Hello, World! and a SYCL Program 
Dissection
Figure 1-1 shows a sample SYCL program. Compiling with the DPC++ 
compiler, and running it, results in the following being printed:
Hello, world! (and some additional text left to experience by running it)
We will completely understand this particular example by the end 
of Chapter 4. Until then, we can observe the single include of <CL/sycl.
hpp> (line 1) that is needed to define all the SYCL constructs. All SYCL 
constructs live inside a namespace called sycl:
*/

const  std::string secret {
    "Ifmmp-!xpsme\"\012J(n!tpssz-!Ebwf/!"
    "J(n!bgsbje!J!dbo(u!ep!uibu/!.!IBM\01"};
const auto sz = secret.size();

int main()
{
    queue Q;
    char*result = malloc_shared<char>(sz, Q);
    std::memcpy(result,secret.data(),sz);
    Q.parallel_for(sz,[=](auto&i) {
    result[i] -= 1;
    }).wait();
    std::cout << result << "\n";

    return 0;
}
