#include <iostream>
#include <Windows.h>
#include "Dict.hpp"

HANDLE sync;

int main(int argc, char* argv[])
{
	sync = CreateMutex(NULL, FALSE, TEXT("Global\\Synchonizer"));
	
	string arg1 = argv[1];
	string arg2 = argv[2];

	WaitForSingleObject(sync, INFINITE);
	Sleep(3000);
	Dict* d = new Dict();
	
	d->output_from_file();
	d->Add(argv[1], argv[2]);
	d->input_in_file();

	ReleaseMutex(sync);

	return 0;
}
