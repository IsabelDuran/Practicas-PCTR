#include <iostream>
#include <chrono>
#include <cmath>
#include <thread>
#include <vector>
#include <mutex>

bool esPrimo(int);

void comprobacionConcurrente(int inicio, int fin, int n, std::vector<bool>* arraySonPrimos, std::mutex* mutex){

    for(int i = inicio; i < fin; i += 2){
        if(n % i == 0){
            mutex->lock();
            arraySonPrimos->push_back(false);
            mutex->unlock();
            return;
        }
    }

    mutex->lock();
    arraySonPrimos->push_back(true);
    mutex->unlock();
}

bool esPrimo(int n) {
    std::mutex mutex;
    std::vector<std::thread> arrayHilos;
    int nHilos = std::thread::hardware_concurrency();

    std::vector<bool> arraySonPrimos;

    int inicio = 3;
    int nTareas = sqrt(n) / nHilos;
    int limite = sqrt(n);
    if(n == 2)
        return true;
    else if(n % 2 == 0)
        return false;
    else{
        for(int i = 0; i < nHilos - 1; i++){
            arrayHilos.push_back(std::thread(comprobacionConcurrente, inicio, inicio + nTareas, n, &arraySonPrimos, &mutex));
            inicio += nTareas;
        }

        arrayHilos.push_back(std::thread(comprobacionConcurrente, inicio, limite, n, &arraySonPrimos, &mutex));

        for(auto &i : arrayHilos)
            i.join();

        for(auto i : arraySonPrimos){
            if(!i)
                return false;
        }
    }

    return true;
}

int main() {
    std::chrono::time_point<std::chrono::system_clock> start, end;
    std::chrono::duration<double> elapsed_seconds;

    start = std::chrono::system_clock::now();

    for(int i = 1; i <= 100000; ++i)
        esPrimo(i);

    end = std::chrono::system_clock::now();

    elapsed_seconds = end - start;
    std::cout << "El concurrente ha tardado " << elapsed_seconds.count() << std::endl;

    return 0;
}
