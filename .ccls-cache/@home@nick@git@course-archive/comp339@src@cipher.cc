#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

/** String split utility function */
vector<string> split(const string &s, char delimiter)
{
    vector<string> tokens;
    string token;
    istringstream token_stream(s);

    while (getline(token_stream, token, delimiter))
        tokens.push_back(token);
    
    return tokens;
}

int main() 
{
    string s = "One two three four";
    vector<string> tokens = split(s, ' ');

    for (const auto& token : tokens)
        cout << token << '\n';

    return 0;
}
