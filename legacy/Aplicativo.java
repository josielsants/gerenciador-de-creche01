import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal do sistema de gestão escolar.
 * Gerencia todas as operações do sistema incluindo cadastros e consultas.
 * 
 * @author Sistema de Gestão Escolar
 * @version 1.0
 */
public class Aplicativo {

    private ArrayList<Turma> turmas;
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Responsavel> responsaveis;
    private ArrayList<Matricula> matriculas;

    private Scanner scanner;

    /**
     * Construtor da classe Aplicativo.
     * Inicializa todas as listas e inicia o sistema.
     */
    public Aplicativo(){
        try {
            scanner = new Scanner(System.in);
            turmas = new ArrayList<>();
            alunos = new ArrayList<>();
            professores = new ArrayList<>();
            funcionarios = new ArrayList<>();
            responsaveis = new ArrayList<>();
            matriculas = new ArrayList<>();

            // Inicializa o sistema
            start();
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inicia o loop principal do sistema.
     * Exibe o cabeçalho e processa as opções do usuário.
     */
    public void start(){
        try {
            exibirCabecalho();
            boolean ativo = true;

            while (ativo){
                try {
                    ativo = selecionarOpcao();
                } catch (Exception e) {
                    print("Erro ao processar opção: " + e.getMessage());
                    input("Pressione Enter para continuar...");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro fatal no sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Exibe o cabeçalho do sistema
     */
    public void exibirCabecalho() {
        print("╔══════════════════════════════════════════════════════════════╗");
        print("║                    SISTEMA DE GESTÃO ESCOLAR                 ║");
        print("║                    Educação Infantil                         ║");
        print("╚══════════════════════════════════════════════════════════════╝");
        print("");
    }

    public void print(String msg){
        System.out.print(msg+"\n");
    }

    public String input(String msg){
        System.out.print(msg+"\n");
        return scanner.nextLine();
    }

    /**
     * Busca um aluno pelo nome.
     * 
     * @param nome nome do aluno a ser buscado
     * @return o aluno encontrado ou null se não existir
     */
    public Aluno getAluno(String nome){
        if(nome == null || nome.trim().isEmpty() || alunos.isEmpty()){
            return null;
        }
        
        for(Aluno a : alunos){
            if(a != null && a.getNome() != null && a.getNome().equalsIgnoreCase(nome.trim())){
                return a;
            }
        }
        return null;
    }


    /**
     * Busca um professor pelo nome.
     * 
     * @param nome nome do professor a ser buscado
     * @return o professor encontrado ou null se não existir
     */
    public Professor getProfessor(String nome){
        if(nome == null || nome.trim().isEmpty() || professores.isEmpty()){
            return null;
        }
        
        for(Professor p : professores){
            if(p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nome.trim())){
                return p;
            }
        }
        return null;
    }

    /**
     * Busca um responsável pelo nome.
     * 
     * @param nome nome do responsável a ser buscado
     * @return o responsável encontrado ou null se não existir
     */
    public Responsavel getResponsavel(String nome){
        if(nome == null || nome.trim().isEmpty() || responsaveis.isEmpty()){
            return null;
        }
        
        for(Responsavel r : responsaveis){
            if(r != null && r.getNome() != null && r.getNome().equalsIgnoreCase(nome.trim())){
                return r;
            }
        }
        return null;
    }

    /**
     * Busca uma turma pelo nome.
     * 
     * @param nome nome da turma a ser buscada
     * @return a turma encontrada ou null se não existir
     */
    public Turma getTurma(String nome){
        if(nome == null || nome.trim().isEmpty() || turmas.isEmpty()){
            return null;
        }
        
        for(Turma t : turmas){
            if(t != null && t.getNome() != null && t.getNome().equalsIgnoreCase(nome.trim())){
                return t;
            }
        }
        return null;
    }

    /**
     * Processa a opção selecionada pelo usuário.
     * 
     * @return true para continuar o sistema, false para encerrar
     */
    public boolean selecionarOpcao(){
        try {
            String opcao = input(getMenu("geral"));
            
            // Valida se a opção é válida
            if(opcao == null || opcao.trim().isEmpty()) {
                print("Opção inválida. Tente novamente.");
                return true;
            }

            switch (opcao.trim()){ //exibir  menu e pegar o input
            case "1":
                print("=== CADASTRAR ALUNO ===");
                try {
                    String nome = input("Nome do aluno:");
                    if (!Utils.validarNome(nome)) {
                        Utils.exibirErro("Nome inválido. Deve ter pelo menos 2 caracteres.");
                        return true;
                    }
                    
                    String idadeStr = input("Idade:");
                    int idade;
                    try {
                        idade = Integer.parseInt(idadeStr);
                        if (idade < 2 || idade > 6) {
                            Utils.exibirErro("Idade inválida. Deve estar entre 2 e 6 anos.");
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        Utils.exibirErro("Idade inválida. Digite um número.");
                        return true;
                    }
                    
                    String necessidade = input("Necessidade especial (ou deixe em branco):");
                    if (necessidade.trim().isEmpty()) {
                        necessidade = "nenhuma";
                    }
                    
                    Aluno aluno = new Aluno(Utils.gerarId(), nome, idade, necessidade);
                    
                    String nomeResp = input("Nome completo do responsável:");
                    Responsavel responsavel = getResponsavel(nomeResp);
                    if(responsavel == null){
                        print("Responsável não encontrado. Cadastro cancelado.");
                        return true;
                    }
                    aluno.setResponsavel(responsavel);
                    
                    String nomeTurma = input("Nome da turma:");
                    Turma turmaAluno = getTurma(nomeTurma);
                    if(turmaAluno == null){
                        print("Turma não encontrada. Cadastro cancelado.");
                        return true;
                    }
                    
                    if(!turmaAluno.addAluno(aluno)){
                        print("Aluno com idade inválida para a turma.");
                        return true;
                    }
                    
                    alunos.add(aluno);
                    Utils.exibirSucesso("Aluno " + aluno.getNome() + " cadastrado com sucesso!");
                    Utils.exibirInfo("ID do aluno: " + aluno.getId());
                    input("\nPressione Enter para continuar...");
                    
                } catch (Exception e) {
                    print("Erro no cadastro: " + e.getMessage());
                    input("\nPressione Enter para continuar...");
                }
                return true;
            case "2":
                print("=== CADASTRAR RESPONSÁVEL ===");
                try {
                    String nomeRes = input("Nome do responsável:");
                    if (!Utils.validarNome(nomeRes)) {
                        print("Nome inválido. Deve ter pelo menos 2 caracteres.");
                        return true;
                    }
                    
                    String parentesco = input("Nível de parentesco (ex: pai, mãe, avô, avó):");
                    if (parentesco.trim().isEmpty()) {
                        print("Parentesco é obrigatório.");
                        return true;
                    }
                    
                    String tel = input("Número de telefone:");
                    if (!Utils.validarTelefone(tel)) {
                        print("Telefone inválido. Use o formato: (11) 99999-9999");
                        return true;
                    }
                    
                    Responsavel resp = new Responsavel(Utils.gerarId(), nomeRes);
                    resp.setTelefone(tel);
                    resp.setParentesco(parentesco);
                    responsaveis.add(resp);
                    print("Responsável " + resp.getNome() + " adicionado com sucesso!");
                    print("ID do responsável: " + resp.getId());
                    print("Telefone: " + Utils.formatarTelefone(resp.getTelefone()));
                    input("\nPressione Enter para continuar...");
                    
                } catch (Exception e) {
                    print("Erro no cadastro: " + e.getMessage());
                    input("\nPressione Enter para continuar...");
                }
                return true;
            case "3":
                print("=== CADASTRAR FUNCIONÁRIO ===");
                try {
                    String tipoFunc = input(getMenu("funcionario"));
                    Funcionario func;
                    String nomeFunc = input("Nome do funcionário:");
                    
                    if (!Utils.validarNome(nomeFunc)) {
                        print("Nome inválido. Deve ter pelo menos 2 caracteres.");
                        return true;
                    }
                    
                    String cargo = "";
                    if(tipoFunc.equals("1")){
                        cargo = "Professor";
                        func = new Professor(Utils.gerarId(), nomeFunc);
                    } else if (tipoFunc.equals("2")){
                        cargo = "Coordenador";
                        func = new Coordenador(Utils.gerarId(), nomeFunc);
                    } else if (tipoFunc.equals("3")){
                        cargo = "Cuidador";
                        String minC = input("Informe a idade mínima (2-6):");
                        String maxC = input("Informe a idade máxima (2-6):");
                        try{
                            int idadeMinCuidador = Integer.parseInt(minC);
                            int idadeMaxCuidador = Integer.parseInt(maxC);
                            
                            if (idadeMinCuidador < 2 || idadeMinCuidador > 6 || 
                                idadeMaxCuidador < 2 || idadeMaxCuidador > 6 ||
                                idadeMinCuidador > idadeMaxCuidador) {
                                print("Idades inválidas! Devem estar entre 2 e 6 anos e idade mínima deve ser menor ou igual à máxima.");
                                return true;
                            }
                            
                            func = new Cuidador(Utils.gerarId(), nomeFunc, idadeMinCuidador, idadeMaxCuidador);
                        } catch (NumberFormatException e){
                            print("Idade inválida! Digite números válidos.");
                            return true;
                        }
                    } else {
                        print("Opção inválida");
                        return true;
                    }
                    
                    func.setCargo(cargo);
                    funcionarios.add(func);
                    if(func instanceof Professor){
                        professores.add((Professor) func);
                    }
                    print("Funcionário " + func.getNome() + " cadastrado com sucesso no cargo " + func.getCargo() + "!");
                    print("ID do funcionário: " + func.getId());
                    input("\nPressione Enter para continuar...");
                    
                } catch (Exception e) {
                    print("Erro no cadastro: " + e.getMessage());
                    input("\nPressione Enter para continuar...");
                }
                return true;
            case "4":
                print("=== CADASTRAR TURMA ===");
                try {
                    String nomeTurma = input("Insira o nome da turma:");
                    if (!Utils.validarNome(nomeTurma)) {
                        print("Nome da turma inválido. Deve ter pelo menos 2 caracteres.");
                        return true;
                    }
                    
                    String turnoTurma = input("Turno da turma (manhã, tarde, vespertino):");
                    if (turnoTurma.trim().isEmpty()) {
                        print("Turno é obrigatório.");
                        return true;
                    }
                    
                    String min = input("Idade mínima (2, 4 ou 6):");
                    String max = input("Idade máxima (3, 5 ou 6):");
                    int idadeMin;
                    int idadeMax;
                    try{
                        idadeMin = Integer.parseInt(min);
                        idadeMax = Integer.parseInt(max);
                        
                        // Validação das combinações de idade permitidas
                        if (!((idadeMin == 2 && idadeMax == 3) || 
                              (idadeMin == 4 && idadeMax == 5) || 
                              (idadeMin == 6 && idadeMax == 6))) {
                            print("Combinação de idades inválida! Use: 2-3, 4-5 ou 6-6");
                            return true;
                        }
                    } catch (NumberFormatException e){
                        print("Idade inválida! Digite números válidos.");
                        return true;
                    }
                    
                    Turma turma = Turma.getTurma(Utils.gerarId(), nomeTurma, turnoTurma, idadeMin, idadeMax);
                    if(turma == null){
                        print("Erro ao criar turma. Verifique os dados.");
                        return true;
                    }
                    
                    String nomeProfTurma = input("Nome do professor:");
                    Professor prof = getProfessor(nomeProfTurma);
                    if(prof == null){
                        print("Professor não encontrado. Cadastre o professor primeiro.");
                        return true;
                    }
                    
                    turma.setProfessor(prof);
                    turmas.add(turma);
                    print("Turma " + turma.getNome() + " cadastrada com sucesso!");
                    print("ID da turma: " + turma.getId());
                    print("Professor: " + prof.getNome());
                    print("Faixa etária: " + idadeMin + " a " + idadeMax + " anos");
                    input("\nPressione Enter para continuar...");
                    
                } catch (Exception e) {
                    print("Erro no cadastro: " + e.getMessage());
                    input("\nPressione Enter para continuar...");
                }
                return true;
            case "5":
                print("=== CADASTRAR MATRÍCULA ===");
                try {
                    String nomeAluno = input("Nome do aluno:");
                    Aluno alunoM = getAluno(nomeAluno);
                    if(alunoM == null){
                        print("Aluno não encontrado! Cadastre o aluno primeiro.");
                        return true;
                    }
                    
                    String nomeTurmaM = input("Nome da turma:");
                    Turma turmaM = getTurma(nomeTurmaM);
                    if(turmaM == null){
                        print("Turma não encontrada! Cadastre a turma primeiro.");
                        return true;
                    }
                    
                    // Verificar se o aluno já está matriculado nesta turma
                    for (Matricula mat : matriculas) {
                        if (mat.getAluno().equals(alunoM) && mat.getTurma().equals(turmaM) && 
                            mat.getSituacao().equals(Matricula.ATIVO)) {
                            print("Aluno já está matriculado nesta turma!");
                            return true;
                        }
                    }
                    
                    String nomeRespM = input("Nome do responsável:");
                    Responsavel respM = getResponsavel(nomeRespM);
                    if(respM == null){
                        print("Responsável não encontrado! Cadastre o responsável primeiro.");
                        return true;
                    }
                    
                    Matricula matricula = new Matricula();
                    matricula.setNumero(matriculas.size() + 1);
                    matricula.setSituacao(Matricula.ATIVO);
                    matricula.setAluno(alunoM);
                    matricula.setTurma(turmaM);
                    matricula.addResponsavel(respM);
                    matricula.setData(LocalDateTime.now());
                    matriculas.add(matricula);
                    
                    print("Matrícula número " + matricula.getNumero() + " realizada com sucesso!");
                    print("Aluno: " + alunoM.getNome());
                    print("Turma: " + turmaM.getNome());
                    print("Responsável: " + respM.getNome());
                    print("Data: " + matricula.getData());
                    input("\nPressione Enter para continuar...");
                    
                } catch (Exception e) {
                    print("Erro no cadastro: " + e.getMessage());
                    input("\nPressione Enter para continuar...");
                }
                return true;
            case "6":
                exibirAlunos();
                input("\nPressione Enter para continuar...");
                return true;
            case "7":
                exibirFuncionarios();
                input("\nPressione Enter para continuar...");
                return true;
            case "8":
                exibirResponsaveis();
                input("\nPressione Enter para continuar...");
                return true;
            case "9":
                exibirTurmas();
                input("\nPressione Enter para continuar...");
                return true;
            case "10":
                exibirMatriculas();
                input("\nPressione Enter para continuar...");
                return true;
            case "11":
                print("Encerrando o sistema.");
                return false;
            default:
                print("Comando inválido. Digite uma opção de 1 a 11.");
                return true;
            }
        } catch (Exception e) {
            print("Erro ao processar opção: " + e.getMessage());
            return true;
        }
    }



    public String getMenu(String tipo){
        if(tipo.equals("geral")) {
            return "┌─────────────────────────────────────────────────────────────┐\n" +
                   "│                    MENU PRINCIPAL                          │\n" +
                   "├─────────────────────────────────────────────────────────────┤\n" +
                   "│  [1]  Cadastrar aluno                                     │\n" +
                   "│  [2]  Cadastrar responsável                               │\n" +
                   "│  [3]  Cadastrar funcionário                               │\n" +
                   "│  [4]  Cadastrar turma                                     │\n" +
                   "│  [5]  Cadastrar matrícula                                 │\n" +
                   "│  [6]  Exibir alunos                                       │\n" +
                   "│  [7]  Exibir funcionários                                 │\n" +
                   "│  [8]  Exibir responsáveis                                 │\n" +
                   "│  [9]  Exibir turmas                                       │\n" +
                   "│  [10] Exibir matrículas                                   │\n" +
                   "│  [11] Encerrar programa                                   │\n" +
                   "└─────────────────────────────────────────────────────────────┘\n" +
                   "Digite sua opção: ";
        }
        return "┌─────────────────────────────────────────────────────────────┐\n" +
               "│                TIPO DE FUNCIONÁRIO                         │\n" +
               "├─────────────────────────────────────────────────────────────┤\n" +
               "│  [1] Professor                                             │\n" +
               "│  [2] Coordenador                                           │\n" +
               "│  [3] Cuidador                                              │\n" +
               "└─────────────────────────────────────────────────────────────┘\n" +
               "Digite sua opção: ";
    }
    
    /**
     * Exibe todos os alunos cadastrados
     */
    public void exibirAlunos() {
        print("\n=== LISTA DE ALUNOS ===");
        if (alunos.isEmpty()) {
            print("Nenhum aluno cadastrado.");
            return;
        }
        
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            print(String.format("%d. %s (ID: %s, Idade: %d)", 
                i + 1, aluno.getNome(), aluno.getId(), aluno.getIdade()));
            print("   Necessidade especial: " + aluno.getNecessidadeEspecial());
            if (aluno.getResponsavel() != null) {
                print("   Responsável: " + aluno.getResponsavel().getNome());
            }
            if (aluno.getTurma() != null) {
                print("   Turma: " + aluno.getTurma().getNome());
            }
            print("");
        }
    }
    
    /**
     * Exibe todos os funcionários cadastrados
     */
    public void exibirFuncionarios() {
        print("\n=== LISTA DE FUNCIONÁRIOS ===");
        if (funcionarios.isEmpty()) {
            print("Nenhum funcionário cadastrado.");
            return;
        }
        
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            print(String.format("%d. %s (ID: %s, Cargo: %s)", 
                i + 1, func.getNome(), func.getId(), func.getCargo()));
            
            if (func instanceof Professor) {
                Professor prof = (Professor) func;
                if (prof.getTurma() != null) {
                    print("   Turma: " + prof.getTurma().getNome());
                }
            } else if (func instanceof Cuidador) {
                Cuidador cuid = (Cuidador) func;
                print("   Idade atendida: " + cuid.getIdadeMin() + " a " + cuid.getIdadeMax() + " anos");
            }
            print("");
        }
    }
    
    /**
     * Exibe todos os responsáveis cadastrados
     */
    public void exibirResponsaveis() {
        print("\n=== LISTA DE RESPONSÁVEIS ===");
        if (responsaveis.isEmpty()) {
            print("Nenhum responsável cadastrado.");
            return;
        }
        
        for (int i = 0; i < responsaveis.size(); i++) {
            Responsavel resp = responsaveis.get(i);
            print(String.format("%d. %s (ID: %s)", 
                i + 1, resp.getNome(), resp.getId()));
            print("   Parentesco: " + resp.getParentesco());
            print("   Telefone: " + Utils.formatarTelefone(resp.getTelefone()));
            print("");
        }
    }
    
    /**
     * Exibe todas as turmas cadastradas
     */
    public void exibirTurmas() {
        print("\n=== LISTA DE TURMAS ===");
        if (turmas.isEmpty()) {
            print("Nenhuma turma cadastrada.");
            return;
        }
        
        for (int i = 0; i < turmas.size(); i++) {
            Turma turma = turmas.get(i);
            print(String.format("%d. %s (ID: %s)", 
                i + 1, turma.getNome(), turma.getId()));
            print("   Turno: " + turma.getTurno());
            print("   Idade: " + turma.getIdadeMin() + " a " + turma.getIdadeMax() + " anos");
            
            if (turma.getProfessor() != null) {
                print("   Professor: " + turma.getProfessor().getClass());
            }
            
            print("   Alunos: " + (turma.getAlunos() != null ? turma.getAlunos().size() : 0));
            print("");
        }
    }
    
    /**
     * Exibe todas as matrículas cadastradas
     */
    public void exibirMatriculas() {
        print("\n=== LISTA DE MATRÍCULAS ===");
        if (matriculas.isEmpty()) {
            print("Nenhuma matrícula cadastrada.");
            return;
        }
        
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula mat = matriculas.get(i);
            print(String.format("%d. Matrícula #%d", i + 1, mat.getNumero()));
            print("   Aluno: " + mat.getAluno().getNome());
            print("   Turma: " + mat.getTurma().getNome());
            print("   Situação: " + mat.getSituacao());
            print("   Data: " + mat.getData());
            print("");
        }
    }

}