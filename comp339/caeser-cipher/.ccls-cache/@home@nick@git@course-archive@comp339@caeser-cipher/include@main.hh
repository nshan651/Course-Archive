#include <iostream>
#include <string>
#include <algorithm>
#include <unordered_set>
#include <fstream>
#include <vector>
#include <sstream>
#include <unistd.h>

const std::string PROJ_DIR = "/home/nick/git/course-archive/comp339/caeser-cipher/data/";

struct Cipher {
    bool encrypt = false;
    int num_lines;
    int min_len = 3;
    std::string dict_file = PROJ_DIR + "dictionary.txt";
    std::string input_file = PROJ_DIR + "input.txt";
    std::string output_file; 
};
