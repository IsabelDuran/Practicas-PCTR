from threading import Thread

contador = 0
niter = 1000000

class myThread(Thread):
    def init(self):
        Thread.init(self)
        self.niter = niter;

    def run(self):
        global contador
        for cont in range(niter):
            contador += 1

def main():
    hebra1 = myThread()
    hebra2 = myThread()
    print("El resultado final debería ser 1000")
    hebra1.start()
    hebra2.start()
    hebra1.join()
    hebra2.join()
    print("Pero resulta que es ", contador)


if __name__ == '__main__':
    main()
