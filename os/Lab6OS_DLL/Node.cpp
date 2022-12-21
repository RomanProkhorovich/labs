#include "pch.h"
#include "Node.h"
#include <string>
#include <iostream>
using namespace std;



Node::Node(string k, string val) {
	key = k;
	value = val;
}
Node::Node() { }
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