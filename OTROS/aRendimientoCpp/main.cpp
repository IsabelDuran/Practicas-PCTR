#include <iostream>
#include <chrono>
#include <thread>
#include <vector>
#include <mutex>
#include <atomic>

int main() {
    int contador = 0;
    std::atomic<int> contadorAtomic;
    contadorAtomic.store(0);
    int nHilos = std::thread::hardware_concurrency();
    std::mutex mutex;
    std::chrono::time_point<std::chrono::system_clock> startMutex, endMutex, startAI, endAI;
    std::chrono::duration<double> elapsed_secondsMutex, elapsed_secondsAI;

    std::vector<std::thread> vectorHilosMutex, vectorHilosAI;
    std::vector<double> vectorTiemposMutex, vectorTiemposAI;
    std::vector<int> accesos;

    for(int iter = 10000; iter <= 600000; iter += 10000){
        accesos.push_back(iter);

        //Medimos los tiempos usando Mutex
        startMutex = std::chrono::system_clock::now();
        for(int j = 0; j < nHilos; j++){
            vectorHilosMutex.push_back(std::thread([&contador, &mutex, &iter](){
                mutex.lock();
                for(int i = 0; i < iter; i++)
                    contador++;
                mutex.unlock();

            }));
        }

        for(auto &i : vectorHilosMutex)
            i.join();

        vectorHilosMutex.clear();
        endMutex = std::chrono::system_clock::now();
        elapsed_secondsMutex = endMutex - startMutex;
        vectorTiemposMutex.push_back(elapsed_secondsMutex.count());

        //Medimos los tiempos usando AtomicInteger
        startAI = std::chrono::system_clock::now();
        for(int j = 0; j < nHilos; j++){
            vectorHilosAI.push_back(std::thread([&contador, &iter, &contadorAtomic](){
                ++contadorAtomic;
            }));
        }

        for(auto &i : vectorHilosAI)
            i.join();

        vectorHilosAI.clear();
        endAI = std::chrono::system_clock::now();
        elapsed_secondsAI = endAI - startAI;
        vectorTiemposAI.push_back(elapsed_secondsAI.count());
    }

        std::cout << "Total de Accesos al Recurso\t\tTiempo con Mutex\t\tTiempo con atomic" << std::endl;
        for(int i = 0; i < accesos.size(); i++){
            std::cout << accesos.at(i) << "\t\t\t\t\t\t\t" << vectorTiemposMutex.at(i) << "\t\t\t\t" << vectorTiemposAI.at(i) << std::endl;
        }

    return 0;
}