from threading import Thread
from threading import Lock
import concurrent.futures

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
        with concurrent.futures.ThreadPoolExecutor(max_workers=10) as ejecutor:
            for i in range(10):
                ejecutor.submit(myThread, 1000);
        print("El estado final del contador es ", iter)
