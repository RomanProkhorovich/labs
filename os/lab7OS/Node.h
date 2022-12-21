#pragma once
#include <string>

using std::string;

class Node {
	string key;
	string value;
public:
	Node();
	//Node(char* k, char* v);
	Node(string k, string val);
	string to_string();
	string GetKey();
	string GetValue();
};