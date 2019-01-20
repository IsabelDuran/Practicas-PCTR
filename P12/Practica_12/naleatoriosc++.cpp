//Ejemplo generar numeros aleatorios del 1 al 20.
#include <iostream>
#include <cstdlib>  //tiene la definicion de la funcion RAND (random)
using std::cout;
using std::endl;
using std::cin;
int main ()
{
  int cantidad;
  cout << "Cuantos números quiere generar?" << endl;
  cin >> cantidad;
  for (int contador =1 ; contador <= cantidad ; contador++)
    {
      cout << (1 + rand() % 20); //mostramos por pantalla los numeros generados del 1 al 20
      cout << endl;                     //salto de linea para separlos
    }
  return 0 ;
} //fin de main.