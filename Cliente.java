
import java.io.*;

public class Cliente {

    private int nuit;
    private String nome;
    private String bi;
    private char sexo;
    private String estadocivil;
    private String cidade;

    public Cliente(int nuit, String nome, String bi, char sexo, String estadocivil, String cidade) {
        this.nuit = nuit;
        this.nome = nome;
        this.bi = bi;
        this.sexo = sexo;
        this.estadocivil = estadocivil;
        this.cidade = cidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public String getNome() {
        return nome;
    }

    public String getBi() {
        return bi;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public char getSexo() {
        return sexo;
    }

    public String getCidade() {
        return estadocivil;
    }

    public int getNuit() {
        return nuit;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nuit=" + nuit + ", nome=" + nome + ", bi=" + bi + ", sexo=" + sexo + ", estadocivil=" + estadocivil + ", cidade=" + cidade + '}';
    }

}
