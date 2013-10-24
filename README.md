project-auto
============

Projeto de automação de testes utilizando JBehave e Selenium.

---------------
Pré-requisitos:
---------------
- Java 1.6
- Maven 3.x
- Firefox 15.0.1 (compatível com selenium 2.26.0)

------------------------
Como disparar os testes?
------------------------

Execução local:

	mvn clean integration-test 
	-Dbrowser=firefox 
	"-Dwebdriver.firefox.bin=C:\Program Files (x86)\Mozilla Firefox15\firefox.exe" 

Execução remota (apenas adicionar parâmetros):

	-Dwebdriver.mode=REMOTE
	-DREMOTE_WEBDRIVER_URL=http://localhost:4444/wd/hub
