/**
 * Classe principal do sistema de gestão escolar.
 * Ponto de entrada da aplicação.
 * 
 * @author Sistema de Gestão Escolar
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            // Cria e inicia o aplicativo
            Aplicativo app = new Aplicativo();
        } catch (Exception e) {
            System.err.println("Erro fatal ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
