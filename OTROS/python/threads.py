from threading import Thread

class myThread(Thread):
    def init(self):
        Thread.init(self)
        self.niter = niter

    def run(self):
        for cont in range(10):
            print(cont)

def main():
    hebra1 = myThread()
    hebra2 = myThread()
    print("¡Vamos hilitos!")
    hebra1.start()
    hebra2.start()
    hebra1.join()
    hebra2.join()

if __name__ == '__main__':
    main()
