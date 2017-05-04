JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Ant.java Board.java Case.java Tower.java Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
