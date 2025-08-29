package Hash;

import java.util.Objects;

public class Hash_Tabela {
    private Pessoa[] tabela;
    private int tamanho;

    public Hash_Tabela(int tamanho){
        this.tamanho = tamanho;
        tabela = new Pessoa[tamanho];
    }
    //
    private int hash (String cpf){
        return Math.abs(cpf.hashCode() % tamanho);
    }

    public void inserir(Pessoa p){
        int pos = hash(p.getCpf());

        while (tabela[pos] != null){
            if (Objects.equals(tabela[pos].getCpf(), p.getCpf())){
                System.out.println("Erro: CPF já cadastrado!");
                return;
            }
            pos = (pos + 1) % tamanho;
        }
        tabela[pos] = p;
    }

    public Pessoa buscar(String cpf){
        int pos = hash(cpf);

        while (tabela[pos] != null){
            if (Objects.equals(tabela[pos].getCpf(), cpf)){
                return tabela[pos];
            }
            pos = (pos + 1 ) % tamanho;
        }
        return null;
    }

    public void mostrar(){
        for (Pessoa p : tabela){
            if (p != null){
                System.out.println(p);
            }
        }
    }


}
