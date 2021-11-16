# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testGUI testBalls testBallsSimulator testRectangleSimulator testRectangleImGameSimulator testRectanglesSchellingSimulator testBoidSimulator testEventManager
GUI : testGUI exeGUI
BallsPrint : testBalls exeBalls
BallsSimulator : testBallsSimulator exeBallsSimulator
Conway : testRectangleSimulator exeRectangleSimulator
ImmigrationGame : testRectangleImGameSimulator exeRectangleImGameSimulator
Schelling : testRectanglesSchellingSimulator exeRectanglesSchellingSimulator
Boid : testBoidSimulator exeBoidSimulator
EventManager : testEventManager exeEventManager

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java
testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBalls.java
testBallsSimulator :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator.java
testRectangleSimulator :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRectangleSimulator.java
testRectangleImGameSimulator :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRectangleImGameSimulator.java
testRectanglesSchellingSimulator :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRectanglesSchellingSimulator.java
testBoidSimulator :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBoidSimulator.java
testEventManager :
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEventManager.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI
exeBalls:
	java -classpath bin:bin/gui.jar TestBalls
exeBallsSimulator:
	java -classpath bin:bin/gui.jar TestBallsSimulator
exeRectangleSimulator:
	java -classpath bin:bin/gui.jar TestRectangleSimulator
exeRectangleImGameSimulator:
	java -classpath bin:bin/gui.jar TestRectangleImGameSimulator
exeRectanglesSchellingSimulator:
	java -classpath bin:bin/gui.jar TestRectanglesSchellingSimulator
exeBoidSimulator:
	java -classpath bin:bin/gui.jar TestBoidSimulator
exeEventManager:
	java -classpath bin:bin/gui.jar TestEventManager

clean:
	rm -rf bin/*.class
