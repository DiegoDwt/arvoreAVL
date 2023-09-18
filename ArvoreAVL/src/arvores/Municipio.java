package arvores;


public class Municipio {
	
    private String nome;
    private double area;
    private int populacao;


    public Municipio(String nome, double area, int populacao) {
        this.nome = nome;
        this.area = area;
        this.populacao = populacao;   
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}
	
	public double calcularDensidadeDemografica() {
	        return populacao / area;
	    }
	 

}
