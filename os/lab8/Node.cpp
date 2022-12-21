#include "Node.h"
#include <string>
#include <iostream>
using namespace std;

Node::Node(string k, string val) {
	key = k;
	value = val;
}
Node::Node() { }
/*
Node::Node(char* k_, char* v_);
{
	string ks = k;
	string vs = v;
	Node(ks, vs);
}
*/
string Node::to_string() {
	return (key + "	" + value);
}

string Node::GetKey()
{
	return key;
}
string Node::GetValue()
{
	return value;
}