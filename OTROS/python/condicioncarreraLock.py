from threading import Thread
from threading import Lock

contador = 0
lock = Lock()

def myThread(nIter):
    global contador
    for i in range(nIter):
        lock.acquire()
        contador += 1
        lock.release()

if __name__ == '__main__':
        iter = int(input('Dame un número de iteraciones '))
        myThread1 = Thread(target=myThread, args=(iter, ))
        myThread2 = Thread(target=myThread, args=(iter, ))
        myThread1.start()
        myThread2.start()
        myThread1.join()
        myThread2.join()
        print("El estado final del contador es ", contador)
