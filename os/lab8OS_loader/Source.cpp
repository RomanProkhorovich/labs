#include <iostream>
#include <string>
#include <Windows.h>
#include "Dict.hpp"
#define PROCESS_COUNT 5

using std::string;

string args[] =
{
	"odin", "edinica1",
	"dva", "edinica2",
	"three", "edinica3",
	"chatire", "edinica4",
	"pyat`", "edinica5"
};

string exec = "D:\\labs\\os\\lab8\\x64\\Debug\\lab8.exe";
string w = " ";

int main(int argc, char* argv[])
{
	HANDLE sync = CreateMutex(NULL, FALSE, TEXT("Global\\Synchonizer"));
	WaitForSingleObject(sync, INFINITE);

	Dict* d = new Dict();
	d->input_in_file();
	delete d;

	PROCESS_INFORMATION pi[PROCESS_COUNT];
	for (size_t i = 0; i < PROCESS_COUNT; i++)
	{
		ZeroMemory(&pi[i], sizeof(PROCESS_INFORMATION));
		STARTUPINFOA si;
		ZeroMemory(&si, sizeof(si));
		si.cb = sizeof(si);

		string cmd = exec + w + args[i * 2] + w + args[i * 2 + 1];

		BOOL isOK = CreateProcessA(
			exec.c_str(),
			(LPSTR)cmd.c_str(),
			NULL,
			NULL,
			FALSE,
			0,
			NULL,
			NULL,
			& si,
			&pi[i]
		);

		std::cout << pi[i].dwProcessId << "\t" << isOK << "\r\n";
	}

	ReleaseMutex(sync);
	for (size_t i = 0; i < PROCESS_COUNT; i++)
	{
		WaitForSingleObject(pi[i].hProcess, INFINITE);
		WaitForSingleObject(pi[i].hThread, INFINITE);
		DWORD ret; // 0
		GetExitCodeProcess(pi[i].hProcess, (LPDWORD) & ret);

		CloseHandle(pi[i].hProcess);
		CloseHandle(pi[i].hThread);
	}

	Dict* d2 = new Dict();
	d2->output_from_file();
	d2->Outout();
	delete d2;

	return 0;
}