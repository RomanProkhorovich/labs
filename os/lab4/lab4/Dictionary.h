#pragma once
#include "DictionaryNode.h"
#include "string.h"
using std::string;
template <typename T, typename D>
class Dictionary {
private:
	DictionaryNode<T, D>* arr;
	int count = 0;
public:
	Dictionary(T key, D value) {
		arr = new DictionaryNode<T, D>(key, value);
		count++;
	}
	void Add(T key, D value) {
		arr = (DictionaryNode*)realloc(arr, sizeof(DictionaryNode)); //добавл€ем одну €чейку
		arr[count] = new DictionaryNode<T, D>(key, value);
		count++;
	}
	string ToString() {
		string res;
		for (int i = 0; i < count; i++)
		{
			res += arr[i].ToString() + "\n";
		}
		return res;
	}
};