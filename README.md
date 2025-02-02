# PROP Grup 22.4

**Membres del grup:**

- Marcos Martinez ([marcos.martinez.martinez@estudiantat.upc.edu](mailto:marcos.martinez.martinez@estudiantat.upc.edu))
- Erik Gauchia ([erik.gauchia@estudiantat.upc.edu](mailto:erik.gauchia@estudiantat.upc.edu))
- David Comino ([david.comino@estudiantat.upc.edu](mailto:david.comino@estudiantat.upc.edu))
- Keinth Aguado ([keinth.john.aguado@estudiantat.upc.edu](mailto:keinth.john.aguado@estudiantat.upc.edu))

---

## Provar el programa

El projecte disposa d'un Makefile que facilita la compilació, execució i neteja dels diferents components del sistema. A més, s'inclouen drivers i tests unitaris per verificar el funcionament correcte.

També es disposa de fitxers de prova que es troben al directori /EXE/inputs.

Per últim també es disposa d'un javadoc que documenta totes les clases, per obrir-ho cal obrir el fitxer index.html que es troba en el directori javadoc

A continuació es detalla com compilar i executar el programa:

### Compilar:

Per compilar tot el projecte, executeu la següent comanda:

    make all

Per compilar només el codi font, executeu la següent comanda:

    make compile

Per compilar només els tests, executeu la següent comanda:

    make compile-tests

Per crear els JARs, executeu la següent comanda:

    make create-jars

### Executar:

Per executar el programa, executeu la següent comanda:

    make run

### Tests:

Per executar el driver de CtrlPersistencia, executeu la següent comanda:

    make run-driver-persistencia

Per executar el driver de CtrlDomini, executeu la següent comanda:

    make run-driver-domain

Per executar el driver de CtrlCataleg, executeu la següent comanda:

    make run-driver-ctrlcataleg

Per executar el driver de CtrlDistribucio, executeu la següent comanda:

    make run-driver-ctrldistribucio

Per executar els tests d'algorisme d'aproximació, executeu la següent comanda:

    make TestAlgoritmeAprox

Per executar els tests d'algorisme de força bruta, executeu la següent comanda:

    make TestAlgoritmeFBruta

Per executar els tests de producte, executeu la següent comanda:

    make TestProducte

Per executar els tests de relació, executeu la següent comanda:

    make TestRelacio

Per executar els tests de prestatgeria, executeu la següent comanda:

    make TestPrestatgeria

### Netejar:

Per netejar els fitxers generats durant la compilació, executeu la següent comanda:

    make clean

Per netejar els fitxers generats durant la compilació i els fitxers .dat, executeu la següent comanda:

    make distclean

---

## Descripció

### DOCS:

Documentació detallada sobre l'arquitectura, funcionalitats i ús del projecte.

### EXE:

Fitxers executables i binaris generats durant el procés de compilació.

### FONTS:

Codi font principal del projecte, incloent les classes i els controladors.

### lib:

Llibreries externes utilitzades pel projecte, com JUnit per als tests o altres dependències.

---

## Estructura del projecte

```plaintext
├── data                            # Directori on es guarda dades de persistencia
├── DOCS                            # Documentació del projecte
├── EXE                             # Executables i binaris generats
├── Ent1Def.iml     
├── FONTS                           # Codi font principal
│   ├── FONTS.iml        
│   └── src
│       ├── drivers                 # Drivers per provar mòduls específics
│       ├── main
│       │   ├── domain   
│       │   │   ├── classes         # Classes principals del domini
│       │   │   ├── controllers     # Controladors per gestionar l'aplicació
│       │   │   ├── exceptions      # Excepcions personalitzades
│       │   │   └── libs            # Llibreries auxiliars
│       │   ├── persistence         # Gestió de dades persistents 
|       |   |   ├──classes          # Clases principals de la persistencia
|       |   |   └── controllers     # Controladors per gestionar la persistencia
│       │   └── presentation        # Interfícies d'usuari 
|       |   |   ├──classes          # Clases principals de la presentació
|       |   |   └── controllers     # Controladors per gestionar la presentació
│       └── test                    # Tests unitaris
├── icons                           # Directori dels icones de la interficie
├── Makefile                        # Scripts per compilar i executar
├── README.md                       # Aquest fitxer
└── lib                             # Llibreries externes (JUnit, Hamcrest)