
import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class Menu {

    private Cliente cliente;
    private Conta conta;
    private Cliente[] arraycliente;
    private Conta[] arrayconta;

    Validacao r = new Validacao();

    public Menu() throws IOException {

        int op;
        do {

            System.out.println("""
                           1.Registrar Cliente
                           2.Criar Conta
                           3.Ver saldo
                           4.Levantar Dinheiro
                           5.Transferir Dinheiro
                           6.Depositar
                           7.Sair
                           """);
            op = r.validarInt(0, 9, "Digite a opcao");
            switch (op) {
                case 1:
                    registrarcliente();
                    break;
                case 2:
                    criarconta();
                    break;
                case 3:
                    versaldo();
                    break;
                case 4:
                    levantardinheiro();
                    break;
                case 5:
                    transferirdinheiro();
                    break;
                case 6:
                    depositar();
                    break;
                case 7:
                    System.out.print("Saindo com sucesso!");
                    break;
                default:
                    System.out.println("Opcao invalida!");

            }
        } while (op != 7);

    }

    public void registrarcliente() throws IOException {
        int nrdecliente = r.validarInt(1, 5, "Digite a quantidade de clientes a registar:");
        //coloquei 5 porque estou a dizer que quero que o maximo a ser cadastro seja 

        String str = "";

        try {
            FileWriter fw = new FileWriter("Cliente", true);
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedReader x = new BufferedReader(new InputStreamReader(System.in));

            for (byte i = 0; i < nrdecliente; i++) {
                int nuit = r.validarInt(1000, 99999, "Introduza o Nuit");
                bw.write(str.valueOf(nuit + ";"));
                String nome = r.validarStr("Introduza o nome");
                bw.write(nome + ";");
                String Bi = r.validarStrBi("Introduza o bi");
                bw.write(Bi + ";");
                char sexo = r.validarChar("Introduza o sexo:");
                bw.write(sexo + ";");
                String cidade = r.validarStr("Introduza a cidade");
                bw.write(cidade + ";");
                String Estadocivil = r.validarStr("Introduza o estadocivil");
                bw.write(Estadocivil);

                bw.newLine();

                System.out.println("Cliente criado com sucesso!!");
                System.out.println("---------------------------");

            }

            bw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public void criarconta() throws IOException {
        String line;
        arraycliente = new Cliente[nrdelinhas("Cliente")];

        try {
            FileReader fr = new FileReader("Cliente");
            BufferedReader br = new BufferedReader(fr);
            int counter = 0;
            line = br.readLine();

            while (line != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                int nuit = Integer.parseInt(str.nextToken());
                String nome = str.nextToken();
                String Bi = str.nextToken();
                char sexo = str.nextToken().charAt(0);
                String cidade = str.nextToken();
                String estadocivil = str.nextToken();
                cliente = new Cliente(nuit, nome, Bi, sexo, estadocivil, cidade);
                arraycliente[counter] = cliente;
                counter++;
                line = br.readLine();

            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < arraycliente.length; i++) {
            System.out.println("[" + (i + 1) + "]: " + arraycliente[i].getNome());
        }
        int op = r.validarInt(0, 10, "Digite a opcao:");

        if (verificarnome(arraycliente[op - 1].getNome()) == true) {
            System.out.println("Esse cliente ja tem uma conta criada!");
        } else {
            Random rand = new Random();
            String Nomebanco = "Mbim";
            double Saldo = 0;
            double contanum = rand.nextInt(1000000);
            String str = "";
            try {
                FileWriter fw = new FileWriter("Conta", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(arraycliente[op - 1].getNome() + ";");
                bw.write(Nomebanco + ";");
                bw.write(str.valueOf(Saldo + ";"));
                bw.write(str.valueOf(contanum));
                bw.newLine();
                bw.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Conta[" + (op) + "] criada com sucesso");
        }

    }

    public void versaldo() throws IOException {
        lerficheiroconta();
        for (int i = 0; i < arrayconta.length; i++) {
            System.out.println("[" + (i + 1) + "]-" + arrayconta[i].getNome());
        }
        int op = r.validarInt(0, nrdelinhas("Conta"), "Digite a opcao:");
        System.out.println("O saldo e:" + arrayconta[op - 1].getSaldo());

    }

    public void levantardinheiro() throws IOException {
        lerficheiroconta();
        int val;

        for (int i = 0; i < arrayconta.length; i++) {
            System.out.println("[" + (i + 1) + "]-" + arrayconta[i].getNome());
        }
        int op = r.validarInt(0, nrdelinhas("Conta"), "Digite a opcao:");

        val = r.validarInt(100, 10000, "Digite o valor a levantar para o cliente [" + (op) + "]:");
        if (val > arrayconta[op - 1].getSaldo()) {
            System.out.println("Cliente [" + (op) + "] Nao tem saldo suficiente!");
        } else {
            System.out.println("Valor Levantado com sucesso");
            arrayconta[op - 1].setSaldo(arrayconta[op - 1].getSaldo() - val);
            System.out.println("Cliente [" + (op) + "],O seu saldo Actual e:" + arrayconta[op - 1].getSaldo());
            System.out.println("---------------------------");

        }
        escrevernaconta();
    }

    public void transferirdinheiro() throws IOException {
        int val, conta1, conta2;
        lerficheiroconta();

        for (int i = 0; i < arrayconta.length; i++) {
            System.out.println("[" + (i + 1) + "]-" + arrayconta[i].getNome());
        }

        val = r.validarInt(200, 10000, "Digite o valor a transferir");
        conta1 = r.validarInt(1, nrdelinhas("Conta"), "Conta partida:");
        conta2 = r.validarInt(1, nrdelinhas("Conta"), "Conta Chegada:");
        if (conta1 == conta2) {
            System.out.println("Impssivel transferir para si mesmo");
        } else {
            if (arrayconta[conta1 - 1].getSaldo() < val) {
                System.out.println("Nao tem saldo suficiente!");
            } else {
                arrayconta[conta1 - 1].setSaldo(arrayconta[conta1 - 1].getSaldo() - val);
                arrayconta[conta2 - 1].setSaldo(arrayconta[conta2 - 1].getSaldo() + val);

                System.out.println("Transferencia efectuada com sucesso!");
            }
        }
        escrevernaconta();

    }

    public void depositar() throws IOException {
        lerficheiroconta();
        int val;

        for (int i = 0; i < arrayconta.length; i++) {
            System.out.println("[" + (i + 1) + "]-" + arrayconta[i].getNome());
        }
        int op = r.validarInt(0, nrdelinhas("Conta"), "Digite a opcao:");
        val = r.validarInt(100, 10000, "Digite o valor a depositar para o cliente [" + (op) + "]:");
        System.out.println("Valor Deposiitado com sucesso ❤");
        arrayconta[op - 1].setSaldo(arrayconta[op - 1].getSaldo() + val);
        System.out.println("Cliente [" + (op) + "],O seu saldo Actual e:" + arrayconta[op - 1].getSaldo());
        System.out.println("---------------------------");
        escrevernaconta();
    }

    public int nrdelinhas(String nome) {
        String line;
        int counter = 0;
        try {
            FileReader fr = new FileReader(nome);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                counter++;
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }

    public boolean verificarnome(String val) {
        String line;
        try {
            FileReader fr = new FileReader("Conta");
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                if (line.contains(val)) {
                    return true;
                }
                line = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void lerficheiroconta() throws IOException {
        String line;
        arrayconta = new Conta[nrdelinhas("Conta")];
        StringTokenizer str;
        try {
            FileReader fr = new FileReader("Conta");
            BufferedReader br = new BufferedReader(fr);
            int counter = 0;
            line = br.readLine();

            while (line != null) {
                str = new StringTokenizer(line, ";");
                String nome = str.nextToken();
                String Nomebanco = str.nextToken();
                double Saldo = Double.parseDouble(str.nextToken());
                double contanum = Double.parseDouble(str.nextToken());
                conta = new Conta(nome, Nomebanco, Saldo, contanum);
                arrayconta[counter] = conta;
                counter++;
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escrevernaconta() throws IOException {
        try {
            String str = "";
            FileWriter fw = new FileWriter("Conta");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < arrayconta.length; i++) {
                bw.write(arrayconta[i].getNome() + ";");
                bw.write(arrayconta[i].getNomebanco() + ";");
                bw.write(str.valueOf(arrayconta[i].getSaldo() + ";"));
                bw.write(str.valueOf(arrayconta[i].getContanum()));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
