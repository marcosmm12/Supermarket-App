# Variables de directorios
SRC_DIR = ./FONTS/src
LIB_DIR = ./lib/
EXE_DIR = ./EXE
JAR_DIR = ./EXE/

# Variables de entrada de clases
CLASS_INPUT = $(SRC_DIR)/main/domain/classes/*.java \
              $(SRC_DIR)/main/domain/exceptions/*.java \
              $(SRC_DIR)/main/domain/controllers/*.java \
              $(SRC_DIR)/main/domain/libs/*.java \
			  $(SRC_DIR)/main/persistence/classes/*.java \
			  $(SRC_DIR)/main/persistence/controllers/*.java \
			  $(SRC_DIR)/main/presentation/classes/*.java \
			  $(SRC_DIR)/main/presentation/controllers/*.java 

# Drivers (drivers)
DRIVERS = $(SRC_DIR)/drivers/DriverCtrlCataleg.java \
          $(SRC_DIR)/drivers/DriverCtrlDistribucio.java \
          $(SRC_DIR)/drivers/DriverCtrlDomain.java \
		  $(SRC_DIR)/drivers/DriverCtrlPersistencia.java

# Variables de salida
CLASS_OUTPUT = $(EXE_DIR)/out
JAR_OUTPUT = $(JAR_DIR)

# Librerías para las pruebas
JUNIT_JARS = $(LIB_DIR)/junit-4.12.jar:$(LIB_DIR)/hamcrest-core-1.3.jar

# Directorio de tests
TEST_DIR = $(SRC_DIR)/test

# Test
JUNIT_TESTS = $(SRC_DIR)/test/*.java \

# Targets

# Target por defecto: compilar clases
all: compile create-jars compile-tests

# Compilar clases
compile:
	@echo "Compilando clases..."
	javac -cp $(LIB_DIR)/jackson-annotations-2.14.0.jar:$(LIB_DIR)/jackson-core-2.14.0.jar:$(LIB_DIR)/jackson-databind-2.14.0.jar -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(DRIVERS)

# Crear los archivos JAR para los drivers
create-jars:
	@echo "Creando JARs..."
	jar cmvf $(SRC_DIR)/drivers/DriverCtrlCataleg.mf $(JAR_OUTPUT)DriverCtrlCataleg.jar -C $(CLASS_OUTPUT) .
	jar cmvf $(SRC_DIR)/drivers/DriverCtrlDistribucio.mf $(JAR_OUTPUT)DriverCtrlDistribucio.jar -C $(CLASS_OUTPUT) .
	jar cmvf $(SRC_DIR)/drivers/DriverCtrlDomain.mf $(JAR_OUTPUT)DriverCtrlDomain.jar -C $(CLASS_OUTPUT) .
	jar cmvf $(SRC_DIR)/drivers/DriverCtrlPersistencia.mf $(JAR_OUTPUT)DriverCtrlPersistencia.jar -C $(CLASS_OUTPUT) .

# Compilar las clases de prueba
compile-tests:
	@echo "Compilando pruebas..."
	javac -cp $(JUNIT_JARS):$(CLASS_OUTPUT) -d $(CLASS_OUTPUT) $(JUNIT_TESTS)

# Ejecutar la aplicación principal
run: compile
	@echo "Ejecutando la aplicación principal..."
	java -cp $(CLASS_OUTPUT):$(LIB_DIR)/jackson-annotations-2.14.0.jar:$(LIB_DIR)/jackson-core-2.14.0.jar:$(LIB_DIR)/jackson-databind-2.14.0.jar main.presentation.controllers.CtrlPresentacio

# Ejecutar los drivers
run-driver-cataleg:
	java -cp $(JAR_OUTPUT)DriverCtrlCataleg.jar drivers.DriverCtrlCataleg

run-driver-distribucio:
	java -cp $(JAR_OUTPUT)DriverCtrlDistribucio.jar drivers.DriverCtrlDistribucio

run-driver-domain:
	java -cp $(JAR_OUTPUT)DriverCtrlDomain.jar drivers.DriverCtrlDomain

run-driver-persistencia:
	java -cp $(JAR_OUTPUT)DriverCtrlPersistencia.jar drivers.DriverCtrlPersistencia

# Limpiar archivos compilados
clean:
	@echo "Limpiando archivos..."
	rm -rf $(CLASS_OUTPUT)/  # Elimina solo los archivos .class

# Limpiar todos los archivos de salida (incluso JARs)
distclean: clean
	@echo "Limpiando todos los archivos..."
	rm -rf $(EXE_DIR)/*.jar         # Elimina solo los archivos .jar


# Ejecutar pruebas unitarias
TestAlgoritmeAprox: all
	@echo "Ejecutando pruebas de algoritmo de aporixmacion..."
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT):$(TEST_DIR) org.junit.runner.JUnitCore test.algoritmeAproximacioTest

TestAlgoritmeFBruta: all
	@echo "Ejecutando pruebas de algoritmo de fuerza bruta..."
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT):$(TEST_DIR) org.junit.runner.JUnitCore test.algoritmeFBrutaTest

TestProducte: all
	@echo "Ejecutando pruebas de productos..."
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT):$(TEST_DIR) org.junit.runner.JUnitCore test.ProducteTest

TestRelacio: all
	@echo "Ejecutando pruebas de relaciones..."
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT):$(TEST_DIR) org.junit.runner.JUnitCore test.RelacioTest

TestPrestatgeria: all
	@echo "Ejecutando pruebas de prestagerias..."
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT):$(TEST_DIR) org.junit.runner.JUnitCore test.PrestatgeriaTest