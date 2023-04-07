
import java.io.*;
import java.lang.*;

public class Validacao {

    BufferedReader x = new BufferedReader(new InputStreamReader(System.in));

    public int validarInt(int min, int max, String msg) throws IOException {
        int valor;

        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                System.out.println(msg);
                valor = Integer.parseInt(x.readLine());
                if (valor < min || valor > max) {
                    System.out.println("Valor nao permitido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um valor inteiro.");
                valor = min - 1;
            }
        } while (valor < min || valor > max);
        return valor;
    }

    public String validarStr(String msg) throws IOException {
        String name;
        do {
            System.out.println(msg);

            try {
                name = x.readLine();
            } catch (IOException e) {
                name = "";
                e.getMessage();
            } catch (IndexOutOfBoundsException e) {
                name = "";
                System.out.print("Error" + e);
            } catch (NullPointerException r) {
                name = "";
                System.out.print("Erro" + r);
            }

            if (name.length() == 0 || name.trim().isEmpty() || numdetector(name) == true) {
                System.out.println("Valor nao permitido");
            }
        } while (name.length() == 0 || name.trim().isEmpty() || numdetector(name) == true);
        return name;

    }

    public String validarStrBi(String msg) throws IOException {
        String name;
        do {
            System.out.println(msg);

            try {
                name = x.readLine();
            } catch (IOException e) {
                name = "";
                e.getMessage();
            } catch (IndexOutOfBoundsException e) {
                name = "";
                System.out.print("Error" + e);
            } catch (NullPointerException r) {
                name = "";
                System.out.print("Erro" + r);
            }

            if (name.length() == 0 || name.trim().isEmpty()) {
                System.out.println("Valor nao permitido");
            }
        } while (name.length() == 0 || name.trim().isEmpty());
        return name;

    }

    public char validarChar(String msg) throws IOException {
        char sexo;
        char m = 'M';
        char f = 'F';
        do {
            System.out.println(msg);
            sexo = x.readLine().charAt(0);

            if (sexo != m && sexo != f) {
                System.out.println("Valor errado:");
            }
        } while (sexo != m && sexo != f);
        return sexo;
    }

    boolean numdetector(String val) throws IOException {
        for (int i = 0; i < val.length(); i++) {
            if (Character.isDigit(val.charAt(i))) {
                return true;
            }

        }
        return false;
    }

}
