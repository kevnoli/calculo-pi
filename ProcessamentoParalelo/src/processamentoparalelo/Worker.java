package processamentoparalelo;

public class Worker extends Thread {

    private final String nome;
    private double soma, largura;
    private final int intervaloMin, intervaloMax;

    private void calcularPI(int intervaloMin, int intervaloMax, double largura) {
        double subSoma=0.0;
        for (int i = intervaloMin; i < intervaloMax; i++) {
            double x = ((i + 0.5) * largura);
            subSoma += 4.0 / (1.0 + x * x);
        }
        subSoma *= largura;
        soma=subSoma;
    }

    @Override
    public void run() {
        calcularPI(this.intervaloMin, this.intervaloMax, this.largura);
    }

    public Worker(String nome, int intervaloMin, int intervaloMax, double largura) {
        this.nome = nome;
        this.intervaloMin = intervaloMin;
        this.intervaloMax = intervaloMax;
        this.largura = largura;
    }

    public double getSoma() {
        return soma;
    }
}
