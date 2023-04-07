
import java.io.*;

public class Conta {

    BufferedReader x = new BufferedReader(new InputStreamReader(System.in));

    private String nomebanco;
    private double saldo;
    private String nome;
    private double contanum;

    public Conta(String nome, String nomebanco, double saldo, double contanum) throws IOException {
        this.nome = nome;
        this.nomebanco = nomebanco;
        this.saldo = saldo;
        this.contanum = contanum;

    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }

    public void setContanum(int contanum) {
        this.contanum = contanum;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getContanum() {
        return contanum;
    }

    @Override
    public String toString() {
        return "Conta{" + "x=" + x + ", nomebanco=" + nomebanco + ", saldo=" + saldo + ", nome=" + nome + ", contanum=" + contanum + '}';
    }

}
