#include <main.hh>

using namespace std;

string convert_alpha(string &word)
/** Convert each character to an upper-case american english letter */ 
{
    string alpha;
    for (char &c : word) {
        if (isalnum(c, locale()))
            alpha += toupper(c, locale());
        else {
            alpha = "";
            break;
        }
    }
    return alpha;
}

vector<string> split_line(string &line, const int min_len)
/** Split and parse the words in each line of a file, maing sure they are alphanumeric */
{
    stringstream ss(line);
    vector<string> words; 
    
    while (ss >> line) {
        /* Ensure min word length */
        if (line.length() < min_len) {
            cerr << "Error, the minimum word length must be " << min_len;
            exit(1);
        }

        string alpha = convert_alpha(line);
        if (!alpha.empty())
            words.push_back(alpha);
    }
    return words;
}

set<string> parse_dict(const string &filename)
/** Read and parse dictionary, excluding any non-Ascii letters */
{
    ifstream file(filename);
    string line;
    set<string> words;

    while (file >> line) {
        string alpha = convert_alpha(line);
        if (!alpha.empty())
            words.insert(alpha);
    }
    return words;
}

int main(int argc, char **argv)
{
    int option;
    Cipher cipher;

    while ((option = getopt(argc, argv, "edn:l:x:i:o:h")) != -1) {
        switch (option) {
            case 'e':
                cout << "Encrypt selected.";
                cipher.encrypt = true; 
                break;
            case 'd':
                cout << "Decrypt selected";
                cipher.encrypt = false; 
                break;
            case 'n':
                cipher.num_lines = atoi(optarg);
                cout << "Line number option selected.";
                break;
            case 'l':
                cipher.min_len = atoi(optarg);
                cout << "Length of min word option selected.";
                break;
            case 'x':
                cipher.dict_file = optarg;
                cout << "Dict option selected.";
                break;
            case 'i':
                cipher.input_file = optarg;
                cout << "Input option selected.";
                break;
            case 'o':
                cipher.output_file = optarg;
                cout << "Output option selected.";
                break;
            case 'h':
                cout << 
                    "=============\n"
                    "CAESER CIPHER\n"
                    "=============\n"
                    "Usage: \n\n"
                    "Options:"
                    "\n\t-e,|-encrypt encode the file"
                    "\n\t-d,|-decrypt decode the file"
                    "\n\t-n,|-lines number of lines of text to generate"
                    "\n\t-l,|-length minimum word length (default 3)"
                    "\n\t-x,|-dict location of dictionary containing the word list" 
                    "\n\t-i,|-input filename input text from filename (if absent, write to standard output)"
                    "\n\t-o,|-output filename output text (of shift values) to filename (if absent, write to standard output)"
                    "\n\t-h,|-help pull up a list of options";
                return 0;
            default: 
                cerr << "Unknown option. See -h for a list of options.";
                return 1;
        }
    }

    /* cipher.input_file = PROJ_DIR + "decrypt.txt"; */
    /* cipher.input_file = PROJ_DIR + "encrypt.txt"; */

    set<string> dictionary = parse_dict(cipher.dict_file);
    cipher.min_len = 1;

    ifstream file(cipher.input_file);
    string line;
    if (!file.is_open()) {
        cerr << "Error opening file.";
        return 1;
    }
    
    while (getline(file, line)) {
        vector<string> words = split_line(line, cipher.min_len);
        int result;
        if (cipher.encrypt)
             result = encoder(words, 15);
        else
            result = decoder(words, dictionary);
        cout << "Shift Value: " << result << "\n";
    }

    return 0;
}
