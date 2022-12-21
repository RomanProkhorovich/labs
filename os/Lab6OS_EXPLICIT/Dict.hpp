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

extern "C" {
	__declspec(dllimport) Dict* Dict_Construct();
	__declspec(dllimport) void Dict_Destruct(Dict* d);
	__declspec(dllimport) string Dict_Outout(Dict* d);
	__declspec(dllimport) void Dict_Add(Dict* d, string key, string value);
	__declspec(dllimport) void Dict_Delete(Dict* d, string key);
	__declspec(dllimport) void Dict_output_from_file(Dict* d);
	__declspec(dllimport) void Dict_input_in_file(Dict* d);
};