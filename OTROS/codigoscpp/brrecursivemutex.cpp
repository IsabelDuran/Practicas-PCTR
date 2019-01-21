#include <iostream>
#include <thread>
#include <mutex>

struct Compleja {
    std::recursive_mutex mutex;
    int i;

    Compleja() : i(0) {}

    void mul(int x){
        std::lock_guard<std::recursive_mutex> cerrojo(mutex);
        i *= x;
    }

    void div(int x){
        std::lock_guard<std::recursive_mutex> corrojo(mutex);
        i /= x;
    }

    void ambas(int x, int y){
        std::lock_guard<std::recursive_mutex> cerrojo(mutex);
        mul(x);
        div(y);
    }
};


int main(){
    Compleja compleja;
    compleja.ambas(32, 23);

    return 0;
}