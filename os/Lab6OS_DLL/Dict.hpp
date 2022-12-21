#pragma once
#include <iostream>
#include <string>
#include "Node.h"
#include "pch.h"
using std::string;
using std::cout;
using std::cin;

class Dict {
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
	__declspec(dllexport) Dict* Dict_Construct();
	__declspec(dllexport) void Dict_Destruct(Dict* d);
	__declspec(dllexport) string Dict_Outout(Dict* d);
	__declspec(dllexport) void Dict_Add(Dict* d, string key, string value);
	__declspec(dllexport) void Dict_Delete(Dict* d, string key);
	__declspec(dllexport) void Dict_output_from_file(Dict* d);
	__declspec(dllexport) void Dict_input_in_file(Dict* d);
};