#pragma once
#include "Node.h"
#include <iostream>
#include <string>
using std::string;
using std::cout;
using std::cin;

class Dict {
	Node** mas;
	int count = 0;
public:
	Dict();
	~Dict();
	void Outout();
	void Add(string key, string value);
	void Delete(string key);
	void output_from_file();
	void input_in_file();
};

