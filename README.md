- Deve-se situar no diretório correto ( ~/Código ) onde se encontram todos os ficheiros .java

- De seguida para compilar deve abrir o terminal nesse mesmo local e correr respetivamente os seguintes comandos: 
	$ javac JogoDos8.java 
	$ java JogoDos8

- Depois deste ultimo comando vai ser pedido o primeiro input sendo que aí iremos adicionar a configuração inicial desejada e a seguir a configuração final, feito isto se for possível encontrar uma solução de uma configuração para a outra, é necessário inserir como último input o número da pesquisa a selecionar.

	Exemplo Input 1 (não encontra solução):
	6 2 7 5 0 3 8 1 4		(Configuração inicial)
	1 2 3 8 0 4 7 6 5		(Configuração final)


	Exemplo Input 2 (encontra solução):
	3 4 2 5 1 7 6 0 8		(Configuração inicial)
	1 2 3 8 0 4 7 6 5		(Configuração final)
	1				(Caso deseje selecionar a pesquisa em largura)
