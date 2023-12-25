package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Базовый класс - электрический чайник
class ElectricKettle {
    private static final Logger log = LogManager.getLogger(ElectricKettle.class);
    public void heatWater() {
        log.info("Heating water in the electric kettle");
    }
}

// Первый потомок - расширение базового класса
class ElectricKettleWithIndicator extends ElectricKettle {
    private static final Logger log = LogManager.getLogger(ElectricKettleWithIndicator.class);
    public void powerIndicator() {
        log.info("Power indicator is on");
    }
}

// Второй потомок - специализация базовой функции
class ElectricKettleWithThermostat extends ElectricKettle {
    private static final Logger log = LogManager.getLogger(ElectricKettleWithThermostat.class);

    @Override
    public void heatWater() {
        super.heatWater(); // Вызов базовой функции
        log.info("Thermostat activated - automatically turning off at 80 degrees Celsius");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        // Использование расширенного класса-наследника
        ElectricKettleWithIndicator kettleWithIndicator = new ElectricKettleWithIndicator();
        kettleWithIndicator.heatWater();
        kettleWithIndicator.powerIndicator();

        // Использование специализированного класса-наследника
        ElectricKettleWithThermostat kettleWithThermostat = new ElectricKettleWithThermostat();
        kettleWithThermostat.heatWater();
    }
}