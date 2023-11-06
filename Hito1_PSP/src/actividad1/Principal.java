package actividad1;

public class Principal {
	
	//metodo main que genera un bufer y los examenes junto a los examinadores
	public static void main(String[] args) throws InterruptedException {
		
		BufferExamenes generador = new BufferExamenes();
		
		new ProductorExamenes(generador);
		new Examinador("Rosa", generador);
		new ProductorExamenes(generador);
		new Examinador("Miguel", generador);
		new ProductorExamenes(generador);
		new Examinador("Carlos", generador);
		
	}
}