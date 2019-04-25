//
// Created by isa on 18/01/19.
//
#include <iostream>
#include <cmath>
#include <random>
#include <ctime>
#include <iomanip>
#include <thread>

struct MatrizImagen {
    static const int LIM_MAX = 20;
    static const int LIM_INF = -20;
    int **matriz;

    MatrizImagen() {
        matriz = new int*[10000];
        for(int i = 0; i < 10000; i++)
            matriz[i] = new int[10000];
    }

    std::array<std::array<int, 3>, 3> celdasAdyacentes(int x, int y) const {
        std::array<std::array<int, 3>, 3> adyacentes;

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                adyacentes[i][j] = 0;

        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++)
                if((x + i) >= 0 && (x + i) < 10000 && (y + j) >= 0 && (y + j) < 10000)
                    adyacentes[i + 1][j + 1] = matriz[x][y];

        return adyacentes;
    }

    void imprimirMatriz(){
        for(int i = 0; i < 10000; i++)
        {
            for(int j = 0; j < 10000; j++)
                std::cout << std::setw(3) << matriz[i][j] << " ";
            std::cout << std::endl;
        }
    }

    ~MatrizImagen(){
        for(int i = 0; i < 10000; i++)
            delete[] matriz[i];

        delete[] matriz;
    }
};

void inicializarMatriz(MatrizImagen& matrizImagen){
    srand (time(NULL));
    for(int i = 0; i < 10000; i++)
        for(int j = 0; j < 10000; j++)
            matrizImagen.matriz[i][j] = MatrizImagen::LIM_INF + rand() % (MatrizImagen::LIM_MAX - MatrizImagen::LIM_INF);
}

int aplicarFiltro(const int filtro[3][3], const std::array<std::array<int, 3>, 3>& subMatriz){
    int resultado = 0;

    for(int i = 0; i < 3; i++)
        for(int j = 0; j < 3; j++)
            resultado += filtro[i][j] * subMatriz[i][j];

    if(resultado > MatrizImagen::LIM_MAX)
        resultado = MatrizImagen::LIM_MAX;

    if(resultado <  MatrizImagen::LIM_INF)
        resultado = MatrizImagen::LIM_INF;

    return resultado;
}

void convolucionarMatriz(const MatrizImagen& matrizInicial,
                        MatrizImagen& matrizConvolucionadaSobel,
                        MatrizImagen& matrizConvolucionadaSharpen ,
                        int inicio,
                        int fin){

    static const int sobel[][3] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    static const int sharpen[][3] = {{1, -2, 1}, {-2, 5, 2}, {1, -2, 1}};
    int op;

    for(int i = inicio; i < fin; i++)
        for(int j = inicio; j < 10000; j++)
        {
            std::array<std::array<int, 3>, 3> subMatriz = matrizInicial.celdasAdyacentes(i, j);

            matrizConvolucionadaSobel.matriz[i][j] = aplicarFiltro(sobel, subMatriz);
            matrizConvolucionadaSharpen.matriz[i][j] = aplicarFiltro(sharpen, subMatriz);

        }
}

int main(){
    MatrizImagen matrizImagen;
    MatrizImagen matrizConvolucionadaSobel;
    MatrizImagen matrizConvolucionadaSharpen;
    inicializarMatriz(matrizImagen);


    int n = std::thread::hardware_concurrency();

    for(int k = 2; k <= n; k+=2){
    std::thread hilos[n];
    int inicio = 0;
    int tamTarea = 10000 / n;

    std::chrono::time_point<std::chrono::system_clock> start, end;
    start = std::chrono::system_clock::now();

    for(int i = 0; i < n - 1; i++)
    {
        hilos[i] = std::thread(convolucionarMatriz, std::ref(matrizImagen), std::ref(matrizConvolucionadaSobel), std::ref(matrizConvolucionadaSharpen), inicio, inicio + tamTarea);
        inicio += tamTarea;
    }

   hilos[n - 1] = std::thread(convolucionarMatriz, std::ref(matrizImagen), std::ref(matrizConvolucionadaSobel), std::ref(matrizConvolucionadaSharpen), inicio, 10000);

    for(int i = 0; i < n; i++)
        hilos[i].join();
    end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end-start;

    auto int_ms = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

    std::cout << k << " " << 4340.0 / int_ms.count() << std::endl;
    }

    return 0;
}