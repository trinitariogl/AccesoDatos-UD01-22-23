package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static File ranking = new File("src/main/resources/ranking.txt");
    static File rankingFinal = new File("src/main/resources/rankingFinal.txt");

    public static void main(String[] args){
        if (!ranking.exists()) {
            System.err.println("Archivo de ranking erroneo.");
            return;
        }

        if (rankingFinal.exists()) {
            if (!rankingFinal.delete()) {
                System.err.println("Error de borrado de archivo.");
            }
        }

        TreeMap<String, Integer> correctasFinal = arreglarCorrectas();
        escribirCorrectas(correctasFinal);
        leerCorrectas();
    }

    private static void escribirCorrectas(TreeMap<String, Integer> correctasFinal){
        ArrayList<String> orden = rankingOrdenado(correctasFinal);
        try (PrintWriter escritor = new PrintWriter(rankingFinal)) {
            for (String jugador : orden) {
                String texto = String.format("%-6s : %3d", jugador, correctasFinal.get(jugador));
                escritor.println(texto);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerCorrectas() {
        try (Scanner sc = new Scanner(rankingFinal)) {
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<String> rankingOrdenado(TreeMap<String, Integer> correctasFinal) {
        ArrayList<String> ordenado = new ArrayList<>();
        for (int i = 0; i < correctasFinal.size(); i++) {
            String maximaCorrectasJugador = "";
            int maximaCorrectas = 0;
            for (Map.Entry<String, Integer> entrada2 : correctasFinal.entrySet()) {
                String comprobacionJugador = entrada2.getKey();
                Integer comprobacionCorrectas = entrada2.getValue();

                if (comprobacionCorrectas >= maximaCorrectas && !ordenado.contains(comprobacionJugador)) {
                    maximaCorrectasJugador = comprobacionJugador;
                    maximaCorrectas = comprobacionCorrectas;
                }
            }
            ordenado.add(maximaCorrectasJugador);
        }
        return ordenado;
    }

    private static TreeMap<String, Integer> arreglarCorrectas() {
        ArrayList<String> jugadores = new ArrayList<>();
        ArrayList<Integer> correctas = new ArrayList<>();
        TreeMap<String, Integer> ranking = new TreeMap<>();

        try (Scanner sc = new Scanner(Main.ranking)) {
            while (sc.hasNext()) {
                jugadores.add(sc.next());
                correctas.add(Integer.valueOf(sc.next()));
            }

            for (int i = 0; i < jugadores.size(); i++) {
                String jugadorActual = jugadores.get(i);
                int correctasActuales = 0;

                if (jugadorActual.contains("AI")) {
                    continue;
                }
                for (int j = 0; j < jugadores.size(); j++) {
                    String comprobandoActual = jugadores.get(j);
                    if (jugadorActual.equals(comprobandoActual)) {
                        correctasActuales += correctas.get(j);
                    }
                }
                ranking.put(jugadorActual, correctasActuales);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ranking;
    }
}