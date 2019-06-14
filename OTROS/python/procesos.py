import multiprocessing
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
        myProcs = []
        iter = int(input('Dame un número de iteraciones '))
        nproc = int(input('Dame un número de procesos '))

        for i in range(nproc):
            proc = multiprocessing.Process(target =  myThread, args=(iter,))
            myProcs.append(proc)

        for i in myProcs: i.start()
        for i in myProcs: i.join()
        print("El estado final del contador es ", iter)
