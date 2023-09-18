package arvores;

public class main {

	public static void main(String[] args) {
		
	ArvoreAVL arvore = new ArvoreAVL();
		
		arvore.inserir(30);
		arvore.inserir(25);
		arvore.inserir(20);
		arvore.inserir(19);
		arvore.inserir(19);
		arvore.inserir(19);
		arvore.inserir(19);
		arvore.inserir(21);
		arvore.inserir(13);
		arvore.inserir(27);
		arvore.inserir(23);
		arvore.inserir(24);
		arvore.inserir(27);
		arvore.inserir(45);
		arvore.inserir(52);
		arvore.inserir(60);
		
		System.out.println("*******Árvore AVL*******");
		arvore.mostrarPorNivel();
		System.out.println();
		
		
		System.out.println("Questão 2: ");
		arvore.remover(20);   
		arvore.mostrarPorNivel();
		System.out.println();
		
		System.out.println("Questão 3: ");
		boolean resposta = arvore.isAVL();   
		System.out.println(resposta);
		System.out.println();
	
		System.out.println("Questão 4: ");
		int nosPrimos = arvore.contaNodosPrimos(arvore.getRaiz());
		System.out.println("Quantidade de nós que guardam números primos: " + nosPrimos);
		System.out.println();
		
		System.out.println("Questão 5: ");
		arvore.mostrarEmOrdem();
		System.out.println();
		
		System.out.print("Questão 6: ");
		arvore.mostrarNodosNoNivel(2);
		System.out.println();
		
		System.out.println("Questão 7: ");
		int soma = arvore.somarNosNiveisImpares();
		System.out.println("A soma dos nós nos níveis ímpares é igual a: " + soma);
		System.out.println();
		
		System.out.println("Questão 8: ");
		Municipio municipio1 = new Municipio("Rio do Sul", 261, 72500);
		Municipio municipio2 = new Municipio("Lontras", 198, 12800);
		Municipio municipio3 = new Municipio("Laurentino", 79.5, 7900);
		Municipio municipio4 = new Municipio("Agronômica", 135.9, 5500);		
		Municipio municipio5 = new Municipio("Rio do Oeste", 245, 7000);
	
		ArvoreAVL arvore2 = new ArvoreAVL();
		arvore2.inserir(municipio1);
		arvore2.inserir(municipio2);
		arvore2.inserir(municipio3);
		arvore2.inserir(municipio4);
		arvore2.inserir(municipio5);
		
		arvore2.mostrarArvoreMunicipios();
		System.out.println();
		
		int qtdMunicipio = arvore2.contarMunicipios();
		System.out.println("a. Quantidade de municípios: " + qtdMunicipio);
		System.out.println();
			
		System.out.println("b. Municípios com mais de 10 mil habitantes: ");
		arvore2.mostrarMunicipiosComMaisDeXHabitantes(10000);
		System.out.println();
		
		System.out.println("c. Mostrar a Densidade Demográfica de cada município: ");
		arvore2.mostrarDensidades();
		System.out.println();
		
		double perc = arvore2.calcularSomatorioArea();
		System.out.println("d. Mostrar o somatório de área em km2 de todas as cidades juntas em relação ao\r\n"
				+ "território nacional (em porcentagem): " + String.format("%.2f",perc) + "%");
		System.out.println();
		
		Municipio cidadeMaiorPopulacao = arvore2.getCidadeMaiorPopulacao();
		System.out.println("e. Cidade com maior população: " + cidadeMaiorPopulacao.getNome());
		
   }
	
	
}
