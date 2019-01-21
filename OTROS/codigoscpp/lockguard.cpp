#include <iostream>
#include <thread>
#include <mutex>
#include <vector>

struct Contador {
	std::mutex cerrojo;
	int valor = 0;

	void incremento() {
		std::lock_guard<std::mutex> guard(cerrojo);
		valor++; 
	}
};

int main() {
	Contador cont;
	std::vector<std::thread> hilos;
	for(int i=0; i<10; ++i) {
		hilos.push_back(std::thread([&cont](){
			for(int j=0; j<100; ++j) {
				cont.incremento();
			}
		}));
	}
	for(auto& thread : hilos) {
		thread.join();
	}
	std::cout << cont.valor << std::endl;
	return 0;
}