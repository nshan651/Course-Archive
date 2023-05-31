#include <iostream>
#include <string>
#include <algorithm>
#include <map>
#include <unordered_set>
#include <fstream>
#include <vector>
#include <sstream>
#include <unistd.h>
#include <ostream> 
#include <set>
#include <cctype>

const std::string PROJ_DIR = "/home/nick/git/course-archive/comp339/caeser-cipher/data/";
const int ALPHABET_SIZE = 26;

struct Cipher {
    bool encrypt = false;
    int num_lines;
    int min_len = 3;
    std::string dict_file = "/usr/share/dict/american-english";
    std::string input_file = PROJ_DIR + "decrypt.txt";
    std::string output_file; 
};

#ifndef MAIN_HH
#define MAIN_HH

int decoder(std::vector<std::string> &words, std::set<std::string> &dictionary);

int encoder(std::vector<std::string> &words, int shift);

#endif
