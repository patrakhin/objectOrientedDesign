package org.example;

class Processor {
    void performCalculations() {
        System.out.println("Performing calculations");
    }
}

class Computer {
    private Processor processor;

    void setProcessor(Processor processor) {
        this.processor = processor;
    }

    void runCalculations() {
        if (processor != null) {
            processor.performCalculations();
        } else {
            System.out.println( "Компьютер не может запустить вычисления, так как не установлен процессор");
        }
    }
}

public class ContainsRelationshipComputer {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Processor processor = new Processor();

        computer.setProcessor(processor);
        computer.runCalculations();  // Выполняем вычисления
    }
}

