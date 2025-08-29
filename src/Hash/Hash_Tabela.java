package Hash;

public class Hash_Tabela {
    private Pessoa[] Tabela;
    private int tamanho;

    public Hash_Tabela(int tamanho){
        this.tamanho = tamanho;
        Tabela = new Pessoa[tamanho];
    }
    private int hash (int cpf){
        return Math.abs(cpf % tamanho);
    }

    





}
