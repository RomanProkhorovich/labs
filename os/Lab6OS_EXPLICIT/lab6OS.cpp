// lab6OS.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <iostream>
#include "Dict.hpp"
using std::endl;

int main()
{
    Dict* d = Dict_Construct();
    Dict_input_in_file(d);
    Dict_output_from_file(d);
    std::cout << "Dict:\n";
    std::cout << Dict_Outout(d);
    Dict_Destruct(d);

}

