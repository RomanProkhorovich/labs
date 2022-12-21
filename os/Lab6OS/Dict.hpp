#pragma once
#include <iostream>
#include <string>
#include "Node.h"

using std::string;

class __declspec(dllexport) Dict {
	Node** mas;
	int count = 3;
public:
	Dict();
	~Dict();
	string Outout();
	void Add(string key, string value);
	void Delete(string key);
	void output_from_file();
	void input_in_file();
};