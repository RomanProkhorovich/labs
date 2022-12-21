#include <sstream>
#include "windows.h"
#include "pch.h"
#include "Dict.hpp"
#include "Node.h"


DWORD dwBytesWritten = 0;
DWORD dwBytesRead = 0;


Dict * Dict_Construct()
{
	return new Dict();
}

void Dict_Destruct(Dict* d)
{
	delete d;
}

string Dict_Outout(Dict* d)
{
	return d->Outout();
}

void Dict_Add(Dict* d, string key, string value)
{
	return d->Add(key, value);
}

void Dict_Delete(Dict* d, string key)
{
	return d->Delete(key);
}

 void Dict_output_from_file(Dict* d)
{
	return d->output_from_file();
}

void Dict_input_in_file(Dict* d)
{
	return d->input_in_file();
}


Dict::Dict() {
	this->mas = new Node * [3] {new Node("first", "odin"), new Node("second", "dva"), new Node("third", "tri")};
}

Dict::~Dict() {
	for (int i = 0; i < count ; i++)
	{
		delete mas[i];
	}
	delete mas;
}

string Dict::Outout() {
	string ret;
	for (int i = 0; i < count; i++)
	{
		ret += mas[i]->to_string() + "\r\n";
		
	}
	return ret;
}

void Dict::Add(string key,string value) {
	bool flag = true;
	for (int i = 0; i < count; i++)
	{
		if (mas[i]->GetKey() == key )
		{
			flag = false;
		}
	}
	if (flag)
	{
		count++;
		Node** newMas = new Node * [count];
		for (int i = 0; i < count - 1; i++)
		{
			newMas[i] = mas[i];
			//delete mas[i];
		}
		newMas[count - 1] = new Node(key, value);
		delete mas;
		mas = newMas;
	}
}

void Dict::Delete(string key) {
	int i = 0;
	for (; i < count && mas[i]->GetKey()!= key; i++){	}
	if (i < count && mas[i]->GetKey() == key)
	{
		delete mas[i];
		count--;
		Node** newMas = new Node * [count];
		int j = 0;
		for (; j < i; j++)
		{
			newMas[j] = mas[j];
		}
		for (; j < count; j++) {
			newMas[j] = mas[j+1];
		}

		delete mas;
		mas = newMas;
	}
	
}

void Dict::input_in_file()
{
	HANDLE hFile;
	hFile = CreateFile(L"Dict.bin", GENERIC_WRITE, FILE_SHARE_WRITE, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, 0);
	size_t abc;
	for (int i = 0; i < count; i++)
	{
		abc = mas[i]->GetKey().length();
		WriteFile(hFile, &abc, sizeof abc, &dwBytesWritten,NULL);
		WriteFile(hFile, mas[i]->GetKey().c_str(), mas[i]->GetKey().length(), &dwBytesWritten, NULL);

		abc = mas[i]->GetValue().length();
		WriteFile(hFile, &abc, sizeof abc, &dwBytesWritten, NULL);
		WriteFile(hFile, mas[i]->GetValue().c_str(),(mas[i]->GetValue().length()), &dwBytesWritten, NULL);

	}
	
	CloseHandle(hFile);
}

void Dict::output_from_file()
{
	delete mas;
	mas = new Node * [0];
	count = 0;
	char* key;
	size_t length;
	int l = 0;
	char* value;
	bool notEOF = true;
	HANDLE hFile;
	hFile = CreateFile(L"Dict.bin", GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_ALWAYS, FILE_ATTRIBUTE_NORMAL, 0);
	while (notEOF) {
		ReadFile(hFile, &length, sizeof(size_t), &dwBytesRead, NULL);
		if (!dwBytesRead)
		{
			break;
		}
	
		key = (char*)malloc(length+1);
		ReadFile(hFile, key, (sizeof(char) * length), &dwBytesRead, NULL);
		key[length] = 0;


		ReadFile(hFile, &length, sizeof(size_t), &dwBytesRead, NULL);
		value = (char*)malloc(length + 1);
		ReadFile(hFile, value, (sizeof(char)*length), &dwBytesRead, NULL);
		value[length] = 0;
		
		this->Add(key,value);
		free (key);
		free(value);
		notEOF = dwBytesRead;
	}
	
	CloseHandle(hFile);

}
