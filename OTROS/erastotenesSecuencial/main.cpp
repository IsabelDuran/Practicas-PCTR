#include <iostream>
#include <chrono>
#include <cmath>

bool esPrimo(int);

bool esPrimo(int n) {
    if(n == 2)
        return true;
    else if(n % 2 == 0)
        return false;
    else{
        for(int i = 3; i < sqrt(n); i += 2){
            if(n % i == 0)
                return false;
        }
    }

    return true;
}

int main() {
    std::chrono::time_point<std::chrono::system_clock> start, end;
    std::chrono::duration<double> elapsed_seconds;
    start = std::chrono::system_clock::now();
    for(int i = 1; i <= 10000000; i++)
        esPrimo(i);
    end = std::chrono::system_clock::now();
    elapsed_seconds = end - start;
    std::cout << "El secuencial ha tardado " << elapsed_seconds.count() << std::endl;

    return 0;
}

