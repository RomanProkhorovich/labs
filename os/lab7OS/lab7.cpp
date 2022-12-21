// lab4OS.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//
#include <iostream>
#include <string>
#include "Dict.hpp"
#include <Windows.h>
using std::endl;

#define THREADCOUNT 10

using namespace std;

Dict* mas;

typedef struct
{
    string arg1;
    string arg2;
} args;

DWORD WINAPI Thread2(LPVOID lpParam)
{
    args* _args = (args*)lpParam;

    mas->Add(_args->arg1, _args->arg2);
    mas->input_in_file();

    delete _args;

    ExitThread(0);
    return 0;
}

DWORD WINAPI Thread1(LPVOID lpParam)
{
    HANDLE HS[THREADCOUNT];
    DWORD* IDS = (DWORD*)lpParam;
    for (size_t i = 0; i < THREADCOUNT; i++)
    {
        string k = "key" + to_string(i);
        string v = "val" + to_string(1+i);
        args* param = new args();
        param->arg1 = k;
        param->arg2 = v;
        HS[i] = CreateThread(NULL, 0, Thread2, (LPVOID)param, 0, &IDS[i]);
    }
    WaitForMultipleObjects(THREADCOUNT, &HS[0], TRUE, -1);
    for (size_t i = 0; i < THREADCOUNT; i++)
    {
        CloseHandle(HS[i]);
    }
    ExitThread(0);
    return 0;
}

int main()
{
    setlocale(LC_ALL, "ru");

    mas = new Dict();
    DWORD ids[THREADCOUNT];
    DWORD dwThreadId;
    HANDLE t1 = CreateThread(NULL, 0, Thread1, &ids[0], 0, &dwThreadId);

    WaitForSingleObject(t1, -1);

    delete mas;

    Dict* local_mas = new Dict();
    local_mas->output_from_file();
    local_mas->Outout();

}