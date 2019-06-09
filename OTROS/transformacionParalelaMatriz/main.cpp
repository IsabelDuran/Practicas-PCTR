#include <iostream>
#include <thread>
#include <vector>
constexpr int tam_ = 100;

void aplicarTranformacion(float **matrizInicial, float ***matrizFinal, int inicio, int fin, int tam);

float** reservarMatriz(int tam){
    float **matriz = new float*[tam];

    for(int i = 0; i < tam; i++)
        matriz[i] = new float[tam];
    return matriz;
}

void liberarMatriz(float** matriz, int tam){
    for(int i = 0; i < tam; i++)
        delete[] matriz[i];
    delete matriz;

}

void imprimirMatriz(float** matriz, int tam){
    for(int i = 0; i < tam; i++){
        for(int j = 0; j < tam; j++)
            std::cout << matriz[i][j] << " ";
        std::cout << std::endl;
    }
}

void inicializarMatrizAleatoria(float*** matriz, int tam){
    srand (time(NULL));
    *matriz = reservarMatriz(tam);
    float **matrizNormal = *matriz;
    for(int i = 0; i < tam; i++)
        for(int j = 0; j < tam; j++){
            matrizNormal[i][j] = (float) rand() / RAND_MAX;
        }
}

void inicializarMatrizManual(float*** matriz, int tam){

    *matriz = reservarMatriz(tam);
    float ** matrizNormal = *matriz;
    for(int i = 0; i < tam; i++)
        for(int j = 0; j < tam; j++){
            std::cout << "Introduce el valor para la fila " << i << " columna " << j << std::endl;
            std::cin >> matrizNormal[i][j];
        }
}

void imprimirMenu(){
    std::cout << "Hola chavalote, ¿Qué desea hacer?" << std::endl;
    std::cout << "1.Insertar datos de forma aleatoria" << std::endl;
    std::cout << "2.Meter datos de forma manual" << std::endl;

}

void run(float** matrizInicial, float*** matrizFinal, int inicio, int fin, int tam){
    for(int fila = inicio; fila < fin; fila++)
        for(int columna = 0; columna < tam; columna++)
            aplicarTranformacion(matrizInicial, matrizFinal, fila, columna, tam);

}

bool comprobarLimites(int fila, int columna, int i, int j, int tam){
    return (fila + i) >= 0 && (fila + i) < tam &&
           (columna + j) >= 0 && (columna + j) < tam;
}

void celdasAdyacentes(float **matrizInicial, float matrizAux[3][3], int fila, int columna, int tam){
    for(int i = 0; i < 3; i++)
        for(int j = 0; j < 3; j++)
            matrizAux[i][j] = 0;

    for(int i = -1; i <= 1; i++)
        for(int j = -1; j <= 1; j++){
            if(comprobarLimites(fila, columna, i, j, tam))
                matrizAux[i + 1][j + 1] = matrizInicial[fila + i][columna + j];
        }

}

void aplicarTranformacion(float **matrizInicial, float ***matrizFinal, int fila, int columna, int tam) {
    static float matrizAux[3][3];
    celdasAdyacentes(matrizInicial, matrizAux, fila, columna, tam);

    (*matrizFinal)[fila][columna] = 3 * matrizAux[1][1] + (matrizAux[0][1] + matrizAux[1][0] + matrizAux[1][2] + matrizAux[2][1]);
}

int main() {
    int opcion;
    float **matrizInicial;
    float **matrizFinal;

    imprimirMenu();
    std::cin >> opcion;
    int tam = tam_;
    switch (opcion){
        case 1 :
            inicializarMatrizAleatoria(&matrizInicial, tam);
            break;
        case 2 :
            std::cout << "Introduce el tamaño de la matriz" << std::endl;
            std::cin >> tam;
            inicializarMatrizManual(&matrizInicial, tam);
            break;
    }

    matrizFinal = reservarMatriz(tam);

    int nHilos = std::thread::hardware_concurrency();
    int nTareas = tam / nHilos;
    int inicio = 0;
    std::vector<std::thread> arrayHilos;
    for(int i = 0; i < nHilos - 1; i++){
        arrayHilos.push_back(std::thread(run, matrizInicial, &matrizFinal, inicio, inicio + nTareas, tam));
        inicio += nTareas;
    }

    arrayHilos.push_back(std::thread(run, matrizInicial, &matrizFinal, inicio, tam, tam));

    for(std::thread& t : arrayHilos)
        t.join();

    imprimirMatriz(matrizFinal, tam);
    liberarMatriz(matrizFinal, tam);
    liberarMatriz(matrizInicial, tam);

    return 0;
}