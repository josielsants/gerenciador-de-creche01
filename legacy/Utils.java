import java.util.Random;
import java.util.regex.Pattern;

/**
 * Classe utilitária com métodos para validação e geração de dados
 */
public class Utils {
    
    private static final Random random = new Random();
    
    /**
     * Gera um ID único baseado no timestamp e número aleatório
     */
    public static String gerarId() {
        return String.valueOf(System.currentTimeMillis() + random.nextInt(1000));
    }
    
    /**
     * Valida se o CPF tem formato correto (apenas números e 11 dígitos)
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
    
    /**
     * Valida se o telefone tem formato correto
     */
    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return false;
        }
        // Remove caracteres não numéricos
        String numeros = telefone.replaceAll("[^0-9]", "");
        return numeros.length() >= 10 && numeros.length() <= 11;
    }
    
    /**
     * Valida se a idade está em um range válido
     */
    public static boolean validarIdade(int idade, int idadeMin, int idadeMax) {
        return idade >= idadeMin && idade <= idadeMax;
    }
    
    /**
     * Valida se o nome não está vazio
     */
    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty() && nome.trim().length() >= 2;
    }
    
    /**
     * Formata o CPF com pontos e hífen
     */
    public static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9, 11);
    }
    
    /**
     * Formata o telefone
     */
    public static String formatarTelefone(String telefone) {
        if (telefone == null) return telefone;
        
        String numeros = telefone.replaceAll("[^0-9]", "");
        if (numeros.length() == 11) {
            return "(" + numeros.substring(0, 2) + ") " + 
                   numeros.substring(2, 7) + "-" + 
                   numeros.substring(7, 11);
        } else if (numeros.length() == 10) {
            return "(" + numeros.substring(0, 2) + ") " + 
                   numeros.substring(2, 6) + "-" + 
                   numeros.substring(6, 10);
        }
        return telefone;
    }
    
    /**
     * Limpa a tela do console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Pausa a execução por um tempo determinado
     */
    public static void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Valida se uma string não está vazia ou nula
     */
    public static boolean naoVazio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    
    /**
     * Converte string para inteiro com tratamento de erro
     */
    public static int converterParaInteiro(String texto, int valorPadrao) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return valorPadrao;
        }
    }
    
    /**
     * Exibe uma linha separadora
     */
    public static void exibirLinhaSeparadora() {
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
    
    /**
     * Exibe uma mensagem de erro formatada
     */
    public static void exibirErro(String mensagem) {
        System.out.println("❌ ERRO: " + mensagem);
    }
    
    /**
     * Exibe uma mensagem de sucesso formatada
     */
    public static void exibirSucesso(String mensagem) {
        System.out.println("✅ SUCESSO: " + mensagem);
    }
    
    /**
     * Exibe uma mensagem de informação formatada
     */
    public static void exibirInfo(String mensagem) {
        System.out.println("ℹ️  INFO: " + mensagem);
    }
}

