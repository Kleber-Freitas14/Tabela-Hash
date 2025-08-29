package Hash;

public class Hash_Tabela {
    private Pessoa[] tabela;
    private int tamanho;

    public Hash_Tabela(int tamanho){
        this.tamanho = tamanho;
        tabela = new Pessoa[tamanho];
    }
    //
    private int hash (int cpf){
        return Math.abs(cpf % tamanho);
    }

    public void inserir(Pessoa p){
        int pos = hash(p.getCpf());


        while (tabela[pos] != null){
            if (tabela[pos].getCpf() == (p.getCpf())){
                System.out.println("Erro: CPF já cadastrado!");
                return;
            }
            pos = (pos + 1) % tamanho;
        }
        tabela[pos] = p;
    }


}
