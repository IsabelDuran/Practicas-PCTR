#include <iostream>
#include <thread>
#include <chrono>

int main() {
	std::cout << "Hola soy el hilo principal" << std::endl;
	std::chrono::milliseconds duracion(2000);
	std::this_thread::sleep_for(duracion);
	std::cout << "He dormido 2000 ms" << std::endl;
	return 0;
}