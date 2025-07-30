package com.solvd.models.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySingleton {
    private static final Logger logger = LoggerFactory.getLogger(MySingleton.class);

    public static class Singleton {
        private static Singleton instance;

        private Singleton() {
            logger.info("MySingleton instance is created");
        }

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        public void logMessage(String message) {
            logger.info(Thread.currentThread().getName() + ": " + message);
        }

        public String getId() {
            return this.toString();
        }
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton singleton = Singleton.getInstance();
            singleton.logMessage("Bella Ciao! My ID: " + singleton.getId());
        };

        Thread thread1 = new Thread(task, "Tomato");
        Thread thread2 = new Thread(task, "Lemon");
        Thread thread3 = new Thread(task, "Carrot");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
