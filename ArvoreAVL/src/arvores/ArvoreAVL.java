package arvores;

import java.util.LinkedList;
import java.util.Queue;


public class ArvoreAVL {
	
	
	
	private class Nodo{
		
		private Municipio municipio;
		private int dado, altd, alte, ocorrencias;   // Questão 5
		private Nodo dir, esq;
		
		public Nodo(int dado) {
			this.dado = dado;
			dir = esq = null;
			altd = alte = 0;
			ocorrencias = 1;  // questão 5
		}
	
		public Nodo(Municipio municipio) {    // Questão 8
			this.municipio = municipio;
	
		}	
		
		public Municipio getCidade() {
			return municipio;
		}
	    
	    public Nodo getEsquerda() {
		    return esq;
		}
		    
	    public void setEsquerda(Nodo esquerda) {
	        this.esq = esquerda;
		}
		    
	    public Nodo getDireita() {
	        return dir;
	    }
		
	    public void setDireita(Nodo direita) {
	        this.dir = direita;
		}
		    
		    
	}
	
	Nodo raiz;
		
	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public ArvoreAVL() {
		raiz = null;
	}
	
	public void inserir(int dado) {
		raiz = inserirDado(raiz, dado);
	}
	
	private Nodo inserirDado(Nodo raiz, int dado) {
		if(raiz == null) {
			raiz = new Nodo(dado);
			return raiz;
		}
		if(dado < raiz.dado) {
			raiz.esq = inserirDado(raiz.esq, dado);
			if(raiz.esq.altd > raiz.esq.alte) {
				raiz.alte = raiz.esq.altd + 1;
			} else {
				raiz.alte = raiz.esq.alte + 1;
			}
			raiz = balanceamento(raiz);
		} else if (dado > raiz.dado) {
			raiz.dir = inserirDado(raiz.dir, dado);
			if(raiz.dir.altd > raiz.dir.alte) {
				raiz.altd = raiz.dir.altd + 1;
			} else {
				raiz.altd = raiz.dir.alte + 1;
			}
			raiz = balanceamento(raiz);
		} else {
			raiz.ocorrencias++;   // Incrementa o número de ocorrências da chave  - Questão 5
		}
		return raiz;
	}
	
	
	private Nodo balanceamento(Nodo raiz) {
		int fb = raiz.altd - raiz.alte;
		int fbSubArv;
		if(fb == 2 ) {
			fbSubArv = raiz.dir.altd - raiz.dir.alte;
			if(fbSubArv >= 0 ) {
				raiz = rotacao_esquerda(raiz);
			} else {
				raiz.dir = rotacao_direita(raiz.dir);
				raiz = rotacao_esquerda(raiz);
			}
		} else if(fb == -2) {
			fbSubArv = raiz.esq.altd - raiz.esq.alte;
			if(fbSubArv <= 0) {
				raiz = rotacao_direita(raiz);
			} else {
				raiz.esq = rotacao_esquerda(raiz.esq);
				raiz = rotacao_direita(raiz);
			}
		}
		return raiz;
	}
	
	
	private Nodo rotacao_esquerda(Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.dir;
		aux2 = aux1.esq;
		raiz.dir = aux2;
		aux1.esq = raiz;
		if(raiz.dir == null) {
			raiz.altd = 0;
		} else if(raiz.dir.alte > raiz.dir.altd) {
			raiz.altd = raiz.dir.alte + 1;
		} else {
			raiz.altd = raiz.dir.altd + 1;	
		} 
		if(aux1.esq.alte > aux1.esq.altd) {
			aux1.alte = aux1.esq.alte + 1;
		} else {
			aux1.alte = aux1.esq.altd + 1;
		}
		return aux1;
	}
	
	
	private Nodo rotacao_direita(Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.esq;
		aux2 = aux1.dir;
		raiz.esq = aux2;
		aux1.dir = raiz;
		if(raiz.esq == null) {
			raiz.alte = 0;
		} else if (raiz.esq.alte > raiz.esq.altd) {
			raiz.alte = raiz.alte + 1;
		} else {
			raiz.alte = raiz.esq.altd + 1;
		}
		if(aux1.dir.alte > aux1.dir.altd) {
			aux1.altd = aux1.dir.alte + 1;
		} else {
			aux1.altd = aux1.dir.altd + 1;
		}
		return aux1;
	}
	
	  private Nodo rotacaoDuplaEsquerda(Nodo raiz) {
	        raiz.esq = rotacao_direita(raiz.esq);
	        return rotacao_esquerda(raiz);
	    }

	    private Nodo rotacaoDuplaDireita(Nodo raiz) {
	        raiz.dir = rotacao_esquerda(raiz.dir);
	        return rotacao_direita(raiz);
	    }
	public void mostrarEmOrdem() {
		mostrandoOrdenado(raiz);
	}
	
	public void mostrandoOrdenado(Nodo raiz) {
		if(raiz != null) {
			mostrandoOrdenado(raiz.esq);
			System.out.println(raiz.dado + " (Ocorrências:" + raiz.ocorrencias + ")");    //   Questão 5
			mostrandoOrdenado(raiz.dir);
		}
	}

	public void mostrarPorNivel() {
	    if(raiz == null) {
	        System.out.println("Árvore vazia!");
	        return;
	    }
	    Queue<Nodo> fila = new LinkedList<>();
	    fila.add(raiz);
	    
	    while(!fila.isEmpty()) {
	        int tamanhoNivel = fila.size();
	        for(int i = 0; i < tamanhoNivel; i++) {
	            Nodo nodoAtual = fila.poll();
	            if(nodoAtual != null) {
	                System.out.print(nodoAtual.dado + " ");
	                if(nodoAtual.esq != null) {
	                    fila.add(nodoAtual.esq);
	                } else {
	                    fila.add(null);
	                }
	                if(nodoAtual.dir != null) {
	                    fila.add(nodoAtual.dir);
	                } else {
	                    fila.add(null);
	                }
	            } else {
	                System.out.print("- ");
	            }
	        }
	        System.out.println();
	    }
	}
	
	public void remover(int valor) {                // Questão 2
	    raiz = removerNo(raiz, valor);
	}

	private Nodo removerNo(Nodo raiz, int valor) {
	    if (raiz == null) {
	        return raiz;
	    }

	    if (valor < raiz.dado) {
	        raiz.esq = removerNo(raiz.esq, valor);
	    } else if (valor > raiz.dado) {
	        raiz.dir = removerNo(raiz.dir, valor);
	    } else {
	        // Nó a ser deletado foi encontrado

	        // Caso 1: Nó não tem filhos ou tem apenas um filho
	        if (raiz.esq == null || raiz.dir == null) {
	            Nodo filho = (raiz.esq != null) ? raiz.esq : raiz.dir;
	            if (filho == null) {
	                // Nó não tem filhos
	                raiz = null;
	            } else {
	                // Nó tem um filho
	                raiz = filho;
	            }
	        } else {
	            // Caso 2: Nó tem dois filhos
	            Nodo sucessor = getSuccessor(raiz.dir);
	            raiz.dado = sucessor.dado;
	            raiz.ocorrencias = sucessor.ocorrencias;         // Questão 5
	            raiz.dir = removerNo(raiz.dir, sucessor.dado);
	        }
	    }

	    // Atualiza as alturas e realiza rotações
	    if (raiz != null) {
	        raiz = balanceamento(raiz);
	    }
	    return raiz;
	}

	private Nodo getSuccessor(Nodo node) {
	    Nodo current = node;
	    while (current.esq != null) {
	        current = current.esq;
	    }
	    return current;
	}
	
	public boolean isAVL() {        // Questão 3
	    return isAVL(raiz);
	}

	private boolean isAVL(Nodo nodo) {
	    if (nodo == null) {
	        return true;
	    }

	    int fatorDeBalanceamento = getFatorDeBalanceamento(nodo);
	    if (fatorDeBalanceamento > 1 || fatorDeBalanceamento < -1) {
	        return false;
	    }

	    return isAVL(nodo.esq) && isAVL(nodo.dir);
	}

	private int getFatorDeBalanceamento(Nodo nodo) {
	    return getAltura(nodo.esq) - getAltura(nodo.dir);
	}

	private int getAltura(Nodo nodo) {
	    if (nodo == null) {
	        return -1;
	    }
	    return 1 + Math.max(getAltura(nodo.esq), getAltura(nodo.dir));
	}
	

    public boolean verificaPrimo(int num) {              // Questão 4
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    
    public int contaNodosPrimos(Nodo node) {
        if (node == null) {
            return 0;
        }
        int count = verificaPrimo(node.dado) ? 1 : 0;
        return count + contaNodosPrimos(node.esq) + contaNodosPrimos(node.dir);
    }

    
    public void mostrarNodosNoNivel(int nivel) {         // Questão 6
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);
        
        int nivelAtual = 1;
        
        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            
            for (int i = 0; i < tamanhoNivel; i++) {
                Nodo nodoAtual = fila.poll();
                
                if (nodoAtual != null) {
                    if (nivelAtual == nivel) {
                        System.out.print(nodoAtual.dado + " ");
                    }
                    
                    if (nodoAtual.esq != null) {
                        fila.add(nodoAtual.esq);
                    } else {
                        fila.add(null);
                    }
                    
                    if (nodoAtual.dir != null) {
                        fila.add(nodoAtual.dir);
                    } else {
                        fila.add(null);
                    }
                } else {
                	System.out.print(" ");
                }
            }
            
            System.out.println();
            nivelAtual++;
        }
    }
    
    public int somarNosNiveisImpares() {       //  Questão 7
        if (raiz == null) {
            return 0;
        }
        
        int soma = 0;
        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);
        int nivelAtual = 1;
        
        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            
            for (int i = 0; i < tamanhoNivel; i++) {
                Nodo nodoAtual = fila.poll();
                
                if (nodoAtual != null) {
                    if (nivelAtual % 2 != 0) {
                        soma += nodoAtual.dado;
                    }
                    
                    if (nodoAtual.esq != null) {
                        fila.add(nodoAtual.esq);
                    }
                    
                    if (nodoAtual.dir != null) {
                        fila.add(nodoAtual.dir);
                    }
                }
            }
            
            nivelAtual++;
        }
        
        return soma;
    }

    public void inserir(Municipio municipio) {               //     Questão 8
    	raiz = inserirNo(raiz, municipio);
    }

    
    private Nodo inserirNo(Nodo raiz, Municipio municipio) {
    	
    	if (raiz == null) {
    	return new Nodo(municipio);
        }

    	int comparacao = municipio.getNome().compareTo(raiz.municipio.getNome());

	    if (comparacao < 0) {
	    	raiz.esq = inserirNo(raiz.esq, municipio);
	    } else if (comparacao > 0) {
	        raiz.dir = inserirNo(raiz.dir, municipio);
	    } else {
	        raiz.ocorrencias++;
	    }
	
	    raiz.alte = getAltura(raiz.esq);
	    raiz.altd = getAltura(raiz.dir);
	
	    // balanceamento da árvore AVL
	
	    if (raiz.altd - raiz.alte == 2) {
	    	if (municipio.getNome().compareTo(raiz.dir.municipio.getNome()) > 0) {
	    		raiz = rotacao_esquerda(raiz);
	        } else {
	        	raiz = rotacaoDuplaDireita(raiz);
	        }
	    } else if (raiz.alte - raiz.altd == 2) {
	        if (municipio.getNome().compareTo(raiz.esq.municipio.getNome()) < 0) {
	        	raiz = rotacao_direita(raiz);
	        } else {
	            raiz = rotacaoDuplaEsquerda(raiz);
	        }
	    }
	    
	    return raiz;
    }
    
    public void mostrarArvoreMunicipios() {
        mostrarArvoreRecursivo(raiz, "");
    }

    private void mostrarArvoreRecursivo(Nodo nodo, String espacos) {
        if (nodo != null) {
            mostrarArvoreRecursivo(nodo.dir, espacos + "   ");
            System.out.println(espacos + "|__ " + nodo.municipio.getNome());
            mostrarArvoreRecursivo(nodo.esq, espacos + "   ");
        }
    }

    
    public int contarMunicipios() {        // Questão 8-a)
        return contarNos(raiz);
    }

    private int contarNos(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }
        
        int total = 1; // conta o próprio nó
        
        total += contarNos(raiz.esq); // conta os nós da subárvore esquerda
        total += contarNos(raiz.dir); // conta os nós da subárvore direita
        
        return total;
    }

    public void mostrarMunicipiosComMaisDeXHabitantes(int x) {       // Questão 8-b
        mostrarMunicipiosRecursivo(raiz, x);
    }

    private void mostrarMunicipiosRecursivo(Nodo nodo, int x) {
        if (nodo != null) {
            mostrarMunicipiosRecursivo(nodo.dir, x);
            if (nodo.municipio.getPopulacao() > x) {
                System.out.println(nodo.municipio.getNome());
            }
            mostrarMunicipiosRecursivo(nodo.esq, x);
        }
    }

    
    public void mostrarDensidades() {    // Questão 8-c
        percorrerNo(raiz);
    }

    private void percorrerNo(Nodo nodo) {
        if (nodo != null) {
            percorrerNo(nodo.esq);
            System.out.println("Densidade demográfica de " + nodo.municipio.getNome()
            	+ ": " + String.format("%.2f",nodo.municipio.calcularDensidadeDemografica()));
            percorrerNo(nodo.dir);
        }
    }
    

    public double calcularSomatorioArea() {             //Questão 8-d
        double somatorio = calcularSomatorioAreaNo(raiz);
        double territorioNacional = 8510000; // área do território nacional em km²
        double porcentagem = (somatorio / territorioNacional) * 100;
        return porcentagem;
    }

    private double calcularSomatorioAreaNo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        double areaEsquerda = calcularSomatorioAreaNo(nodo.esq);
        double areaDireita = calcularSomatorioAreaNo(nodo.dir);
        return areaEsquerda + areaDireita + nodo.municipio.getArea();
    }
   
    public Municipio getCidadeMaiorPopulacao() {              // Questão 8-e
        if (raiz == null) {
            return null;
        }
        return getCidadeMaiorPopulacao(raiz);
    }

    
    private Municipio getCidadeMaiorPopulacao(Nodo nodo) {
        if (nodo == null) {
            return null;
        }
        
        Municipio cidadeAtual = nodo.getCidade();
        Municipio cidadeEsquerda = getCidadeMaiorPopulacao(nodo.getEsquerda());
        Municipio cidadeDireita = getCidadeMaiorPopulacao(nodo.getDireita());
        
        Municipio cidadeMaiorPopulacao = cidadeAtual;
        
        if (cidadeEsquerda != null && cidadeEsquerda.getPopulacao() > cidadeMaiorPopulacao.getPopulacao()) {
            cidadeMaiorPopulacao = cidadeEsquerda;
        }
        
        if (cidadeDireita != null && cidadeDireita.getPopulacao() > cidadeMaiorPopulacao.getPopulacao()) {
            cidadeMaiorPopulacao = cidadeDireita;
        }
        
        return cidadeMaiorPopulacao;
    }

	   
}
