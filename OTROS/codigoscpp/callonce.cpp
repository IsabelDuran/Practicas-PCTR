#include <iostream>
#include <thread>
#include <mutex>

std::once_flag bandera;

void hacer_algo() {
	std::call_once(bandera, [](){std::cout<<"Lamado una vez"<<std::endl;});

	std::cout<<"Llamado cada vez"<<std::endl;
}

int main() {
	std::thread t1(hacer_algo);
	std::thread t2(hacer_algo);
	std::thread t3(hacer_algo);
	std::thread t4(hacer_algo);
	t1.join(); t2.join();
	t3.join(); t4.join();

	return 0;
}