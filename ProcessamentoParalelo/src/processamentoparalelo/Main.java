package processamentoparalelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Digite o intervalo para o calculo de PI: ");
		Scanner teclado = new Scanner(System.in);
		int intervalo = teclado.nextInt();
		System.out.println("Digite o numero de threads: ");
		int threads = teclado.nextInt();
		int controle = intervalo / threads;
		double largura = (1.0 / intervalo);

		ArrayList<Worker> workers = new ArrayList<Worker>();

		for (int i = 0; i < threads; i++) {
			Worker w = new Worker("T" + i, controle * i, controle * (i + 1), largura);
			workers.add(w);
		}
		workers.forEach((worker) -> {
			worker.start();
		});

		workers.forEach((worker) -> {
			try {
				worker.join();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		});
		double soma = 0.0;
		for (int i = 0; i < workers.size(); i++) {
			soma += workers.get(i).getSoma();
		}

		System.out.println("Estimation of pi is " + soma);
		teclado.close();
	}
}