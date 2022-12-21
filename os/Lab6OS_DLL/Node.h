#pragma once
#include <string>
#include "pch.h"

using std::string;

class __declspec(dllexport) Node {
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
