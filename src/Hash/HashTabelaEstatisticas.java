package Hash;

public class HashTabelaEstatisticas {

    private Pessoa[] tabela;
    private int tamanho;
    private int count = 0;

    // Estatísticas
    private int totalInsercoes = 0;
    private int totalBuscas = 0;
    private int totalExclusoes = 0;
    private int totalColisoes = 0;
    private int totalSondagensInsercao = 0;
    private int totalSondagensBusca = 0;
    private int totalSondagensExclusao = 0;

    private final double LOAD_FACTOR = 0.7;

    public HashTabelaEstatisticas(int tamanho) {
        this.tamanho = Math.max(3, tamanho);
        tabela = new Pessoa[this.tamanho];
    }

    private int hash(String cpf) {
        return Math.abs(cpf.hashCode() % tamanho);
    }

    public void inserir(Pessoa p) {
        if (p == null || p.getCpf() == null) throw new IllegalArgumentException("Pessoa ou CPF nulo");

        totalInsercoes++;

        if ((count + 1) / (double) tamanho > LOAD_FACTOR) {
            rehash();
        }

        int pos = hash(p.getCpf());
        int start = pos;
        int sondagens = 0;

        while (tabela[pos] != null) {
            sondagens++;
            totalColisoes++;
            if (tabela[pos].getCpf().equals(p.getCpf())) {
                System.out.println("CPF já cadastrado: " + p.getCpf());
                totalSondagensInsercao += sondagens;
                return;
            }
            pos = (pos + 1) % tamanho;
            if (pos == start) {
                System.out.println("Tabela cheia. Não foi possível inserir: " + p.getCpf());
                totalSondagensInsercao += sondagens;
                return;
            }
        }
        tabela[pos] = p;
        count++;
        totalSondagensInsercao += sondagens + 1;
    }

    public Pessoa buscar(String cpf) {
        if (cpf == null) return null;

        totalBuscas++;
        int pos = hash(cpf);
        int start = pos;
        int sondagens = 0;

        while (tabela[pos] != null) {
            sondagens++;
            if (tabela[pos].getCpf().equals(cpf)) {
                totalSondagensBusca += sondagens;
                return tabela[pos];
            }
            pos = (pos + 1) % tamanho;
            if (pos == start) break;
        }
        totalSondagensBusca += sondagens;
        return null;
    }

    public void excluir(String cpf) {
        if (cpf == null) return;

        totalExclusoes++;
        int pos = hash(cpf);
        int start = pos;
        int sondagens = 0;

        while (tabela[pos] != null) {
            sondagens++;
            if (tabela[pos].getCpf().equals(cpf)) {
                tabela[pos] = null;
                count--;
                System.out.println("Cadastro removido: " + cpf);
                reorganizar(pos);
                totalSondagensExclusao += sondagens;
                return;
            }
            pos = (pos + 1) % tamanho;
            if (pos == start) break;
        }
        System.out.println("Cadastro não encontrado: " + cpf);
        totalSondagensExclusao += sondagens;
    }

    private void reorganizar(int posRemovido) {
        int pos = (posRemovido + 1) % tamanho;
        while (tabela[pos] != null) {
            Pessoa p = tabela[pos];
            tabela[pos] = null;
            count--;
            inserir(p);
            pos = (pos + 1) % tamanho;
        }
    }

    public void mostrar() {
        System.out.println("\n--- Tabela Hash ---");
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("[%d] %s%n", i, tabela[i]);
        }
    }

    public void mostrarEstatisticas() {
        System.out.println("\n=== Estatísticas da Tabela Hash ===");
        System.out.println("Tamanho da tabela: " + tamanho);
        System.out.println("Elementos atuais: " + count);
        System.out.printf("Fator de carga atual: %.2f%n", count / (double) tamanho);
        System.out.println("Total inserções: " + totalInsercoes);
        System.out.println("Total buscas: " + totalBuscas);
        System.out.println("Total exclusões: " + totalExclusoes);
        System.out.println("Total colisões em inserções: " + totalColisoes);
        System.out.printf("Média de sondagens por inserção: %.2f%n",
                totalInsercoes == 0 ? 0 : totalSondagensInsercao / (double) totalInsercoes);
        System.out.printf("Média de sondagens por busca: %.2f%n",
                totalBuscas == 0 ? 0 : totalSondagensBusca / (double) totalBuscas);
        System.out.printf("Média de sondagens por exclusão: %.2f%n",
                totalExclusoes == 0 ? 0 : totalSondagensExclusao / (double) totalExclusoes);
    }

    private void rehash() {
        Pessoa[] old = tabela;
        int newSize = tamanho * 2 + 1;
        tabela = new Pessoa[newSize];
        int oldTamanho = tamanho;
        tamanho = newSize;
        count = 0;

        System.out.println("\n*** Rehash realizado. Novo tamanho: " + tamanho + " ***");

        for (Pessoa p : old) {
            if (p != null) {
                inserir(p);
            }
        }
    }
}
