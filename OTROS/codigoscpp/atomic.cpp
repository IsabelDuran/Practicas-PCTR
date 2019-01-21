#include <iostream>
#include <vector>
#include <thread>
#include <atomic>

struct ContadorAtomico {
    std::atomic<int> valor;

    void incremento(){ ++valor; }
    
    void decremento(){ --valor; }
    
    int obtener(){ return valor.load(); }
};

int main(){
    ContadorAtomico contadoratomico;

    contadoratomico.valor.store(0);

    std::vector<std::thread> hilos;
    for(int i = 0; i < 3; ++i){
        hilos.push_back(std::thread([&contadoratomico](){
            for(int i = 0; i < 100; ++i){
                contadoratomico.incremento();
            }
        }));
    }

    for(int i = 0; i < 3; ++i){
        hilos.push_back(std::thread([&contadoratomico](){
            for(int i = 0; i < 100; ++i){
                contadoratomico.decremento();
            }
        }));
    }

    for(auto& thread : hilos){
        thread.join();
    }

    std::cout << contadoratomico.obtener() << std::endl;

    return 0;
}
