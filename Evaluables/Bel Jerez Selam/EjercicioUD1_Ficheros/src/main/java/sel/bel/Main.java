package sel.bel;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static File scoreboard = new File("src/main/resources/scoreboard.txt");
    static File scoreboardArreglado = new File("src/main/resources/scoreboardArreglado.txt");

    public static void main(String[] args){
        if (!scoreboard.exists()) {
            System.err.println("Archivo de ranking erroneo.");
            return;
        }

        if (scoreboardArreglado.exists()) {
            if (!scoreboardArreglado.delete()) {
                System.err.println("ERROR BORRANDO ARCHIVO.");
            }
        }

        TreeMap<String, Integer> puntuacionesArregladas = arreglarPuntuaciones();
        escribirPuntuaciones(puntuacionesArregladas);
        leerPuntuaciones();

    }

    private static void escribirPuntuaciones(TreeMap<String, Integer> puntuacionesArregladas){
        ArrayList<String> orden = mapaOrdenado(puntuacionesArregladas);
        try (PrintWriter escritor = new PrintWriter(scoreboardArreglado)) {
            for (String usuarioActual : orden) {
                String texto = String.format("%-15s -> %3d", usuarioActual, puntuacionesArregladas.get(usuarioActual));
                escritor.println(texto);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<String> mapaOrdenado(TreeMap<String, Integer> puntuacionesArregladas) {
        ArrayList<String> orden = new ArrayList<>();
        for (int i = 0; i < puntuacionesArregladas.size(); i++) {
            String maxPuntuacionUsuario = "";
            int maxPuntuacionPuntos = 0;
            for (Map.Entry<String, Integer> entrada2 : puntuacionesArregladas.entrySet()) {
                String usuarioComprobando = entrada2.getKey();
                Integer puntuacionComprobando = entrada2.getValue();

                if (puntuacionComprobando >= maxPuntuacionPuntos && !orden.contains(usuarioComprobando)) {
                    maxPuntuacionUsuario = usuarioComprobando;
                    maxPuntuacionPuntos = puntuacionComprobando;
                }


            }
            orden.add(maxPuntuacionUsuario);

        }
        return orden;
    }

    private static TreeMap<String, Integer> arreglarPuntuaciones() {
        ArrayList<String> usuarios = new ArrayList<>();
        ArrayList<Integer> puntuaciones = new ArrayList<>();
        TreeMap<String, Integer> mapa = new TreeMap<>();

        try (Scanner sc = new Scanner(scoreboard)) {
            while (sc.hasNext()) {
                usuarios.add(sc.next());
                puntuaciones.add(Integer.valueOf(sc.next()));
            }

            for (int i = 0; i < usuarios.size(); i++) {
                String userActual = usuarios.get(i);
                int puntuacionActual = 0;

                if (userActual.contains("AI")) {
                    continue;
                }
                for (int j = 0; j < usuarios.size(); j++) {
                    String comprobandoActual = usuarios.get(j);
                    if (userActual.equals(comprobandoActual)) {
                        puntuacionActual += puntuaciones.get(j);
                    }
                }
                mapa.put(userActual, puntuacionActual);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return mapa;
    }

    private static void leerPuntuaciones() {
        try (Scanner sc = new Scanner(scoreboardArreglado)) {
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


