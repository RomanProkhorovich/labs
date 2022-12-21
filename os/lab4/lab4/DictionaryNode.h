#include "string"
#include <sstream>
#pragma once
using std::string;
template <typename T, typename D>

class DictionaryNode
{
private:
	T key;
    D value;
	
public:
	DictionaryNode(T key, D value) {
		this->key = key;
		this->value = value;
	}
	string ToString() {
		std::stringstream ss;
		string res;
		ss << "key: ";
		ss << key;
		ss << " value: ";
		ss <<value;
		ss >> res;
		return ss.get();
	}
};


