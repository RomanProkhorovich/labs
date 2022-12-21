// lab6OS.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <iostream>
#include <Windows.h>
#include "Dict.hpp"
using std::endl;




int main()
{
    Dict* (*Dict_Construct)();
    void (*Dict_Destruct)(Dict * d);
    string(*Dict_Outout)(Dict * d);
    void (*Dict_Add)(Dict * d, string key, string value);
    void (*Dict_Delete)(Dict * d, string key);
    void (*Dict_output_from_file)(Dict * d);
    void (*Dict_input_in_file)(Dict * d);
    HMODULE lib = LoadLibraryA("Lab6OS_DLL\0");
    Dict_Construct = (Dict * (*)())GetProcAddress(lib, "Dict_Construct");
    Dict_Destruct = (void (*)(Dict*))GetProcAddress(lib, "Dict_Destruct");
    Dict_Outout = (string (*)(Dict*))GetProcAddress(lib, "Dict_Outout");
    Dict_input_in_file = (void (*)(Dict*))GetProcAddress(lib, "Dict_input_in_file");
    Dict_output_from_file = (void (*)(Dict*))GetProcAddress(lib, "Dict_output_from_file");

    Dict* d = Dict_Construct();
    Dict_input_in_file(d);
    Dict_output_from_file(d);
    std::cout << "Dict:\n";
    std::cout << Dict_Outout(d);
    Dict_Destruct(d);

    FreeLibrary(lib);
}

