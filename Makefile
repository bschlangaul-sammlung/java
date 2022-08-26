install_dependencies:
	mvn install:install-file \
		-Dfile=dependencies/aplu5-3.34.jar \
		-DgroupId=ch.aplu \
		-DartifactId=aplu \
		-Dversion=3.34 \
		-Dpackaging=jar \
		-DgeneratePom=true

	mvn install:install-file \
		-Dfile=dependencies/greenfoot-3.6.1.jar \
		-DgroupId=greenfoot \
		-DartifactId=greenfoot \
		-Dversion=3.6.1 \
		-Dpackaging=jar \
		-DgeneratePom=true

	mvn install:install-file \
		-Dfile=dependencies/engine-alpha-3.2.0.jar \
		-DgroupId=ea.edu \
		-DartifactId=AlphaEngine \
		-Dversion=3.2.0 \
		-Dpackaging=jar \
		-DgeneratePom=true

install_cli:
	mvn compile assembly:single
	sudo mkdir -p /usr/local/share/java/jars
	sudo cp -f target/didaktik-0.1.0-jar-with-dependencies.jar /usr/local/share/java/jars/bschlangaul-werkzeug.jar
	sudo cp -f cli.sh /usr/local/bin/bschlangaul-werkzeug.java

test:
	mvn test

doc:
	mvn javadoc:javadoc

.phony: install_dependencies install_cli test doc
