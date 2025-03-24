package br.com.empresa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.text.NumberFormat;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        // Removendo João
        funcionarios.removeIf(func -> func.nome.equals("João"));
        
        // Imprimindo funcionários
        funcionarios.forEach(System.out::println);
        
        // Aumentando salários em 10%
        funcionarios.forEach(func -> func.salario = func.salario.multiply(new BigDecimal("1.1")));
        
        // Imprimindo funcionários com aumento
        System.out.println("\n\nSalario aumentado em 10%");
        funcionarios.forEach(System.out::println);
        
        // Agrupando funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(func -> func.funcao));
        
        // Imprimindo agrupados por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });
        
        // Funcionários aniversariantes em outubro e dezembro
        System.out.println("\nAniversariantes em Outubro e Dezembro:");
        funcionarios.stream()
                .filter(func -> func.dataNascimento.getMonthValue() == 10 || func.dataNascimento.getMonthValue() == 12)
                .forEach(System.out::println);
        
        // Funcionário mais velho
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(func -> func.dataNascimento));
        System.out.println("\nFuncionário mais velho: " + maisVelho.nome + ", Idade: " + (LocalDate.now().getYear() - maisVelho.dataNascimento.getYear()));
        
        // Lista em ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(func -> func.nome)).forEach(System.out::println);
        
        // Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(func -> func.salario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat formatter = NumberFormat.getInstance(Locale.of("pt", "BR"));
        String totalSalariosFormatado = formatter.format(totalSalarios);

        System.out.println("\nTotal dos salários: " + totalSalariosFormatado);
        
        // Quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1518.00");
        System.out.println("\nSalários mínimos por funcionário:");
        funcionarios.forEach(func -> System.out.printf("%s ganha %.2f salários mínimos%n", func.nome, func.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP)));
    }
}
