package Hash;

public class Pessoa {


    private String name;
    private String cpf;
    private int idade;

    public Pessoa() {
        this.name = name;
        this.cpf = cpf;
        this.idade = idade;
    }

    public Pessoa(String name, String cpf, int idade) {
        this.name = name;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = java.lang.String.valueOf(cpf);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString(){
      return "Nome: " +  name + "  CPF: " +  cpf + "  Idade: " +  idade;
    }
}
